package DAO;

import DBConnection.DBConnection;
import DTO.OutPlayerDTO;
import DTO.TeamRespDTO;
import  DTO.TeamPlayerListDTO;
import model.Team;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeamDao {
    private Connection connection = DBConnection.getInstance();

    //팀 등록
    public void insert(Map<String, Object> paramMap){
        String sql = "insert into team(stadium_id, t_name, created_at) values(?, ?, now())";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, (Integer) paramMap.get("stadiumId"));
            pstmt.setString(2,(String) paramMap.get("tName"));
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //팀 삭제
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

    //팀 업댓
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

    //팀 목록
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


    //팀 아이디로 찾기
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
    //팀과 야구장
    public TeamRespDTO findByAllTeamwithStadium(){
        TeamRespDTO dto = null;
        String sql="select team.*, stadium.s_name \n" +
                " from team team left outer join stadium stadium on team.stadium_id = stadium.s_id\n";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                dto = new TeamRespDTO(
                        rs.getInt("t_id"),
                        rs.getInt("stadium_id"),
                        rs.getString("t_name"),
                        rs.getString("s_name")
                ); System.out.println(dto);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }


    //팀과 선수
    public List<TeamPlayerListDTO> findTeamplayer(Map<String, Object> paramMap){

        List<TeamPlayerListDTO> teamPlayerListDTOList = new ArrayList<>();
        String sql="select team.*,player.p_name,player.position\n" +
                "from team team left outer join player player on team.t_id = player.team_id\n" +
                "where team_id =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, (Integer) paramMap.get("tId"));
            ResultSet rs = pstmt.executeQuery();
           while (rs.next()){
               TeamPlayerListDTO  teamPlayerListDTO = new TeamPlayerListDTO(
                        rs.getInt("t_id"),
                        rs.getString("t_name"),
                        rs.getString("p_name"),
                        rs.getString("position")
                        );
               teamPlayerListDTOList.add(teamPlayerListDTO);
            }
           // System.out.println(teamPlayerListDTOList);

        }catch (Exception e){
            e.printStackTrace();
        }
        return teamPlayerListDTOList;
    }












}
