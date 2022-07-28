package controllers;

import dao.DAOTrackingUpdate;
import entity.Milestone;
import entity.TrackingUpdate;
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
 * @author Tuan
 */
@WebServlet(name = "TrackingUpdateController", urlPatterns = {"/trackingupdate"})
public class TrackingUpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("go");
        if (service == null) {
            service = "view";
        }
        DAOTrackingUpdate dao = new DAOTrackingUpdate();

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
                String sMilestone = request.getParameter("milestone");
                int milestone_id = (sMilestone == null || "".equals(sMilestone)) ? -1 : Integer.parseInt(sMilestone);

                String note = request.getParameter("search");
                note = (note == null || "".equals(note)) ? null : note;

                //get sorting filter request
                String str = "milestone.milestone_name|update.date";
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

                int tracking_id = Integer.parseInt(request.getParameter("tracking_id"));
                Vector<TrackingUpdate> trackingUpdates = dao.getTrackingUpdate(pageIndex, pageSize, tracking_id, milestone_id, note, filter_col, filter_type);
                int count = dao.countTrackingUpdate(tracking_id, milestone_id, note, filter_col, filter_type);
                Vector<Milestone> milestones = dao.getMilestoneOfTeam(tracking_id);
                request.setAttribute("trackingUpdates", trackingUpdates);
                request.setAttribute("assignee_name", dao.getAssignee_name(tracking_id));
                request.setAttribute("tracking_id", tracking_id);
                request.setAttribute("milestones", milestones);
                request.setAttribute("filter_types", filter_types);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("search_value", note);
                request.setAttribute("totalSize", (int) Math.ceil((double) count / pageSize));
                disPath(request, response, "views/tracking/TrackingUpdateList.jsp");
            }
            if (service.equals("update")) {
                int update_id = Integer.parseInt(request.getParameter("update_id"));
                String note = request.getParameter("note");
                if (!Validator.checkDescription(note)) {
                    out.print("long");
                } else {
                    TrackingUpdate temp = new TrackingUpdate();
                    temp.setUpdate_id(update_id);
                    temp.setNote(note);
                    int n = dao.updateTrackingUpdate(temp);
                    if (n != 0) {
                        out.print(temp.getNote());
                    } else {
                        out.print("database");
                    }
                }
            }
            if (service.equals("add")) {
                int tracking_id = Integer.parseInt(request.getParameter("tracking_id"));
                int milestone_id = Integer.parseInt(request.getParameter("milestone_id"));
                String note = request.getParameter("note");
                String date = request.getParameter("date");

                TrackingUpdate temp = new TrackingUpdate();
                temp.setTracking_id(tracking_id);
                temp.setMilestone_id(milestone_id);
                temp.setNote(note);
                if (!date.equals("")) {
                    temp.setDate(date);
                } else {
                    temp.setDate(java.time.LocalDate.now().toString());
                }
                int n = dao.addTrackingUpdate(temp);
                if (n != 0) {
                    out.print("success");
                } else {
                    out.print("database");
                }
            }
            if (service.equals("delete")) {
                int update_id = Integer.parseInt(request.getParameter("update_id"));
                int n = dao.deleteTrackingUpdate(update_id);
                if (n != 0) {
                    out.print("success");
                } else {
                    out.print("database");
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
