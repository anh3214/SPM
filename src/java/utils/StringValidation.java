package utils;

public class StringValidation {

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean checkName(String name) {
        return name.length() <= 30 && !name.equals("") && name != null;
    }

    public static boolean dateCompare(String from_date, String to_date, int days) {
        try {
            String[] input_from = from_date.split("\\-");
            String[] input_to = to_date.split("\\-");
            int year = Integer.parseInt(input_to[0]) - Integer.parseInt(input_from[0]);
            int month = Integer.parseInt(input_to[1]) - Integer.parseInt(input_from[1]);
            int day = Integer.parseInt(input_to[2]) - Integer.parseInt(input_from[2]);
            System.out.println(year);
            System.out.println(month);
            System.out.println(day);
            System.out.println(day - days);
            return ((year * 365 + month * 30 + day) - days) / 7 == 0;
        } catch (Exception ex) {
            return false;
        }
    }
}
