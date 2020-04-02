package com.spritle.codetest.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.Data;

/**
 * Movie class is used to store the details about the movies.
 * 
 * @author Hamad
 *
 */
@Data
@Entity
@Table(name = "movies" , schema="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("u_id")
    private long uId;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("id")
    @Column(unique=true)
    private Integer id;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("title")
    private String title;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("vote_count")
    private Integer voteCount;

    @JsonProperty("video")
    private Boolean video;

    @JsonProperty("vote_average")
    private Integer voteAverage;

    @Override
    public String toString() {
    	ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	String json = StringUtils.EMPTY;
    	try {
    		 json = objectWriter.writeValueAsString(this);
		} catch (JsonProcessingException exception) {
			exception.printStackTrace();
		}
		return json;
    }
}
