/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dutn.dto;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private List<Movie> movies;
    
    private double distance = 0;

    public User() {
        movies = new ArrayList<>();
    }
    
    public User(String name, List<Movie> movies) {
        this.name = name;
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    
    public void addMovies(Movie movie) {
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", movies=" + movies + '}';
    }
    
}
