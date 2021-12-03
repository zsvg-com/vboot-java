package com.zsvg.vboot.module.ass.file.excel;

import com.zsvg.vboot.common.util.file.XexcelUtil;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("unchecked")
public class ExcelHandler {
    //excel导出
//    public HSSFWorkbook createXlsWithMapByMap(Map<String, String> map, List<Map<String, Object>> list)
//    {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet();
//        sheet.autoSizeColumn(1, true);
//        //字段设置
//        HSSFRow row0 = sheet.createRow(0);
//        int k = 0;
//        for (String key : map.keySet())
//        {
//            HSSFCell cell0 = row0.createCell(k);
//            cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
//            if(key.contains("_")){
//                key=key.substring(3);
//            }
//            cell0.setCellValue(key);
//            k++;
//        }
//        //字段描述设置
//        HSSFRow row1 = sheet.createRow(1);
//        int l = 0;
//        for (String value : map.values())
//        {
//            HSSFCell cell1 = row1.createCell(l);
//            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
//            cell1.setCellValue(value);
//            l++;
//        }
//        //先转成jsonarray再设置其余行
//        for (int i = 0; i < list.size(); i++)
//        {
//            HSSFRow row = sheet.createRow(i + 2);
//            int j = 0;
//            for (String key : map.keySet())
//            {
//                if(key.contains("_")){
//                    key=key.substring(3);
//                }
//                HSSFCell cell = row.createCell(j);
//                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//                if (list.get(i).get(key) == null)
//                {
//                    cell.setCellValue("");
//                } else
//                {
//                    cell.setCellValue("" + list.get(i).get(key));
//                }
//                j++;
//            }
//        }
//        return workbook;
//    }


    //excel导出
    public XSSFWorkbook createXlsxByObjList(List<Map<String, String>> fieldList, List dataList,Boolean firstLineFlag) throws NoSuchFieldException, IllegalAccessException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        DataFormat format = workbook.createDataFormat();
        XSSFSheet sheet = workbook.createSheet();

        CellStyle numberStyle = workbook.createCellStyle();
        numberStyle.setDataFormat(format.getFormat("#,#0.00"));

//        Font font1 = workbook.createFont();
//        font1.setFontName("Arial");
//        font1.setFontHeightInPoints((short) 11);
//        CellStyle cellStyle=workbook.createCellStyle();
//            cellStyle.setFont(font1);
//            cellStyle.setWrapText(true);//自动换行
//            cellStyle.setAlignment(HorizontalAlignment.CENTER);
//            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//            cellStyle.setDataFormat(format.getFormat("0.0"));
        if(firstLineFlag){
            XSSFRow row0 = sheet.createRow(0);
            XSSFRow row1 = sheet.createRow(1);
            for (int i = 0; i < fieldList.size(); i++) {
                XSSFCell cell0 = row0.createCell(i);
                cell0.setCellValue(fieldList.get(i).get("field"));
                XSSFCell cell1 = row1.createCell(i);
                cell1.setCellValue(fieldList.get(i).get("title"));
            }
        }else{
            XSSFRow row0 = sheet.createRow(0);
            for (int i = 0; i < fieldList.size(); i++) {
                XSSFCell cell0 = row0.createCell(i);
                cell0.setCellValue(fieldList.get(i).get("title"));
            }
        }

        //先转成jsonarray再设置其余行
        for (int i = 0; i < dataList.size(); i++) {
            XSSFRow row;
            if(firstLineFlag){
                row = sheet.createRow(i + 2);
            }else{
                row = sheet.createRow(i + 1);
            }
            for (int j = 0; j < fieldList.size(); j++) {
                XSSFCell cell = row.createCell(j);
                Field field = dataList.get(i).getClass().getDeclaredField(fieldList.get(j).get("field"));
                field.setAccessible(true);//这里是不是可以再优化，减少每次都反射
                Object obj = field.get(dataList.get(i));
                if ("number".equals(fieldList.get(j).get("type"))) {
                    cell.setCellStyle(numberStyle);
                    cell.setCellValue(Double.parseDouble(""+obj));
                }else{
                    cell.setCellValue(obj == null ? "" : "" + obj);
                }

                //最后一次的时候调整列宽
                if (i == dataList.size() - 1&&i<=5000) {
                    if (fieldList.get(j).get("width") != null && !"0".equals(fieldList.get(j).get("width"))) {
                        sheet.setColumnWidth(j, Integer.parseInt(fieldList.get(j).get("width")) * 256);//手动设置
                    } else {
                        sheet.autoSizeColumn(j); //自动调整第N列宽度
                        if ("0".equals(fieldList.get(j).get("width")) || XstringUtil.containChinese("" + obj)) {
                            sheet.setColumnWidth(j, sheet.getColumnWidth(j) * 17 / 10);
                        }
                        if(sheet.getColumnWidth(j)<fieldList.get(j).get("title").length()*2*256){
                            sheet.setColumnWidth(j,fieldList.get(j).get("title").length()*2*256+256);
                        }
                    }
                }
            }
        }
        return workbook;
    }



    //excel导出
    public XSSFWorkbook createXlsxByMapList(List<Map<String, String>> fieldList, List<Map<String, Object>> mapList,Boolean firstLineFlag) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        DataFormat format = workbook.createDataFormat();
        XSSFSheet sheet = workbook.createSheet();
        XSSFCellStyle numberStyle = workbook.createCellStyle();
        numberStyle.setDataFormat(format.getFormat("#,#0.00"));

        XSSFCellStyle integerStyle = workbook.createCellStyle();
        integerStyle.setDataFormat(format.getFormat("#,#0"));//数据格式只显示整数

        // 设置标题
        CellStyle greenTitleStyle = getGreenTitleStyle(workbook);
       if(firstLineFlag){
            XSSFRow row0 = sheet.createRow(0);
            row0.setHeight(Short.valueOf("400"));
            XSSFRow row1 = sheet.createRow(1);
            for (int i = 0; i < fieldList.size(); i++) {
                XSSFCell cell0 = row0.createCell(i);
                cell0.setCellValue(fieldList.get(i).get("field"));
                cell0.setCellStyle(greenTitleStyle);
                XSSFCell cell1 = row1.createCell(i);
                cell1.setCellValue(fieldList.get(i).get("title"));
                cell1.setCellStyle(greenTitleStyle);

            }
        }else{
            XSSFRow row0 = sheet.createRow(0);
            for (int i = 0; i < fieldList.size(); i++) {
                XSSFCell cell0 = row0.createCell(i);
                cell0.setCellValue(fieldList.get(i).get("title"));
                cell0.setCellStyle(greenTitleStyle);

            }
        }
