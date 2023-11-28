package az.orient.client.bankdemoclient.service.impl;

import az.orient.client.bankdemoclient.request.TokenRequest;
import az.orient.client.bankdemoclient.request.UserRequest;
import az.orient.client.bankdemoclient.response.Response;
import az.orient.client.bankdemoclient.response.UserResponse;
import az.orient.client.bankdemoclient.service.TokenService;
import az.orient.client.bankdemoclient.utility.Utility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@PropertySource("classpath:config.properties")
public class TokenServiceImpl implements TokenService {

    private final Utility utility;

    private final ObjectMapper objectMapper;

    @Value("${api.url}")
    private String apiUrl;

    @Override
    public Response updateTokenUpdateDate(TokenRequest tokenRequest) throws Exception {
        String reqTokenJson = objectMapper.writeValueAsString(tokenRequest);
        String result = utility.sendPost(apiUrl + "token/update", reqTokenJson);
        Response<UserResponse> userResponse = objectMapper.readValue(result, new TypeReference<>() {});
        return userResponse;
    }

}

