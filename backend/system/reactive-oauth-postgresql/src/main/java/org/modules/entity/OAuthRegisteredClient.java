package org.modules.entity;

import lombok.Getter;
import lombok.Setter;
import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type OAuth2RegisteredClient.
 *
 * @author Jx-zou
 */
@Setter
@Getter
@Table(name = "t_oauth_registered_client", schema = "auth")
public class OAuthRegisteredClient extends SnowflakeIdEntity {

    @Column
    private String clientId;
    @Column
    private String clientSecret;
    @Column
    private String clientAuthenticationMethod;
    @Column
    private String authorizationGrantType;
    @Column
    private String redirectUri;
    @Column
    private String scope;
    @Column
    private String authorizationUri;
    @Column
    private String tokenUri;
    @Column
    private String userInfoUri;
    @Column
    private String userNameAttributeName;
    @Column
    private String jwkSetUri;
    @Column
    private String clientName;
}
