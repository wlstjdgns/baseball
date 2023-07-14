package service;

import DAO.TeamDao;
import DTO.OutPlayerDTO;
import DTO.TeamRespDTO;
import DTO.TeamPlayerListDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


public class TeamService {
    private Connection connection;

    public TeamService(Connection connection) {
        this.connection = connection;
    }

    TeamDao teamDao = new TeamDao();

    //팀등록
    public void TeamRegistration(Map<String, Object> paramMap) {
        Integer stadiumId = (Integer) paramMap.get("stadiumId");
        String tName = (String) paramMap.get("tName");
        teamDao.insert(paramMap);
        System.out.println("팀 등록 성공");
    }

    //팀전체 목록
    public TeamRespDTO FindAllTeam() {
        teamDao.findByAllTeamwithStadium();
        return null;
    }

    //팀별 선수 목록
    public TeamPlayerListDTO getTeamPlayerList(Map<String, Object> paramMap) {
        Integer tId = (Integer) paramMap.get("tId");
        List<TeamPlayerListDTO> teamPlayerListDTOList = teamDao.findTeamplayer(paramMap);
        for (TeamPlayerListDTO teamPlayerListDTO : teamPlayerListDTOList) {
            System.out.println(teamPlayerListDTO.toString());
        }
        return null;


    }


}
