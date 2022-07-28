/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.DAOClass;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author RxZ
 */
public class Validator {

    private static final String NAME_REGEX
            = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    // VN phone number with or without dashes
    private static final String PHONE_REGEX
            = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";

    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    // local-part + @ + domain part
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\\.)?[a-zA-Z]+\\.)?fpt\\.edu\\.vn$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Validate facebook profile
    private static final String FB_REGEX = "(?:https?:\\/\\/)?(?:www\\.)?facebook\\.com\\/(?:(?:\\w)*#!\\/)?(?:pages\\/)?(?:[\\w\\-]*\\/)*([\\w\\-\\.]*)";

    private static final Pattern FB_PATTERN = Pattern.compile(FB_REGEX);

    // 8-16 characters password with at least one digit, one lowercase letter,
    // one uppercase letter, one special character with no whitespaces
    private static final String PASSWORD_REGEX
            = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

    private static final Pattern PASSWORD_PATTERN
            = Pattern.compile(PASSWORD_REGEX);

    // Date in US format with support for leap years
    private static final String DATE_REGEX =  "^(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))-((0[0-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01])))$";

    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
    
    // Others
    private static final String ROLE_REGEX
            = "^1|2|3|4$";

    private static final Pattern ROLE_PATTERN = Pattern.compile(ROLE_REGEX);
    
    private static final String ID_REGEX = "[0-9]+";
    
    private static final Pattern ID_PATTERN = Pattern.compile(ID_REGEX);
    
    private static final String STATUS_REGEX
            = "^2|1|0$";

    private static final Pattern STATUS_PATTERN = Pattern.compile(STATUS_REGEX);
    
    // Validate gitlab
    private static final String GITLAB_REGEX = "(?:https?:\\/\\/)?(?:www\\.)?gitlab\\.com\\/(?:(?:\\w)*#!\\/)?(?:pages\\/)?(?:[\\w\\-]*\\/)*([\\w\\-\\.]*)";
    
    private static final Pattern GITLAB_PATTERN = Pattern.compile(GITLAB_REGEX);

    public static boolean checkName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    public static boolean checkPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean checkEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean checkFbLink(String fbLink) {
        return FB_PATTERN.matcher(fbLink).matches();
    }

    public static boolean checkPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean checkDate(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }
    
    public static boolean checkRole(String role) {
        return ROLE_PATTERN.matcher(role).matches();
    }
    
    public static boolean checkStatus(String status) {
        return STATUS_PATTERN.matcher(status).matches();
    }
    
    public static boolean checkID(String id) {
        return id != null && ID_PATTERN.matcher(id).matches();
    }
    
    public static boolean checkGitLabLink(String gitlabLink) {
        return FB_PATTERN.matcher(gitlabLink).matches();
    }
    
    public static boolean checkDescription(String description) {
        return (description.length() <= 500);
    }
    public static boolean checkClassCode(String code){
        if(code == null || code.equals("")){
            return false;
        }
        return true;
    }
    public static  boolean checkExitClassCode(String clascode){
        DAOClass dao =new DAOClass();
        Vector<entity.Class> clas2s = dao.viewallClass();
        if (clas2s.stream().anyMatch((clas2) -> (clas2.equals(clascode)))) {
            return true;
        }
        return false;
    }
    public static boolean checkNull(String string){
        if(string != null || !"".equals(string)){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(Validator.checkDate("2022-06-01"));
    }
}
