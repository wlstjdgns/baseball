package service;
import DAO.TeamDao;
import DTO.TeamRespDTO;
import DTO.TeamPlayerListDTO;

import java.sql.Connection;

public class TeamService {
    private Connection connection;
    public TeamService(Connection connection) {
        this.connection = connection;
    }

    TeamDao teamDao = new TeamDao();

    public  void TeamRegistration(Integer stadiumId, String tName){
        teamDao.insert(stadiumId, tName);
        System.out.println("팀 등록 성공");
    }

    public TeamRespDTO FindAllTeam() {
        teamDao.findByAllTeamwithStadium();
        return null;
    }

public TeamPlayerListDTO TeamPlayerList(Integer tId){
        teamDao.findTeamplayer(tId);
        return  null;


}













}
