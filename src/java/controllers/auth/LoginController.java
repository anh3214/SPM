package controllers.auth;

import dao.DAOClass;
import dao.DAOIssue;
import dao.DAOMilestone;
import dao.DAOUser;
import entity.Issue;
import entity.Subject;
import entity.Team;
import entity.User;
import utils.BCrypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                User curOn = getLoginCookie(request);
                if (curOn == null) {
                    disPath(request, response, "/views/common/Login.jsp");
                } else {
                    response.sendRedirect("dashboard");
                }
            } else {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                User curOn = DAOUser.loginCheck(email, password);
                if (curOn != null) {
                    setLoginCookie(request, response, email, password);
                    response.sendRedirect("dashboard");
                } else {
                    email = email.length() >= 30 ? email.substring(0, 30) : email;
                    request.setAttribute("email", email);
                    request.setAttribute("warning", "Wrong email or password!");
                    disPath(request, response, "/views/common/Login.jsp");
                }
            }
        }
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] c = request.getCookies();
        for (Cookie cook : c) {
            if (cook.getName().equals("email")) {
                Cookie cookie = new Cookie("email", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cook.getName().equals("password")) {
                Cookie cookie = new Cookie("password", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        HttpSession session = request.getSession();
        session.invalidate();
    }

    public static void setLoginCookie(HttpServletRequest request, HttpServletResponse response, String email, String password)
            throws ServletException, IOException {
        String autologin = request.getParameter("autologin");
        Cookie usermail = new Cookie("email", email);
        Cookie userpass = new Cookie("password", password);
        if (autologin != null) {
            //1 day
            usermail.setMaxAge(60 * 60 * 24);
            userpass.setMaxAge(60 * 60 * 24);
        }
        response.addCookie(usermail);
        response.addCookie(userpass);
    }

    public static User getLoginCookie(HttpServletRequest request) {
        Cookie[] c = request.getCookies();
        String email = null;
        String password = null;
        try {
        for (Cookie cook : c) {
            if (cook.getName().equals("email")) {
                email = cook.getValue();
            }
            if (cook.getName().equals("password")) {
                password = cook.getValue();
            }
        }} catch (Exception e) {
            
        }
        if (email == null || password == null) {
            return null;
        }
        User cur = DAOUser.loginCheck(email, password);
        if (cur != null ) {
            buildSession(request, cur);
        }
        return cur;
    }

    public static void buildSession(HttpServletRequest request, User user) {
        HttpSession sess = request.getSession(true);
        
        //set class list to sidebar
        DAOClass classDAO = new DAOClass();
        Vector<entity.Class> classes = classDAO.queryClassSideBar(user.getUser_id(), user.getRole_id());
        Hashtable<Integer, Vector<Team>> teamOfClass = new Hashtable<Integer, Vector<Team>>();
        
        //set team list to sidebar
        for (int i=0; i<classes.size(); i++) {
            int class_id = classes.get(i).getClass_id();
            teamOfClass.put(class_id, classDAO.queryTeamByClass(class_id, user.getUser_id(), user.getRole_id()));
        }
        
        Vector<Subject> subjects = classDAO.querySubject(user.getUser_id(), user.getRole_id());
        
        String role_name = "User";
        if (user.getRole_id() == 1) role_name = "Admin";
        if (user.getRole_id() == 2) role_name = "Author";
        if (user.getRole_id() == 3) role_name = "Teacher";
        if (user.getRole_id() == 4) role_name = "Student";
        
        sess.setAttribute("classes", classes);
        sess.setAttribute("subjects", subjects);
        sess.setAttribute("teams", teamOfClass);
        
        sess.setAttribute("id", user.getUser_id());
        sess.setAttribute("full_name", user.getFull_name());
        sess.setAttribute("avt_link", user.getAvatar_link());
        sess.setAttribute("role_id", user.getRole_id());
        sess.setAttribute("role_name", role_name);
        sess.setAttribute("gitlab_token", user.getToken_user());
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
