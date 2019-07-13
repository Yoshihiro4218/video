package video.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeDto {
    // TODO マスタ毎にテーブル分けたほうがよいので、変更します。
    private int id;
    private int codeType;
    private String codeName;
    private int codeNum;
    private String value;
}
