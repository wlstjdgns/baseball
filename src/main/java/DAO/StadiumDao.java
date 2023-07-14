package DAO;

import DBConnection.DBConnection;
import model.Stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StadiumDao {
    private Connection connection = DBConnection.getInstance();

    //야구장 등록
    public void insert (Map<String, Object> paramMap){
        String sql = "insert into stadium( s_name, created_at) values( ?, now())";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, (String) paramMap.get("sName"));
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //야구장 삭제
    public void delete(Integer sId){
        String sql = "delete from stadium where s_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sId);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //야구장 없댓
    public void update(String sName , Integer sId){
        String sql="update stadium set s_name = ? where s_id =  ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, sName);
            pstmt.setInt(2, sId);

            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //야구장 전체 목록
    public List<Stadium> findAll(){
        List<Stadium> stadiumsList = new ArrayList<>();
        String sql = "select * from stadium order by s_id";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Stadium stadium = new Stadium(
                        rs.getInt("s_id"),
                        rs.getString("s_name"),
                        rs.getTimestamp("created_at")

                );
                stadiumsList.add(stadium);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return stadiumsList;
    }


    //아이디로 야구장 찾기
    public Stadium findById(Integer sId){
        Stadium stadium = null;
        String sql = "select * from stadium where id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                stadium = new Stadium(
                        rs.getInt("s_id"),
                        rs.getString("s_name"),
                        rs.getTimestamp("created_at")

                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return stadium;
    }

















}
