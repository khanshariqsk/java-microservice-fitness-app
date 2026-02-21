package com.fitness.aiservice.entity;

import com.fitness.aiservice.constants.message.recommendation.RecommendationEntityNames;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@CompoundIndexes({
        @CompoundIndex(
                name = "activity_user_idx",
                def = "{'activityId': 1, 'userId': 1}"
        )
})
@Document(collection = RecommendationEntityNames.RECOMMENDATIONS_LC)
public class Recommendation {

    public Recommendation(String activityId, String userId, String recommendation, List<String> improvements, List<String> suggestions, List<String> safety) {
        this.activityId = activityId;
        this.userId = userId;
        this.recommendation = recommendation;
        this.improvements = improvements;
        this.suggestions = suggestions;
        this.safety = safety;
    }

    @Id
    private String id;
    private String activityId;

    @Indexed
    private String userId;

    private String recommendation;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}


