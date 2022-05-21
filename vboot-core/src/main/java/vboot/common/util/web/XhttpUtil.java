package vboot.common.util.web;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class XhttpUtil {

    public static String httpGetConnect(String httpUrl, String key) {
        String back = "error";
        if (StringUtils.isBlank(httpUrl)) {
            return back;
        }
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = "";
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/html");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    //sbf.append("\r\n");
                }
                result = sbf.toString();
                back = result;
            } else {
                System.out.println("http链接错误！" + key);
                back = "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            back = "error";
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }
        return back;
    }

    public static String httpPostConnect(String httpUrl, String param, String key) {
        String back = "";
        if (StringUtils.isBlank(httpUrl)) {
            return back;
        }
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            /** 设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个 */
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);
            /** 设置通用的请求属性 */
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置传入参数的格式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //name1=value1&name2=value2 的形式
            //connection.setRequestProperty("Content-Type", "application/json;charset=utf-8"); //json格式
            //connection.connect();

            os = connection.getOutputStream();
            if (StringUtils.isNotBlank(param)) {
                os.write(param.getBytes());
            }

            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sbf.append(line);
                    //sbf.append("\r\n");
                }
                back = sbf.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            back = "error";
        } finally {
            // 关闭资源
            try {
                if (null != br) {
                    br.close();
                }
                if (null != is) {
                    is.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            connection.disconnect();// 关闭远程连接
        }
        return back;
    }


}
