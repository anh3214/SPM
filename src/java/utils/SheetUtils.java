/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.OutputStream;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.*;
import entity.ClassUser;
import entity.User;
import entity.ClassSetting;
import entity.Function;
import entity.Team;
import entity.Feature;
import java.io.InputStream;
import java.util.ArrayList;
import java.sql.Date;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author RxZ
 */
public class SheetUtils {

    public static void exportFuncXLSX(String baseName, ArrayList<Function> funcList, ArrayList<User> ownerList, ArrayList<ClassSetting> statusList, ArrayList<Feature> feList, int subjectId, int classId, int teamId, int featureId, OutputStream out) {
        String[] headers_func = {"ID", "NAME", "ACCESS ROLES", "COMPLEXITY", "OWNER", "PRIORITY", "STATUS", "FEATURE", "DESCRIPTION"};
        String[] headers_owner = {"ROLL NUMBER", "NAME"};
        String[] headers_status = {"STATUS"};
        String[] headers_feature = {"FEATURE"};
        String[] headers_base = {"SUBJECT", "CLASS", "TEAM", "FEATURE"};
        String[] headers_notes = {"INFO"};

        // color red
        byte[] rgb = new byte[3];
        rgb[0] = (byte) 255; // red
        rgb[1] = (byte) 0; // green
        rgb[2] = (byte) 53; // blue
        XSSFColor rColor = new XSSFColor(rgb);
        
        byte[] rgb1 = new byte[3];
        rgb1[0] = (byte) 0; // red
        rgb1[1] = (byte) 255; // green
        rgb1[2] = (byte) 53; // blue
        XSSFColor gColor = new XSSFColor(rgb1);

        XSSFWorkbook workbook = new XSSFWorkbook();

        // Build for functions
        XSSFSheet sheet = workbook.createSheet("FUNCTIONS");

        int rowNumFunc = 0;
        Row rowFunc = sheet.createRow(rowNumFunc++);
        int cellNumFunc = 0;

        // create style
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setBold(true);
        style.setFont(font);

        // headers
        for (Object obj : headers_func) {
            Cell cell = rowFunc.createCell(cellNumFunc++);
            cell.setCellStyle(style);
            if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer) obj);
            }
        }

        // fill
        for (Function func : funcList) {
            cellNumFunc = 0;
            rowFunc = sheet.createRow(rowNumFunc++);
            rowFunc.createCell(cellNumFunc++).setCellValue((Integer) func.getId());
            rowFunc.createCell(cellNumFunc++).setCellValue((String) func.getName());
            rowFunc.createCell(cellNumFunc++).setCellValue((String) func.getAccessRoles());
            rowFunc.createCell(cellNumFunc++).setCellValue((String) func.getComplexity());
            rowFunc.createCell(cellNumFunc++).setCellValue((String) func.getOwnerName().split(" - ")[0]);
            rowFunc.createCell(cellNumFunc++).setCellValue((String) func.getPriority());
            rowFunc.createCell(cellNumFunc++).setCellValue((String) func.getStatusName());
            rowFunc.createCell(cellNumFunc++).setCellValue((String) func.getFeatureName());
            rowFunc.createCell(cellNumFunc++).setCellValue((String) func.getDescription());
        }
        for (int i = 0; i <= headers_func.length - 1; i++) {
            sheet.autoSizeColumn(i);
        }

        // Build for owner
        XSSFSheet sheetOwner = workbook.createSheet("OWNERS");
        sheetOwner.setTabColor(rColor);
        int rowNumOwner = 0;
        Row rowOwner = sheetOwner.createRow(rowNumOwner++);
        int cellNumOwner = 0;
        for (Object obj : headers_owner) {
            Cell cell = rowOwner.createCell(cellNumOwner++);
            cell.setCellStyle(style);
            if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer) obj);
            }
        }

        for (User clu : ownerList) {
            cellNumOwner = 0;
            rowOwner = sheetOwner.createRow(rowNumOwner++);
            rowOwner.createCell(cellNumOwner++).setCellValue((String) clu.getRoll_number());
            rowOwner.createCell(cellNumOwner++).setCellValue((String) clu.getFull_name());
        }

        for (int i = 0; i <= headers_owner.length - 1; i++) {
            sheetOwner.autoSizeColumn(i);
        }

        // Build for status
        XSSFSheet sheetStatus = workbook.createSheet("STATUS");
        sheetStatus.setTabColor(rColor);
        int rowNumStatus = 0;
        Row rowStatus = sheetStatus.createRow(rowNumStatus++);
        int cellNumStatus = 0;
        for (Object obj : headers_status) {
            Cell cell = rowStatus.createCell(cellNumStatus++);
            cell.setCellStyle(style);
            if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer) obj);
            }
        }

        for (ClassSetting clu : statusList) {
            cellNumStatus = 0;
            rowStatus = sheetStatus.createRow(rowNumStatus++);
            rowStatus.createCell(cellNumStatus++).setCellValue((String) clu.getType_title());
        }

        for (int i = 0; i <= headers_status.length - 1; i++) {
            sheetStatus.autoSizeColumn(i);
        }
        
        // Build for feature
        XSSFSheet sheetFe = workbook.createSheet("FEATURES");
        sheetFe.setTabColor(rColor);
        int rowNumFe = 0;
        Row rowFe = sheetFe.createRow(rowNumFe++);
        int cellNumFe = 0;
        for (Object obj : headers_feature) {
            Cell cell = rowFe.createCell(cellNumFe++);
            cell.setCellStyle(style);
            if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer) obj);
            }
        }

        for (Feature clu : feList) {
            cellNumFe = 0;
            rowFe = sheetFe.createRow(rowNumFe++);
            rowFe.createCell(cellNumFe++).setCellValue((String) clu.getFeature_name());
        }

        for (int i = 0; i <= headers_feature.length - 1; i++) {
            sheetFe.autoSizeColumn(i);
        }
        
        // Build for tut
        XSSFSheet sheetTut = workbook.createSheet("INFO");
        sheetTut.setTabColor(gColor);
        int rowNumTut = 0;
        Row rowTut = sheetTut.createRow(rowNumTut++);
        int cellNumTut = 0;
        for (Object obj : headers_notes) {
            Cell cell = rowTut.createCell(cellNumTut++);
            cell.setCellStyle(style);
            if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer) obj);
            }
        }
        
        Cell cellz;

        cellNumTut = 0;

        rowTut = sheetTut.createRow(rowNumTut++);

        cellz = rowTut.createCell(cellNumTut++);
        cellz.setCellValue("> Dont edit ID colum and BASE Sheet!");
        cellz.setCellStyle(style);
        
        cellNumTut = 0;

        rowTut = sheetTut.createRow(rowNumTut++);

        cellz = rowTut.createCell(cellNumTut++);
        cellz.setCellValue("> To create new Function add ID as # character!");
        cellz.setCellStyle(style);
      
        

        for (int i = 0; i <= headers_notes.length - 1; i++) {
            sheetTut.autoSizeColumn(i);
        }

        // Build for base
        XSSFSheet sheetBase = workbook.createSheet("BASE");
        sheetBase.setTabColor(rColor);
        int rowNumBase = 0;
        Row rowBase = sheetBase.createRow(rowNumBase++);
        int cellNumBase = 0;
        for (Object obj : headers_base) {
            Cell cell = rowBase.createCell(cellNumBase++);
            cell.setCellStyle(style);
            if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer) obj);
            }
        }

        cellNumBase = 0;

        rowBase = sheetBase.createRow(rowNumBase++);

        rowBase.createCell(cellNumBase++).setCellValue((Integer) subjectId);
        rowBase.createCell(cellNumBase++).setCellValue((Integer) classId);
        rowBase.createCell(cellNumBase++).setCellValue((Integer) teamId);
        rowBase.createCell(cellNumBase++).setCellValue((Integer) featureId);

        for (int i = 0; i <= headers_base.length - 1; i++) {
            sheetBase.autoSizeColumn(i);
        }
        
        

        try {

            workbook.write(out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Function> importFuncXLSX(InputStream is) {
        ArrayList<Function> clList = new ArrayList<>();
        try {
            int subjectId = -1, classId = -1, teamId = -1, featureId = -1;

            XSSFWorkbook workbook = new XSSFWorkbook(is);

            // bind base
            XSSFSheet sheetBase = workbook.getSheetAt(5);
            Iterator<Row> rowBaseIterator = sheetBase.iterator();
            rowBaseIterator.next();
            // bypass first row
            if (rowBaseIterator.hasNext()) {
                Row rowBase = rowBaseIterator.next();
                Iterator<Cell> cellBaseIterator = rowBase.cellIterator();
                subjectId = (int) cellBaseIterator.next().getNumericCellValue();
                classId = (int) cellBaseIterator.next().getNumericCellValue();
                teamId = (int) cellBaseIterator.next().getNumericCellValue();
                featureId = (int) cellBaseIterator.next().getNumericCellValue();
            }

            // bind base
            XSSFSheet sheetFunc = workbook.getSheetAt(0);
            Iterator<Row> rowFuncIterator = sheetFunc.iterator();
            rowFuncIterator.next();
            // bypass first row

            while (rowFuncIterator.hasNext()) {
                Function func = new Function();
                
                Row rowFunc = rowFuncIterator.next();
                Iterator<Cell> cellFuncIterator = rowFunc.cellIterator();
                int rIdx = 0;
                while (cellFuncIterator.hasNext()) {
                    
                    String rValue = "";
                    Cell cell = cellFuncIterator.next();
                    if (cell.getCellType() == CellType.STRING) {
                        rValue = cell.getStringCellValue().trim();
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        rValue = Integer.toString((int) cell.getNumericCellValue());
                    }
                    // id
                    if (rIdx == 0 && !"#".equals(rValue)) {
                        func.setId(Integer.parseInt(rValue));
                    }
                    if (rIdx == 0 && "#".equals(rValue)) {
                        func.setId(-1);
                    }
                    // name
                    if (rIdx == 1 && !"".equals(rValue)) {
                        func.setName(rValue);
                    }
                    // access
                    if (rIdx == 2 && !"".equals(rValue)) {
                        func.setAccessRoles(rValue);
                    }
                    // complexity
                    if (rIdx == 3 && !"".equals(rValue)) {
                        func.setComplexity(rValue);
                    }
                    // owner
                    if (rIdx == 4 && !"".equals(rValue)) {
                        func.setOwnerName(rValue);
                    }
                    // priority
                    if (rIdx == 5 && !"".equals(rValue)) {
                        func.setPriority(rValue);
                    }
                    // status
                    if (rIdx == 6 && !"".equals(rValue)) {
                        func.setStatusName(rValue);
                    }
                    
                    // feature
                    if (rIdx == 7 && !"".equals(rValue)) {
                        func.setFeatureName(rValue);
                    }
                    // desc
                    if (rIdx == 8) {
                        func.setDescription(rValue);
                    }
                    rIdx++;
                }
                
                func.setFeatureId(-1);
                func.setTeamId(teamId);
                func.setClassId(classId);
                func.setSubjectId(subjectId);
                clList.add(func);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clList;
    }

    public static void exportXLSX(String className, ArrayList<ClassUser> clList, ArrayList<Team> teamList, OutputStream out) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(className + "_USERS_DATA");
        int rownum = 0;
        Row row = sheet.createRow(rownum++);
        int cellnum = 0;
        String[] headers = {"Roll Number", "Full Name", "Gender", "Email", "Team Id", "Team Leader", "Dropout Date", "User Notes", "Ongoing Eval", "Final Present Eval", "Final Topic Eval", "Status"};
        String[] headers_team = {"ID", "Topic Code", "Topic Name"};
        for (Object obj : headers) {
            Cell cell = row.createCell(cellnum++);
            if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer) obj);
            }
        }

        for (ClassUser clu : clList) {
            cellnum = 0;
            row = sheet.createRow(rownum++);
            row.createCell(cellnum++).setCellValue((String) clu.getRollNumber());
            row.createCell(cellnum++).setCellValue((String) clu.getFullName());
            row.createCell(cellnum++).setCellValue((String) clu.getGender());
            row.createCell(cellnum++).setCellValue((String) clu.getEmail());
            row.createCell(cellnum++).setCellValue((Integer) clu.getTeamId());
            row.createCell(cellnum++).setCellValue((Integer) clu.getTeamLeader());
            row.createCell(cellnum++).setCellValue((String) clu.getDropOutDate().toString());
            row.createCell(cellnum++).setCellValue((String) clu.getUserNotes());
            row.createCell(cellnum++).setCellValue((String) clu.getOnGoingEval());
            row.createCell(cellnum++).setCellValue((String) clu.getFinalPresEval());
            row.createCell(cellnum++).setCellValue((String) clu.getFinalTopicEval());
            row.createCell(cellnum++).setCellValue((Integer) clu.getStatus());
        }

        for (int i = 0; i <= headers.length - 1; i++) {
            sheet.autoSizeColumn(i);
        }

        // team sheet
        XSSFSheet sheet_team = workbook.createSheet(className + "_TEAMS");
        int rownumteam = 0;
        Row rowteam = sheet_team.createRow(rownumteam++);
        int cellnumteam = 0;
        for (Object obj : headers_team) {
            Cell cell = rowteam.createCell(cellnumteam++);
            if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else if (obj instanceof Integer) {
                cell.setCellValue((Integer) obj);
            }
        }

        for (Team clu : teamList) {
            cellnumteam = 0;
            row = sheet_team.createRow(rownumteam++);
            row.createCell(cellnumteam++).setCellValue((Integer) clu.getTeam_id());
            row.createCell(cellnumteam++).setCellValue((String) clu.getTopic_code());
            row.createCell(cellnumteam++).setCellValue((String) clu.getTopic_name());
        }

        for (int i = 0; i <= headers_team.length - 1; i++) {
            sheet_team.autoSizeColumn(i);
        }

        // return content
        try {

            workbook.write(out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ClassUser> importXLSX(InputStream is) {
        ArrayList<ClassUser> clList = new ArrayList<>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            // bypass first row
            rowIterator.next();
            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                ClassUser clu = new ClassUser();
                Cell cell = cellIterator.next();
                clu.setRollNumber(cell.getStringCellValue());

                cell = cellIterator.next();
                clu.setFullName(cell.getStringCellValue());
                cell = cellIterator.next();
                clu.setGender(cell.getStringCellValue());
                cell = cellIterator.next();
                clu.setEmail(cell.getStringCellValue());
                cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    clu.setTeamId(Integer.parseInt(cell.getStringCellValue()));
                }
                if (cell.getCellType() == CellType.NUMERIC) {
                    clu.setTeamId((int) cell.getNumericCellValue());
                }
                cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    clu.setTeamLeader(Integer.parseInt(cell.getStringCellValue()));
                }
                if (cell.getCellType() == CellType.NUMERIC) {
                    clu.setTeamLeader((int) cell.getNumericCellValue());
                }
                cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    clu.setDropOutDate(Date.valueOf(cell.getStringCellValue()));
                }

                cell = cellIterator.next();
                clu.setUserNotes(cell.getStringCellValue());
                cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    clu.setOnGoingEval(cell.getStringCellValue());
                }
                if (cell.getCellType() == CellType.NUMERIC) {
                    clu.setOnGoingEval(Double.toString(cell.getNumericCellValue()));
                }
                cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    clu.setFinalPresEval(cell.getStringCellValue());
                }
                if (cell.getCellType() == CellType.NUMERIC) {
                    clu.setFinalPresEval(Double.toString(cell.getNumericCellValue()));
                }
                cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    clu.setFinalTopicEval(cell.getStringCellValue());
                }
                if (cell.getCellType() == CellType.NUMERIC) {
                    clu.setFinalTopicEval(Double.toString(cell.getNumericCellValue()));
                }
                cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    clu.setStatus(Integer.parseInt(cell.getStringCellValue()));
                }
                if (cell.getCellType() == CellType.NUMERIC) {
                    clu.setStatus((int) cell.getNumericCellValue());
                }
                clList.add(clu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clList;
    }

    public static void main(String[] args) {
        //SheetUtils.exportXLSX("SE1619", DAOClassUser.queryClassUsers(1, 100, 60, 1, -1, -1, null));
        //ArrayList<ClassUser> clList = importXLSX( );
        //for (ClassUser u:clList) System.out.println(u);
    }
}
