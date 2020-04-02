package com.spritle.codetest.core;

import lombok.Data;

/**
 * MovieDto is a data transfer object to store movie details.
 * 
 * @author Hamad
 *
 */
@Data
public class MovieDto {

    private long uId;

    private String posterPath;

    private Boolean adult;

    private String overview;

    private String releaseDate;

    private Integer id;

    private String originalTitle;

    private String originalLanguage;

    private String title;

    private String backdropPath;

    private Double popularity;

    private Integer voteCount;

    private Boolean video;

    private Integer voteAverage;

}
