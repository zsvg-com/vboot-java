package vboot.common.util.file;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.*;

public class XpdfUtil {


    /**
     * 中间或者两边水印
     * @param bos   添加完水印的输出
     * @param input 原PDF文件输入
     * @param word  水印内容
     * @param model 水印添加位置1中间，2两边
     */
    public static void setWatermark(BufferedOutputStream bos, InputStream input, String word, int model) {
        PdfReader reader = null;
        try {
            reader = new PdfReader(input);
            PdfStamper stamper = new PdfStamper(reader, bos);
            PdfContentByte content;
            // 创建字体,第一个参数是字体路径,itext有一些默认的字体比如说：
//            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            //2  BaseFont base = BaseFont.createFont("/msyh.ttf", BaseFont.IDENTITY_H,
            //         BaseFont.EMBEDDED);
                //这个是为了解决linux服务器，水印乱码问题，如果是windows服务器，用第一个就可以。
            // 需要指定的字体，用第2，3个写法，在resources文件夹下放置对应字体就行。

//            BaseFont base =  BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            BaseFont base = BaseFont.createFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
//            BaseFont base = BaseFont.createFont();

            PdfGState gs = new PdfGState();
            // 获取PDF页数
            int total = reader.getNumberOfPages();
            // 遍历每一页
            for (int i = 0; i < total; i++) {
                // 页宽度
                float width = reader.getPageSize(i + 1).getWidth();
                // 页高度
                float height = reader.getPageSize(i + 1).getHeight();
                // 内容
                content = stamper.getOverContent(i + 1);
                //开始写入文本
                content.beginText();
                //水印透明度
                gs.setFillOpacity(0.2f);
                content.setGState(gs);
                content.setColorFill(BaseColor.LIGHT_GRAY);
                //设置字体的输出位置
                content.setTextMatrix(70, 200);

                if (model == 1) {
                    //平行居中的3条水印
                    //字体大小
                    content.setFontAndSize(base, 50);
                    //showTextAligned 方法的参数分别是（文字对齐方式，位置内容，输出水印X轴位置，Y轴位置，旋转角度）
                    content.showTextAligned(Element.ALIGN_CENTER, word, width / 2, 650, 30);
                    content.showTextAligned(Element.ALIGN_CENTER, word, width / 2, 400, 30);
                    content.showTextAligned(Element.ALIGN_CENTER, word, width / 2, 150, 30);
                } else {
                    // 左右两边个从上到下4条水印
                    // 水印旋转度数
                    float rotation = 30;
                    content.setFontAndSize(base, 20);
                    content.showTextAligned(Element.ALIGN_LEFT, word, 20, height - 50, rotation);
                    content.showTextAligned(Element.ALIGN_LEFT, word, 20, height / 4 * 3 - 50, rotation);
                    content.showTextAligned(Element.ALIGN_LEFT, word, 20, height / 2 - 50, rotation);
                    content.showTextAligned(Element.ALIGN_LEFT, word, 20, height / 4 - 50, rotation);

                    content.setFontAndSize(base, 22);
                    content.showTextAligned(Element.ALIGN_RIGHT, word, width - 20, height - 50, rotation);
                    content.showTextAligned(Element.ALIGN_RIGHT, word, width - 20, height / 4 * 3 - 50, rotation);
                    content.showTextAligned(Element.ALIGN_RIGHT, word, width - 20, height / 2 - 50, rotation);
                    content.showTextAligned(Element.ALIGN_RIGHT, word, width - 20, height / 4 - 50, rotation);
                }
                //结束写入文本
                content.endText();
                //要打图片水印的话
                //Image image = Image.getInstance("c:/1.jpg");
                //content.addImage(image);
            }
            stamper.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}
