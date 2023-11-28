package az.orient.client.bankdemoclient.service.impl;

import az.orient.client.bankdemoclient.request.ReqCustomer;
import az.orient.client.bankdemoclient.response.RespCustomer;
import az.orient.client.bankdemoclient.response.Response;
import az.orient.client.bankdemoclient.response.UserResponse;
import az.orient.client.bankdemoclient.service.CustomerService;
import az.orient.client.bankdemoclient.utility.Utility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:config.properties")
public class CustomerServiceImpl implements CustomerService {

    private final Utility utility;

    @Value("${api.url}")
    private String apiUrl;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Response<List<RespCustomer>> customerList(UserResponse userResponse) throws Exception{
        String tokenJson = objectMapper.writeValueAsString(userResponse.getTokenResponse());
        String result = utility.sendPost(apiUrl + "customer/list", tokenJson);
        Response<List<RespCustomer>> response = objectMapper.readValue(result, new TypeReference<>() {});
        return response;
    }

    @Override
    public Response addCustomer(ReqCustomer reqCustomer) throws Exception {
        String reqCustomerJson = objectMapper.writeValueAsString(reqCustomer);
        String result = utility.sendPost(apiUrl + "customer/save", reqCustomerJson);
        Response response = objectMapper.readValue(result, new TypeReference<Response>() {});
        return response;
    }
}