//
//        XSSFCellStyle hssfCellStyleDouble = workbook.createCellStyle();
//        XSSFDataFormat df = workbook.createDataFormat(); // 此处设置数据格式
//        hssfCellStyleDouble.setDataFormat(df.getFormat("#,##0.0"));//保留1位小数点
//


        //先转成jsonarray再设置其余行
        for (int i = 0; i < mapList.size(); i++) {
            XSSFRow row;
            if(firstLineFlag){
                row = sheet.createRow(i + 2);
            }else{
                row = sheet.createRow(i + 1);
            }
            for (int j = 0; j < fieldList.size(); j++) {
                XSSFCell cell = row.createCell(j);
                String value=mapList.get(i).get(fieldList.get(j).get("field"))+"";

                //if ("number".equals(fieldList.get(j).get("type"))) {
                    if("null".equals(value)){
                        cell.setCellValue("");
                    }else{
                        if(value.matches("^(-?\\d+)(\\.\\d+)?$")){//判断是否为数值
                            if(value.matches("^[-\\+]?[\\d]*$")){//判断是否为整数
                                cell.setCellValue(Double.parseDouble(value));
                                cell.setCellStyle(integerStyle);
                            }else{
                                cell.setCellValue(Double.parseDouble(value));
                                cell.setCellStyle(numberStyle);
                            }
                        }else{
                            cell.setCellValue(value);
                        }
                    }
                /*}else{
                    cell.setCellValue("null".equals(value)? "" :value);
                }*/

//                //最后一次的时候调整列宽 注意这个一个速度会慢很多。当行数小于5000调整
                if (i == mapList.size() - 1&&i<=5000) {
                    if (fieldList.get(j).get("width") != null && !"0".equals(fieldList.get(j).get("width"))) {
                        sheet.setColumnWidth(j, Integer.parseInt(fieldList.get(j).get("width")) * 256);//手动设置
                    } else {
                        sheet.autoSizeColumn(j); //自动调整第N列宽度
                        if ("0".equals(fieldList.get(j).get("width")) || XstringUtil.containChinese(value)) {
                            sheet.setColumnWidth(j, sheet.getColumnWidth(j) * 17 / 10);
                        }
                        if(sheet.getColumnWidth(j)<fieldList.get(j).get("title").length()*2*256){
                            sheet.setColumnWidth(j,fieldList.get(j).get("title").length()*2*256+256);
                        }
                    }
                }
            }
        }
        return workbook;
    }


    //---------------------辅助处理方法----------------------------------------------
    private CellStyle getGreenTitleStyle(Workbook workBook) {
        CellStyle titleStyle = getCommonStyle(workBook);
        titleStyle.setFillForegroundColor( HSSFColor.LIME.index);// 背景色
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);// 完全填充
        titleStyle.setFont(getTitleFont(workBook));// 字体
        return titleStyle;
    }


    //通用字体
    public Font getCommonFont(Workbook workBook) {
        Font headFont = workBook.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 10);// 字体大小
        return headFont;
    }

    //标题字体
    public Font getTitleFont(Workbook workBook) {
        Font headFont = getCommonFont(workBook);
        headFont.setBold(true);// 加粗
        return headFont;
    }

    public CellStyle getCommonStyle(Workbook workBook) {
        CellStyle commonStyle = workBook.createCellStyle();
        commonStyle.setRightBorderColor(HSSFColor.BLACK.index);
        commonStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        commonStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        commonStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        commonStyle.setLocked(true);
        commonStyle.setWrapText(true);// 自动换行
        return commonStyle;
    }


    //-----------------------------------------导入开始结束--------------------------------------
    //excel导入
    public List<Map<String, Object>> getData(MultipartFile file) throws Exception
    {
        Workbook workbook = getWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        //第1行是字段名称
        Row row0 = sheet.getRow(0);
        //列数
        int cellNum = sheet.getRow(0).getLastCellNum();
        //行数
        int rowNum = sheet.getLastRowNum();

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        //从第3行开始导入
        for (int i = 2; i <= rowNum; i++)
        {
            Row row = sheet.getRow(i);
            if (row.getCell(0) == null || "".equals(row.getCell(0)))
            {
                break;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            for (int j = 1; j < cellNum; j++)
            {
                map.put(XexcelUtil.getCellValue(row0.getCell(j)) + "", XexcelUtil.getCellValue(row.getCell(j)));
            }
            list.add(map);
        }
        return list;
    }


    public Workbook getWorkbook(MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        String fileName = file.getOriginalFilename();
        if (fileName.toLowerCase().endsWith("xls")) {
            return new HSSFWorkbook(in);
        }
        if (fileName.toLowerCase().endsWith("xlsx")) {
            return new XSSFWorkbook(in);
        }
        return null;
    }




}
