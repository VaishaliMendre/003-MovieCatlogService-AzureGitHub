package com.cg.ctrl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.MovieCatalog;
import com.cg.exception.MovieNotFoundException;
import com.cg.service.IMovieCatalogService;

@RestController
@RequestMapping("/movieCatlog")
public class MovieCatalogController
{
	@Autowired
	IMovieCatalogService movieCatlogServ;
	//---------------------------------------------
	//http://localhost:8081/movieCatlog
	@GetMapping
	public Set<MovieCatalog> fetchAllMovieCatlog()
	{
		return movieCatlogServ.getAllMovieCatalog();		
	}	
	//---------------------------
	//http://localhost:8081/movieCatlog/1004
	@GetMapping("/{movId}")	
	public MovieCatalog getMovieById(@PathVariable("movId")int mId)
	throws MovieNotFoundException
	{
		MovieCatalog cat = movieCatlogServ.getMovieCatalogById(mId);
		System.out.println("No Movie: "+cat);
		if(cat==null) {
			throw new MovieNotFoundException("movie Id not found"+mId);
		}
		else {
		return cat;
		}
	}
	
	//---------------------------
	//http://localhost:8081/movieCatlog
	//{"movieId":1007,"movieName":"ABC"}
	@PostMapping()
	public HashSet<MovieCatalog> insertMovie(@RequestBody MovieCatalog mov) 
	{		
		System.out.println(mov +" in  insertMovie ctrl..........................");
		movieCatlogServ.addMovie(mov);		
		System.out.println(mov +" mov  Added..........");
		return (HashSet)movieCatlogServ.getAllMovieCatalog()	;

	}	
	
	@DeleteMapping(value="/{movieId}")
    public String deleteTodo(@PathVariable("movieId") int movieId) {
		movieCatlogServ.deleteMovie(movieId);
        return "Sucessfully deleted movie "+movieId;
    }
	
	@PutMapping(value="/{movieId}")
    public String updateMovie(@PathVariable("movieId") int movieId, @RequestBody MovieCatalog movieCatalog) {
		movieCatlogServ.updateMovie(movieId, movieCatalog);
	return "Sucessfully updated movie "+movieId;
       
    }
	
}
