package DAO;

import DTO.PositionRespDTO;
import DTO.TeamPlayerListDTO;
import model.Player;
import service.PlayerService;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerDao {
    Connection connection = DBConnection.getInstance();

    //선수 등록
    public void insert(Map<String, Object> paramMap) {

        String sql = "insert into player(team_id, p_name, position, created_at) values(?, ?, ?, now())";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, (Integer) paramMap.get("teamId"));
            pstmt.setString(2, (String) paramMap.get("pName"));
            pstmt.setString(3, (String) paramMap.get("position"));
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //선수 삭제
    public void delete(Integer pId) {
        String sql = "delete from player where p_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, pId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //선수 업댓
    public void update(String position, Integer pId) {
        String sql = "update player set position = ? where p_id =  ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, position);
            pstmt.setInt(2, pId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //선수 전체 목록
    public List<Player> findAll() {
        List<Player> playersList = new ArrayList<>();
        String sql = "select * from player order by team_id";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("p_id"),
                        rs.getInt("team_id"),
                        rs.getString("p_name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")

                );
                playersList.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return playersList;
    }


    //선수 아이디로 찾기
    public Player findById(Integer pId) {
        Player player = null;
        String sql = "select * from player where p_id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, pId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                player = new Player(
                        rs.getInt("p_id"),
                        rs.getInt("team_id"),
                        rs.getString("p_name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")

                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return player;
    }


    //포지션별 팀 선수 목록
    public PositionRespDTO findPositionTPlayer() {
        PositionRespDTO dto = null;
        String sql = "select\n" +
                "  position  '포지션',\n" +
                "  MAX(case when t_id = 1 then p_name else null end)  '애틀랜타 브레이브스',\n" +
                "  MAX(case when  t_id = 2 then p_name else null end)  '마이애미 말린소',\n" +
                "  MAX(case when  t_id = 3 then p_name else null end)  '필라델피아 필리스'\n" +
                "from\n" +
                "(\n" +
                "  select\n" +
                "    p.p_name  p_name,\n" +
                "    p.position  position,\n" +
                "    t.t_id  t_id\n" +
                "  from\n" +
                "    player p\n" +
                "    inner join team t on p.team_id = t.t_id\n" +
                ") p\n" +
                "\n" +
                "group by\n" +
                "  position";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new PositionRespDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                System.out.println(dto);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;



    }



}
