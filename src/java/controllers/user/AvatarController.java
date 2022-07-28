/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import controllers.auth.LoginController;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import entity.User;
import dao.DAOUser;
import controllers.auth.LoginController;

/**
 *
 * @author RxZ
 */
@WebServlet(name = "AvatarController", urlPatterns = {"/profile/avatar"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 2, // 2MB
        maxRequestSize = 1024 * 1024 * 2) // 2MB

public class AvatarController extends HttpServlet {
    private static final long SerialVersionUID = 1L;
    private static final String  UPLOAD_DIR = "images";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result = uploadFile(request);
        response.getWriter().print(result);
    }
    
    private String uploadFile(HttpServletRequest request) throws IOException, ServletException{
        String fileName="";
        try{
            User curU = LoginController.getLoginCookie(request);
            Part filePart = request.getPart("file");
            int uid = curU.getUser_id();
            String uidCustom = request.getParameter("uid");
            if (uidCustom!=null&&!"".equals(uidCustom) && curU.getRole_id()==1)  uid = Integer.parseInt(uidCustom);
            fileName = (String) "avatar_" + uid + ".jpg";
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new  File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes =  new  byte[1024];
                while((read = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            }finally{
                if(inputStream != null){
                    inputStream.close();
                }
                if(outputStream != null){
                    outputStream.close();
                }
                if (!DAOUser.updateAvatarLink(uid, "services/image/" + fileName)) {
                    throw new Exception();
                } else LoginController.buildSession(request, LoginController.getLoginCookie(request));
            }
            
        }catch(Exception e){
            e.printStackTrace();
            fileName = "";
        }
        return fileName != "" ? "services/image/" + fileName : "error";
    }
    private String  getFileName(Part part){
        final String  partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :"+ partHeader);
        for(String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content.substring(content.indexOf('=')+1).trim().replace("\"", "" );
            }
        }
        
        return null;
    }
}
