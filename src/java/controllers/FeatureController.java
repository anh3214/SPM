/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import dao.DAOClass;
import dao.DAOFeature;
import entity.Feature;
import entity.Team;
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
@WebServlet(name = "FeatureController", urlPatterns = {"/feature"})
public class FeatureController extends HttpServlet {

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

        DAOFeature dao = new DAOFeature();
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

                String feature_name = request.getParameter("feature_nameSearch");
                feature_name = (feature_name == null || "".equals(feature_name)) ? null : feature_name;

                String subject = request.getParameter("subject");
                subject = (subject == null || "-1".equals(subject)) ? null : subject;

                String classs = request.getParameter("class_id");
                int class_id = (classs == null || "".equals(classs)) ? -1 : Integer.parseInt(classs);

                //get sorting filter request
                String str = "feature.feature_id|feature.feature_name|feature.team_id|class.class_code|subject.subject_code|team.topic_code";
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
                int countFeature = dao.countFeatures(u_id, role, team_id, status, feature_name, subject);

                Vector<Feature> features = dao.getFeatures(u_id, role, team_id, pageIndex, pageSize, status, feature_name, subject, filter_col, filter_type);
                Vector<String> listSubject = dao.getSubject(u_id, role);
                Vector<String> listClass = dao.getClass(u_id, role);
                Vector<String> listTeamID = dao.listTeamID(u_id);

                DAOClass classDao = new DAOClass();
                Vector<Team> teams = classDao.queryTeamByClass(class_id, u_id, role);

                request.setAttribute("class_id", class_id);
                request.setAttribute("teams", teams);
                request.setAttribute("features", features);
                request.setAttribute("team_id", team_id);
                request.setAttribute("filter_types", filter_types);
                request.setAttribute("search_value", feature_name);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalSize", (int) Math.ceil((double) countFeature / pageSize));
                disPath(request, response, "/views/feature/DisplayFeatureList.jsp");
            }

            if (service.equals("getTeam")) {
                try {
                    DAOClass classDao = new DAOClass();
                    int class_id = Integer.parseInt(request.getParameter("class_id"));
                    Vector<Team> teams = classDao.queryTeamByClass(class_id, u_id, role);
                    for (int i = 0; i < teams.size(); i++) {
                        out.print("<li><a class=\"dropdown-item\" onclick=\"urlSearch('team_id', " + teams.get(i).getTeam_id() + ")\">" + teams.get(i).getTeam_name() + "</a></li>");
                    }
                } catch (Exception e) {

                }
            }

            if (service.equals("updateStatus")) {
                int featureID = Integer.parseInt(request.getParameter("feature_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateFeature(featureID, status);
                if (n == 0) {
                    out.print("fail");
                } else {
                    out.print("success");
                }
            }
            if (service.equals("edit")) {
                if (role != 4) {
                    response.sendError(403);
                } else {
                    String error_list = "";
                    int team_id = Integer.parseInt(request.getParameter("team_id"));
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        int feature_id = Integer.parseInt(request.getParameter("feature_id"));
                        Feature feature = dao.listAllByID(feature_id, u_id);
                        request.setAttribute("team_id", team_id);
                        request.setAttribute("feature", feature);
                        disPath(request, response, "/views/feature/DisplayFeatureDetail.jsp");

                    } else {
                        try {
                            String feature_id = request.getParameter("feature_id");
                            String feature_name = request.getParameter("feature_name");
                            String status = request.getParameter("status") == null ? "-1" : request.getParameter("status");
                            String description = request.getParameter("description");
                            if (!Validator.checkName(feature_name)) {
                                error_list = error_list + "|feature_name";
                            }

                            if (!Validator.checkStatus(status)) {
                                error_list = error_list + "|status";
                            }

                            if (!"".equals(error_list)) {
                                throw new Exception(error_list.substring(1));
                            }

                            int n = dao.updateFeature(new Feature(Integer.parseInt(feature_id), team_id, feature_name, Integer.parseInt(status), description));
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
            if (service.equals("add")) {
                if (role != 4) {
                    response.sendError(403);
                } else {
                    String error_list = "";
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        int team_id = Integer.parseInt(request.getParameter("team_id"));
                        request.setAttribute("team_id", team_id);
                        disPath(request, response, "/views/feature/AddFeature.jsp");
                    } else {
                        try {
                            int team_id = Integer.parseInt(request.getParameter("team_id"));
                            String feature_name = request.getParameter("feature_name");
                            String status = request.getParameter("status") == null ? "-1" : request.getParameter("status");
                            String description = request.getParameter("description");
                            if (!Validator.checkName(feature_name)) {
                                error_list = error_list + "|feature_name";
                            }

                            if (!Validator.checkStatus(status)) {
                                error_list = error_list + "|status";
                            }

                            if (!"".equals(error_list)) {
                                throw new Exception(error_list.substring(1));
                            }

                            Feature s = new Feature();
                            s.setTeam_id(team_id);
                            s.setFeature_name(feature_name);
                            s.setStatus(Integer.parseInt(status));
                            s.setDescription(description);
                            int n = dao.addFeature(s);

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
