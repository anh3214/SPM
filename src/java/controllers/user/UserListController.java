/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import controllers.auth.LoginController;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import entity.User;
import dao.DAOUser;
import java.sql.Date;
import utils.Validator;

/**
 *
 * @author RxZ
 */
@WebServlet(name = "UserListController", urlPatterns = {"/users"})
public class UserListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User currentUser = LoginController.getLoginCookie(request);
            if (currentUser.getRole_id() != 1) {
                response.sendError(403);
                return;
            }
            
            int pageIndex, pageSize, totalItems;
            String sPageIndex = request.getParameter("page");
            String sPageSize = request.getParameter("size");

            String sRoleId = request.getParameter("role");
            int roleId = (sRoleId == null || "".equals(sRoleId)) ? -1 : Integer.parseInt(sRoleId);

            String sStatus = request.getParameter("status");
            int status = (sStatus == null || "".equals(sStatus)) ? -1 : Integer.parseInt(sStatus);

            String sName = request.getParameter("name");
            sName = (sName == null || "".equals(sName)) ? null : sName;

            String sEmail = request.getParameter("email");
            sEmail = (sEmail == null || "".equals(sEmail)) ? null : sEmail;

            pageIndex = (sPageIndex == null || "".equals(sPageIndex)) ? 1 : Integer.parseInt(sPageIndex);
            pageSize = (sPageSize == null || "".equals(sPageSize)) ? 15 : Integer.parseInt(sPageSize);
            if (pageIndex<=0) pageIndex = 1;
            if (pageSize<=0) pageSize = 15;

            totalItems = DAOUser.queryUsersCount(roleId, status, sName, sEmail);

            int startIdx = 1;
            if (pageIndex == 1) {
            } else {
                startIdx = pageIndex - 1;
                startIdx = startIdx * pageSize + 1;
            }

            ArrayList<User> dummies = DAOUser.queryUsers(startIdx, pageSize, roleId, status, sName, sEmail);
            for (User d : dummies) {
                System.out.println(d);
            }

            request.setAttribute("dummies", dummies);
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("totalSize", (int) Math.ceil((double) totalItems / pageSize));
            request.getRequestDispatcher("/views/user/UserList.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
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

            // parse all params and validate
            String action = request.getParameter("action");
            if ("update_status".equals(action)) {
                int uid = Integer.parseInt(request.getParameter("uid"));
                int status = Integer.parseInt(request.getParameter("status"));
                if (status != 1 && status != 0) throw new Exception("invalid_status");
                if (DAOUser.updateStatus(uid, status)) {
                    LoginController.buildSession(request, LoginController.getLoginCookie(request));
                    response.getWriter().print("success");
                    return;
                } else {
                    throw new Exception("database");

                }
            }
            throw new Exception("no_action");

        } catch (Exception e) {
            response.getWriter().print("error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
