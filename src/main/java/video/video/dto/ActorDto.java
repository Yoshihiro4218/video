package video.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto {

    private int id;
    private String name;
    private String sex;
    private int birthday;
    private int height;
    private String citizenship;
    private int mainWorks1;
    private int mainWorks2;
    private int mainWorks3;
    private int mainWorks4;
    private boolean deadFlg;
}
