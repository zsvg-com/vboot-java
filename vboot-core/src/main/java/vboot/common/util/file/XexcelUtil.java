package vboot.common.util.file;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class XexcelUtil
{
    //转换列数字序号列字母序号
    public static String excelColIndexToStr(int columnIndex)
    {
        if (columnIndex <= 0)
        {
            return null;
        }
        String columnStr = "";
        columnIndex--;
        do
        {
            if (columnStr.length() > 0)
            {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
            columnIndex = ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr;
    }


    //---------------------以下为EXCEL复制功能-----------------


    //行复制功能
    public static void copyRow(Row fromRow, Row toRow, boolean copyValueFlag) {
        toRow.setHeight(fromRow.getHeight());
        for(Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext(); ) {
            Cell tmpCell = (Cell) cellIt.next();
            Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(tmpCell, newCell, copyValueFlag);
        }
        Sheet worksheet = fromRow.getSheet();
        for(int i = 0; i < worksheet.getNumMergedRegions(); i++) {
            CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
            if(cellRangeAddress.getFirstRow() == fromRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(toRow.getRowNum(), (toRow.getRowNum() +
                        (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())), cellRangeAddress
                        .getFirstColumn(), cellRangeAddress.getLastColumn());
                worksheet.addMergedRegionUnsafe(newCellRangeAddress);
            }
        }
    }

    //复制单元格  true则连同cell的内容一起复制
    public static void copyCell(Cell srcCell, Cell distCell, boolean copyValueFlag) {
        //样式
        distCell.setCellStyle(srcCell.getCellStyle());
        //评论
        if(srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // 不同数据类型处理
        CellType srcCellType = srcCell.getCellTypeEnum();
        distCell.setCellType(srcCellType);
        if(copyValueFlag) {
            if(srcCellType == CellType.NUMERIC) {
                if(DateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            } else if(srcCellType == CellType.STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            } else if(srcCellType == CellType.BLANK) {

            } else if(srcCellType == CellType.BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            } else if(srcCellType == CellType.ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            } else if(srcCellType == CellType.FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());
            } else {
            }
        }
    }

    //复制单元格  true则连同cell的内容一起复制
    public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short index)
    {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(index);
        style.setBorderBottom(BorderStyle.HAIR);
        style.setBorderTop(BorderStyle.HAIR);
        style.setBorderLeft(BorderStyle.HAIR);
        style.setBorderRight(BorderStyle.HAIR);
        return style;
    }


}
