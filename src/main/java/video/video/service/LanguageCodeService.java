package video.video.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import video.video.dto.LanguageCodeDto;

import java.util.List;

@Service
@Slf4j
public class LanguageCodeService {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<LanguageCodeDto> MAPPER = new BeanPropertyRowMapper<>(LanguageCodeDto.class);

    public LanguageCodeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LanguageCodeDto> selectLanguageCodeList() {
        List<LanguageCodeDto> languageList = jdbcTemplate.query("SELECT * FROM language_code ORDER BY id;", MAPPER);
        log.info("LanguageCode={}", languageList);
        return languageList;
    }
}
