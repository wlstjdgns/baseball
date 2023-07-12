package DAO;

import DBConnection.DBConnection;
import model.Stadium;
import model.Team;
import DTO.TeamRespDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    private Connection connection = DBConnection.getInstance();

    public void insert(Integer stadiumId, String tName){
        String sql = "insert into team(stadium_id, t_name, created_at) values(?, ?, now())";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, stadiumId);
            pstmt.setString(2,tName);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Integer tId){
        String sql = "delete from team where t_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tId);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void update(String tName , Integer tId){
        String sql="update team set t_name = ? where t_id =  ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, tName);
            pstmt.setInt(2, tId);
            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Team> findAll(){
        List<Team> teamsList = new ArrayList<>();
        String sql = "select * from team order by t_id";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Team team = new Team(
                        rs.getInt("t_id"),
                        rs.getInt("stadium_id"),
                        rs.getString("t_name"),
                        rs.getTimestamp("created_at")

                );
                teamsList.add(team);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return teamsList;
    }


    public Team findById(Integer tId){
        Team team = null;
        String sql = "select * from team where t_id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                team = new Team(
                        rs.getInt("t_id"),
                        rs.getInt("stadium_id"),
                        rs.getString("t_name"),
                        rs.getTimestamp("created_at")

                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return team;
    }

    public TeamRespDTO findByNamewithStadium(){
        TeamRespDTO dto = null;
        String sql="select team.*, stadium.s_name stadiumname\n" +
                " from team team left outer join stadium stadium on team.stadium_id = stadium.s_id\n";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                dto = new TeamRespDTO(
                        rs.getInt("t_id"),
                        rs.getInt("stadium_id"),
                        rs.getString("t_name"),
                        rs.getString("s_name")

                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return dto;
    }




}