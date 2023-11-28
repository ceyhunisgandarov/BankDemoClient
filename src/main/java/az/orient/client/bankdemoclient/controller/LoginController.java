package az.orient.client.bankdemoclient.controller;

import az.orient.client.bankdemoclient.request.TokenRequest;
import az.orient.client.bankdemoclient.request.UserRequest;
import az.orient.client.bankdemoclient.response.RespCustomer;
import az.orient.client.bankdemoclient.response.Response;
import az.orient.client.bankdemoclient.response.UserResponse;
import az.orient.client.bankdemoclient.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = {"/", "/loginView"})
    public ModelAndView loginView(@RequestParam(value = "msg", required = false) String msg) {
        ModelAndView model = new ModelAndView("login");

        model.addObject("msg", msg);
        return model;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView login(UserRequest userRequest, HttpServletRequest request) {
        RedirectView redirectView = null;
        try {
            Response<UserResponse> response = loginService.login(userRequest);
            HttpSession session = request.getSession(true);
            if (response.getStatus().getCode() == 1) {
                session.setAttribute("user", response.getT());
                redirectView = new RedirectView("/client/app/getCustomerList");
            } else {
                redirectView = new RedirectView("/client/");
                redirectView.addStaticAttribute("msg", "Username or password is incorrect");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return redirectView;
    }

    @PostMapping(value = "/logout", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView logout(HttpServletRequest request) {
        RedirectView redirectView = null;
        try {
            HttpSession session = request.getSession(false);
            UserResponse user = (UserResponse) session.getAttribute("user");
            Response response = loginService.logout(user.getTokenResponse());
            if (response.getStatus().getCode() == 1) {
                redirectView = new RedirectView("/client/");
            } else if (response.getStatus().getCode()==106) {
                redirectView = new RedirectView("/client/");
                redirectView.addStaticAttribute("msg", "your session is ended");
            } else {
                redirectView = new RedirectView("/client/app/getCustomerList");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return redirectView;

    }

}
