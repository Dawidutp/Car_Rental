package com.example.application.data.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class LoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        String targeturl = determineTargetUrl(authentication);

        if(response.isCommitted()){
            return;
    }
    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request,response,targeturl);

    }

    protected String determineTargetUrl(Authentication authentication){
        String url = "/login2?error=true";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a: authorities){
            roles.add(a.getAuthority());
        }
        //check user role and decide the redirect url
        if(roles.contains("ROLE_ADMIN")){
            url = "/ListView";
        } else if(roles.contains("ROLE_USER")){
            url = "/MainClientView";
        }
        return url;
    }
}
