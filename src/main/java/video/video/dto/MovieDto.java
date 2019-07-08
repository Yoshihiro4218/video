package video.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    @NotNull
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String originalTitle;
    @NotNull
    private int releaseYear;
    @NotNull
    private String originalLanguage;
    @NotNull
    private int showTimes;
    private int starringNum1;
    private int starringNum2;
    private int starringNum3;
    private int starringNum4;
    @NotNull
    private boolean watchedFlg;

    //TODO 一つのDTOにするのか・・・？

    private int name;
}
