package az.orient.client.bankdemoclient.service;

import az.orient.client.bankdemoclient.request.TokenRequest;
import az.orient.client.bankdemoclient.response.Response;

public interface TokenService {

    Response updateTokenUpdateDate(TokenRequest tokenRequest) throws Exception;

}
