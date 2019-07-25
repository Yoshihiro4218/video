package video.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private int id;
    @NotEmpty(message = "タイトルを入力してください")
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
