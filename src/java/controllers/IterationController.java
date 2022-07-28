package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controllers.auth.LoginController;
import dao.DAOIteration;
import entity.Iteration;
import entity.Milestone;
import entity.Subject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Tuan
 */
@WebServlet(name = "IterationController", urlPatterns = {"/iteration"})
public class IterationController extends HttpServlet {

    DAOIteration dao = new DAOIteration();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }
        int role = LoginController.getLoginCookie(request).getRole_id();
        int u_id = LoginController.getLoginCookie(request).getUser_id();
        try (PrintWriter out = response.getWriter()) {
            if (role == 2 || role == 1) {

                if (action.equals("view")) {
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

                    //get sorting filter request
                    String str = "iteration.duration|iteration.iteration_weight";
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

                    //get filter and search data
                    String sStatus = request.getParameter("status");
                    int status = (sStatus == null || "".equals(sStatus)) ? -1 : Integer.parseInt(sStatus);

                    String sIs_ongoin = request.getParameter("is_ongoin");
                    int is_ongoin = (sIs_ongoin == null || "".equals(sIs_ongoin)) ? -1 : Integer.parseInt(sIs_ongoin);

                    String search = request.getParameter("search");
                    search = (search == null || "".equals(search)) ? null : search;

                    String sSubject_id = request.getParameter("subject_id");
                    int subject_id = (sSubject_id == null || "".equals(sSubject_id)) ? -1 : Integer.parseInt(sSubject_id);

                    Vector<Iteration> iterations = dao.queryIteration(pageIndex, pageSize, subject_id, status, is_ongoin, search, filter_col, filter_type);
                    int count = dao.countIteration(subject_id, status, is_ongoin, search);

                    request.setAttribute("iterations", iterations);
                    request.setAttribute("pageIndex", pageIndex);
                    request.setAttribute("search_value", search);
                    request.setAttribute("filter_types", filter_types);
                    request.setAttribute("subject", dao.getSubjectInfo(subject_id));
                    request.setAttribute("totalSize", (int) Math.ceil((double) count / pageSize));
                    disPath(request, response, "/views/iteration/IterationList.jsp");

                } else if (action.equals("edit")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        int iter_id = Integer.parseInt(request.getParameter("id"));
                        Iteration iter = dao.getIteration(iter_id);
                        request.setAttribute("iteration", iter);
                        disPath(request, response, "views/iteration/IterationDetails.jsp");
                    } else {
                        String error_list = "";
                        try {
                            String iteration_id = request.getParameter("iteration_id");
                            String subject_id = request.getParameter("subject_id");
                            String iteration_name = request.getParameter("iteration_name");
                            String duration = request.getParameter("duration");
                            String iteration_weight = request.getParameter("iteration_weight");
                            String is_ongoin = request.getParameter("is_ongoin");
                            String status = request.getParameter("status");

                            if (!StringValidation.checkName(iteration_name)) {
                                error_list += "|iteration_name";
                            }
                            if ("0".equals(duration) || "".equals(duration)) {
                                error_list += "|duration";
                            }
                            if ("0".equals(iteration_weight) || "".equals(iteration_weight)) {
                                error_list += "|iteration_weight";
                            }
                            if (!"".equals(error_list)) {
                                throw new Exception(error_list.substring(1));
                            }

                            Iteration temp = new Iteration();
                            temp.setIteration_id(Integer.parseInt(iteration_id));
                            temp.setSubject_id(Integer.parseInt(subject_id));
                            temp.setIteration_name(iteration_name);
                            temp.setDuration(Integer.parseInt(duration));
                            temp.setIteration_weight(Integer.parseInt(iteration_weight));
                            temp.setIs_ongoin(Integer.parseInt(is_ongoin));
                            temp.setStatus(Integer.parseInt(status));
                            int n = dao.updateIteration(temp);
                            if (n == -1) {
                                response.getWriter().print("duplicated");
                            } else if (n != 0) {
                                response.getWriter().print("success");
                            } else {
                                throw new Exception("database");
                            }
                        } catch (Exception ex) {
                            out.print("error: " + ex.getMessage());
                        }
                    }
                } else if (action.equals("add")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        int sub_id = Integer.parseInt(request.getParameter("subject_id"));
                        Subject subject = dao.getSubjectInfo(sub_id);
                        request.setAttribute("subject", subject);
                        disPath(request, response, "views/iteration/CreateIteration.jsp");
                    } else {
                        String error_list = "";
                        try {
                            String subject_id = request.getParameter("subject_id");
                            String iteration_name = request.getParameter("iteration_name");
                            String duration = request.getParameter("duration");
                            String iteration_weight = request.getParameter("iteration_weight");
                            String is_ongoin = request.getParameter("is_ongoin");
                            String status = request.getParameter("status");

                            if (!StringValidation.checkName(iteration_name)) {
                                error_list += "|iteration_name";
                            }
                            if ("0".equals(duration) || "".equals(duration)) {
                                error_list += "|duration";
                            }
                            if ("0".equals(iteration_weight) || "".equals(iteration_weight)) {
                                error_list += "|iteration_weight";
                            }
                            if (!"".equals(error_list)) {
                                throw new Exception(error_list.substring(1));
                            }

                            Iteration temp = new Iteration();
                            temp.setSubject_id(Integer.parseInt(subject_id));
                            temp.setIteration_name(iteration_name);
                            temp.setDuration(Integer.parseInt(duration));
                            temp.setIteration_weight(Integer.parseInt(iteration_weight));
                            temp.setIs_ongoin(Integer.parseInt(is_ongoin));
                            temp.setStatus(Integer.parseInt(status));
                            int n = dao.addIteration(temp);
                            if (n == -1) {
                                response.getWriter().print("duplicated");
                            } else if (n != 0) {
                                response.getWriter().print("success");
                            } else {
                                throw new Exception("database");
                            }
                        } catch (Exception ex) {
                            out.print("error: " + ex.getMessage());
                        }
                    }
                } else if (action.equals("edit_status")) {
                    int iter_id = Integer.parseInt(request.getParameter("id"));
                    int status = Integer.parseInt(request.getParameter("status"));
                    int n = dao.editStatus(iter_id, status);
                    if (n == 1) {
                        out.print("success");
                    } else {
                        out.print("Some thing wrong!");
                    }
                } else if (action.equals("edit_ongoin")) {
                    int iter_id = Integer.parseInt(request.getParameter("id"));
                    int ongoin = Integer.parseInt(request.getParameter("ongoin"));
                    int n = dao.editOngoin(iter_id, ongoin);
                    if (n == 1) {
                        out.print("success");
                    } else {
                        out.print("Some thing wrong!");
                    }
                }
            } else {
                response.sendError(403);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
