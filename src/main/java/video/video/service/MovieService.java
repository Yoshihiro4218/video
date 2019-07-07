package video.video.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import video.video.dto.MovieDto;

import java.util.List;

@Service
@Slf4j
public class MovieService {

    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<MovieDto> MAPPER = new BeanPropertyRowMapper<>(MovieDto.class);

    public MovieService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MovieDto> indexMovieList() {
        List<MovieDto> list = jdbcTemplate.query("SELECT * FROM blog ORDER BY created_at DESC", MAPPER);
        log.info("MovieList:{}", list);
        return list;
    }
}
