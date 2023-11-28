package az.orient.client.bankdemoclient.filter;

import az.orient.client.bankdemoclient.request.TokenRequest;
import az.orient.client.bankdemoclient.response.UserResponse;
import az.orient.client.bankdemoclient.service.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Component
@Order
public class FilterData implements Filter {

    @Autowired
    private TokenService tokenService;

    public FilterData() {
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if (session == null) {
            new RedirectView("/client/loginView");
        } else {
            UserResponse userResponse = (UserResponse) session.getAttribute("user");
            System.out.println(userResponse);
            TokenRequest tokenRequest = new TokenRequest();
            if (userResponse == null) {
                System.out.println("userResponse is null " + userResponse);;
            } else {
                tokenRequest.setUserId(userResponse.getTokenResponse().getUserId());
                tokenRequest.setToken(userResponse.getTokenResponse().getToken());
                System.out.println(tokenService.updateTokenUpdateDate(tokenRequest));
                tokenService.updateTokenUpdateDate(tokenRequest);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
