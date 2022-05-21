package vboot.common.util.web;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public class XresUtil {


    public static void downloadExcelXls(HttpServletResponse response, HSSFWorkbook workbook, String name) throws IOException
    {
        String fileName = URLEncoder.encode(name, "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
        response.setContentType("application/vnd.ms-excel");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }

    public static void downloadExcelXlsx(HttpServletResponse response, XSSFWorkbook workbook, String name) throws IOException
    {
        String fileName = URLEncoder.encode(name, "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("application/vnd.ms-excel");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }
}
