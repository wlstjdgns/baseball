package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getInstance(){
        String url="jdbc:mysql://localhost:3306/baseball";
        String username ="root";
        String password="1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            //System.out.println("DB 연결 성공");
            return  connection;
        } catch (Exception e) {
            System.out.println("DB 연결 실패: "+ e.getMessage());
        }
        return  null;
    }
}
