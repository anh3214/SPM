/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.auth.LoginController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.ClassUser;
import dao.DAOClassUser;
import dao.DAOFunction;
import entity.User;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;
import utils.SheetUtils;
import javax.servlet.http.Part;
import utils.Validator;

/**
 *
 * @author RxZ
 */
@WebServlet(name = "ClassUserController", urlPatterns = {"/class_user"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 2, // 2MB
        maxRequestSize = 1024 * 1024 * 2) // 2MB
public class ClassUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // View - view, add, edit
        try {
            User currentUser = LoginController.getLoginCookie(request);
           
            
            String action = request.getParameter("go");

            // View list of users
            if ("view".equals(action) || "view_adv".equals(action)) {
                int pageIndex, pageSize, totalItems;
                String sPageIndex = request.getParameter("page");
                String sPageSize = request.getParameter("size");

                String sClassId = request.getParameter("class_id");
                int classId = (sClassId == null || "".equals(sClassId)) ? -1 : Integer.parseInt(sClassId);
               
                String sTeamId = request.getParameter("team_id");
                int teamId = (sTeamId == null || "".equals(sTeamId)) ? -1 : Integer.parseInt(sTeamId);

                String sStatus = request.getParameter("status");
                int status = (sStatus == null || "".equals(sStatus)) ? -1 : Integer.parseInt(sStatus);

                String sName = request.getParameter("search");
                sName = (sName == null || "".equals(sName)) ? null : sName;

                pageIndex = (sPageIndex == null || "".equals(sPageIndex)) ? 1 : Integer.parseInt(sPageIndex);
                pageSize = (sPageSize == null || "".equals(sPageSize)) ? 15 : Integer.parseInt(sPageSize);
                if (pageIndex <= 0) {
                    pageIndex = 1;
                }
                if (pageSize <= 0) {
                    pageSize = 15;
                }

                totalItems = DAOClassUser.queryClassUsersCount(currentUser.getUser_id(), currentUser.getRole_id(), classId, teamId, status, sName);

                int startIdx = 1;
                if (pageIndex == 1) {
                } else {
                    startIdx = pageIndex - 1;
                    startIdx = startIdx * pageSize + 1;
                }

                ArrayList<ClassUser> dummies = DAOClassUser.queryClassUsers(currentUser.getUser_id(), currentUser.getRole_id(), startIdx, pageSize, classId, teamId, status, sName);
                for (ClassUser d : dummies) {
                    System.out.println(d);
                }
                entity.Class cl = DAOClassUser.getClassById(classId);
                request.setAttribute("classes", DAOFunction.getClass(currentUser.getUser_id(), currentUser.getRole_id(), -1));
                request.setAttribute("cname", cl.getClass_code());
                request.setAttribute("cid", classId);
                request.setAttribute("dummies", dummies);
                request.setAttribute("team_dummies", DAOClassUser.queryAllTeamByClassId(classId));
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalSize", (int) Math.ceil((double) totalItems / pageSize));
                if (currentUser.getRole_id() == 3) {
                if ("view".equals(action)) {
                    request.getRequestDispatcher("/views/class_user/ClassUserListNorm.jsp").forward(request, response);
                }
                if ("view_adv".equals(action)) {
                    request.getRequestDispatcher("/views/class_user/ClassUserList.jsp").forward(request, response);
                }}
                else request.getRequestDispatcher("/views/class_user/ClassUserList.jsp").forward(request, response);
            }

            //  edit screen
            if ("edit".equals(action) && currentUser.getRole_id() == 3) {
                String sClassId = request.getParameter("class_id");
                int classId = (sClassId == null || "".equals(sClassId)) ? -1 : Integer.parseInt(sClassId);

                String sTeamId = request.getParameter("team_id");
                int teamId = (sTeamId == null || "".equals(sTeamId)) ? -1 : Integer.parseInt(sTeamId);

                String sUserId = request.getParameter("user_id");
                int userId = (sUserId == null || "".equals(sUserId)) ? -1 : Integer.parseInt(sUserId);

                ClassUser cur = DAOClassUser.getClassUser(classId, teamId, userId);
                request.setAttribute("dummy", cur);
                request.setAttribute("team_dummies", DAOClassUser.queryAllTeamByClassId(classId));
                request.getRequestDispatcher("/views/class_user/ClassUserDetail.jsp").forward(request, response);
            }

            // add screen
            if ("add".equals(action)&& currentUser.getRole_id() == 3) {
                String sClassId = request.getParameter("class_id");
                int classId = (sClassId == null || "".equals(sClassId)) ? -1 : Integer.parseInt(sClassId);

                entity.Class cl = DAOClassUser.getClassById(classId);
                request.setAttribute("dummy", cl);
                request.setAttribute("team_dummies", DAOClassUser.queryAllTeamByClassId(classId));
                request.getRequestDispatcher("/views/class_user/ClassUserAdd.jsp").forward(request, response);
            }

            // Download xlsx file
            if ("export".equals(action)) {
                String sClassId = request.getParameter("class_id");
                int classId = (sClassId == null || "".equals(sClassId)) ? -1 : Integer.parseInt(sClassId);
                entity.Class cl = DAOClassUser.getClassById(classId);
                if (cl == null) {
                    response.sendError(404);
                }
                ArrayList<ClassUser> dummies = DAOClassUser.queryClassUsers(currentUser.getUser_id(), currentUser.getRole_id(), 1, 99999, classId, -1, -1, null);
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + cl.getClass_code() + "_users.xlsx");

                SheetUtils.exportXLSX(cl.getClass_code(), dummies, DAOClassUser.queryAllTeamByClassId(classId), response.getOutputStream());
//                return;
                
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
            if (currentUser.getRole_id() != 3) {
                response.sendError(403);
            }
            // parse all params and validate
            String action = request.getParameter("go");
            if ("update_status".equals(action)) {
                int uid = Integer.parseInt(request.getParameter("uid"));
                int cid = Integer.parseInt(request.getParameter("cid"));
                int tid = Integer.parseInt(request.getParameter("tid"));
                int status = Integer.parseInt(request.getParameter("status"));
                if (status != 1 && status != 0) {
                    throw new Exception("invalid_status");
                }
                if (DAOClassUser.updateStatus(uid, cid, tid, status)) {
                    response.getWriter().print("success");
                    return;
                } else {
                    throw new Exception("database");

                }
            }

            if ("import".equals(action)) {
                System.out.println("IMPORT XLSX===============================================");
                Part filePart = request.getPart("file");
                int cid = Integer.parseInt(request.getParameter("cid"));
                ArrayList<ClassUser> clList = SheetUtils.importXLSX(filePart.getInputStream());
                int successCount = DAOClassUser.insertOrUpdateFromList(cid, clList);
                response.getWriter().print("success: " + successCount);
                return;
            }

            if ("find".equals(action)) {
                String email = request.getParameter("email_found"); //i
                User ue = DAOClassUser.getUserByEmail(email);
                if (ue == null) {
                    throw new Exception("no_account");
                }
                response.getWriter().print(ue.getUser_id() + "#" + ue.getRoll_number() + " - " + ue.getFull_name());
                return;
            }

            if ("delete".equals(action)) {
                int successCount = 0;
                String[] uidl = request.getParameter("params").split("\\|");
                for (String u : uidl) {
                    System.out.println(u);
                    String[] sl = u.split("-");
                    int uid = Integer.parseInt(sl[0]);
                    int cid = Integer.parseInt(sl[1]);
                    int tid = Integer.parseInt(sl[2]);
                    successCount += DAOClassUser.deleteClassUser(uid, cid, tid);
                }

                response.getWriter().print("success: " + successCount);
                return;
            }

            if ("edit".equals(action)) {
                try {
                    System.out.println("EDIT");
                    String error_list = "";
                    // parse all params and validate
                    // 'user_id=19&class_id=1&team_id=2&current_team_id=2&status=on&is_leader=on&onging_eval=5.0&final_pres_eval=5.0&final_topic_eval=5.0&dropout=&user_notes=note'
                    String sUserId = request.getParameter("user_id"); //i
                    String sClassId = request.getParameter("class_id"); //i
                    String sCTeamId = request.getParameter("current_team_id"); //i
                    String sNewTeamId = request.getParameter("team_id"); //i
                    int status = request.getParameter("status") != null && "on".equals(request.getParameter("status")) ? 1 : 0;
                    int teamLeader = request.getParameter("is_leader") != null && "on".equals(request.getParameter("is_leader")) ? 1 : 0;
                    String onGoing = request.getParameter("onging_eval");
                    String finalPres = request.getParameter("final_pres_eval");;
                    String finalTopic = request.getParameter("final_topic_eval");
                    String dDate = request.getParameter("dropout");
                    String uNotes = request.getParameter("user_notes");

                    if (Double.parseDouble(onGoing) > 10 || Double.parseDouble(onGoing) < 0) {
                        error_list += "|onging_eval";
                    }

                    if (Double.parseDouble(finalPres) > 10 || Double.parseDouble(finalPres) < 0) {
                        error_list += "|final_pres_eval";
                    }

                    if (Double.parseDouble(finalTopic) > 10 || Double.parseDouble(finalTopic) < 0) {
                        error_list += "|final_topic_eval";
                    }

                    if (uNotes.length() > 100 || uNotes.length() <= 0) {
                        error_list += "|user_notes";
                    }

                    if (!Validator.checkDate(dDate)) {
                        error_list += "|dropout";
                    }

                    if (!"".equals(error_list)) {
                        throw new Exception(error_list.substring(1));
                    }

                    java.sql.Date dopDate = java.sql.Date.valueOf(dDate);

                    int userId = Integer.parseInt(sUserId);
                    int classId = Integer.parseInt(sClassId);
                    int cTeamId = Integer.parseInt(sCTeamId);
                    int newTeamId = Integer.parseInt(sNewTeamId);

                    // update team leader
                    if (teamLeader == 1) {
                        DAOClassUser.removeTeamLeader(classId, newTeamId);
                    }

                    ClassUser cu = new ClassUser(-1, newTeamId, userId, teamLeader, dopDate, uNotes, onGoing, finalPres, finalTopic, status, null, null, null, null, null, null);
                    if (DAOClassUser.updateClassUser(classId, userId, cu) != 0) {
                        DAOClassUser.setTeamLeader(classId, cTeamId, userId);
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

            if ("add".equals(action)) {
                try {
                    System.out.println("ADD");
                    String error_list = "";
                    // parse all params and validate
                    // 'user_id=19&class_id=1&team_id=2&current_team_id=2&status=on&is_leader=on&onging_eval=5.0&final_pres_eval=5.0&final_topic_eval=5.0&dropout=&user_notes=note'
                    String sUserId = request.getParameter("user_id"); //i
                    String sClassId = request.getParameter("class_id"); //i
                    String sNewTeamId = request.getParameter("team_id"); //i
                    int status = request.getParameter("status") != null && "on".equals(request.getParameter("status")) ? 1 : 0;
                    int teamLeader = request.getParameter("is_leader") != null && "on".equals(request.getParameter("is_leader")) ? 1 : 0;
                    String onGoing = request.getParameter("onging_eval");
                    String finalPres = request.getParameter("final_pres_eval");;
                    String finalTopic = request.getParameter("final_topic_eval");
                    String dDate = request.getParameter("dropout");
                    String uNotes = request.getParameter("user_notes");

                    if (Double.parseDouble(onGoing) > 10 || Double.parseDouble(onGoing) < 0) {
                        error_list += "|onging_eval";
                    }

                    if (Double.parseDouble(finalPres) > 10 || Double.parseDouble(finalPres) < 0) {
                        error_list += "|final_pres_eval";
                    }

                    if (Double.parseDouble(finalTopic) > 10 || Double.parseDouble(finalTopic) < 0) {
                        error_list += "|final_topic_eval";
                    }

                    if (uNotes.length() > 100 || uNotes.length() <= 0) {
                        error_list += "|user_notes";
                    }

                    if (!Validator.checkDate(dDate)) {
                        error_list += "|dropout";
                    }

                    if (!"".equals(error_list)) {
                        throw new Exception(error_list.substring(1));
                    }

                    java.sql.Date dopDate = java.sql.Date.valueOf(dDate);

                    int userId = Integer.parseInt(sUserId);
                    int classId = Integer.parseInt(sClassId);
                    int newTeamId = Integer.parseInt(sNewTeamId);

                    // update team leader
                    int n = 0;
                    ClassUser cu = new ClassUser(classId, newTeamId, userId, teamLeader, dopDate, uNotes, onGoing, finalPres, finalTopic, status, null, null, null, null, null, null);
                    if (DAOClassUser.cuExists(userId, classId)) {
                        n += DAOClassUser.updateClassUser(cu);
                    } else {
                        n += DAOClassUser.insertNewClassUser(cu);
                    }
                    response.getWriter().print("success: " + n);
                    return;

                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().print("error: " + e.getMessage());
                    return;
                }
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
