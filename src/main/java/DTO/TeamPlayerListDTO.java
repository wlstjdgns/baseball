package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TeamPlayerListDTO {
    private  Integer tId;
    private  String tName;
    private  String pName;
    private  String position;
}
