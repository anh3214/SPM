/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middlewares;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controllers.auth.LoginController;

/**
 *
 * @author RxZ
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/dashboard/*", "/services/*", "*.jsp", "/setting", "/iteration", "/users", 
    "/subjectsetting", "/milestone","/class","/feature","/class_user","/team","/criteri", "/function"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        boolean isAccess = false;
        try {
            isAccess = LoginController.getLoginCookie(request) != null;
        } catch (Exception e) {
        }
        if (!isAccess) {
            response.sendRedirect(request.getContextPath() + "/login"); // No logged-in User found, so redirect to login page.
        } else {
            chain.doFilter(req, res); // Logged-in User found, so just continue request.
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

}
