package com.cg.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.dto.MovieCatalog;

@Repository
public interface MovieCatlogRepository extends
JpaRepository<MovieCatalog, Integer>
{
	@Query("SELECT movcat FROM MovieCatalog movcat where movcat.movieName = :mnm") 
	public MovieCatalog getMovieInfoByName(@Param("mnm")String movName);
	
	@Transactional
	@Modifying
	@Query("delete from MovieCatalog mc where mc.movieName = :mnm")
	void deleteMovie(@Param("mnm") String movName);

}

