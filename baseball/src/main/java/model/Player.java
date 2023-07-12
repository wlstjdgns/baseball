package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class Player {
    private  Integer pId; //pk
    private  Integer teamId;
    private  String pName;
    private  String position;
    private Timestamp createdAt;

}
