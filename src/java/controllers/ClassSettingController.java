/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import dao.DAOClass;
import dao.DAOClassSetting;
import dao.DAOUser;
import entity.ClassSetting;
import entity.Setting;
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
 * @author buitr
 */
@WebServlet(name = "ClassSettingController", urlPatterns = {"/classsetting"})
public class ClassSettingController extends HttpServlet {

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
        int user_id = LoginController.getLoginCookie(request).getUser_id();
        String service = request.getParameter("go");
        if (service == null) {
            service = "viewAll";
        }

        DAOClassSetting dao = new DAOClassSetting();
        try (PrintWriter out = response.getWriter()) {
            int role = LoginController.getLoginCookie(request).getRole_id();
            int u_id = LoginController.getLoginCookie(request).getUser_id();
            
            if (role == 4) {
                response.sendError(403);
                return;
            }
            
            if (service.equals("viewAll")) {
                int class_id = Integer.parseInt(request.getParameter("class_id"));
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

                String label = request.getParameter("label");
                label = (label == null || "".equals(label)) ? null : label;

                String sType_id = request.getParameter("type_id");
                int type_id = (sType_id == null || "-1".equals(sType_id) || "".equals(sType_id)) ? -1 : Integer.parseInt(sType_id);

                //get sorting filter request
                String str = "cs.type_title|cs.color|cs.description";
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
                int countClassSetting = dao.countClassSetting(u_id, role, class_id, type_id, label);

                Vector<ClassSetting> ClassSetting = dao.getClassSetting(u_id, role, pageIndex, pageSize, class_id, type_id, label, filter_col, filter_type);
                
                request.setAttribute("class_id", class_id);
                request.setAttribute("ClassSetting", ClassSetting);
                request.setAttribute("filter_types", filter_types);
                request.setAttribute("search_value", label);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalSize", (int) Math.ceil((double) countClassSetting / pageSize));
                disPath(request, response, "/views/class_setting/ClassSettingList.jsp");
            }

            if (service.equals("edit")) {

                String submit = request.getParameter("submit");
                if (submit == null) {
                    int class_id = Integer.parseInt(request.getParameter("class_id"));
                    int class_setting_id = Integer.parseInt(request.getParameter("id"));
                    ClassSetting ClassSettingByID = dao.listAllByID(class_setting_id);

                    request.setAttribute("class_id", class_id);
                    request.setAttribute("ClassSettingByID", ClassSettingByID);
                    disPath(request, response, "/views/class_setting/ClassSettingDetail.jsp");

                } else {
                    String error_list = "";
                    try {
                        String class_setting_id = request.getParameter("class_setting_id");
                        String type_id = request.getParameter("type_id");
                        String type_title = request.getParameter("type_title");
                        String type_value = request.getParameter("type_value");
                        String color = request.getParameter("color");
                        String _class_id = request.getParameter("class_id");
                        String description = request.getParameter("description");
                        if (!StringValidation.checkName(type_title)) {
                            error_list = error_list + "|type_title";
                        }

                        if (!("".equals(error_list))) {
                            throw new Exception(error_list.substring(1));
                        }
                        ClassSetting temp = new ClassSetting();
                        temp.setClass_setting_id(Integer.parseInt(class_setting_id));
                        temp.setType_id(Integer.parseInt(type_id));
                        temp.setType_title(type_title);
                        temp.setType_value(Integer.parseInt(type_value));
                        temp.setColor(color);
                        temp.setDescription(description);
                        temp.setClass_id(Integer.parseInt(_class_id));
                        int n = dao.updateClassSetting(temp);
                        if (n == 0) {
                            out.print("fail");
                        } else {
                            out.print("success");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            if (service.equals("add")) {

                String error_list = "";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int class_id = Integer.parseInt(request.getParameter("class_id"));

                    request.setAttribute("class_id", class_id);
                    disPath(request, response, "/views/class_setting/AddClassSetting.jsp");
                } else {
                    try {
                        String type_id = request.getParameter("type_id");
                        String type_title = request.getParameter("type_title");
                        String color = request.getParameter("color");
                        String _class_id = request.getParameter("class_id");
                        String description = request.getParameter("description");
                        if (!StringValidation.checkName(type_title)) {
                            error_list = error_list + "|type_title";
                        }

                        if (!"".equals(error_list)) {
                            throw new Exception(error_list.substring(1));
                        }

                        ClassSetting cs = new ClassSetting();
                        cs.setType_id(Integer.parseInt(type_id));
                        cs.setType_title(type_title);
                        cs.setColor(color);
                        cs.setClass_id(Integer.parseInt(_class_id));
                        cs.setDescription(description);
                        int n = dao.addClassSetting(cs);

                        if (n == 0) {
                            out.print("fail");
                        } else {
                            out.print("success");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            if(service.equals("sync_labels")){
                int class_id = Integer.parseInt(request.getParameter("class_id"));
                DAOClass daoC = new DAOClass();
                HttpSession session = request.getSession();
                DAOUser daoU = new DAOUser();
                String token = daoU.getToken(user_id);
                entity.Class group_id = daoC.getClassById(class_id);
                int n = dao.syncClass(group_id.getGitlabID()+"", token, class_id);
                if (n !=0) {
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
