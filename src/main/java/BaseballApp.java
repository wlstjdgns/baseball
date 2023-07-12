import DAO.OutPlayDao;
import DAO.PlayerDao;
import DAO.StadiumDao;
import DAO.TeamDao;
import DBConnection.DBConnection;
import model.Player;
import model.Stadium;
import model.Team;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.sql.Timestamp;


public class BaseballApp {
    static Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        StadiumService stadiumService = new StadiumService(connection);
        PlayerService playerService = new PlayerService(connection);
        TeamService teamService = new TeamService(connection);
        OutPlayerService outPlayerService = new OutPlayerService(connection);

//        Stadium stadium = new Stadium(4, "서영홈", currentTimestamp);
//        stadiumDao.insert(stadium);

     //   stadiumService.StadiumRegistration("우리팀");
//        stadiumDao.update("테스트 서영팀",4);

//        stadiumDao.delete(4);


//        Stadium stadium = stadiumDao.findById(1);
//        System.out.println(stadium.toString());


//        Team team = new Team(4,4,"서영팀",currentTimestamp);
//        teamDao.insert(team);

//        teamDao.update("영팀",7);

//        Team team = teamDao.findById(1);
//        System.out.println(team.toString());

//
//        Player player = new Player(30,7,"서영박","투수",currentTimestamp);
//        playerDao.insert(player);

//        playerDao.update("포수",30);

//        Player player = playerDao.findById(30);
//        System.out.println(player.toString());



      //  playerService.PlayerRegistration(7,"운동하자","우견수");
       //stadiumService.StadiumRegistration("부산");

        //stadiumService.StadiumList();

       // teamService.TeamRegistration(6,"우리팀");












    }

}
