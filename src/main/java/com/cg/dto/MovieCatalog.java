package com.cg.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mov_cat")
public class MovieCatalog 
{	
	@Id
	@Column(name="mov_id",length=10)
	private int movieId;
	
	@Column(name="mov_name",length=50)
	private String movieName;
	public MovieCatalog() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public MovieCatalog(int movieId, String movieName) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
	}

	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
}