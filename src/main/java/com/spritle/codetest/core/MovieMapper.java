package com.spritle.codetest.core;

import java.util.List;

import org.mapstruct.Mapper;

/**
 * MovieMapper is a mapper class for MovieDTO.
 * 
 * @author Hamad
 *
 */
@Mapper(componentModel="spring")
public abstract class MovieMapper {
	
	public abstract MovieDto toMovieDto(Movie movie);

	public abstract List<MovieDto> toMovieDtos(List<Movie> movies);
    
	public abstract Movie toMovie(MovieDto movieDto);

}
