/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.auth;

import dao.DAOUser;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.BCrypt;
import utils.MailValidation;

/**
 *
 * @author Luu Dat
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change_password"})
public class ChangePasswordController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        request.getRequestDispatcher("/views/common/ChangePassword.jsp").forward(request, response);

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
        DAOUser dao = new DAOUser();

        String old_password = request.getParameter("old_password");
        String new_password_1 = request.getParameter("new_password_1");
        String new_password_2 = request.getParameter("new_password_2");

        Cookie[] c = request.getCookies();
        String old_password_real = "";

        String email_real = "";
        for (Cookie cook : c) {
            if (cook.getName().equals("password")) {
                old_password_real = cook.getValue();

            }
            if (cook.getName().equals("email")) {
                email_real = cook.getValue();
            }
        }

        BCrypt en2 = new BCrypt();

        if (!old_password_real.equals(old_password)) {
            String Notification1 = "Wrong old password.";
            request.setAttribute("notification1", Notification1);
            request.getRequestDispatcher("/views/common/ChangePassword.jsp").forward(request, response);
        } else {
            if (!new_password_1.equals(new_password_2)) {
                String Notification1 = "Password incorrect.";
                request.setAttribute("notification1", Notification1);
                request.getRequestDispatcher("/views/common/ChangePassword.jsp").forward(request, response);
            } else {
                if (old_password.equals(new_password_1)) {
                    String Notification1 = "The new password cannot be the same as the old password!";
                    request.setAttribute("notification1", Notification1);
                    request.getRequestDispatcher("/views/common/ChangePassword.jsp").forward(request, response);
                } else {
                    String newpassword_encode = null;
                    try {
                        newpassword_encode = BCrypt.hashpw(new_password_1, BCrypt.gensalt(12));
                    } catch (Exception e) {
                        newpassword_encode = null;
                    }

                    String subject = "Change Core UI password";
                    String message = "<html><head>\n"
                            + "    <title></title>\n"
                            + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                            + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                            + "    <style type=\"text/css\">\n"
                            + "        @media screen {\n"
                            + "            @font-face {\n"
                            + "                font-family: 'Lato';\n"
                            + "                font-style: normal;\n"
                            + "                font-weight: 400;\n"
                            + "                src: local('Lato Regular'), local('Lato-Regular'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format('woff');\n"
                            + "            }\n"
                            + "\n"
                            + "            @font-face {\n"
                            + "                font-family: 'Lato';\n"
                            + "                font-style: normal;\n"
                            + "                font-weight: 700;\n"
                            + "                src: local('Lato Bold'), local('Lato-Bold'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format('woff');\n"
                            + "            }\n"
                            + "\n"
                            + "            @font-face {\n"
                            + "                font-family: 'Lato';\n"
                            + "                font-style: italic;\n"
                            + "                font-weight: 400;\n"
                            + "                src: local('Lato Italic'), local('Lato-Italic'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format('woff');\n"
                            + "            }\n"
                            + "\n"
                            + "            @font-face {\n"
                            + "                font-family: 'Lato';\n"
                            + "                font-style: italic;\n"
                            + "                font-weight: 700;\n"
                            + "                src: local('Lato Bold Italic'), local('Lato-BoldItalic'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format('woff');\n"
                            + "            }\n"
                            + "        }\n"
                            + "\n"
                            + "        /* CLIENT-SPECIFIC STYLES */\n"
                            + "        body,\n"
                            + "        table,\n"
                            + "        td,\n"
                            + "        a {\n"
                            + "            \n"
                            + "            -webkit-text-size-adjust: 100%;\n"
                            + "            -ms-text-size-adjust: 100%;\n"
                            + "        }\n"
                            + "\n"
                            + "        table,\n"
                            + "        td {\n"
                            + "            mso-table-lspace: 0pt;\n"
                            + "            mso-table-rspace: 0pt;\n"
                            + "        }\n"
                            + "\n"
                            + "        img {\n"
                            + "            -ms-interpolation-mode: bicubic;\n"
                            + "        }\n"
                            + "\n"
                            + "        /* RESET STYLES */\n"
                            + "        img {\n"
                            + "            border: 0;\n"
                            + "            height: auto;\n"
                            + "            line-height: 100%;\n"
                            + "            outline: none;\n"
                            + "            text-decoration: none;\n"
                            + "        }\n"
                            + "\n"
                            + "        table {\n"
                            + "            border-collapse: collapse !important;\n"
                            + "        }\n"
                            + "\n"
                            + "        body {\n"
                            + "            height: 100% !important;\n"
                            + "            margin: 0 !important;\n"
                            + "            padding: 0 !important;\n"
                            + "            width: 100% !important;\n"
                            + "        }\n"
                            + "\n"
                            + "        /* iOS BLUE LINKS */\n"
                            + "        a[x-apple-data-detectors] {\n"
                            + "            color: inherit !important;\n"
                            + "            text-decoration: none !important;\n"
                            + "            font-size: inherit !important;\n"
                            + "            font-family: inherit !important;\n"
                            + "            font-weight: inherit !important;\n"
                            + "            line-height: inherit !important;\n"
                            + "        }\n"
                            + "\n"
                            + "        /* MOBILE STYLES */\n"
                            + "        @media screen and (max-width:600px) {\n"
                            + "            h1 {\n"
                            + "                font-size: 32px !important;\n"
                            + "                line-height: 32px !important;\n"
                            + "            }\n"
                            + "        }\n"
                            + "\n"
                            + "        /* ANDROID CENTER FIX */\n"
                            + "        div[style*=\"margin: 16px 0;\"] {\n"
                            + "            margin: 0 !important;\n"
                            + "        }\n"
                            + "        \n"
                            + "    </style>\n"
                            + "</head>\n"
                            + "\n"
                            + "<body style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">\n"
                            + "    <!-- HIDDEN PREHEADER TEXT -->\n"
                            + "    <div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: 'Lato', Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\"> We're thrilled to have you here! Get ready to dive into your new account.\n"
                            + "    </div>\n"
                            + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                            + "        <!-- LOGO -->\n"
                            + "        <tbody><tr>\n"
                            + "            <td bgcolor=\"#FFA73B\" align=\"center\">\n"
                            + "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
                            + "                    <tbody><tr>\n"
                            + "                        <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 40px 10px;\" > </td>\n"
                            + "                    </tr>\n"
                            + "                </tbody></table>\n"
                            + "            </td>\n"
                            + "        </tr>\n"
                            + "        <tr>\n"
                            + "            <td bgcolor=\"#FFA73B\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
                            + "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
                            + "                    <tbody><tr>\n"
                            + "                        <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">\n"
                            + "                           \n"
                            + "                             <img src=\" https://cdn-icons-png.flaticon.com/512/6195/6195699.png\" width=\"125\" height=\"120\" style=\"display: block; border: 0px;\">\n"
                            + "                        </td>\n"
                            + "                    </tr>\n"
                            + "                </tbody></table>\n"
                            + "            </td>\n"
                            + "        </tr>\n"
                            + "        <tr>\n"
                            + "            <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
                            + "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
                            + "                    <tbody><tr>\n"
                            + "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
                            + "                            <p style=\"margin: 0;\">Your password has been change.</p>\n"
                            + "                        </td>\n"
                            + "                    </tr>\n"
                            + "                    <tr>\n"
                            + "                        <td bgcolor=\"#ffffff\" align=\"left\">\n"
                            + "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                            + "                                <tbody><tr>\n"
                            + "                                    <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">\n"
                            + "                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                            + "                                            <tbody><tr>\n"
                            + "                                                <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#FFA73B\"><a href=\"http://localhost:8080/g3/login\" target=\"_blank\" style=\"font-size: 20px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 15px 25px; border-radius: 2px; border: 1px solid #FFA73B; display: inline-block;\">Protect your Account</a></td>\n"
                            + "                                            </tr>\n"
                            + "                                        </tbody></table>\n"
                            + "                                    </td>\n"
                            + "                                </tr>\n"
                            + "                            </tbody></table>\n"
                            + "                        </td>\n"
                            + "                    </tr> <!-- COPY -->\n"
                            + "                    <tr>\n"
                            + "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 0px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
                            + "                            <p style=\"margin: 0;\">If you have already done this, you can safely ignore this email.</p>\n"
                            + "                        </td>\n"
                            + "                    </tr> <!-- COPY -->\n"
                            + "                    \n"
                            + "                    <tr>\n"
                            + "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
                            + "                            <p style=\"margin: 0;\">If you have not done this, please protect your account.</p>\n"
                            + "                        </td>\n"
                            + "                    </tr>\n"
                            + "                    <tr>\n"
                            + "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
                            + "                            <p style=\"margin: 0;\">Sincerely thank you!,<br>Team 3's security team</p>\n"
                            + "                        </td>\n"
                            + "                    </tr>\n"
                            + "                </tbody></table>\n"
                            + "            </td>\n"
                            + "        </tr>\n"
                            + "        <tr>\n"
                            + "            <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 30px 10px 0px 10px;\">\n"
                            + "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
                            + "                    <tbody><tr>\n"
                            + "                        <td bgcolor=\"#FFECD1\" align=\"center\" style=\"padding: 30px 30px 30px 30px; border-radius: 4px 4px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
                            + "                            <h2 style=\"font-size: 20px; font-weight: 400; color: #111111; margin: 0;\">Need more help?</h2>\n"
                            + "                            <p style=\"margin: 0;\"><a target=\"_blank\" style=\"color: #2302fa;\">Send to email: luutiendat12072001@gmail.com</a></p>\n"
                            + "                        </td>\n"
                            + "                    </tr>\n"
                            + "                </tbody></table>\n"
                            + "            </td>\n"
                            + "        </tr>\n"
                            + "        <tr>\n"
                            + "            <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
                            + "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
                            + "                    <tbody><tr>\n"
                            + "                        <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;\"> <br>\n"
                            + "                            <p style=\"margin: 0;\">If these emails get annoying, please feel free to <a href=\"#\" target=\"_blank\" style=\"color: #111111; font-weight: 700;\">unsubscribe</a>.</p>\n"
                            + "                        </td>\n"
                            + "                    </tr>\n"
                            + "                </tbody></table>\n"
                            + "            </td>\n"
                            + "        </tr>\n"
                            + "    </tbody></table>\n"
                            + "<!-- Code injected by live-server -->\n"
                            + "<script type=\"text/javascript\">\n"
                            + "	// <![CDATA[  <-- For SVG support\n"
                            + "	if ('WebSocket' in window) {\n"
                            + "		(function () {\n"
                            + "			function refreshCSS() {\n"
                            + "				var sheets = [].slice.call(document.getElementsByTagName(\"link\"));\n"
                            + "				var head = document.getElementsByTagName(\"head\")[0];\n"
                            + "				for (var i = 0; i < sheets.length; ++i) {\n"
                            + "					var elem = sheets[i];\n"
                            + "					var parent = elem.parentElement || head;\n"
                            + "					parent.removeChild(elem);\n"
                            + "					var rel = elem.rel;\n"
                            + "					if (elem.href && typeof rel != \"string\" || rel.length == 0 || rel.toLowerCase() == \"stylesheet\") {\n"
                            + "						var url = elem.href.replace(/(&|\\?)_cacheOverride=\\d+/, '');\n"
                            + "						elem.href = url + (url.indexOf('?') >= 0 ? '&' : '?') + '_cacheOverride=' + (new Date().valueOf());\n"
                            + "					}\n"
                            + "					parent.appendChild(elem);\n"
                            + "				}\n"
                            + "			}\n"
                            + "			var protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://';\n"
                            + "			var address = protocol + window.location.host + window.location.pathname + '/ws';\n"
                            + "			var socket = new WebSocket(address);\n"
                            + "			socket.onmessage = function (msg) {\n"
                            + "				if (msg.data == 'reload') window.location.reload();\n"
                            + "				else if (msg.data == 'refreshcss') refreshCSS();\n"
                            + "			};\n"
                            + "			if (sessionStorage && !sessionStorage.getItem('IsThisFirstTime_Log_From_LiveServer')) {\n"
                            + "				console.log('Live reload enabled.');\n"
                            + "				sessionStorage.setItem('IsThisFirstTime_Log_From_LiveServer', true);\n"
                            + "			}\n"
                            + "		})();\n"
                            + "	}\n"
                            + "	else {\n"
                            + "		console.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');\n"
                            + "	}\n"
                            + "	// ]]>\n"
                            + "</script>\n"
                            + "\n"
                            + "</body></html>";
                    MailValidation.send(email_real, subject, message, "ryudatto12072001@gmail.com", "Luutiendat1");
                    
                    LoginController.setLoginCookie(request, response, email_real, new_password_1);
                    
                    dao.updatepassword(email_real, newpassword_encode);
                    request.getSession().setAttribute("useraccount", null);
                    String Notification1 = "Password change bar successfully!";
                    request.setAttribute("notification1", Notification1);
                    request.getRequestDispatcher("/views/common/ChangePassword.jsp").forward(request, response);

                }
            }
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
