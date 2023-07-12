package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;


@Getter
@AllArgsConstructor
@ToString
public class OutPlayer {
    private  Integer outId;
    private  Integer playerId;
    private  String reason;
    private Timestamp createdAt;
}
