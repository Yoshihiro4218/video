package video.video.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import video.video.dto.CitizenshipCodeDto;

import java.util.List;

@Service
@Slf4j
public class CitizenshipCodeService {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<CitizenshipCodeDto> MAPPER = new BeanPropertyRowMapper<>(CitizenshipCodeDto.class);

    public CitizenshipCodeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CitizenshipCodeDto> selectCitizenshipCodeList() {
        List<CitizenshipCodeDto> citizenshipList = jdbcTemplate.query("SELECT * FROM citizenship_code ORDER BY id;", MAPPER);
        log.info("LanguageCode={}", citizenshipList);
        return citizenshipList;
    }
}
