/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import dao.DAOClassUser;
import entity.ClassUser;
import dao.DAOFunction;
import entity.Function;
import entity.Class;
import entity.User;
import entity.Team;
import entity.Feature;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.SheetUtils;
import utils.Validator;

/**
 *
 * @author RxZ
 */
@WebServlet(name = "FunctionController", urlPatterns = {"/function"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 2, // 2MB
        maxRequestSize = 1024 * 1024 * 2) // 2MB

public class FunctionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // View - view, add, edit
        try {
            User currentUser = LoginController.getLoginCookie(request);
            String action = request.getParameter("go");
            if (action == null || "".equals(action.trim())) {
                action = "view";
            }
            // View list of users
            if ("view".equals(action)) {
                int pageIndex, pageSize, totalItems;
                String sPageIndex = request.getParameter("page");
                String sPageSize = request.getParameter("size");

                String sStatus = request.getParameter("status");
                int status = (sStatus == null || "".equals(sStatus)) ? -1 : Integer.parseInt(sStatus);

                String sName = request.getParameter("search");
                sName = (sName == null || "".equals(sName)) ? null : sName;

                String sSubject = request.getParameter("subject");
                int iSubject = (sSubject == null || "".equals(sSubject)) ? -1 : Integer.parseInt(sSubject);

                String sClass = request.getParameter("class");
                int iClass = (sClass == null || "".equals(sClass)) ? -1 : Integer.parseInt(sClass);

                String sTeam = request.getParameter("team");
                int iTeam = (sTeam == null || "".equals(sTeam)) ? -1 : Integer.parseInt(sTeam);

                String sFeature = request.getParameter("feature");
                int iFeature = (sFeature == null || "".equals(sFeature)) ? -1 : Integer.parseInt(sFeature);

                pageIndex = (sPageIndex == null || "".equals(sPageIndex)) ? 1 : Integer.parseInt(sPageIndex);
                pageSize = (sPageSize == null || "".equals(sPageSize)) ? 15 : Integer.parseInt(sPageSize);
                if (pageIndex <= 0) {
                    pageIndex = 1;
                }
                if (pageSize <= 0) {
                    pageSize = 15;
                }

                totalItems = DAOFunction.countFunctions(currentUser.getUser_id(), currentUser.getRole_id(), status, iSubject, iClass, iTeam, iFeature, sName);

                int startIdx = 1;
                if (pageIndex == 1) {
                } else {
                    startIdx = pageIndex - 1;
                    startIdx = startIdx * pageSize + 1;
                }

                ArrayList<Function> dummies = DAOFunction.getFunctions(startIdx, pageSize, currentUser.getUser_id(), currentUser.getRole_id(), status, iSubject, iClass, iTeam, iFeature, sName);
                for (Function d : dummies) {
                    System.out.println(d);
                }

                // for table
                request.setAttribute("dummies", dummies);

                Team curTeam = DAOFunction.getCurrentTeam(iTeam);
                request.setAttribute("curTeam", curTeam); 
                
                // for filter
                request.setAttribute("classes", DAOFunction.getClass(currentUser.getUser_id(), currentUser.getRole_id(), iSubject));
                request.setAttribute("subjects", DAOFunction.getSubject(currentUser.getUser_id(), currentUser.getRole_id()));
                request.setAttribute("teams", DAOFunction.getTeamByClassId(currentUser.getUser_id(), currentUser.getRole_id(), iClass));
                request.setAttribute("features", DAOFunction.getFeatureByTeamId(iTeam));
                request.setAttribute("func_status", DAOFunction.getFunctionStatus());
                request.setAttribute("features_jso", DAOFunction.getBaseFeatureJso(currentUser.getUser_id(), currentUser.getRole_id()));

                // for paginating
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalSize", (int) Math.ceil((double) totalItems / pageSize));
                request.getRequestDispatcher("/views/function/FunctionList.jsp").forward(request, response);

            }

            // add screen
            if ("add".equals(action)) {
                request.setAttribute("features", DAOFunction.getBaseFeatureJso(currentUser.getUser_id(), currentUser.getRole_id()));
                request.setAttribute("func_status", DAOFunction.getFunctionStatus());
                request.getRequestDispatcher("/views/function/FunctionAdd.jsp").forward(request, response);
            }

            // detail screen
            if ("edit".equals(action)) {
                String sFuncId = request.getParameter("id");
                int funcId = (sFuncId == null || "".equals(sFuncId)) ? -1 : Integer.parseInt(sFuncId);
                Function cur = DAOFunction.getFunctionById(currentUser.getUser_id(), currentUser.getRole_id(), funcId);
                request.setAttribute("current", cur);
                request.setAttribute("teams", DAOFunction.getTeamByClassId(currentUser.getUser_id(), currentUser.getRole_id(), cur.getClassId()));
                request.setAttribute("features", DAOFunction.getFeatureByTeamId(cur.getTeamId()));
                request.setAttribute("owner_list", DAOFunction.getOwnerList(cur.getClassId(), cur.getTeamId()));
                request.setAttribute("func_status", DAOFunction.getFunctionStatus());
                request.getRequestDispatcher("/views/function/FunctionDetail.jsp").forward(request, response);
            }

            // Download xlsx file
            if ("export".equals(action)) {
                String sSubjectId = request.getParameter("subject");
                int subjectId = (sSubjectId == null || "".equals(sSubjectId)) ? -1 : Integer.parseInt(sSubjectId);
                String sClassId = request.getParameter("class");
                int classId = (sClassId == null || "".equals(sClassId)) ? -1 : Integer.parseInt(sClassId);
                String sTeamId = request.getParameter("team");
                int teamId = (sTeamId == null || "".equals(sTeamId)) ? -1 : Integer.parseInt(sTeamId);
                String sFeatureId = request.getParameter("feature");
                int feId = (sFeatureId == null || "".equals(sFeatureId)) ? -1 : Integer.parseInt(sFeatureId);
                ArrayList<Function> dummies = DAOFunction.getFunctions(1, 99999, currentUser.getUser_id(), currentUser.getRole_id(), -1, -1, -1, teamId, feId, null);
                String baseName = "";
                if (dummies.size() > 0) {
                    Function dim = (Function) dummies.get(0);
//                    baseName = dim.getSubjectCode() + "_" + dim.getClassCode() + "_" + dim.getTeamName() + "_" + dim.getFeatureName();
                    baseName = dim.getClassCode() + "_" + dim.getTeamName() + "_" + dim.getFeatureName();
                }

                baseName = baseName.toUpperCase();

                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + baseName + "_FUNCS.xlsx");

                SheetUtils.exportFuncXLSX(baseName, dummies, DAOFunction.getOwnerList(classId, teamId), DAOFunction.getFunctionStatus(), DAOFunction.getFeatureByTeamId(teamId), subjectId, classId, teamId, feId, response.getOutputStream());

            }

            throw new Exception("no_action");

        } catch (Exception e) {
//            response.getWriter().print(e.getMessage());
            //response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User currentUser = LoginController.getLoginCookie(request);

            // parse all params and validate
            String action = request.getParameter("go");

            if ("class_by_subject".equals(action)) {
                int cid = Integer.parseInt(request.getParameter("subject"));
                ArrayList<Class> cll = DAOFunction.getClass(currentUser.getUser_id(), currentUser.getRole_id(), cid);
                String out = "";
                for (Class cls : cll) {
                    out += "|" + cls.getClass_id() + "#" + cls.getClass_code();
                }
                response.getWriter().print("success: " + out.substring(1));
                return;
            }

            if ("team_by_class".equals(action)) {
                int cid = Integer.parseInt(request.getParameter("class"));
                ArrayList<Team> cll = DAOFunction.getTeamByClassId(currentUser.getUser_id(), currentUser.getRole_id(), cid);
                String out = "";
                for (Team cls : cll) {
                    out += "|" + cls.getTeam_id() + "#" + cls.getTeam_name();
                }
                response.getWriter().print("success: " + out.substring(1));
                return;
            }

            if ("feature_by_team".equals(action)) {
                int cid = Integer.parseInt(request.getParameter("team"));
                ArrayList<Feature> cll = DAOFunction.getFeatureByTeamId(cid);
                String out = "";
                for (Feature cls : cll) {
                    out += "|" + cls.getFeature_id() + "#" + cls.getFeature_name();
                }
                response.getWriter().print("success: " + out.substring(1));
                return;
            }

            if ("owner_list".equals(action)) {
                int cid = Integer.parseInt(request.getParameter("class"));
                int tid = Integer.parseInt(request.getParameter("team"));
                ArrayList<User> cll = DAOFunction.getOwnerList(cid, tid);
                String out = "";
                for (User cls : cll) {
                    out += "|" + cls.getUser_id() + "#" + cls.getRoll_number() + "#" + cls.getFull_name();
                }
                response.getWriter().print("success: " + out.substring(1));
                return;
            }

            if ("import".equals(action)) {
                System.out.println("IMPORT XLSX===============================================");
                Part filePart = request.getPart("file");
                ArrayList<Function> clList = SheetUtils.importFuncXLSX(filePart.getInputStream());
                String result = DAOFunction.UpdateFromList(currentUser.getUser_id(), currentUser.getRole_id(), clList);
                response.getWriter().print(result);
                return;
            }

            if ("add".equals(action)) {
                try {
                    System.out.println("ADD");
                    String error_list = "";
                    // parse all params and validate
                    // 'name=&access=&priority=1&complexity=1&status=11&desc=&subject=2&class=1&team=1&feature=1&owner=9'
                    String sName = request.getParameter("name");
                    String sAccess = request.getParameter("access");
                    String sPriority = request.getParameter("priority");
                    String sComplexity = request.getParameter("complexity"); //i
                    String sStatus = request.getParameter("status"); //i
                    String desc = request.getParameter("desc");
                    String subject = request.getParameter("subject"); //i
                    String sClass = request.getParameter("class"); //i
                    String sTeam = request.getParameter("team"); //i
                    String sFeature = request.getParameter("feature"); //i
                    String sOwner = request.getParameter("owner"); //i

                    // validate string before
                    if (subject == null || "".equals(subject)) {
                        error_list += "|subject";
                    }
                    if (sClass == null || "".equals(sClass)) {
                        error_list += "|class";
                    }
                    if (sTeam == null || "".equals(sTeam)) {
                        error_list += "|team";
                    }
                    if (sFeature == null || "".equals(sFeature)) {
                        error_list += "|feature";
                    }
                    if (sOwner == null || "".equals(sOwner)) {
                        error_list += "|owner";
                    }

                    if (desc.length() < 0 || desc.length() >= 100) {
                        error_list += "|desc";
                    }

                    if (sAccess != null && sAccess.length() >= 100) {
                        error_list += "|access";
                    } else sAccess = "";

                    if (sName.length() <= 0 || sName.length() >= 100) {
                        error_list += "|name";
                    }

                    if (!"".equals(error_list)) {
                        throw new Exception(error_list.substring(1));
                    }

                    // validate number before
                    int complexity = Integer.parseInt(sComplexity);
                    int priority = Integer.parseInt(sPriority);
                    int feature = Integer.parseInt(sFeature);
                    int team = Integer.parseInt(sTeam);
                    int owner = Integer.parseInt(sOwner);
                    int status = Integer.parseInt(sStatus);

                    if (complexity > 3 || complexity < 1) {
                        error_list += "|complexity";
                    }

                    if (priority <= 0) {
                        error_list += "|priority";
                    }

                    // update team leader
                    Function fu = new Function();
                    fu.setName(sName);
                    fu.setAccessRoles(sAccess);
                    fu.setPriority(sPriority);
                    fu.setComplexityId(complexity);
                    fu.setStatusId(status);
                    fu.setDescription(desc);
                    fu.setTeamId(team);
                    fu.setFeatureId(feature);
                    fu.setOwnerId(owner);

                    String getOk = DAOFunction.getOk(currentUser.getUser_id(), currentUser.getRole_id(), owner, team, feature, -1);
                    if (!"success".equals(getOk)) {
                        throw new Exception(getOk);
                    }
                    if (DAOFunction.insertNewFunction(fu) != 0) {
                        response.getWriter().print("success");
                        return;
                    } else {
                        throw new Exception("database");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().print("error: " + e.getMessage());
                    return;
                }
            }

            if ("edit".equals(action)) {
                try {
                    System.out.println("EDIT");
                    String error_list = "";
                    // parse all params and validate
                    // 'name=&access=&priority=1&complexity=1&status=11&desc=&subject=2&class=1&team=1&feature=1&owner=9'
                    String sId = request.getParameter("id");
                    String sName = request.getParameter("name");
                    String sAccess = request.getParameter("access");
                    String sPriority = request.getParameter("priority");
                    String sComplexity = request.getParameter("complexity"); //i
                    String sStatus = request.getParameter("status"); //i
                    String desc = request.getParameter("desc");
                    String subject = request.getParameter("subject"); //i
                    String sClass = request.getParameter("class"); //i
                    String sTeam = request.getParameter("team"); //i
                    String sFeature = request.getParameter("feature"); //i
                    String sOwner = request.getParameter("owner"); //i

                    // validate string before
                    if (sClass == null || "".equals(sClass)) {
                        error_list += "|class";
                    }
                    if (sTeam == null || "".equals(sTeam)) {
                        error_list += "|team";
                    }
                    if (sFeature == null || "".equals(sFeature)) {
                        error_list += "|feature";
                    }
                    if (sOwner == null || "".equals(sOwner)) {
                        error_list += "|owner";
                    }

                    if (desc.length() < 0 || desc.length() >= 100) {
                        error_list += "|desc";
                    }

                    if (sAccess != null && sAccess.length() >= 100) {
                        error_list += "|access";
                    } else sAccess = "";

                    if (sName.length() <= 0 || sName.length() >= 100) {
                        error_list += "|name";
                    }

                    if (!"".equals(error_list)) {
                        throw new Exception(error_list.substring(1));
                    }

                    // validate number before
                    int complexity = Integer.parseInt(sComplexity);
                    int priority = Integer.parseInt(sPriority);
                    int feature = Integer.parseInt(sFeature);
                    int team = Integer.parseInt(sTeam);
                    int owner = Integer.parseInt(sOwner);
                    int status = Integer.parseInt(sStatus);
                    int id = Integer.parseInt(sId);

                    if (complexity > 3 || complexity < 1) {
                        error_list += "|complexity";
                    }

                    if (priority <= 0) {
                        error_list += "|priority";
                    }

                    // update team leader
                    Function fu = new Function();
                    fu.setName(sName);
                    fu.setAccessRoles(sAccess);
                    fu.setPriority(sPriority);
                    fu.setComplexityId(complexity);
                    fu.setStatusId(status);
                    fu.setDescription(desc);
                    fu.setTeamId(team);
                    fu.setFeatureId(feature);
                    fu.setOwnerId(owner);

                    String getOk = DAOFunction.getOk(currentUser.getUser_id(), currentUser.getRole_id(), owner, team, feature, id);
                    if (!"success".equals(getOk)) {
                        throw new Exception(getOk);
                    }
                    if (DAOFunction.updateFunction(id, fu) != 0) {
                        response.getWriter().print("success");
                        return;
                    } else {
                        throw new Exception("database");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().print("error: " + e.getMessage());
                    return;
                }
            }
            
            if ("function_sync".equals(action)) {
                int team_id = Integer.parseInt(request.getParameter("team_id"));
                int n = DAOFunction.syncFunction(team_id);
                if (n == 1) {
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("some thing wrong");
                }
                return;
            }

            throw new Exception("no_action");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
