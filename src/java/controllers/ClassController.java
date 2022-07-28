/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import entity.Class;
import dao.DAOClass;
import dao.DAOSubject;
import dao.DAOUser;
import entity.Subject;
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
import utils.StringValidation;
import utils.Validator;

/**
 *
 * @author ptuan
 */
@WebServlet(name = "ClassController", urlPatterns = {"/class"})
public class ClassController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            DAOClass dao = new DAOClass();
            int loop, first, last;
            DAOSubject daoSubj = new DAOSubject();
            DAOUser daoUser = new DAOUser();
            int role_id = LoginController.getLoginCookie(request).getRole_id();
            int user_id = LoginController.getLoginCookie(request).getUser_id();
            if (role_id != 1 && role_id != 2 && role_id != 3) {
                response.sendError(403);
                return;
            }
            String service = request.getParameter("action");
            if (service == null) {
                service = "view";
            }
            if (service.equals("view")) {
                String str = "class_code|class_year";
                String[] filter_keys = str.split("\\|");
                String[] filter_types = new String[filter_keys.length];
                String filter_col = null;
                String filter_type = null;
                for (int i = 0; i < filter_keys.length; i++) {
                    String sKey = request.getParameter(filter_keys[i]);
                    String sType = (sKey == null || (!"asc".equals(sKey) && !"desc".equals(sKey))) ? null : sKey;
                    if (sType != null) {
                        filter_col = filter_keys[i];
                        filter_type = sType;
                        filter_types[i] = (filter_type.equals("asc")) ? "desc" : "asc";
                    } else {
                        filter_types[i] = "asc";
                    }
                }

                String content = request.getParameter("inputSearch");
                String trainer = request.getParameter("trainer");
                String status1 = request.getParameter("status");
                int trainer_id = (trainer == null || trainer.equals("")) ? -1 : Integer.parseInt(trainer);
                int status = (status1 == null || status1.equals("")) ? -1 : Integer.parseInt(status1);

                String sPageIndex = request.getParameter("page");
                String sPageSize = request.getParameter("size");
                int pageIndex = (sPageIndex == null || "".equals(sPageIndex)) ? 0 : Integer.parseInt(sPageIndex);
                int pageSize = (sPageSize == null || "".equals(sPageSize)) ? 5 : Integer.parseInt(sPageSize);
                if (pageIndex <= 0) {
                    pageIndex = 1;
                }
                int total = dao.queryCountClass(user_id, role_id, status, trainer_id, content);
                if (pageSize <= 0) {
                    pageSize = 5;
                }
                if (total <= pageSize) {
                    first = 0;
                    last = total;
                } else {
                    first = (pageIndex - 1) * pageSize;
                    last = pageSize;
                }
                loop = getMaxPage(total, first, last, pageSize);
                Vector<Class> vector = dao.queryClass(first, last, filter_col, filter_type, user_id, role_id, trainer_id, status, content);
                Vector<Subject> vectorS = daoSubj.viewAll();
                Vector<User> vectorU = daoUser.viewAllUsers();
                Vector<Integer> vectorI = dao.viewAllTrainer();
                String chekc = null;
                if(role_id == 3){
                    chekc = "true";
                }
                request.setAttribute("chekc", chekc);
                request.setAttribute("search", content);
                request.setAttribute("trainer", vectorI);
                request.setAttribute("maxpage", loop);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("filter_types", filter_types);
                request.setAttribute("subject", vectorS);
                request.setAttribute("user", vectorU);
                request.setAttribute("class", vector);

                dispath(request, response, "/views/class/ClassList.jsp");
            }
            if (role_id != 3 && role_id !=4) {
                if (service.equals("edit")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String id = request.getParameter("id");
                        int id2 = Integer.parseInt(id);
                        Class vector = dao.getClassById(id2);
                        Vector<Subject> vectorS = daoSubj.viewAll();
                        Vector<User> vectorU = daoUser.viewAllUsers();

                        request.setAttribute("subject", vectorS);
                        request.setAttribute("user", vectorU);
                        request.setAttribute("cat", vector);
                        dispath(request, response, "/views/class/ClassDetails.jsp");
                    } else {
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            String class_code = request.getParameter("class_code");
                            int trainer = Integer.parseInt(request.getParameter("trainer"));
                            int subject = Integer.parseInt(request.getParameter("subject"));
                            String year = request.getParameter("year");
                            String term = request.getParameter("term");
                            int block5 = Integer.parseInt(request.getParameter("block5"));
                            int status = Integer.parseInt(request.getParameter("status"));
                            String gitLab = request.getParameter("git");
                            String error_list = "";

                            if (!Validator.checkClassCode(class_code)) {
                                error_list += "|class_code";
                            }
                            if (!StringValidation.isInteger(year)) {
                                error_list += "|year";
                            }
                            if (!Validator.checkName(term)) {
                                error_list += "|term";
                            }

                            if (!"".equals(error_list)) {
                                throw new Exception(error_list.substring(1));
                            }
                            int year2 = Integer.parseInt(year);
                            int n = dao.updateClass(new Class(user_id, class_code, trainer, subject, year2, term, block5, status,gitLab));
                            if (n > 0) {
                                response.getWriter().print("success");
                            } else {
                                throw new Exception("database");
                            }
                        } catch (Exception e) {
                            response.getWriter().print("error: " + e.getMessage());
                        }
                    }
                }
                if (service.equals("add")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {

                        Vector<Subject> vectorS = daoSubj.viewAll();
                        Vector<User> vectorU = daoUser.viewAllUsers();

                        request.setAttribute("subject", vectorS);
                        request.setAttribute("user", vectorU);

                        dispath(request, response, "/views/class/AddClass.jsp");
                    } else {
                        try {
                            String class_code = request.getParameter("class_code");
                            int trainer = Integer.parseInt(request.getParameter("trainer"));
                            int subject = Integer.parseInt(request.getParameter("subject"));
                            String year = request.getParameter("year");
                            String term = request.getParameter("term");
                            String gitlab = request.getParameter("git");
                            String block = request.getParameter("block5");
                            String status2 = request.getParameter("status");
                            String error_list = "";

                            if (!Validator.checkClassCode(class_code)) {
                                error_list += "|class_code";
                            }
                            if (!StringValidation.isInteger(year)) {
                                error_list += "|year";
                            }
                            if (!Validator.checkName(term)) {
                                error_list += "|term";
                            }
                            if (!Validator.checkNull(gitlab)) {
                                error_list += "|git";
                            }
                            if (!StringValidation.isInteger(block)) {
                                error_list += "|block5";
                            }
                            if (!StringValidation.isInteger(status2)) {
                                error_list += "|status";
                            }
                            if (!"".equals(error_list)) {
                                throw new Exception(error_list.substring(1));
                            }

                            int block5 = Integer.parseInt(block);
                            int status = Integer.parseInt(status2);
                            int year2 = Integer.parseInt(year);
                            int n = dao.addClass(new Class(user_id, class_code, trainer, subject, year2, term, block5, status,gitlab));
                            if (n > 0) {
                                response.getWriter().print("success");
                            } else {
                                throw new Exception("database");
                            }
                        } catch (Exception e) {
                            response.getWriter().print("error: " + e.getMessage());
                        }
                    }
                }
            } else {
                response.sendError(403);
            }
        }
    }

    public void dispath(HttpServletRequest request,
            HttpServletResponse response, String page)
            throws IOException, ServletException {
        RequestDispatcher dispath = request.getRequestDispatcher(page);
        dispath.forward(request, response);
    }

    public int getMaxPage(int total, int first, int last, int pagsize) {
        int num, loop;
        if ((total / pagsize) % 2 == 0) {
            num = total / pagsize;
        } else {
            num = (total + 1) / pagsize;
        }
        //Nếu total lẻ thêm 1
        if (total % 2 != 0) {
            loop = (total / pagsize) + 1;
        } else {
            //Nếu total chẵn nhỏ hơn fullpage và # fullPage thì thêm 1
            if (total < (num * pagsize) + pagsize && total != num * pagsize) {
                loop = (total / pagsize) + 1;
            } else {
                //Nếu bằng fullPage thì không thêm
                loop = (total / pagsize);
            }
        }
        return loop;
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
