package com.zsvg.vboot.common.util.file;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class XfileUtil
{
    public static String getFileSize(long fileS)
    {
        String size = "";
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileS < 1024)
        {
            size = df.format((double) fileS) + "B";
        } else if (fileS < 1048576)
        {
            size = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824)
        {
            size = df.format((double) fileS / 1048576) + "MB";
        } else
        {
            size = df.format((double) fileS / 1073741824) + "GB";
        }
        return size;
    }

    public static void unZipFiles(File zipFile,String descDir)throws IOException
    {
        File pathFile = new File(descDir);
        if(!pathFile.exists())
        {
            pathFile.mkdirs();
        }
        //解决zip文件中有中文目录或者中文文件
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        for(Enumeration entries = zip.entries(); entries.hasMoreElements();)
        {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String zipEntryName = entry.getName();
            int len2=zipEntryName.split("/")[0].length();
            zipEntryName = zipEntryName.substring(len2, zipEntryName.length());
            System.out.println(zipEntryName);
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if(!file.exists())
            {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if(new File(outPath).isDirectory())
            {
                continue;
            }
            //输出文件路径信息
//            System.out.println(outPath);
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while((len=in.read(buf1))>0)
            {
                out.write(buf1,0,len);
            }
            in.close();
            out.close();
        }
//        System.out.println("******************解压完毕********************");
    }

}
