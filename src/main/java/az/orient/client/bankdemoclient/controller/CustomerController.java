package az.orient.client.bankdemoclient.controller;

import az.orient.client.bankdemoclient.request.ReqCustomer;
import az.orient.client.bankdemoclient.request.TokenRequest;
import az.orient.client.bankdemoclient.response.RespCustomer;
import az.orient.client.bankdemoclient.response.Response;
import az.orient.client.bankdemoclient.response.UserResponse;
import az.orient.client.bankdemoclient.service.CustomerService;
import az.orient.client.bankdemoclient.utility.Utility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/app")
public class CustomerController {

    private final CustomerService customerService;
    private final Utility utility;

    @GetMapping("/getCustomerList")
    public ModelAndView getCustomerList(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("index");
        try {
            HttpSession session = request.getSession(false);
            UserResponse userResponse = (UserResponse) session.getAttribute("user");
            Response<List<RespCustomer>> response = customerService.customerList(userResponse);
            System.out.println(response.getStatus());
            if (response.getStatus().getCode() == 1) {
                model.addObject("customers", response.getT());
            } else if (response.getStatus().getCode() == 106) {
                model.setViewName("redirect:/");
                model.addObject("msg", "your session is ended");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return model;
    }

    @GetMapping("/newCustomer")
    public ModelAndView newCustomer (@RequestParam(value = "msg", required = false) String msg) {
        ModelAndView model = new ModelAndView("newCustomer");

        model.addObject("msg", msg);
        return model;
    }

    @PostMapping("/addCustomer")
    public RedirectView addCustomer(ReqCustomer reqCustomer, HttpServletRequest request) {
        RedirectView redirectView = null;
        try {
            HttpSession session = request.getSession(false);
            UserResponse userResponse = (UserResponse) session.getAttribute("user");
            TokenRequest tokenRequest = new TokenRequest();
            tokenRequest.setUserId(userResponse.getTokenResponse().getUserId());
            tokenRequest.setToken(userResponse.getTokenResponse().getToken());
            reqCustomer.setTokenRequest(tokenRequest);
            Response response = customerService.addCustomer(reqCustomer);
            if (response.getStatus().getCode() ==1) {
                redirectView = new RedirectView("/client/app/getCustomerList");
            } else if (response.getStatus().getCode() == 106) {
                redirectView = new RedirectView("/client/");
                redirectView.addStaticAttribute("msg", "Your session is ended");
            } else {
                redirectView = new RedirectView("/client/app/newCustomer");
                redirectView.addStaticAttribute("msg", "Username or password is incorrect");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return redirectView;
    }

}
