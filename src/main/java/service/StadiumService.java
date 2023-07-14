package service;
import DAO.StadiumDao;
import model.Stadium;

import java.sql.Connection;
import java.util.Map;

public class StadiumService {
    private Connection connection;
    public StadiumService(Connection connection) {
        this.connection = connection;
    }
    StadiumDao stadiumDao = new StadiumDao();


    public void StadiumRegistration(Map<String, Object> paramMap) {

        String sName = (String) paramMap.get("sName");
        stadiumDao.insert(paramMap);
        System.out.println("야구장 등록 완료");
    }


    public  void  StadiumList(){
       stadiumDao.findAll();
        System.out.println(stadiumDao.findAll());
    }
}
