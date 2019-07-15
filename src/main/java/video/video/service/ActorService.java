package video.video.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import video.video.dto.ActorDto;

import java.util.List;

@Service
@Slf4j
@CrossOrigin
public class ActorService {

    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<ActorDto> MAPPER = new BeanPropertyRowMapper<>(ActorDto.class);

    public ActorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ActorDto> indexActorList() {
        List<ActorDto> list = jdbcTemplate.query("SELECT id, name, birthday FROM actor ORDER BY id;", MAPPER);
        log.info("ActorList:{}", list);
        return list;
    }
}
