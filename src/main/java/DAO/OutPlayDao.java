package DAO;

import DBConnection.DBConnection;
import DTO.OutPlayerDTO;
import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OutPlayDao {
    private Connection connection = DBConnection.getInstance();


    //퇴출선수 삽입
    public void insert(Map<String, Object> paramMap){
        String sql = "insert ignore into out_player(player_id, reason, created_at) values(?, ?, now())";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, (Integer) paramMap.get("outplayerId"));
            pstmt.setString(2, (String) paramMap.get("reason"));
            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //퇴출선수 업댓
    public void update(Map<String, Object> paramMap){
        String sql="update player set team_id = ? where p_id =  ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setNull(1, Types.BIGINT);
            pstmt.setInt(2, (Integer) paramMap.get("outplayerId"));

            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //퇴출선수 목록
    public List<OutPlayerDTO> findAll(){
        List<OutPlayerDTO> outPlayersList = new ArrayList<>();
        String sql = "select a.p_id as id, a.p_name as name, a.position as position, " +
                "b.reason as reason, b.created_at as day from player a left outer join  out_player b  " +
                " on a.p_id = b.player_id   order by b.created_at desc";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                OutPlayerDTO outPlayerDTO = new OutPlayerDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getString("reason"),
                        rs.getTimestamp("day")

                );
                outPlayersList.add(outPlayerDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return outPlayersList;
    }









}
