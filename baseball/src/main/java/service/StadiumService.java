package service;
import DAO.StadiumDao;
import model.Stadium;

import java.sql.Connection;

public class StadiumService {
    private Connection connection;
    public StadiumService(Connection connection) {
        this.connection = connection;
    }
    StadiumDao stadiumDao = new StadiumDao();


    public void StadiumRegistration(String sName) {
        stadiumDao.insert(sName);
        System.out.println("야구장 등록 완료");
    }


    public  void  StadiumList(){
       stadiumDao.findAll();
        System.out.println(stadiumDao.findAll());
    }
}
