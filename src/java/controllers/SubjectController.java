/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import dao.DAOSubject;
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
import utils.Validator;

/**
 *
 * @author buitr
 */
@WebServlet(name = "SubjectController", urlPatterns = {"/subject"})
public class SubjectController extends HttpServlet {

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

        DAOSubject dao = new DAOSubject();
        try (PrintWriter out = response.getWriter()) {
            int role = LoginController.getLoginCookie(request).getRole_id();
            int u_id = LoginController.getLoginCookie(request).getUser_id();

            if (service.equals("viewAll")) {
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

                String subject_search = request.getParameter("subject_search");
                subject_search = (subject_search == null || "".equals(subject_search)) ? null : subject_search;

                String Sauthor_id = request.getParameter("author_id");
                int author_id = (Sauthor_id == null || "".equals(Sauthor_id)) ? -1 : Integer.parseInt(Sauthor_id);

                String Strainer_id = request.getParameter("trainer_id");
                int trainer_id = (Strainer_id == null || "".equals(Strainer_id)) ? -1 : Integer.parseInt(Strainer_id);

//get sorting filter request
                String str = "s.subject_code|s.subject_name|u.full_name";
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
                int countSubject = dao.countSubject(u_id, role, status, author_id, trainer_id, subject_search);

                Vector<Subject> subjects = dao.getSubject(u_id, role, pageIndex, pageSize, status, author_id, trainer_id, subject_search, filter_col, filter_type);
                Vector<Subject> listTrainer = dao.getTrainer();
                Vector<Subject> listAuthor = dao.getAuthor();

                request.setAttribute("subjects", subjects);
                request.setAttribute("listTrainer", listTrainer);
                request.setAttribute("listAuthor", listAuthor);
                request.setAttribute("filter_types", filter_types);
                request.setAttribute("search_value", subject_search);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalSize", (int) Math.ceil((double) countSubject / pageSize));
                disPath(request, response, "/views/subject/SubjectList.jsp");
            }

            if (service.equals("updateStatus")) {
                int subject_id = Integer.parseInt(request.getParameter("subject_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateSubject(subject_id, status);
                if (n == 0) {
                    out.print("fail");
                } else {
                    out.print("success");
                }
            }
            if (service.equals("edit")) {
                String error_list = "";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int subject_id = Integer.parseInt(request.getParameter("subject_id"));
                    Vector<Subject> listAuthor = dao.getAuthor_();
                    Subject subject = dao.listAllByID(subject_id, u_id);
                    request.setAttribute("subject", subject);
                    request.setAttribute("listAuthor", listAuthor);
                    disPath(request, response, "/views/subject/SubjectDetail.jsp");

                } else {
                    try {
                        String subject_id = request.getParameter("subject_id");
                        String subject_code = request.getParameter("subject_code");
                        String subject_name = request.getParameter("subject_name");
                        int author_id = Integer.parseInt(request.getParameter("author_id"));
                        String status = request.getParameter("status") == null ? "-1" : request.getParameter("status");
                        if (!Validator.checkClassCode(subject_code)) {
                            error_list = error_list + "|subject_code";
                        }
                        if (!Validator.checkName(subject_name)) {
                            error_list = error_list + "|subject_name";
                        }

                        if (!Validator.checkStatus(status)) {
                            error_list = error_list + "|status";
                        }

                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        int n = dao.updateSubject(new Subject(Integer.parseInt(subject_id), subject_code, subject_name, author_id, Integer.parseInt(status)));
                        if (n == 0) {
                            out.print("fail");
                        } else {
                            out.print("success");
                        }
                    } catch (Exception e) {
                        out.print("error: " + e.getMessage());
                    }
                }

            }
            if (service.equals("add")) {

                String error_list = "";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    Vector<Subject> listAuthor = dao.getAuthor_();
                    request.setAttribute("listAuthor", listAuthor);
                    disPath(request, response, "/views/subject/SubjectAdd.jsp");
                } else {
                    try {
                        String subject_code = request.getParameter("subject_code");
                        String status = request.getParameter("status") == null ? "-1" : request.getParameter("status");
                        String subject_name = request.getParameter("subject_name");
                        int author_id = Integer.parseInt(request.getParameter("author_id"));
                        if (!Validator.checkClassCode(subject_code)) {
                            error_list = error_list + "|subject_code";
                        }
                        if (!Validator.checkName(subject_name)) {
                            error_list = error_list + "|subject_name";
                        }

                        if (!Validator.checkStatus(status)) {
                            error_list = error_list + "|status";
                        }

                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        Subject s = new Subject();
                        s.setSubject_code(subject_code);
                        s.setSubject_name(subject_name);
                        s.setStatus(Integer.parseInt(status));
                        s.setAuthor_id(author_id);
                        int n = dao.addSubject(s);

                        if (n == 0) {
                            out.print("fail");
                        } else {
                            out.print("success");
                        }
                    } catch (Exception e) {
                        out.print("error: " + e.getMessage());
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
