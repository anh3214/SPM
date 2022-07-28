/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import controllers.auth.LoginController;
import dao.DAOUser;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Validator;

/**
 *
 * @author RxZ
 */
@WebServlet(name = "UserDetailController", urlPatterns = {"/edit_user"})
public class UserDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User currentUser = LoginController.getLoginCookie(request);
            if (currentUser.getRole_id() != 1) {
                response.sendError(403);
                return;
            }
            
            String sUid = request.getParameter("uid");
            int uid = Integer.parseInt(sUid);
            
            if (currentUser.getUser_id() == uid) {
                response.sendRedirect("profile");
            } else {
                User pUser = DAOUser.getUserById(uid);
                if (pUser == null) {
                    response.sendRedirect("users");
                }
                request.setAttribute("current_user", pUser);
                request.getRequestDispatcher("/views/user/UserDetail.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.getWriter().print("error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User currentUser = LoginController.getLoginCookie(request);
            if (currentUser.getRole_id() != 1) {
                response.sendError(403);
                return;
            }
            request.setAttribute("current_user", currentUser);
            String error_list = "";
            // parse all params and validate
            String sUid = request.getParameter("uid");
            String full_name = request.getParameter("full_name");
            String fb_link = request.getParameter("fb_link");
            String email_addr = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String date_of_birth = request.getParameter("birthday");
            String gender = "1".equals(request.getParameter("gender")) ? "male" : "female";
            String role_id = request.getParameter("role_id");
            String status = request.getParameter("status");

            if (!Validator.checkName(full_name)) {
                error_list += "|full_name";
            }
            if (!Validator.checkFbLink(fb_link)) {
                error_list += "|fb_link";
            }
            if (!Validator.checkEmail(email_addr)) {
                error_list += "|email";
            }
            if (!Validator.checkPhone(mobile)) {
                error_list += "|mobile";
            }
            if (!Validator.checkDate(date_of_birth)) {
                error_list += "|birthday";
            }
            if (!Validator.checkRole(role_id)) {
                error_list += "|role_id";
            }
            if (!Validator.checkStatus(status)) {
                error_list += "|status";
            }

            int uid = Integer.parseInt(sUid);
            if (currentUser.getUser_id() == uid) {
                throw new Exception("duplicate_function");
            }

            if (!"".equals(error_list)) {
                throw new Exception(error_list.substring(1));
            }
            // User(int user_id, String roll_number, String full_name, String gender, String date_of_birth, String email, String mobile, String avatar_link, String facebook_link, int role_id, int status, String password)
            User user = new User(uid, null, full_name, gender, date_of_birth, email_addr, mobile, null, fb_link, Integer.parseInt(role_id), Integer.parseInt(status), null);
            System.out.println(user);
            if (DAOUser.updateUserById(user)) {
                response.getWriter().print("success");

            } else {
                throw new Exception("database");

            }
        } catch (Exception e) {
            response.getWriter().print("error: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
