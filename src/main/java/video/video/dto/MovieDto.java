package video.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private int id;
    @NotEmpty(message = "タイトルを入力してください")
    private String title;
    @NotEmpty(message = "検索用タイトルを入力してください")
    private String searchTitle;
    @NotNull(message = "リリース年を入力してください")
    private Integer releaseYear;
    @NotEmpty(message = "言語を選択してください")
    private String originalLanguage;
    @NotNull(message = "上映時間を入力してください")
    private Integer showTimes;
    private Integer starringNum1;
    private Integer starringNum2;
    private Integer starringNum3;
    private Integer starringNum4;
    @NotNull(message = "閲覧ステータスを選択してください")
    private boolean watchedFlg;
}
