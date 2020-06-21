package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;

    public LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        User user = userService.getUserByEmail(httpServletRequest.getParameter("j_email"));
        List<Role> roles = user.getRoles();
        String redirect = "/user";
        for (Role role : roles) {
            if (role.getName().equals("ROLE_ADMIN")) {
                redirect = "/admin";
            }
        }
        httpServletRequest.getSession().setAttribute("user", user);
        httpServletResponse.sendRedirect(redirect);
    }
}