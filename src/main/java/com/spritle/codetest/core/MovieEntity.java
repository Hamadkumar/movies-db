
package com.spritle.codetest.core;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * MovieEntity is used as a entity to parse movie details from json.
 * 
 * @author Hamad
 *
 */
@Data
public class MovieEntity {

    @JsonProperty("results")
    private List<Movie> results = new ArrayList<Movie>();

}
