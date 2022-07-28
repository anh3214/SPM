/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import dao.DAOClassUser;
import dao.DAOIssue;
import dao.DAOTeam;
import entity.Issue;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Validator;

/**
 *
 * @author buitr
 */
@WebServlet(name = "IssueController", urlPatterns = {"/issue"})
public class IssueController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("go");

        if (service == null) {
            service = "viewAll";
        }

        DAOIssue dao = new DAOIssue();
        try (PrintWriter out = response.getWriter()) {
            int role = LoginController.getLoginCookie(request).getRole_id();
            int u_id = LoginController.getLoginCookie(request).getUser_id();

            if (service.equals("viewAll")) {
                int team_id = Integer.parseInt(request.getParameter("team_id"));
                String sPageIndex = request.getParameter("page");
                String sPageSize = request.getParameter("size");
                int pageIndex = (sPageIndex == null || "".equals(sPageIndex)) ? 1 : Integer.parseInt(sPageIndex);
                int pageSize = (sPageSize == null || "".equals(sPageSize)) ? 5 : Integer.parseInt(sPageSize);
                if (pageIndex <= 0) {
                    pageIndex = 1;
                }
                if (pageSize <= 0) {
                    pageSize = 5;
                }

                String sStatus = request.getParameter("status");
                int status = (sStatus == null || "".equals(sStatus)) ? -1 : Integer.parseInt(sStatus);

                String assignee = request.getParameter("assignee");
                assignee = (assignee == null || "-1".equals(assignee)) ? null : assignee;
                
                String created_date = request.getParameter("created_date");
                created_date = (created_date == null || "-1".equals(created_date)) ? null : created_date;

                String description = request.getParameter("description_search");
                description = (description == null || "-1".equals(description)) ? null : description;

                String function = request.getParameter("function");
                function = (function == null || "-1".equals(function)) ? null : function;

                String milestone = request.getParameter("milestone");
                milestone = (milestone == null || "-1".equals(milestone)) ? null : milestone;

                //get sorting filter request
                String str = "u1.full_name|i.created_at|m.milestone_name";
                String[] filter_keys = str.split("\\|");
                String[] filter_types = new String[filter_keys.length];
                String filter_col = null;
                String filter_type = null;
                for (int i = 0; i < filter_keys.length; i++) {
                    String sKey = request.getParameter(filter_keys[i].substring(filter_keys[i].indexOf(".") + 1));
                    String sType = (sKey == null || (!"asc".equals(sKey) && !"desc".equals(sKey))) ? null : sKey;
                    if (sType != null) {
                        filter_col = filter_keys[i];
                        filter_type = sType;
                        filter_types[i] = (filter_type.equals("asc")) ? "desc" : "asc";
                    } else {
                        filter_types[i] = "asc";
                    }
                }
                int countIssue = dao.countIssue(u_id, role, team_id, status, assignee, description, function, milestone);

                Vector<Issue> issueList = dao.getIssue(u_id, role, team_id, pageIndex, pageSize, status, assignee, description, function, milestone, filter_col, filter_type);
                Vector<String> listAssignee = dao.getAssignee(u_id, role, team_id);
                Vector<String> listFunction = dao.getFunction(u_id, role, team_id);
                Vector<String> listMilestone = dao.getMilestone(u_id, role, team_id);
                

                request.setAttribute("listAssignee", listAssignee);
                request.setAttribute("listFunction", listFunction);
                request.setAttribute("listMilestone", listMilestone);

                request.setAttribute("issueList", issueList);
                request.setAttribute("filter_types", filter_types);

                request.setAttribute("team", team_id);
                request.setAttribute("search_value", description);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalSize", (int) Math.ceil((double) countIssue / pageSize));
                disPath(request, response, "/views/issue/IssueList.jsp");
            }
            if (service.equals("updateStatus")) {
                int issue_id = Integer.parseInt(request.getParameter("issue_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateStatus(issue_id, status);
                if (n == 0) {
                    out.print("fail");
                } else {
                    out.print("success");
                }
            }
            if(service.equals("sync_issue")){
                int team_id = Integer.parseInt(request.getParameter("team_id"));
                DAOTeam daoT = new DAOTeam();
                int group_id = daoT.getProject_id(team_id);
                String token = daoT.getTeam_tokent(team_id);
                int class_id = DAOClassUser.getClassId(team_id);
                int n = dao.syncIssue(group_id+"", token, team_id,class_id);
                if (n !=0) {
                    out.print("success");
                } else {
                    out.print("some thing wrong");
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    public void disPath(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispath = request.getRequestDispatcher(path);
        dispath.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
