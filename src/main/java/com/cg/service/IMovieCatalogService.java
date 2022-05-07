package com.cg.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.cg.dto.MovieCatalog;

@Service
public interface IMovieCatalogService 
{
	public Set<MovieCatalog> getAllMovieCatalog();
	public MovieCatalog getMovieCatalogById(int movId);
	public MovieCatalog addMovie(MovieCatalog movie);
	public void deleteMovie(int movieid);
	public void updateMovie(int movieId, MovieCatalog movieCatalog);
}