package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Team {
    private Integer tId; //pk
    private Integer stadiumId;
    private String tName;
    private Timestamp createdAt;
}