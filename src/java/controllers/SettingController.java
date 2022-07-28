package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controllers.auth.LoginController;
import dao.DAOSetting;
import entity.Setting;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ptuan
 */
@WebServlet(name = "SettingController", urlPatterns = {"/setting"})
public class SettingController extends HttpServlet {

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
        String service = request.getParameter("action");
        try ( PrintWriter out = response.getWriter()) {
            if (null == service) {
                settingList(request, response);
            } else {
                switch (service) {
                    case "add":
                        addSetting(request, response);
                        break;
                    case "edit":
                        settingDetails(request, response);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void addSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message;
        DAOSetting dao = new DAOSetting();
        String submit = request.getParameter("submit");
        if (submit == null) {
            Vector<String> s = dao.viewAllType();
            request.setAttribute("list", s);
            dispath(request, response, "/views/setting/AddSetting.jsp");
        } else {
            int type_id = Integer.parseInt(request.getParameter("type"));
            String setting_title = request.getParameter("name");
            String setting_value = request.getParameter("value");
            String display_order = request.getParameter("order");
            int status = Integer.parseInt(request.getParameter("status"));

            Setting s = new Setting();

            s.setType_id(type_id);
            s.setSetting_tile(setting_title);
            s.setSetting_value(setting_value);
            s.setDisplay_order(display_order);
            s.setStatus(status);
            int n = dao.addSetting(s);
            if (n == 0) {
                message = "Add fail!";
                request.setAttribute("message", message);
                dispath(request, response, "/views/setting/AddSetting.jsp");
            } else {
                message = "Add Successfully!";
                Vector<String> s1 = dao.viewAllType();
                request.setAttribute("list", s1);
                request.setAttribute("message", message);
                dispath(request, response, "/views/setting/AddSetting.jsp");
            }
        }
    }

    public void settingDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message;
        DAOSetting dao = new DAOSetting();
        String submit = request.getParameter("submit");
        if (submit == null) {
            int setting_id = Integer.parseInt(request.getParameter("setting_id"));
            Setting sec = dao.listAll(setting_id);
            Vector<String> s = dao.viewAllType();
            request.setAttribute("list", s);
            request.setAttribute("sec", sec);
            dispath(request, response, "/views/setting/DisplaySettingDetail.jsp");
        } else {
            int type_id = Integer.parseInt(request.getParameter("type"));
            int setting_id = Integer.parseInt(request.getParameter("setting_id"));
            String display_order = request.getParameter("order");
            String setting_title = request.getParameter("name");
            String setting_value = request.getParameter("value");
            int status = Integer.parseInt(request.getParameter("status"));
            Setting s = new Setting(setting_id, type_id, setting_title, setting_value, display_order, status);
            int n = dao.updateSetting(s);
            if (n == 0) {
                message = "Update fail!";
                request.setAttribute("message", message);
                dispath(request, response, "/views/setting/DisplaySettingDetail.jsp");
            } else {
                message = "Update Successfully!";
                Vector<String> s1 = dao.viewAllType();
                request.setAttribute("list", s1);
                request.setAttribute("message", message);
                dispath(request, response, "/views/setting/DisplaySettingDetail.jsp");
            }
        }
    }

    public void settingList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int first, last, pages, total, back, next, loop;
        HttpSession session = request.getSession();
        dao.DAOSetting dao = new DAOSetting();
        String service = request.getParameter("go");
        User u = LoginController.getLoginCookie(request);
        if (u.getRole_id() != 1) {
            response.sendError(403);
        }
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String name_type;
                pages = (request.getParameter("pages") != null) ? (int) Integer.parseInt(request.getParameter("pages")) : 1;
                back = (pages == 1) ? 1 : pages - 1;
                total = dao.getCountSetting();

                if (total <= 10) {
                    first = 0;
                    last = total;
                } else {
                    first = (pages - 1) * 10;
                    last = 10;
                }

                loop = getMaxPage(total, pages);
                next = getNext(total, pages);

                Vector<Setting> vector = dao.getSetting(first, last);
                Vector<String> vector2 = dao.viewAllType();
                // pre some other data
                for (String object : vector2) {
                    name_type = getServletContext().getInitParameter(object);
                    session.setAttribute(object.trim(), name_type);
                }
                request.setAttribute("pre", back);
                request.setAttribute("next", next);
                request.setAttribute("page", pages);
                request.setAttribute("maxpage", loop);
                request.setAttribute("total", total);
                request.setAttribute("list", vector);
                request.setAttribute("list2", vector2);
                // select jsp
                dispath(request, response, "/views/setting/Setting.jsp");
            }
        }
        
        if (service.equals("delete")) {
            String ID = request.getParameter("ID");
            String status = request.getParameter("status");
            int statuss = Integer.parseInt(status);
            int id = Integer.parseInt(ID);
            int n = dao.deleteSetting(id, statuss);
            if (n > 0) {
                response.getWriter().print("success");
                return;
            } else {
                response.getWriter().print("AAA");
            }
        }
        
        if (service.equals("search")) {
            String get = request.getParameter("tye");
            pages = (request.getParameter("pages") != null) ? (int) Integer.parseInt(request.getParameter("pages")) : 1;
            back = (pages == 1) ? 1 : pages - 1;
            int getty = Integer.parseInt(get);

            total = dao.countSettingType(getty);
            if (total <= 10) {
                first = 0;
                last = total;
            } else {
                first = (pages - 1) * 10;
                last = 10;
            }
            loop = getMaxPage(total, pages);
            next = getNext(total, pages);
            //Lap so pages
            Vector<Setting> vector = dao.readSettingType(getty, first, last);
            Vector<String> vector2 = dao.viewAllType();

            request.setAttribute("page", pages);
            request.setAttribute("next", next);
            request.setAttribute("pre", back);
            request.setAttribute("tye", get);
            request.setAttribute("maxpage", loop);
            request.setAttribute("total", total);
            request.setAttribute("list", vector);
            request.setAttribute("list2", vector2);
            dispath(request, response, "/views/setting/Setting.jsp");
        }
        if (service.equals("searchs")) {
            String get = request.getParameter("sta");
            int getty = Integer.parseInt(get);
            pages = (request.getParameter("pages") != null) ? (int) Integer.parseInt(request.getParameter("pages")) : 1;
            back = (pages == 1) ? 1 : pages - 1;
            //Lấy tổng sản phẩm trong data bằng query select count(id) from name_table với JDBC Connect
            total = dao.countSettingSta(getty);

            if (total <= 10) {
                first = 0;
                last = total;
            } else {
                first = (pages - 1) * 10;
                last = 10;
            }
            loop = getMaxPage(total, pages);
            next = getNext(total, pages);

            Vector<Setting> vector = dao.readSettingSta(getty, first, last);
            Vector<String> vector2 = dao.viewAllType();

            request.setAttribute("pre", back);
            request.setAttribute("sta", get);
            request.setAttribute("next", next);
            request.setAttribute("maxpage", loop);
            request.setAttribute("page", pages);
            request.setAttribute("total", total);
            request.setAttribute("list", vector);
            request.setAttribute("list2", vector2);
            dispath(request, response, "/views/setting/Setting.jsp");
        }
        if (service.equals("searchName")) {
            String get = request.getParameter("name");
            pages = (request.getParameter("pages") != null) ? (int) Integer.parseInt(request.getParameter("pages")) : 1;
            back = (pages == 1) ? 1 : pages - 1;
            total = dao.countSettingName(get);
            if (total <= 10) {
                first = 0;
                last = total;
            } else {
                first = (pages - 1) * 10;
                last = 10;
            }
            loop = getMaxPage(total, pages);
            next = getNext(total, pages);

            Vector<Setting> vector = dao.readSettingName(get, first, last);
            Vector<String> vector2 = dao.viewAllType();

            request.setAttribute("page", pages);
            request.setAttribute("maxpage", loop);
            request.setAttribute("next", next);
            request.setAttribute("pre", back);
            request.setAttribute("name", get);
            request.setAttribute("total", total);
            request.setAttribute("list", vector);
            request.setAttribute("list2", vector2);
            dispath(request, response, "/views/setting/Setting.jsp");
        }
    }

    public int getMaxPage(int total, int pages) {
        int num, loop;
        if ((total / 10) % 2 == 0) {
            num = total / 10;
        } else {
            num = (total + 1) / 10;
        }
        //Nếu total lẻ thêm 1
        if (total % 2 != 0) {
            loop = (total / 10) + 1;
        } else {
            //Nếu total chẵn nhỏ hơn fullpage và # fullPage thì thêm 1
            if (total < (num * 10) + 10 && total != num * 10) {
                loop = (total / 10) + 1;
            } else {
                //Nếu bằng fullPage thì không thêm
                loop = (total / 10);
            }
        }
        return loop;
    }

    public int getNext(int total, int pages) {
        int num = 0, next;
        if (total % 2 != 0) {
            if (pages == (total / 10) + 1) {
                next = pages;//Khong next
            } else {
                next = pages + 1;//Co next
            }
        } else {
            //Nếu total chẵn nhỏ hơn fullpage
            //Và không fullPage thì thêm 1
            if (total < (num * 10) + 10 && total != num * 10) {
                if (pages == (total / 10) + 1) {
                    next = pages;//Khong next
                } else {
                    next = pages + 1;//Co next
                }
            } else {
                //Nếu fullPage đến trang cuối dừng
                //Chưa tới trang cuối thì được next
                if (pages == (total / 10)) {
                    next = pages;//Khong next
                } else {
                    next = pages + 1;//Co next
                }
            }
        }
        return next;
    }

    public void dispath(HttpServletRequest request,
            HttpServletResponse response, String page)
            throws IOException, ServletException {
        RequestDispatcher dispath = request.getRequestDispatcher(page);
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
