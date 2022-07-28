/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.auth;

import dao.DAOUser;
import entity.User;
import utils.BCrypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.MailValidation;

/**
 *
 * @author Luu Dat
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DAOUser dao = new DAOUser();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String submit = request.getParameter("submit");

            if (submit == null) {
                disPath(request, response, "/views/common/Register.jsp");
            } else {

                String roll_number = "0";
                String full_name = request.getParameter("Full_name");
                String gender = request.getParameter("Gender");
                String date_of_birth = request.getParameter("date_of_birth");
                Date dob = Date.valueOf(date_of_birth);
                String email = request.getParameter("Email");

                String mobile = request.getParameter("Phone");
                String password = request.getParameter("Password");
                String re_password = request.getParameter("Re_Password");
                String encode_user = request.getParameter("checkcode"); // ma xac thuc

                if (encode_user != null) {
                    Cookie[] c = request.getCookies();
                    boolean check2 = true;
                    for (Cookie cook : c) {
                        if (cook.getName().equals("encode") && cook.getValue().equals(encode_user)) {
                            check2 = false;

                        }

                    }
                    if (!check2) {

                        // ma hoa password
                        String password2 = null;
                        try {
                            password2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
                        } catch (Exception e) {
                            password2 = null;
                        }
                        // ma hoa password

                        // xóa kí tự đặc biệt trong input fullname
                        String fullname2 = "";
                        String specialChars2 = "`~!@#$%^&*()_+[]\\;\',./{}|:\"<>?";
                        for (int i = 0; i < full_name.length(); i++) {
                            boolean check = true;
                            for (int j = 0; j < specialChars2.length(); j++) {
                                if (specialChars2.charAt(j) == full_name.charAt(i)) {
                                    check = false;
                                }
                            }
                            if (check) {
                                fullname2 += full_name.charAt(i);
                            }
                        }
                        fullname2 = fullname2.trim().replaceAll("\\s+", " ");
                        fullname2 = fullname2.replaceAll("\\s+", " ");
                        // xóa kí tự đặc biệt trong input fullname

                        //Cat email de gan vao rollnumber
                        String roll_number2 = "";
                        for (int i = email.length() - 1; i >= 0; i--) {
                            if (email.charAt(i) == '@') {
                                for (int j = 1; j <= 8; j++) {
                                    roll_number2 += email.charAt(i - j);
                                }
                            }
                        }
                        StringBuffer r3 = new StringBuffer(roll_number2);
                        roll_number2 = r3.reverse().toString();
                        //Cat email de gan vao rollnmber

                        //Validation
                        if (full_name.equals("") || full_name.equals(" ") || full_name == null) {
                            String thongbao = "Full name is null!";
                            request.setAttribute("thongbao", thongbao);
                            request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);

                        }
                        if (!email.endsWith("@fpt.edu.vn")) {
                            String thongbao = "Email invalid!";
                            request.setAttribute("thongbao", thongbao);
                            request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                        } else {
                            if (!password.equals(re_password)) {
                                String thongbao = "password does not match!";
                                request.setAttribute("thongbao", thongbao);
                                request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                            }
                            if (mobile.length() != 10) {
                                String thongbao = "This is not a personal phone number!";
                                request.setAttribute("thongbao", thongbao);
                                request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                            } else {
                                User user = new User(roll_number2, fullname2, gender, date_of_birth, email, mobile, "null", "null", 4, 1, password2);
                                int n = dao.addUser(user);
                                if (n != 0) {
                                    LoginController.setLoginCookie(request, response, email, password);
                                    response.sendRedirect("dashboard");
//                             disPath(request, response, "dashboard");
                                } else {
                                    String thongbao1 = "You register fail!";
                                    request.setAttribute("thongbao1", thongbao1);
                                    request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);

                                }
                            }
                        }
                        //Validation
                    } else {
                        request.setAttribute("Full_name", full_name);
                        request.setAttribute("date_of_birth", dob);
                        request.setAttribute("Email", email);
                        request.setAttribute("Phone", mobile);
                        request.setAttribute("Password", password);
                        request.setAttribute("Re_Password", re_password);
                        String en1 = "konull";
                        request.setAttribute("encode", en1);// encode
                        String thongbao1 = "Ma xac minh sai!";
                        request.setAttribute("thongbao1", thongbao1);
                        request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                    }

                } else {

                    // ma hoa password
                    String password2 = null;
                    try {
                        password2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    } catch (Exception e) {
                        password2 = null;
                    }
                    // ma hoa password

                    // xóa kí tự đặc biệt trong input fullname
                    String fullname2 = "";
                    String specialChars2 = "`~!@#$%^&*()_+[]\\;\',./{}|:\"<>?";
                    for (int i = 0; i < full_name.length(); i++) {
                        boolean check = true;
                        for (int j = 0; j < specialChars2.length(); j++) {
                            if (specialChars2.charAt(j) == full_name.charAt(i)) {
                                check = false;
                            }
                        }
                        if (check) {
                            fullname2 += full_name.charAt(i);
                        }
                    }
                    fullname2 = fullname2.trim().replaceAll("\\s+", " ");
                    fullname2 = fullname2.replaceAll("\\s+", " ");
                    // xóa kí tự đặc biệt trong input fullname

                    //Cat email de gan vao rollnumber
                    String roll_number2 = "";
                    for (int i = email.length() - 1; i >= 0; i--) {
                        if (email.charAt(i) == '@') {
                            for (int j = 1; j <= 8; j++) {
                                roll_number2 += email.charAt(i - j);
                            }
                        }
                    }
                    StringBuffer r3 = new StringBuffer(roll_number2);
                    roll_number2 = r3.reverse().toString();
                    //Cat email de gan vao rollnmber

                    //Validation
                    if (full_name.equals("") || full_name.equals(" ") || full_name == null) {
                        String thongbao = "Full name is null!";
                        request.setAttribute("thongbao", thongbao);
                        request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                    }
                    if (!email.endsWith("@fpt.edu.vn")) {
                        String thongbao = "Email invalid!";
                        request.setAttribute("thongbao", thongbao);
                        request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                    } else {
                        if (!password.equals(re_password)) {
                            String thongbao = "password does not match!";
                            request.setAttribute("thongbao", thongbao);
                            request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                        }
                        if (mobile.length() != 10) {
                            String thongbao = "This is not a personal phone number!";
                            request.setAttribute("thongbao", thongbao);
                            request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                        } else {
                            Random rand = new Random();
                            int n = rand.nextInt(8999);
                            n += 1000;
                            Cookie encode_real = new Cookie("encode", Integer.toString(n));
                            encode_real.setMaxAge(60 * 5);
                            response.addCookie(encode_real);

                            String subject = "Ma xac thuc cua ban : " + n;
                            String message = "<html><head>\n"
                                    + "  <title></title>\n"
                                    + "  <!--[if !mso]><!-- -->\n"
                                    + "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                                    + "  <!--<![endif]-->\n"
                                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                                    + "<style type=\"text/css\">\n"
                                    + "  #outlook a { padding: 0; }\n"
                                    + "  .ReadMsgBody { width: 100%; }\n"
                                    + "  .ExternalClass { width: 100%; }\n"
                                    + "  .ExternalClass * { line-height:100%; }\n"
                                    + "  body { margin: 0; padding: 0; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\n"
                                    + "  table, td { border-collapse:collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\n"
                                    + "  img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; }\n"
                                    + "  p { display: block; margin: 13px 0; }\n"
                                    + "</style>\n"
                                    + "<!--[if !mso]><!-->\n"
                                    + "<style type=\"text/css\">\n"
                                    + "  @media only screen and (max-width:480px) {\n"
                                    + "    @-ms-viewport { width:320px; }\n"
                                    + "    @viewport { width:320px; }\n"
                                    + "  }\n"
                                    + "</style>\n"
                                    + "<!--<![endif]-->\n"
                                    + "<!--[if mso]>\n"
                                    + "<xml>\n"
                                    + "  <o:OfficeDocumentSettings>\n"
                                    + "    <o:AllowPNG/>\n"
                                    + "    <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                                    + "  </o:OfficeDocumentSettings>\n"
                                    + "</xml>\n"
                                    + "<![endif]-->\n"
                                    + "<!--[if lte mso 11]>\n"
                                    + "<style type=\"text/css\">\n"
                                    + "  .outlook-group-fix {\n"
                                    + "    width:100% !important;\n"
                                    + "  }\n"
                                    + "</style>\n"
                                    + "<![endif]-->\n"
                                    + "\n"
                                    + "<!--[if !mso]><!-->\n"
                                    + "    <link href=\"https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700\" rel=\"stylesheet\" type=\"text/css\">\n"
                                    + "    <style type=\"text/css\">\n"
                                    + "\n"
                                    + "        @import url(https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700);\n"
                                    + "\n"
                                    + "    </style>\n"
                                    + "  <!--<![endif]--><style type=\"text/css\">\n"
                                    + "  @media only screen and (min-width:480px) {\n"
                                    + "    .mj-column-per-100, * [aria-labelledby=\"mj-column-per-100\"] { width:100%!important; }\n"
                                    + "  }\n"
                                    + "</style>\n"
                                    + "</head>\n"
                                    + "<body style=\"background: #F9F9F9;\">\n"
                                    + "  <div style=\"background-color:#F9F9F9;\"><!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"640\" align=\"center\" style=\"width:640px;\">\n"
                                    + "        <tr>\n"
                                    + "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
                                    + "      <![endif]-->\n"
                                    + "  <style type=\"text/css\">\n"
                                    + "    html, body, * {\n"
                                    + "      -webkit-text-size-adjust: none;\n"
                                    + "      text-size-adjust: none;\n"
                                    + "    }\n"
                                    + "    a {\n"
                                    + "      color:#1EB0F4;\n"
                                    + "      text-decoration:none;\n"
                                    + "    }\n"
                                    + "    a:hover {\n"
                                    + "      text-decoration:underline;\n"
                                    + "    }\n"
                                    + "  </style>\n"
                                    + "<div style=\"margin:0px auto;max-width:640px;background:transparent;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:transparent;\" align=\"center\" border=\"0\"><tbody><tr><td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:40px 0px;\"><!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:top;width:640px;\">\n"
                                    + "      <![endif]--><div aria-labelledby=\"mj-column-per-100\" class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td style=\"word-break:break-word;font-size:0px;padding:0px;\" align=\"center\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;border-spacing:0px;\" align=\"center\" border=\"0\"><tbody><tr><td style=\"width:138px;\"></td></tr></tbody></table></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]--></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]-->\n"
                                    + "      <!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"640\" align=\"center\" style=\"width:640px;\">\n"
                                    + "        <tr>\n"
                                    + "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
                                    + "      <![endif]--><div style=\"max-width:640px;margin:0 auto;box-shadow:0px 1px 5px rgba(0,0,0,0.1);border-radius:4px;overflow:hidden\"><div style=\"margin:0px auto;max-width:640px;background:#7289DA url(https://cdn.discordapp.com/email_assets/f0a4cc6d7aaa7bdf2a3c15a193c6d224.png) top center / cover no-repeat;\"><!--[if mso | IE]>\n"
                                    + "      <v:rect xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"true\" stroke=\"false\" style=\"width:640px;\">\n"
                                    + "        <v:fill origin=\"0.5, 0\" position=\"0.5,0\" type=\"tile\" src=\"https://cdn.discordapp.com/email_assets/f0a4cc6d7aaa7bdf2a3c15a193c6d224.png\" />\n"
                                    + "        <v:textbox style=\"mso-fit-shape-to-text:true\" inset=\"0,0,0,0\">\n"
                                    + "      <![endif]--><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:#7289DA url(https://cdn.discordapp.com/email_assets/f0a4cc6d7aaa7bdf2a3c15a193c6d224.png) top center / cover no-repeat;\" align=\"center\" border=\"0\" background=\"https://cdn.discordapp.com/email_assets/f0a4cc6d7aaa7bdf2a3c15a193c6d224.png\"><tbody><tr><td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:57px;\"><!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:undefined;width:640px;\">\n"
                                    + "      <![endif]--><div style=\"cursor:auto;color:white;font-family:Whitney, Helvetica Neue, Helvetica, Arial, Lucida Grande, sans-serif;font-size:36px;font-weight:600;line-height:36px;text-align:center;\">Verification mail!</div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]--></td></tr></tbody></table><!--[if mso | IE]>\n"
                                    + "        </v:textbox>\n"
                                    + "      </v:rect>\n"
                                    + "      <![endif]--></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]-->\n"
                                    + "      <!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"640\" align=\"center\" style=\"width:640px;\">\n"
                                    + "        <tr>\n"
                                    + "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
                                    + "      <![endif]--><div style=\"margin:0px auto;max-width:640px;background:#ffffff;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:#ffffff;\" align=\"center\" border=\"0\"><tbody><tr><td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:40px 70px;\"><!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:top;width:640px;\">\n"
                                    + "      <![endif]--><div aria-labelledby=\"mj-column-per-100\" class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td style=\"word-break:break-word;font-size:0px;padding:0px 0px 20px;\" align=\"left\"><div style=\"cursor:auto;color:#737F8D;font-family:Whitney, Helvetica Neue, Helvetica, Arial, Lucida Grande, sans-serif;font-size:16px;line-height:24px;text-align:left;\">\n"
                                    + "            <p><img src=\"https://upload.wikimedia.org/wikipedia/vi/1/1d/Logo_%C4%90%E1%BA%A1i_h%E1%BB%8Dc_FPT.png\" alt=\"Party Wumpus\" title=\"None\" width=\"500\" style=\"height: auto;\"></p>\n"
                                    + "\n"
                                    + "  <h2 style=\"font-family: Whitney, Helvetica Neue, Helvetica, Arial, Lucida Grande, sans-serif;font-weight: 500;font-size: 20px;color: #4F545C;letter-spacing: 0.27px;\">Hey " + full_name + ",</h2>\n"
                                    + "<p>Wowwee! Thanks for registering an account with my system! You're the coolest person in all the land (and I've met a lot of really cool people).</p>\n"
                                    + "<p>Before we get started, we'll need to verify your email.</p>\n"
                                    + "<br> This code will expire in 3 minutes.\n"
                                    + " <br>\n"
                                    + "          </div></td></tr><tr><td style=\"word-break:break-word;font-size:0px;padding:10px 25px;\" align=\"center\"><div style=\"text-align:center;font-size:36px;margin-top:20px;line-height:44px\">" + n + "</div></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]--></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]-->\n"
                                    + "      <!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"640\" align=\"center\" style=\"width:640px;\">\n"
                                    + "        <tr>\n"
                                    + "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
                                    + "      <![endif]--></div><div style=\"margin:0px auto;max-width:640px;background:transparent;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:transparent;\" align=\"center\" border=\"0\"><tbody><tr><td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\"><!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:top;width:640px;\">\n"
                                    + "      <![endif]--><div aria-labelledby=\"mj-column-per-100\" class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td style=\"word-break:break-word;font-size:0px;\"><div style=\"font-size:1px;line-height:12px;\">&nbsp;</div></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]--></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]-->\n"
                                    + "      <!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"640\" align=\"center\" style=\"width:640px;\">\n"
                                    + "        <tr>\n"
                                    + "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
                                    + "      <![endif]--><div style=\"margin:0 auto;max-width:640px;background:#ffffff;box-shadow:0px 1px 5px rgba(0,0,0,0.1);border-radius:4px;overflow:hidden;\"><table cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:#ffffff;\" align=\"center\" border=\"0\"><tbody><tr><td style=\"text-align:center;vertical-align:top;font-size:0px;padding:0px;\"><!--[if mso | IE]>\n"
                                    + "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:top;width:640px;\">\n"
                                    + "      <![endif]--><div aria-labelledby=\"mj-column-per-100\" class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr></tr><tr><td style=\"word-break:break-word;font-size:0px;padding:14px 70px 30px 70px;\" align=\"center\"><div style=\"cursor:auto;color:#737F8D;font-family:Whitney, Helvetica Neue, Helvetica, Arial, Lucida Grande, sans-serif;font-size:16px;line-height:22px;text-align:center;\">\n"
                                    + "     We're sending this email to notify you of material changes to your Google Account and services.\n"
                                    + "    </div></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]--></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]-->\n"
                                    + "      <!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"640\" align=\"center\" style=\"width:640px;\">\n"
                                    + "        <tr>\n"
                                    + "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
                                    + "      <![endif]--><div style=\"margin:0px auto;max-width:640px;background:transparent;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:transparent;\" align=\"center\" border=\"0\"><tbody><tr><td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:20px 0px;\"><!--[if mso | IE]>\n"
                                    + "      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:top;width:640px;\">\n"
                                    + "      <![endif]--><div aria-labelledby=\"mj-column-per-100\" class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\"><table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td style=\"word-break:break-word;font-size:0px;padding:0px;\" align=\"center\"></td></tr><tr><td style=\"word-break:break-word;font-size:0px;padding:0px;\" align=\"center\"><div style=\"cursor:auto;color:#99AAB5;font-family:Whitney, Helvetica Neue, Helvetica, Arial, Lucida Grande, sans-serif;font-size:12px;line-height:24px;text-align:center;\">Lo E2a-7, Duong D1, Khu Cong nghe cao, P.Long Thach My, Tp. Thu Duc, TP.HCM. · Phone number: (028) 7300 5588 · Email: daihoc.hcm@fpt.edu.vn</div></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]--></td></tr></tbody></table></div><!--[if mso | IE]>\n"
                                    + "      </td></tr></table>\n"
                                    + "      <![endif]--></div>\n"
                                    + "\n"
                                    + "</body></html>";
                            MailValidation.send(email, subject, message, "thainv35nb@gmail.com", "uekekfcciswhkyfm");

                            request.setAttribute("Full_name", full_name);
                            request.setAttribute("date_of_birth", dob);
                            request.setAttribute("Email", email);
                            request.setAttribute("Phone", mobile);
                            request.setAttribute("Password", password);
                            request.setAttribute("Re_Password", re_password);
                            String en1 = "konull";
                            request.setAttribute("encode", en1);// encode
                            String thongbao1 = "Check mail to register!";
                            request.setAttribute("thongbao1", thongbao1);
                            request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);
                        }
                    }
                    //Validation

                }

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public void disPath(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispath = request.getRequestDispatcher(path);
        dispath.forward(request, response);
    }

    public String checkNumber(String number) {
        if (number == null || number.isEmpty()) {
            number = "0";
        }
        return number;
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
//request.getRequestDispatcher("/views/common/Register.jsp").forward(request, response);

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
