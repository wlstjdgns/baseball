package service;
import DAO.OutPlayDao;
import DAO.PlayerDao;
import DTO.OutPlayerDTO;
import model.Player;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OutPlayerService {
    private Connection connection;

    public OutPlayerService(Connection connection) {
        this.connection = connection;
    }

    PlayerDao playerDao = new PlayerDao();

    OutPlayDao outPlayDao = new OutPlayDao();


     // 퇴사자등록 서비스
    public void registOutPlayer(Map<String, Object> paramMap) {


        //1. 퇴사자가 있는지 없는지 체크
        // db접근 -> 파라미터로 플레이어아이디를넘김 -> 존재하면 있는거
        try {
            Integer outplayerId = (Integer) paramMap.get("outplayerId");

            Player player = playerDao.findById(outplayerId);
            //2. 없으면 없다고 알림! 있으면 왜또 넣냐처리

            if(player == null){
                System.out.println("존재하지 않는 플레이어입니다.");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //3. 있으면 퇴출선수를 등록한다.
        outPlayDao.insert(paramMap);
        //4. 등록했으면 플레이어 명단 삭제 - 퇴사자 삭제처리
        outPlayDao.update(paramMap);

        System.out.println("성공");
    }


     // 퇴사자목록 서비스
    public void outPlayerList(){
        //1.퇴사자목록가져오기
        List< OutPlayerDTO> outPlayerList = outPlayDao.findAll();

        //2.출력하기
        for (OutPlayerDTO outPlayerDTO : outPlayerList){
            System.out.println(outPlayerDTO.toString());
        }
    }

}
