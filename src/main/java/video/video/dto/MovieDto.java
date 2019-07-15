package video.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private int id;
    private String title;
    private String searchTitle;
    private int releaseYear;
    private String originalLanguage;
    private int showTimes;
    private Integer starringNum1;
    private Integer starringNum2;
    private Integer starringNum3;
    private Integer starringNum4;
    private boolean watchedFlg;
}
