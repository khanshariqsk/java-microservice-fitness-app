package com.fitness.gateway.filter;

import com.fitness.gateway.dto.user.RegisterUserRequest;
import com.fitness.gateway.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class KeycloakUserSyncFilter implements WebFilter {

    private final UserService userService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        return exchange.getPrincipal()
                .cast(JwtAuthenticationToken.class)
                .flatMap(authentication -> {

                    var jwt = authentication.getToken();

                    RegisterUserRequest request = new RegisterUserRequest(
                            jwt.getClaim("email"),
                            jwt.getClaim("given_name"),
                            jwt.getClaim("family_name"),
                            jwt.getSubject() // keycloakId
                    );

                    return userService.syncUser(request)
                            .then(chain.filter(exchange));
                })
                .switchIfEmpty(chain.filter(exchange));
    }
}
