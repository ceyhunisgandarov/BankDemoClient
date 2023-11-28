package az.orient.client.bankdemoclient.service;

import az.orient.client.bankdemoclient.request.ReqCustomer;
import az.orient.client.bankdemoclient.response.RespCustomer;
import az.orient.client.bankdemoclient.response.Response;
import az.orient.client.bankdemoclient.response.UserResponse;

import java.util.List;

public interface CustomerService {

    Response<List<RespCustomer>> customerList(UserResponse userResponse) throws Exception;


    Response addCustomer(ReqCustomer reqCustomer) throws Exception;
}
