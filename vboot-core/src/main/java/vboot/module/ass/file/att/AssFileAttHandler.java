package vboot.module.ass.file.att;

import vboot.common.util.file.XfileUtil;
import vboot.common.util.lang.XdateUtil;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class AssFileAttHandler {

    @Value("${app.att.path}")
    private String ATT_PATH;

    public List<AssFileAtt> saveFiles(MultipartFile[] files) throws IOException
    {
        List<AssFileAtt> attList = new ArrayList<>();
        if (files != null)
        {
            for (int i = 0; i < files.length; i++)
            {
                String fileName = files[i].getOriginalFilename();

                if (!"".equals(fileName))
                {
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    String pName;
                    String sName = "";
                    if(fileName.contains(".")){
                        pName= fileName.substring(0,fileName.lastIndexOf("."));
                        sName = fileName.substring(fileName.lastIndexOf(".")+1);
                    }else{
                        pName=fileName;
                    }
                    String uuid = XstringUtil.getUUID();
                    String newName = uuid +"."+ sName;
                    int code = newName.hashCode();
                    String dirY = XdateUtil.getYYYY();
                    String dirM = XdateUtil.getMM();
                    String dirD = XdateUtil.getDD();
                    String dirX = Integer.toHexString(code & 0xf);
                    String savePath = dirY + "/" + dirM + "/" + dirD + "/" + dirX;
                    File targetFolder = new File(ATT_PATH + "/" + savePath);
                    File targetFile = new File(ATT_PATH + "/" + savePath, newName);
                    if (!targetFolder.exists())
                    {
                        targetFolder.mkdirs();
                    }
                    files[i].transferTo(targetFile);
                    AssFileAtt att = new AssFileAtt();
                    att.setZsize(XfileUtil.getFileSize(files[i].getSize()));
                    att.setPname(pName);
                    att.setAddress(savePath);
                    att.setId(uuid);
                    att.setSname(sName);
                    attList.add(att);
                }
            }
        }
        return attList;
    }

    public AssFileAtt saveFile(MultipartFile file) throws IOException
    {
        AssFileAtt att = new AssFileAtt();

        String fileName = file.getOriginalFilename();
        if (!"".equals(fileName))
        {
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            String pName;
            String sName = "";
            if(fileName.contains(".")){
                pName= fileName.substring(0,fileName.lastIndexOf("."));
                sName = fileName.substring(fileName.lastIndexOf(".")+1);
            }else{
                pName=fileName;
            }
            String uuid = XstringUtil.getUUID();
            String newName = uuid +"."+ sName;
            int code = newName.hashCode();
            String dirY = XdateUtil.getYYYY();
            String dirM = XdateUtil.getMM();
            String dirD = XdateUtil.getDD();
            String dirX = Integer.toHexString(code & 0xf);
            String savePath = dirY + "/" + dirM + "/" + dirD + "/" + dirX;
            File targetFolder = new File(ATT_PATH + "/" + savePath);
            File targetFile = new File(ATT_PATH + "/" + savePath, newName);
            if (!targetFolder.exists())
            {
                targetFolder.mkdirs();
            }
            att.setZsize(XfileUtil.getFileSize(file.getSize()));
            att.setPname(pName);
            att.setAddress(savePath);
            att.setId(uuid);
            att.setSname(sName);
            file.transferTo(targetFile);
//            if("jpg".equals(sName)||"JPG".equals(sName)||"png".equals(sName)||"PNG".equals(sName)){
//                Thumbnails.of(ATT_PATH + "/" + savePath+"/"+newName).scale(1f).outputQuality(0.25f).toFile(ATT_PATH + "/" + savePath+"/"+uuid +"_2.jpg");
//            }
        }
        return att;
    }

    public AssFileAtt saveFile2(MultipartFile file) throws IOException
    {
        AssFileAtt att = new AssFileAtt();

        String fileName = file.getOriginalFilename();
        if (!"".equals(fileName))
        {
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            String pName;
            String sName = "";
            if(fileName.contains(".")){
                pName= fileName.substring(0,fileName.lastIndexOf("."));
                sName = fileName.substring(fileName.lastIndexOf(".")+1);
            }else{
                pName=fileName;
            }
            String uuid = XstringUtil.getUUID();
            String newName = uuid +"."+ sName;
            int code = newName.hashCode();
            String dirY = XdateUtil.getYYYY();
            String dirM = XdateUtil.getMM();
            String dirD = XdateUtil.getDD();
            String dirX = Integer.toHexString(code & 0xf);
            String savePath = dirY + "/" + dirM + "/" + dirD + "/" + dirX;
            File targetFolder = new File(ATT_PATH + "/" + savePath);
            File targetFile = new File(ATT_PATH + "/" + savePath, newName);
            if (!targetFolder.exists())
            {
                targetFolder.mkdirs();
            }
            att.setZsize(XfileUtil.getFileSize(file.getSize()));
            att.setPname(pName);
            att.setAddress(savePath);
            att.setId(uuid);
            att.setSname(sName);
            file.transferTo(targetFile);
        }
        return att;
    }

    public AssFileAtt saveImg(String path, String imgData) throws IOException
    {
        AssFileAtt att = new AssFileAtt();
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        byte[] b = decoder.decodeBuffer(imgData);
        for (int i = 0; i < b.length; ++i)
        {
            if (b[i] < 0)
            {//调整异常数据
                b[i] += 256;
            }
        }
        //生成jpeg图片
        String dirY = XdateUtil.getYYYY();
        String dirM = XdateUtil.getMM();
        String dirD = XdateUtil.getDD();
        String uid= XstringUtil.getUUID();
        String savePath =dirY + "/" + dirM + "/" + dirD + "/" +uid + ".png";
        byte2File(b, path + "att/kk/" + "/" + dirY + "/" + dirM + "/" + dirD+"/", uid + ".png");
        att.setAddress(savePath);
        att.setId(uid);
        return att;
    }

    public void download(HttpServletRequest request,
                         HttpServletResponse response, AssFileAtt att) throws Exception
    {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String realName = att.getPname()+"."+att.getSname();
        String storeName = att.getAddress() + "/" + att.getId() + realName.substring(realName.lastIndexOf("."));
        String downLoadPath = ATT_PATH + "/" + storeName;

        long fileLength = new File(downLoadPath).length();

        response.setContentType("application/octet-stream");
        realName = URLEncoder.encode(realName, "UTF-8");
        Locale localLanguage = request.getLocale();
        if (realName.length() > 150)
        {
            realName = new String(realName.getBytes("gb2312"), "ISO8859-1");
        }
        response.setHeader("Content-disposition", "attachment; filename=" + realName);
        response.setHeader("Content-Length", String.valueOf(fileLength));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
        {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    public void downloadByPath(HttpServletRequest request,
                         HttpServletResponse response, String name,String path) throws Exception
    {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String downLoadPath = ATT_PATH + "/" + path;

        long fileLength = new File(downLoadPath).length();

        response.setContentType("application/octet-stream");
        name = URLEncoder.encode(name, "UTF-8");
        Locale localLanguage = request.getLocale();
        if (name.length() > 150)
        {
            name = new String(name.getBytes("gb2312"), "ISO8859-1");
        }
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setHeader("Content-Length", String.valueOf(fileLength));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
        {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    private  void byte2File(byte[] buf, String filePath, String fileName){
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try
        {
            File dir = new File(filePath);
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bos != null)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
