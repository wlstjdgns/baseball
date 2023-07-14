import DAO.OutPlayDao;
import DAO.PlayerDao;
import DAO.StadiumDao;
import DAO.TeamDao;
import DBConnection.DBConnection;
import DTO.TeamRespDTO;
import model.Player;
import model.Stadium;
import model.Team;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class BaseballApp {
    static Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        StadiumService stadiumService = new StadiumService(connection);
        PlayerService playerService = new PlayerService(connection);
        TeamService teamService = new TeamService(connection);
        OutPlayerService outPlayerService = new OutPlayerService(connection);


        Scanner scanner = new Scanner(System.in);

        //질문
        System.out.println("무엇을 도와드릴까요?");

        //입력
        String result = scanner.nextLine();  // 입력값 예시 : "퇴출등록?playerId=1&reason=도박"


//        //서비스에 넘길 파라미터 세팅
        Map<String, Object> paramMap = new HashMap<String, Object>();

        //=================야구장 등록==================
        if(result.equals("야구장등록")) {
            System.out.println("등록할 야구장 이름: ");
            String sName = scanner.nextLine();
            paramMap.put("sName", sName);

            stadiumService.StadiumRegistration(paramMap);
        }

        //================전체 야구장 목록보기=================
        if(result.equals("전체 야구장 목록보기")) {
            stadiumService.StadiumList();
        }

        //================팀등록=======================
        if(result.equals("팀등록")) {
            System.out.println("등록할 팀의 야구장 변호: ");
            Integer stadiumId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("등록할 팀 이름: ");
            String tName = scanner.nextLine();
            paramMap.put("stadiumId", stadiumId);
            paramMap.put("tName", tName);

            teamService.TeamRegistration(paramMap);
        }


        //========전체 팀 목록==================
        if(result.equals("전체 팀 목록")) {
            teamService.FindAllTeam();
        }

        //================선수 둥록 ==============
        if(result.equals("선수등록")) {
            System.out.println("등록할 선수의 팀 번호: ");
            Integer teamId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("등록할 선수 이름: ");
            String pName = scanner.nextLine();
            System.out.println("선수의 포지션은: ");
            String position = scanner.nextLine();
            paramMap.put("teamId", teamId);
            paramMap.put("pName", pName);
            paramMap.put("position", position);

            playerService.registPlayer(paramMap); //선수등록 서비스
        }





        //==============선수 퇴출 등록==================
        if (result.equals("퇴출등록")) {
            System.out.println("퇴출등록 선수번호는: ");
            Integer outplayerId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("퇴출이유도 말해주세요");
            String outReason = scanner.nextLine();

            paramMap.put("outplayerId", outplayerId);
            paramMap.put("outReason", outReason);

            outPlayerService.registOutPlayer(paramMap);

            //퇴출자등록 서비스
        }

        //==============선수 퇴출 목록=============
        if (result.equals("퇴출목록")) {
            outPlayerService.outPlayerList(); //퇴출자등록 서비스
        }



        //============포지션별 팀 야구 선수 페이지=================
        if (result.equals("포지션별목록")) {
            playerService.positionRespTPlayer();
        }



        /*다시*/
        //=========팀별 선수 목록=================
        if(result.equals("팀별 선수 목록")) {
            System.out.println("팀 번호: ");
            Integer tId = scanner.nextInt();
            teamService.getTeamPlayerList(paramMap);
        }


    }

}
