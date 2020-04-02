package com.spritle.codetest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spritle.codetest.core.Movie;
import com.spritle.codetest.store.MovieRepository;

import lombok.RequiredArgsConstructor;

/**
 * MovieService is a service class to perform CRUD operations on movies.
 * 
 * @author Hamad
 *
 */
@Service
@Configurable
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    /**
     * findAll is used to find all the movies list.
     * 
     * @return List<Movie>
     */
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * findById is used to find movies by ID.
     * 
     * @return Optional<Movie>
     */
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    /**
     * save is used to save the movie details.
     * 
     * @param movie
     */
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * deleteById is used to delete a movie by ID.
     * 
     * @param id
     */
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

	/**
	 * findAllByPage is used to find movies by page.
	 * 
	 * @param pageNo
	 * @return List<Movie>
	 */
	public List<Movie> findAllByPage(int pageNo) {
		int moviesPerPage = 5;
		Pageable pageable = PageRequest.of(pageNo, moviesPerPage); // Using pageable interface to do pagination, 5 movies per page.
		Page<Movie> moviePage =  movieRepository.findAll(pageable);
		return moviePage.getContent();
	}

	/**
	 * saveAll is used to save the list of movies.
	 * 
	 * @param lastestMovieResult
	 */
	public void saveAll(List<Movie> lastestMovieResult) {
		movieRepository.saveAll(lastestMovieResult);		
	}

}