package com.example.turboaz.services;

import com.example.turboaz.dtos.LoginPostDto;
import com.example.turboaz.dtos.LoginResponseDto;
import com.example.turboaz.dtos.RegisterPostDto;
import com.example.turboaz.dtos.RegisterResponseDto;
import com.example.turboaz.models.User;
import com.example.turboaz.repositories.UserRepository;
import javassist.tools.web.BadHttpRequest;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.keycloak.authorization.client.Configuration;


import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.credentials.admin-username}")
    private String adminUsername;

    @Value("${keycloak.credentials.admin-password}")
    private String adminPassword;

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public RegisterResponseDto register(RegisterPostDto userDTO) {
        Boolean isRegisteredToKeyclaok = registerToKeyclaok(userDTO);
        if (isRegisteredToKeyclaok){
            User user = new com.example.turboaz.models.User();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setFullName(userDTO.getName()+" "+userDTO.getSurname());
            user.setPhone(userDTO.getPhone());
            user.setBalance(0);
            return new RegisterResponseDto(userRepository.save(user));
        }
        return null;
    }

    @Override
    public LoginResponseDto login(LoginPostDto user) {
        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "password");
        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);
        AccessTokenResponse response =
                authzClient.obtainAccessToken(user.getUsername(), user.getPassword());
        return new LoginResponseDto(response.getToken());
    }

    public boolean registerToKeyclaok(RegisterPostDto userDTO){
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD).realm("master").clientId("admin-cli")
                .username(adminUsername).password(adminPassword)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getName());
        user.setLastName(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();
        Response response = usersResource.create(user);
        keycloak.tokenManager().getAccessToken();
        if (response.getStatus() == 201) {
            String userId = CreatedResponseUtil.getCreatedId(response);
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(userDTO.getPassword());
            UserResource userResource = usersResource.get(userId);
            userResource.resetPassword(passwordCred);
        }
        return response.getStatus() == 201;
    }

}
