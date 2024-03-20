package org.modules.repository;

import org.modules.entity.OAuthRegisteredClient;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * The type OAuth2AuthorizedClientRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface OAuth2AuthorizedClientRepository extends R2dbcRepository<OAuthRegisteredClient, Long>, ReactiveClientRegistrationRepository {

    @Override
    default Mono<ClientRegistration> findByRegistrationId(String registrationId) {
        return findById(Long.parseLong(registrationId)).map(client -> ClientRegistration
                .withRegistrationId(registrationId)
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getClientAuthenticationMethod()))
                .authorizationGrantType(new AuthorizationGrantType(client.getAuthorizationGrantType()))
                .redirectUri(client.getRedirectUri())
                .scope(client.getScope().split(","))
                .authorizationUri(client.getAuthorizationUri())
                .tokenUri(client.getTokenUri())
                .userInfoUri(client.getUserInfoUri())
                .userNameAttributeName(client.getUserNameAttributeName())
                .jwkSetUri(client.getJwkSetUri())
                .clientName(client.getClientName())
                .build()
        );
    }
}
