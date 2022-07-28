/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import dao.DAOMilestone;
import entity.Milestone;
import entity.Class;
import entity.Iteration;
import entity.Subject;
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
import utils.StringValidation;
import utils.Validator;

/**
 *
 * @author Tuan
 */
@WebServlet(name = "MilestoneController", urlPatterns = {"/milestone"})
public class MilestoneController extends HttpServlet {

    DAOMilestone dao = new DAOMilestone();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int role = LoginController.getLoginCookie(request).getRole_id();
        int user_id = LoginController.getLoginCookie(request).getUser_id();
        String service = request.getParameter("go");
        if (service == null) {
            service = "view";
        }

        try (PrintWriter out = response.getWriter()) {
            if (service.equals("view")) {

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

                //get filter and search data
                String sStatus = request.getParameter("status");
                int status = (sStatus == null || "".equals(sStatus)) ? -1 : Integer.parseInt(sStatus);

                String sIteration_id = request.getParameter("iteration_id");
                int iteration_id = (sIteration_id == null || "".equals(sIteration_id)) ? -1 : Integer.parseInt(sIteration_id);

                String search = request.getParameter("search");
                search = (search == null || "".equals(search)) ? null : search;

                String sClass_id = request.getParameter("class_id");
                int class_id = (sClass_id == null || "".equals(sClass_id)) ? -1 : Integer.parseInt(sClass_id);

                //get sorting filter request
                String str = "milestone.milestone_name|iteration.iteration_name|class.class_code|milestone.from_date|milestone.to_date|subject.subject_name";
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
//                System.out.println(iteration_id);
                Vector<Iteration> iterations = dao.getIterationByClass(class_id);
                Vector<Milestone> milestones = dao.queryMilestone(user_id, role, pageIndex, pageSize, class_id, iteration_id, status, search, filter_col, filter_type);
                Vector<Subject> subjects = dao.querySubjectMilestone(user_id, role);
                int totalMilestone = dao.countMilestone(user_id, role, class_id, iteration_id, status, search);
                Class _class = dao.getClass(class_id);

                request.setAttribute("milestones", milestones);
                request.setAttribute("subjects", subjects);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("search_value", search);
                request.setAttribute("role_id", role);
                request.setAttribute("totalSize", (int) Math.ceil((double) totalMilestone / pageSize));
                request.setAttribute("filter_types", filter_types);
                request.setAttribute("class", _class);
                request.setAttribute("iterations", iterations);
                disPath(request, response, "/views/milestone/MilestoneList.jsp");

            } else if (service.equals("update_status")) {
                int milestone_id = Integer.parseInt(request.getParameter("milestone_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateMilestoneStatus(milestone_id, status);
                if (n != 0) {
                    out.print("success");
                } else {
                    out.print("failed");
                }
            } else if (service.equals("edit")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    int milestone_id = Integer.parseInt(request.getParameter("id"));
                    Milestone mil = dao.getMilestoneByID(user_id, role, milestone_id);
                    if (mil == null) {
                        response.sendError(403);
                    } else {
                        Vector<Iteration> iterations = dao.getIterationByClass(mil.getClass_id());
                        request.setAttribute("iterations", iterations);
                        request.setAttribute("milestone", mil);
                        disPath(request, response, "views/milestone/MilestoneDetails.jsp");
                    }
                } else {
                    String error_list = "";
                    try {
                        String milestone_id = request.getParameter("milestone_id");
                        String iteration_id = request.getParameter("iteration_id");
                        String class_id = request.getParameter("class_id");
                        String from_date = request.getParameter("from_date");
                        String to_date = request.getParameter("to_date");
                        String description = request.getParameter("description");
                        String status = request.getParameter("status");
                        String milestone_name = request.getParameter("name");

                        if (!Validator.checkID(milestone_id)) {
                            error_list += "|milestone_id";
                        }
                        if (!Validator.checkID(iteration_id)) {
                            error_list += "|iteration_id";
                        }
                        if (!Validator.checkID(class_id)) {
                            error_list += "|class_id";
                        }
                        if (!Validator.checkDescription(description)) {
                            error_list += "|description";
                        }
                        if (!Validator.checkStatus(status)) {
                            error_list += "|status";
                        }
                        if (StringValidation.isInteger(iteration_id)) {
                            int duration = dao.getDurationIteration(Integer.parseInt(iteration_id));
                            if (!StringValidation.dateCompare(from_date, to_date, duration * 7)) {
                                error_list += "|from_date|to_date";
                            }
                        }
                        if (!StringValidation.checkName(milestone_name)) {
                            error_list += "|name";
                        }
                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        Milestone mil = new Milestone();
                        mil.setIteration_id(Integer.parseInt(iteration_id));
                        mil.setMilestone_id(Integer.parseInt(milestone_id));
                        mil.setClass_id(Integer.parseInt(class_id));
                        mil.setFrom_date(from_date);
                        mil.setTo_date(to_date);
                        mil.setStatus(Integer.parseInt(status));
                        mil.setDescription(description);
                        mil.setMilestone_name(milestone_name);
                        int n = dao.updateMilestone(mil);
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
                if (role == 4) {
                    response.sendError(403);
                }
                String submit = request.getParameter("submit");
                if (submit == null) {
                    Vector<Class> classes = dao.queryClass(user_id, role);
                    if (classes != null) {
                        Vector<Iteration> iterations = dao.getIterationByClass(classes.get(0).getClass_id());
                        request.setAttribute("iterations", iterations);
                    }
                    request.setAttribute("classes", classes);
                    disPath(request, response, "views/milestone/AddMilestone.jsp");
                } else {
                    String error_list = "";
                    try {
                        String iteration_id = request.getParameter("iteration_id");
                        String class_id = request.getParameter("class_id");
                        String from_date = request.getParameter("from_date");
                        String to_date = request.getParameter("to_date");
                        String description = request.getParameter("description");
                        String status = request.getParameter("status");
                        String milestone_name = request.getParameter("name");

                        if (!Validator.checkID(iteration_id)) {
                            error_list += "|iteration_id";
                        }
                        if (!Validator.checkID(class_id)) {
                            error_list += "|class_id";
                        }
                        if (!Validator.checkDescription(description)) {
                            error_list += "|description";
                        }
                        if (!Validator.checkStatus(status)) {
                            error_list += "|status";
                        }
                        if (StringValidation.isInteger(iteration_id)) {
                            int duration = dao.getDurationIteration(Integer.parseInt(iteration_id));
                            if (!StringValidation.dateCompare(from_date, to_date, duration * 7)) {
                                error_list += "|from_date|to_date";
                            }
                        }
                        if (!StringValidation.checkName(milestone_name)) {
                            error_list += "|name";
                        }
                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        Milestone mil = new Milestone();
                        mil.setIteration_id(Integer.parseInt(iteration_id));
                        mil.setClass_id(Integer.parseInt(class_id));
                        mil.setFrom_date(from_date);
                        mil.setTo_date(to_date);
                        mil.setStatus(Integer.parseInt(status));
                        mil.setDescription(description);
                        mil.setMilestone_name(milestone_name);
                        int n = dao.addMilestone(mil);
                        if (n != 0) {
                            response.getWriter().print("success");
                        } else {
                            throw new Exception("A milestone for the selected iteration and class already exists!");
                        }
                    } catch (Exception ex) {
                        out.print("error: " + ex.getMessage());
                    }
                }
            } else if (service.equals("getIteration")) {
                try {
                    int class_id = Integer.parseInt(request.getParameter("class_id"));
                    Vector<Iteration> iterations = dao.getIterationByClass(class_id);
                    for (int i = 0; i < iterations.size(); i++) {
                        out.print("<option value=\"" + iterations.get(i).getIteration_id() + "\">" + iterations.get(i).getIteration_name() + " - " + iterations.get(i).getDuration() + "Weeks</option>");
                    }
                } catch (Exception e) {

                }
            } else if (service.equals("getClass")) {
                try {
                    int subject_id = Integer.parseInt(request.getParameter("subject_id"));
                    Vector<Class> classes = dao.getClassBySubject(role, user_id, subject_id);
                    for (int i = 0; i < classes.size(); i++) {
                        out.print("<option value=\"" + classes.get(i).getClass_id() + "\">" + classes.get(i).getClass_code() + "</option>");
                    }
                } catch (Exception e) {

                }
            } else if (service.equals("sync_milestone")) {
                int class_id = Integer.parseInt(request.getParameter("class_id"));
                String group_id = dao.getGitLab_id(class_id);

                HttpSession session = request.getSession();
                String token = session.getAttribute("gitlab_token").toString();

                int n = dao.syncMilestoneGit(group_id, token, class_id);
                if (n == 1) {
                    out.print("success");
                } else {
                    out.print("some thing wrong");
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
