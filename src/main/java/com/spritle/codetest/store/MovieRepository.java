package com.spritle.codetest.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spritle.codetest.core.Movie;


/**
 * MovieRepository is a repository class to perform CRUD operations on movies.
 * 
 * @author Hamad
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
