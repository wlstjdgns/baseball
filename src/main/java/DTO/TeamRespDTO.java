package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TeamRespDTO {
    private Integer tId;
    private Integer stadiumId;
    private String tName;
    private  String sName;



}
