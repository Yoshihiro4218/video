package video.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeDto {
    private int id;
    private String codeType;
    private int codeNum;
    private String value;
}
