package DAO;

import model.Player;
import service.PlayerService;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
    Connection connection = DBConnection.getInstance();
    public void insert(Integer teamId, String pName, String position){

        String sql = "insert into player(team_id, p_name, position, created_at) values(?, ?, ?, now())";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,teamId);
            pstmt.setString(2,pName);
            pstmt.setString(3,position);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Integer pId){
        String sql = "delete from player where p_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, pId);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(String position , Integer pId){
        String sql="update player set position = ? where p_id =  ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, position);
            pstmt.setInt(2, pId);

            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Player> findAll(){
        List<Player> playersList = new ArrayList<>();
        String sql = "select * from player order by team_id";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Player player = new Player(
                        rs.getInt("p_id"),
                        rs.getInt("team_id"),
                        rs.getString("p_name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")

                );
                playersList.add(player);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return playersList;
    }


    public Player findById(Integer pId){
        Player player = null;
        String sql = "select * from player where p_id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, pId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                player = new Player(
                        rs.getInt("p_id"),
                        rs.getInt("team_id"),
                        rs.getString("p_name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")

                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return player;
    }























}
