package co.jp.ths.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author yutf 2018/6/21
 *
 */
public class HttpUtils {
    
    private HttpUtils() {}
    
    /** 
     * @throws IOException 
     * @Description:使用HttpURLConnection发送get请求 
     */  
    public static String sendGet(String urlParam, String charset) throws IOException {  
        StringBuilder resultBuffer = null;  
        HttpURLConnection con = null;  
        BufferedReader br = null;  
        try {  
            URL url = new URL(urlParam);  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.connect();  
            resultBuffer = new StringBuilder();  
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }  
        } catch (IOException e) {  
            throw new IOException("Http Get Error happens", e);  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                } finally {  
                    con.disconnect();  
                    con = null;  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
    
    public static String sendPost(String urlParam, Map<String,String> header, String charset) throws IOException {  
        StringBuilder resultBuffer = null;  
        
        HttpURLConnection con = null;  
        OutputStreamWriter osw = null;  
        BufferedReader br = null;  
        // 发送请求  
        try {  
            URL url = new URL(urlParam);  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestMethod("POST");  
            con.setDoOutput(true);  
            con.setDoInput(true);  
            con.setUseCaches(false);  
            if (!header.isEmpty()) {
                for(Entry<String, String> entry: header.entrySet()) {
                    con.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            osw = new OutputStreamWriter(con.getOutputStream(), charset);
            osw.flush();
            // 读取返回内容  
            resultBuffer = new StringBuilder();  
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }
        } catch (Exception e) {  
            throw new IOException("Http Post Error happens", e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                } finally {  
                    con.disconnect();  
                    con = null;  
                }  
            }  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                } finally {  
                    con = null; 
                }  
            }  
        }  
  
        return resultBuffer.toString();  
    }  
}
