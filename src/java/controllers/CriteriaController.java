/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import dao.DAOCriteria;
import entity.Criteria;
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
import utils.StringValidation;
import utils.Validator;

/**
 *
 * @author Luu Dat
 */
@WebServlet(name = "CriteriaController", urlPatterns = {"/criteria"})
public class CriteriaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DAOCriteria dao = new DAOCriteria();

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
            /* TODO output your page here. You may use following sample code. */
//            if (role == 1 && role == 2) {
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

                String search_value = "";
                String search_type = "byIteration";

                //get filter and search data
                String sStatus = request.getParameter("status");
                int status = (sStatus == null || "".equals(sStatus)) ? -1 : Integer.parseInt(sStatus);

                String sIterationID = request.getParameter("iterationID");
                int iterationID = (sIterationID == null || "".equals(sIterationID)) ? -1 : Integer.parseInt(sIterationID);

                String sSubject_id = request.getParameter("subject_id");
                int subject_id = (sSubject_id == null || "".equals(sSubject_id)) ? -1 : Integer.parseInt(sSubject_id);

                String criteria_id = request.getParameter("criteria_id");
                criteria_id = (criteria_id == null || "".equals(criteria_id)) ? null : criteria_id;
                if (criteria_id != null) {
                    search_value = criteria_id;
                    search_type = "byCriteria_id";
                }

                String title = request.getParameter("title");
                title = (title == null || "".equals(title)) ? null : title;
                if (title != null) {
                    search_value = title;
                    search_type = "byIteration";
                }

                String evalution_title = request.getParameter("evalution_title");
                evalution_title = (evalution_title == null || "".equals(evalution_title)) ? null : evalution_title;
                if (evalution_title != null) {
                    search_value = evalution_title;
                    search_type = "byEvalution_title";
                }

                String evalution_weight = request.getParameter("evalution_weight");
                evalution_weight = (evalution_weight == null || "".equals(evalution_weight)) ? null : evalution_weight;
                if (evalution_weight != null) {
                    search_value = evalution_weight;
                    search_type = "byEvalution_weight";
                }

                //get sorting filter request
                String str = "evalution_criteria.criterial_id|iteration.iteration_name|evalution_criteria.evalution_title|evalution_criteria.evalution_weight";
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

                Vector<Criteria> criterias = dao.queryCriteria(user_id, role, pageIndex, pageSize, subject_id, iterationID, status, criteria_id, title, filter_col, filter_type);
                Vector<Iteration> iterations = dao.queryIterationCriteria();
                int totalCriteria = dao.countCriteria(user_id, role, subject_id, iterationID, status, criteria_id, title);

                request.setAttribute("subject_id", subject_id);
                request.setAttribute("criterias", criterias);
                request.setAttribute("iterations", iterations);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("search_type", search_type);
                request.setAttribute("search_value", search_value);
                request.setAttribute("role_id", role);
                request.setAttribute("totalSize", (int) Math.ceil((double) totalCriteria / pageSize));
                request.setAttribute("filter_types", filter_types);
                request.setAttribute("subject", dao.getSubject(subject_id));
                disPath(request, response, "/views/criteria/CriteriaList.jsp");

            } else if (service.equals("update_status")) {
                int criteria_id = Integer.parseInt(request.getParameter("criteria_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateCriteriaStatus(criteria_id, status);
                if (n != 0) {
                    out.print("success");
                } else {
                    out.print("failed");
                }
            } else if (service.equals("add")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int subject_id = Integer.parseInt(request.getParameter("subject_id"));
                    Subject subject = dao.getSubject(subject_id);
                    Vector<Iteration> iterations = dao.getIterationBySubject(subject_id);
                    request.setAttribute("iterations", iterations);
                    request.setAttribute("subject", subject);
                    disPath(request, response, "views/criteria/CreateCriteria.jsp");
                } else {
                    String error_list = "";
                    try {
                        String iteration_id = request.getParameter("iteration_id");
                        String evalution_title = request.getParameter("evalution_title");
                        String evalution_weight = request.getParameter("evalution_weight");
                        String criteria_order = request.getParameter("criteria_order");
                        String max_loc = request.getParameter("max_loc");
                        String team_evaluation = request.getParameter("team_evaluation");
                        String status = request.getParameter("status");

                        if (!Validator.checkID(iteration_id)) {
                            error_list += "|iteration_id";
                        }
                        if (!StringValidation.checkName(evalution_title)) {
                            error_list += "|evalution_title";
                        }
                        if (!StringValidation.isInteger(evalution_weight)) {
                            error_list += "|evalution_weight";
                        }
                        if (!StringValidation.isInteger(criteria_order)) {
                            error_list += "|criteria_order";
                        }
                        if (!StringValidation.isInteger(max_loc)) {
                            error_list += "|max_loc";
                        }
                        if (!Validator.checkName(team_evaluation)) {
                            error_list += "|team_evaluation";
                        }
                        if (!Validator.checkStatus(status)) {
                            error_list += "|status";
                        }

                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        Criteria temp = new Criteria();
                        temp.setIteration_id(Integer.parseInt(iteration_id));
                        temp.setEvalution_title(evalution_title);
                        temp.setEvalution_weight(evalution_weight);
                        temp.setTeam_evaluation(team_evaluation);
                        temp.setCriteria_order(criteria_order);
                        temp.setMax_loc(max_loc);
                        temp.setStatus(Integer.parseInt(status));
                        System.out.println(temp);
                        int n = dao.addCriteria(temp);
                        if (n != 0) {
                            response.getWriter().print("success");
                        } else {
                            throw new Exception("database");
                        }
                    } catch (Exception ex) {
                        out.print("error: " + ex.getMessage());
                    }
                }
            } else if (service.equals("edit")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int criteria_id = Integer.parseInt(request.getParameter("id"));
                    Criteria crite = dao.getCriteriaById(user_id, role, criteria_id);
                    if (crite == null) {
                        response.sendError(403);
                    } else {
                        Vector<Iteration> iterations = dao.getIterationBySubject(crite.getSubject_id());
                        Subject subject = dao.getSubject(crite.getSubject_id());
                        request.setAttribute("iterations", iterations);
                        request.setAttribute("criteria", crite);
                        request.setAttribute("subject", subject);
                        disPath(request, response, "views/criteria/CriteriaDetails.jsp");
                    }
                } else {
                    String error_list = "";
                    try {
                        String criteria_id = request.getParameter("criteria_id");
                        String subject_id = request.getParameter("subject_id");
                        String iteration_id = request.getParameter("iteration_name");
                        String evalution_title = request.getParameter("evalution_title");
                        String evalution_weight = request.getParameter("evalution_weight");
                        String criteria_order = request.getParameter("criteria_order");
                        String max_loc = request.getParameter("max_loc");
                        String team_evaluation = request.getParameter("team_evaluation");
                        String status = request.getParameter("status");

                        if (!Validator.checkID(criteria_id)) {
                            error_list += "|criteria_id";
                        }
                        if (!Validator.checkID(subject_id)) {
                            error_list += "|subject_id";
                        }
                        if (!Validator.checkID(iteration_id)) {
                            error_list += "|iteration_name";
                        }
                        if (!StringValidation.checkName(evalution_title)) {
                            error_list += "|evalution_title";
                        }
                        if (!StringValidation.isInteger(evalution_weight)) {
                            error_list += "|evalution_weight";
                        }
                        if (!StringValidation.isInteger(criteria_order)) {
                            error_list += "|criteria_order";
                        }
                        if (!StringValidation.isInteger(max_loc)) {
                            error_list += "|max_loc";
                        }
                        if (!Validator.checkName(team_evaluation)) {
                            error_list += "|team_evaluation";
                        }
                        if (!Validator.checkStatus(status)) {
                            error_list += "|status";
                        }

                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        Criteria temp = new Criteria();
                        temp.setCriteria_id(Integer.parseInt(criteria_id));
                        temp.setIteration_id(Integer.parseInt(iteration_id));
                        temp.setEvalution_title(evalution_title);
                        temp.setEvalution_weight(evalution_weight);
                        temp.setTeam_evaluation(team_evaluation);
                        temp.setCriteria_order(criteria_order);
                        temp.setMax_loc(max_loc);
                        temp.setStatus(Integer.parseInt(status));
                        System.out.println(temp);
                        int n = dao.updateCriteria(temp);
                        if (n != 0) {
                            response.getWriter().print("success");
                        } else {
                            throw new Exception("database");
                        }
                    } catch (Exception ex) {
                        out.print("error: " + ex.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
