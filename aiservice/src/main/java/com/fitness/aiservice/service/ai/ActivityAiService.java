package com.fitness.aiservice.service.ai;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.aiservice.dto.recommendation.ai.RecommendationResult;
import com.fitness.aiservice.entity.Recommendation;
import com.fitness.aiservice.integration.gemini.GeminiService;
import com.fitness.aiservice.messaging.event.ActivityCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivityAiService {
    private final GeminiService geminiService;
    private final ObjectMapper objectMapper;

    public Recommendation generateRecommendation(ActivityCreatedEvent event) {
        String prompt = createPromptForActivity(event);
        String rawResponse = geminiService.getRecommendation(prompt);
        RecommendationResult result = extractRecommendationResult(rawResponse);

        return mapToRecommendationEntity(result, event);
    }

    private RecommendationResult extractRecommendationResult(String response) {
        try {
            JsonNode rootNode = objectMapper.readTree(response);

            String innerJson = rootNode
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText()
                    .trim();

            innerJson = cleanMarkdown(innerJson);

            return objectMapper.readValue(innerJson, RecommendationResult.class);

        } catch (Exception e) {
            return buildFallbackRecommendationResult();
        }
    }

    private RecommendationResult buildFallbackRecommendationResult() {

        return new RecommendationResult(
                new RecommendationResult.Analysis(
                        "We are currently analyzing your workout. Please check back shortly for detailed recommendations.",
                        "Pace data unavailable.",
                        "Heart rate data unavailable.",
                        "Calorie analysis unavailable."
                ),
                List.of(
                        new RecommendationResult.Improvement(
                                "General",
                                "Maintain consistency in your training."
                        )
                ),
                List.of(
                        new RecommendationResult.Suggestion(
                                "Continue Current Routine",
                                "Continue your current routine while analysis completes."
                        )
                ),
                List.of(
                        "Ensure proper warm-up and cool-down."
                )
        );
    }


    private String cleanMarkdown(String text) {
        if (text.startsWith("```")) {
            return text
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();
        }
        return text;
    }

    private Recommendation mapToRecommendationEntity(
            RecommendationResult result,
            ActivityCreatedEvent event
    ) {
        String recommendation = String.format("""
                Overall: %s
                
                Pace: %s  
                
                Heart Rate: %s
                
                Calories: %s
                """, result.analysis().overall(), result.analysis().pace(), result.analysis().heartRate(), result.analysis().caloriesBurned()).trim();

        List<String> improvements = result.improvements()
                .stream()
                .map(i -> String.format("%s: %s", i.area(), i.recommendation()))
                .toList();

        List<String> suggestions = result.suggestions()
                .stream()
                .map(s -> String.format("%s: %s", s.workout(), s.description()))
                .toList();

        return new Recommendation(
                event.activityId(),
                event.userId(),
                recommendation,
                improvements,
                suggestions,
                result.safety()
        );
    }

    private String createPromptForActivity(ActivityCreatedEvent event) {
        return String.format("""
                        Analyze this fitness activity and provide detailed recommendations in the following EXACT JSON format:
                        {
                          "analysis": {
                            "overall": "Overall analysis here",
                            "pace": "Pace analysis here",
                            "heartRate": "Heart rate analysis here",
                            "caloriesBurned": "Calories analysis here"
                          },
                          "improvements": [
                            {
                              "area": "Area name",
                              "recommendation": "Detailed recommendation"
                            }
                          ],
                          "suggestions": [
                            {
                              "workout": "Workout name",
                              "description": "Detailed workout description"
                            }
                          ],
                          "safety": [
                            "Safety point 1",
                            "Safety point 2"
                          ]
                        }
                        
                        Analyze this activity:
                        Activity Type: %s
                        Duration: %d minutes
                        Calories Burned: %d
                        Additional Metrics: %s
                        
                        Provide detailed analysis focusing on performance, improvements, next workout suggestions, and safety guidelines.
                        Ensure the response follows the EXACT JSON format shown above.
                        """,
                event.type(),
                event.duration(),
                event.caloriesBurned(),
                event.additionalMetrics()
        );
    }
}
