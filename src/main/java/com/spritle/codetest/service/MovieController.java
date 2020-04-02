package com.spritle.codetest.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.spritle.codetest.core.Movie;
import com.spritle.codetest.core.MovieDto;
import com.spritle.codetest.core.MovieEntity;
import com.spritle.codetest.core.MovieMapper;

/**
 * MovieController is a controller to perform CRUD operations on movies.
 * 
 * @author Hamad
 *
 */
@RestController
@RequestMapping("/api")
public class MovieController {

	@Autowired
    MovieService movieService;
	
	@Autowired
    RestTemplate restTemplate;

	MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

	/**
	 * getAllMovies is used to retrieve all the  movies list.
	 * 
	 * @return ResponseEntity<List<MovieDto>>
	 */
	@GetMapping("/getAllMovies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieMapper.toMovieDtos(movieService.findAll()));
    }
    
    /**
     * getMoviesByPage is used to get movies by page number.
     * 
     * @param pageNo
     * @return ResponseEntity<List<MovieDto>>
     */
    @GetMapping("/getMoviesByPage/{pageNo}")
    public ResponseEntity<List<MovieDto>> getMoviesByPage(@PathVariable(value = "pageNo") int pageNo) {
        return ResponseEntity.ok(movieMapper.toMovieDtos(movieService.findAllByPage(pageNo)));
    }

    /**
     * createMovie is used to add a movie. 
     * 
     * @param movieDto
     * @return ResponseEntity<MovieDto>
     */
    @PostMapping("/createMovie")
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        movieService.save(movieMapper.toMovie(movieDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDto);
    }

    /**
     * getMovieById is used to get movies by ID.
     * 
     * @param movieId
     * @return ResponseEntity<MovieDto>
     */
    @GetMapping("/findMovieById/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable(value = "id") Long movieId) {
    	Movie movie;
    	try {
    	movie = movieService.findById(movieId).orElseThrow(
				() -> new ResourceNotFoundException("There is no record for the given ID", "id", movieId));
    	} catch (ResourceNotFoundException exception) {
			return ResponseEntity.of(Optional.of("There is no record for the given ID"));
		}
    	return ResponseEntity.ok(movieMapper.toMovieDto(movie));
    }

    /**
     * updateMovie is used to update the movie by ID.
     * 
     * @param movieId
     * @param movieDto
     * @return ResponseEntity<MovieDto>
     */
    @PutMapping("/updateMovieById/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable(value = "id") Long movieId,
			@Valid @RequestBody MovieDto movieDto) {
		try {
			movieService.findById(movieId).orElseThrow(
					() -> new ResourceNotFoundException("There is no record for the given ID", "id", movieId));
			Movie movieDetails = movieMapper.toMovie(movieDto);
			movieDetails.setUId(movieId);
			movieService.save(movieDetails);
		} catch (ResourceNotFoundException exception) {
			return ResponseEntity.of(Optional.of("There is no record for the given ID"));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieDto);
	}

    /**
     * deleteMovie is used to delete the movie by ID.
     * 
     * @param movieId
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/deleteMovieById/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable(value = "id") Long movieId) {
    	try {
        movieService.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no record for the given ID", "id", movieId));
        movieService.deleteById(movieId);
    	} catch(ResourceNotFoundException exception) {
    		return ResponseEntity.of(Optional.of("There is no record for the given ID"));
    	}
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    
    /**
     * fetchFromMoviesApi is used fetch movies using moviesDB API by pageNo.
     * 
     * @param pageNo
     * @return ResponseEntity<?>
     */
    @PostMapping("/fetchFromMoviesApi/{pageNo}")
    public ResponseEntity<?> fetchFromMoviesApi(@PathVariable int pageNo) {
    	try {
		String apiUrl = "https://api.themoviedb.org/4/list/1?page="+pageNo+"&api_key=f744e04541a554aa083d7ae00d1c82d4";
		ResponseEntity<MovieEntity> latestMovies = restTemplate.getForEntity(apiUrl+"&sort_by=release_date.desc",
				MovieEntity.class);
		movieService.saveAll(latestMovies.getBody().getResults());
		} catch (DataIntegrityViolationException exception) {
			return ResponseEntity.of(Optional.of("Record already exists"));
		} catch (HttpClientErrorException exception) {
			return ResponseEntity.of(Optional.of("Page Number should be less than 500"));
		}
		return ResponseEntity.of(Optional.of("Records are successfully inserted"));
    }
}
