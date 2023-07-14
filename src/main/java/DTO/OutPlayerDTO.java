package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
@AllArgsConstructor
public class OutPlayerDTO {
    private Integer id;
    private String name;
    private String position;
    private String readson;
    private Timestamp day;
}
