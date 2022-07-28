/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import dao.DAOTracking;
import entity.Function;
import entity.Milestone;
import entity.Team;
import entity.Tracking;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Validator;
import entity.Class;

/**
 *
 * @author Luu Dat
 */
@WebServlet(name = "TrackingController", urlPatterns = {"/tracking"})
public class TrackingController extends HttpServlet {

    DAOTracking dao = new DAOTracking();
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
        
        int role = LoginController.getLoginCookie(request).getRole_id();
        int u_id = LoginController.getLoginCookie(request).getUser_id();
        String service = request.getParameter("go");

        if (service == null) {
            service = "viewAll";
        }
        try (PrintWriter out = response.getWriter()) {
     
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

                String note = request.getParameter("note_search");
                note = (note == null || "-1".equals(note)) ? null : note;

                String function = request.getParameter("function");
                function = (function == null || "-1".equals(function)) ? null : function;

                String milestone = request.getParameter("milestone");
                milestone = (milestone == null || "-1".equals(milestone)) ? null : milestone;

                //get sorting filter request
                String str = "tracking.tracking_id|function.function_name|milestone.milestone_name|user.assignee_name|function.role|tracking.status";
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
                
              
                
                int countTracking = dao.countTracking(u_id, role, team_id, pageIndex, pageSize, status, assignee, note, function, milestone);

                Vector<Tracking> trackingList = dao.getTracking(u_id, role, team_id, pageIndex, pageSize, status, assignee, note, function, milestone, filter_col, filter_type);
                Vector<String> listAssignee = dao.getAssignee(u_id, role, team_id);
                Vector<String> listFunction = dao.getFunction(u_id, role, team_id);
                Vector<String> listMilestone = dao.getMilestone(u_id, role, team_id);

                request.setAttribute("listAssignee", listAssignee);
                request.setAttribute("listFunction", listFunction);
                request.setAttribute("listMilestone", listMilestone);

                request.setAttribute("trackingList", trackingList);
                request.setAttribute("filter_types", filter_types);

                request.setAttribute("search_value", note);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalSize", (int) Math.ceil((double) countTracking / pageSize));
                
                request.setAttribute("team_id", team_id);
                
                disPath(request, response, "/views/tracking/TrackingList.jsp");
 
            } else if (service.equals("edit")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    int tracking_id = Integer.parseInt(request.getParameter("id"));
                    Tracking tra = dao.getTrackingByID(u_id, role, tracking_id);
                    if (tra == null) {
                        response.sendError(403);
                    } else {
                        Vector<Milestone> milestones = dao.getMilestoneByClass(tra.getTracking_id());
                        Vector<Function> funtions = dao.getFunctionByClass(tra.getTracking_id());
                        Vector<User> assignees = dao.getAssigneeByClass(tra.getTracking_id());
                        Vector<User> assigners = dao.getAssignerByClass(tra.getTracking_id());
                        
                        request.setAttribute("milestones", milestones);
                        request.setAttribute("funtions", funtions);
                        request.setAttribute("assignees", assignees);
                        request.setAttribute("assigners", assigners);
                        request.setAttribute("tracking", tra);
                        disPath(request, response, "views/tracking/TrackingDetails.jsp");
                    }
                } else {
                    String error_list = "";
                    
                    try {
                        String milestone_id = request.getParameter("milestone_name");
                        String funtion_id = request.getParameter("funtion_name");
                        String assignee_id = request.getParameter("assignee_name");
                        String assigner_id = request.getParameter("assigner_name");
                        String status = request.getParameter("status_name");
                        String note = request.getParameter("note_name");
                        String tracking_id = request.getParameter("tracking_id");

                        if (!Validator.checkID(milestone_id)) {
                            error_list += "|milestone_id";
                        }
                        if (!Validator.checkID(funtion_id)) {
                            error_list += "|funtion_id";
                        }
                        if (!Validator.checkID(assignee_id)) {
                            error_list += "|assignee_id";
                        }
                        if (!Validator.checkID(assigner_id)) {
                            error_list += "|assigner_id";
                        }
                        if (!Validator.checkDescription(note)) {
                            error_list += "|note";
                        }
                        if (!Validator.checkStatus(status)) {
                            error_list += "|status";
                        }
                        if (!Validator.checkID(tracking_id)) {
                            error_list += "|tracking_id";
                        }
                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        Tracking tra = new Tracking();
                        tra.setMilestone_id(Integer.parseInt(milestone_id));
                        tra.setFunction_id(Integer.parseInt(funtion_id));
                        tra.setAssignee_id(Integer.parseInt(assignee_id));
                        tra.setAssigner_id(Integer.parseInt(assigner_id));
                        tra.setTracking_note(note);
                        tra.setStatus(Integer.parseInt(status));
                        tra.setTracking_id(Integer.parseInt(tracking_id));
                        
                        int n = dao.updateTracking(tra);
                        if (n != 0) {
                            response.getWriter().print("success");
                        } else {
                            throw new Exception("database");
                        }
                    } catch (Exception ex) {
                        out.print("error: " + ex.getMessage());
                    }
                }
            } else if (service.equals("add")) {
                if (role != 4) {
                    response.sendError(403);
                }
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int team_id = Integer.parseInt(request.getParameter("team_id"));
                    Vector<Milestone> milestones = dao.getMilestone(team_id);
                    Vector<Function> funtions = dao.getFunction(team_id);
                    Vector<User> assignees = dao.getAssignee(team_id);
                    Vector<User> assigners = dao.getAssigner(team_id);
                    request.setAttribute("team", dao.getCurTeam(team_id));
                    request.setAttribute("milestones", milestones);
                    request.setAttribute("funtions", funtions);
                    request.setAttribute("assignees", assignees);
                    request.setAttribute("assigners", assigners);
                    disPath(request, response, "views/tracking/AddTracking.jsp");
                } else {
                    String error_list = "";
                    try {
                        String team_id = request.getParameter("team_id");
                        String milestone_id = request.getParameter("milestone_name");
                        String funtion_id = request.getParameter("funtion_name");
                        String assignee_id = request.getParameter("assignee_name");
                        String assigner_id = request.getParameter("assigner_name");
                        String status = request.getParameter("status_name");
                        String note = request.getParameter("note_name");
                        

                        if (!Validator.checkID(team_id)) {
                            error_list += "|team_id";
                        }
                        if (!Validator.checkID(milestone_id)) {
                            error_list += "|milestone_id";
                        }
                        if (!Validator.checkID(funtion_id)) {
                            error_list += "|funtion_id";
                        }
                        if (!Validator.checkID(assignee_id)) {
                            error_list += "|assignee_id";
                        }
                        if (!Validator.checkID(assigner_id)) {
                            error_list += "|assigner_id";
                        }
                        if (!Validator.checkDescription(note)) {
                            error_list += "|note";
                        }
                        if (!Validator.checkStatus(status)) {
                            error_list += "|status";
                        }
                        
                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        Tracking tra = new Tracking();
                        tra.setTeam_id(Integer.parseInt(team_id));
                        tra.setMilestone_id(Integer.parseInt(milestone_id));
                        tra.setFunction_id(Integer.parseInt(funtion_id));
                        tra.setAssignee_id(Integer.parseInt(assignee_id));
                        tra.setAssigner_id(Integer.parseInt(assigner_id));
                        tra.setTracking_note(note);
                        tra.setStatus(Integer.parseInt(status));
                        int n = dao.addTracking(tra);
                        if (n != 0) {
                            response.getWriter().print("success");
                        } else {
                            throw new Exception("A tracking for the selected iteration and class already exists!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
    }

    public void disPath(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispath = request.getRequestDispatcher(path);
        dispath.forward(request, response);
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
