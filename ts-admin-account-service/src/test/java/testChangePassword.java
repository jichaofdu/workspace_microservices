import accounts.domain.Account;
import accounts.domain.NewPasswordInfo;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class testChangePassword {

    public static void main(String[] args) throws Exception{
        Account acc = getAccountByPhoneNum("352323");
        System.out.println(acc.getName());


        URL url = new URL("http://localhost:12343/changePassword");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("PUT");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        connection.connect();
        //POST请求
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        //登录信息
        NewPasswordInfo npi = new NewPasswordInfo();
        npi.setId(acc.getId());
        npi.setOldPassword("defaultPassword");
        npi.setNewPassword("jichaofudan");
        Gson gson = new Gson();
        String str = gson.toJson(npi);
        System.out.println(str);
        //写入
        out.write(str.getBytes("UTF-8"));//这样可以处理中文乱码问题
        out.flush();
        out.close();
        //读取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String lines;
        StringBuffer sb = new StringBuffer("");
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8");
            sb.append(lines);
        }
        System.out.println(sb);
        reader.close();
        // 断开连接
        connection.disconnect();



    }

    public static Account getAccountByPhoneNum(String phoneNum) throws  Exception{
        URL url = new URL("http://localhost:12343/findAccount/" + phoneNum);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        connection.connect();
        //读取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String lines;
        StringBuffer sb = new StringBuffer("");
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8");
            sb.append(lines);
        }
        String jsonData = sb.toString();
        System.out.println(sb);
        reader.close();
        // 断开连接
        connection.disconnect();
        Gson gson = new Gson();
        Account result = gson.fromJson(jsonData, Account.class);
        return result;
    }
}