package service;
import DAO.PlayerDao;
import java.sql.Connection;



public class PlayerService {
    private Connection connection;

    public PlayerService(Connection connection) {
        this.connection = connection;
    }
    PlayerDao playerDao = new PlayerDao();


    public void PlayerRegistration(Integer teamId, String tName, String position) {
        playerDao.insert(teamId, tName, position);
        System.out.println("선수 등록완료");
    }


}














