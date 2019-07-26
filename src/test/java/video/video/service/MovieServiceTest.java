package video.video.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import video.video.dto.MovieDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    @InjectMocks
    MovieService target;
    @Mock
    JdbcTemplate jdbcTemplate;

    private static final RowMapper<MovieDto> MAPPER = new BeanPropertyRowMapper<>(MovieDto.class);

    @Test
    public void updateWatchedFlgWhenFigIsTrueNow() {
        int movieId = 77;
        MovieDto movie = new MovieDto();
        movie.setId(movieId);
        movie.setWatchedFlg(true);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt()))
                .thenReturn(movie);
        String actual = target.updateWatchedFlg(movieId);
        assertEquals("false", actual);
    }

    @Test
    public void updateWatchedFlgWhenFigIsFalseNow() {
        int movieId = 77;
        MovieDto movie = new MovieDto();
        movie.setId(movieId);
        movie.setWatchedFlg(false);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt()))
                .thenReturn(movie);
        String actual = target.updateWatchedFlg(movieId);
        assertEquals("true", actual);
    }
}
