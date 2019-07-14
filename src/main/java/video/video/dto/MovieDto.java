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
    private int starringNum1;
    private int starringNum2;
    private int starringNum3;
    private int starringNum4;
    private boolean watchedFlg;
}