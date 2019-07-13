package video.video.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import video.video.dto.CodeDto;

import java.util.List;

@Service
@Slf4j
public class CodeService {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<CodeDto> MAPPER = new BeanPropertyRowMapper<>(CodeDto.class);

    public CodeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CodeDto> selectSpecificCodeList(String codeType) {
        List<CodeDto> codeList = jdbcTemplate.query("SELECT * FROM code WHERE code_type = ?", MAPPER, codeType);
        log.info("Code={}", codeList);
        return codeList;
    }
}
