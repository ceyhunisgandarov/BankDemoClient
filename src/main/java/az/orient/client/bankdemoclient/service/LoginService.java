package az.orient.client.bankdemoclient.service;

import az.orient.client.bankdemoclient.request.UserRequest;
import az.orient.client.bankdemoclient.response.Response;
import az.orient.client.bankdemoclient.response.TokenResponse;
import az.orient.client.bankdemoclient.response.UserResponse;

public interface LoginService {


    Response<UserResponse> login(UserRequest userRequest) throws Exception;

    Response logout(TokenResponse tokenResponse) throws Exception;
}
