package service;

import DAO.PlayerDao;
import DTO.PositionRespDTO;
import DTO.TeamPlayerListDTO;
import model.Player;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;


public class PlayerService {
    private Connection connection;

    public PlayerService(Connection connection) {
        this.connection = connection;
    }

    PlayerDao playerDao = new PlayerDao();


    public void registPlayer(Map<String, Object> paramMap) {

        try {
            Integer teamId = (Integer) paramMap.get("teamId");
            String pName = (String) paramMap.get("pName");
            String position = (String) paramMap.get("position");


            if (teamId != null) {
                playerDao.insert(paramMap);
                System.out.println("선수를 등록하였습니다.");
                return;

            } else {
                System.out.println("이미 존재하는 아이디입니다.");
            }
        }
        //2. 이미 있으면 있다고 알림! 없으면 넣어야지.

        catch (Exception e) {
            e.printStackTrace();
        }

    }
        public PositionRespDTO positionRespTPlayer(){
            playerDao.findPositionTPlayer();
            return null;
        }





}