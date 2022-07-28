/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DAOUser;
import controllers.auth.LoginController;
import utils.Validator;
/**
 *
 * @author RxZ
 */
@WebServlet(name = "UserProfileController", urlPatterns = {"/profile"})
public class UserProfileController extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User currentUser = LoginController.getLoginCookie(request);
        request.setAttribute("current_user", currentUser);
        request.getRequestDispatcher("/views/user/UserProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User currentUser = LoginController.getLoginCookie(request);
            request.setAttribute("current_user", currentUser);
            String error_list = "";
            // parse all params and validate
            String full_name = request.getParameter("full_name");
            String fb_link = request.getParameter("fb_link");
            String email_addr = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String date_of_birth = request.getParameter("birthday");
            String gender = "1".equals(request.getParameter("gender")) ? "male" : "female";
            String user_token = request.getParameter("token");
            
            if (!Validator.checkName(full_name)) error_list += "|full_name";
            if (!Validator.checkFbLink(fb_link)) error_list += "|fb_link";
            if (!Validator.checkEmail(email_addr)) error_list += "|email";
            if (!Validator.checkPhone(mobile)) error_list += "|mobile";
            if (!Validator.checkDate(date_of_birth)) error_list += "|birthday";
            
            if (!"".equals(error_list)) throw new Exception(error_list.substring(1));
            
            Date dob = Date.valueOf(date_of_birth);

            // User(int user_id, String roll_number, String full_name, String gender, String date_of_birth, String email, String mobile, String avatar_link, String facebook_link, int role_id, int status, String password)
            User user = new User(LoginController.getLoginCookie(request).getUser_id(), null, full_name, gender, date_of_birth, email_addr, mobile, null, fb_link, 0, 0, null);
            System.out.println(user);
            if (DAOUser.updateProfile(user, user_token)) {
                LoginController.buildSession(request, LoginController.getLoginCookie(request));
                response.getWriter().print("success");
                
            } else {
                throw new Exception("database");

            }
        } catch (Exception e) {
            response.getWriter().print("error: " + e.getMessage());
        }
    }
}
