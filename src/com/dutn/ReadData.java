/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dutn;

import com.dutn.dto.Movie;
import com.dutn.dto.User;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadData {

    private List<User> users;

    public ReadData() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void read(String file) {
        try {
            int i = 0;
            int j = 0;
            InputStream is = getClass().getResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                if (i == 0) {
                    for (int ii = 0; ii < arr.length; ii++) {
                        if (i != 0) {
                            User user = new User();
                            user.setName(arr[ii]);
                            users.add(user);
                        }
                        i++;
                    }
                } else {
                    j = 0;
                    for (int jj = 0; jj < arr.length; jj++) {
                        if (j != 0) {
                            Movie movie = new Movie();
                            movie.setName(arr[0]);
                            try {
                                int rating = Integer.parseInt(arr[jj]);
                                movie.setRating(rating);
                            } catch (NumberFormatException numberFormatException) {
                                movie.setRating(0);
                            }
                            users.get(jj-1).addMovies(movie);
                        }
                        j++;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
