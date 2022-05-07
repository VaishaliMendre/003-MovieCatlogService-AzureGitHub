package com.cg.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.dao.MovieCatlogRepository;
import com.cg.dto.MovieCatalog;

@Service
public class MovieCatalogService implements IMovieCatalogService 
{
	@Autowired
	MovieCatlogRepository movieRepo;
	public static final String CG_TOPIC="MovieTopic";
	@Autowired
	org.springframework.kafka.core.KafkaTemplate<String,String> KafkaTemplate;

	public Set<MovieCatalog> getAllMovieCatalog() {
		ArrayList<MovieCatalog> movList=(ArrayList)movieRepo.findAll();
		HashSet<MovieCatalog> movSet=new  HashSet<MovieCatalog>();
		for(MovieCatalog mc:movList)
		{
			movSet.add(mc);
		}		
		return movSet;
	}

	public MovieCatalog getMovieCatalogById(int movId) {
		Optional<MovieCatalog> optionalCat=	movieRepo.findById(movId);
		System.out.println("ins service ..optional.."+optionalCat);
		if(!optionalCat.isEmpty()) {
			return optionalCat.get();	
		}
		else {
			return null;
		}
	}

	
	public MovieCatalog addMovie(MovieCatalog movie) {
		movieRepo.save(movie);
		KafkaTemplate.send(CG_TOPIC," Movie Insrted : " +movie.getMovieName());
		System.out.println(" movie insrted....");
		
		Optional<MovieCatalog> cat=	movieRepo.findById(movie.getMovieId());
		return cat.get();

	}

	public void deleteMovie(int movieid) {
		movieRepo.deleteById(movieid);
		
	}

	public void updateMovie(int movieId, MovieCatalog movieCatalog) {
		MovieCatalog movieCatalogfrmDb = movieRepo.findById(movieId).get();
		System.out.println(movieCatalogfrmDb.toString());
		movieCatalogfrmDb.setMovieId(movieCatalog.getMovieId());
		movieCatalogfrmDb.setMovieName(movieCatalog.getMovieName());
		movieRepo.save(movieCatalogfrmDb);
		
	}
	

}

/*
 * @Override
	public Set<MovieCatalog> getAllMovieCatalog() 
	{
		ArrayList<MovieCatalog> movList=(ArrayList)movieRepo.findAll();
		HashSet<MovieCatalog> movSet=new  HashSet<MovieCatalog>();
		for(MovieCatalog mc:movList)
		{
			movSet.add(mc);
		}		
		return movSet;
	}

	
	@Override
	public MovieCatalog getMovieCatalogById(int movId) {
		Optional<MovieCatalog> optionalCat=	movieRepo.findById(movId);
		System.out.println("ins service ..optional.."+optionalCat);
		if(!optionalCat.isEmpty()) {
			return optionalCat.get();	
		}
		else {
			return null;
		}
	}

	@Override
	public MovieCatalog addMovie(MovieCatalog movie)
	{
		movieRepo.save(movie);
		System.out.println(" movie insrted....");
		Optional<MovieCatalog> cat=	movieRepo.findById(movie.getMovieId());
		return cat.get();

	}
	
	@Override
	public void deleteMovie(int movieid) {
		movieRepo.deleteById(movieid);
	}

	@Override
	public void updateMovie(int movieId, MovieCatalog movieCatalog) {
		MovieCatalog movieCatalogfrmDb = movieRepo.findById(movieId).get();
		System.out.println(movieCatalogfrmDb.toString());
		movieCatalogfrmDb.setMovieId(movieCatalog.getMovieId());
		movieCatalogfrmDb.setMovieName(movieCatalog.getMovieName());
		movieRepo.save(movieCatalogfrmDb);
	}
 * 
 * */



