/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dutn;

import com.dutn.dto.Movie;
import com.dutn.dto.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Distance {

    private final List<User> mUsers;

    public Distance(List<User> users) {
        this.mUsers = users;
    }

    public double pearson(User user1, User user2) {
        double r = 0;
        int n = 0;
        List<Movie> movies1 = user1.getMovies();
        List<Movie> movies2 = user2.getMovies();
        n = movies1.size();
        double sumxy = 0, sumx = 0, sumy = 0, sumx2 = 0, sumy2 = 0;
        for (int i = 0; i < n; i++) {
            Movie m1 = movies1.get(i);
            Movie m2 = movies2.get(i);
            sumxy += (double) (m1.getRating() * m2.getRating());
            sumx += (double) m1.getRating();
            sumy += (double) m2.getRating();
            sumx2 += (double) (Math.pow(m1.getRating(), 2));
            sumy2 += (double) (Math.pow(m2.getRating(), 2));
        }

        double numerator = (sumxy - ((sumx * sumy) / n));
        double denominator = Math.sqrt(sumx2 - (Math.pow(sumx, 2) / n))
                * Math.sqrt(sumy2 - (Math.pow(sumy, 2) / n));
        if (denominator == 0 || Double.isNaN(denominator)) {
            return 0;
        }
        r = numerator / denominator;
        return r;
    }

    public List<User> computeNearestNeighbor(String name, List<User> users) {
        List<User> results = new ArrayList<>();
        User user = getUserByName(name);
        if (user == null) {
            System.err.println(name + " is not exists");
            return null;
        }

        for (int i = 0; i < mUsers.size(); i++) {
            User u = mUsers.get(i);
            if (!u.getName().equals(user.getName())) {
                double pearson = pearson(user, u);
                System.out.println(u.getName() + " pearson " + pearson);
                double distance = 0;
                double add = 0;
                for (int j = 0; j < u.getMovies().size(); j++) {
                    Movie m1 = u.getMovies().get(j);
                    Movie m2 = user.getMovies().get(j);
                    int rating = Math.abs(m1.getRating() - m2.getRating());
                    add += Math.pow(rating, pearson);
                }
                distance = Math.pow(add, 1 / pearson);
                u.setDistance(distance);
                results.add(u);
            }
        }
        
//        quickSort(users, 0, users.size() - 1);

        return results;
    }

    public void recommendation(String name) {
        List<User> users = computeNearestNeighbor(name, mUsers);
        User u1 = getUserByName(name);
        double min = 0;
        int index = 0;
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (i == 0) {
                min = user.getDistance();
                index = 0;
            }
            if (user.getDistance() < min) {
                min = user.getDistance();
                index = i;
            }
        }
        
        User u2 = users.get(index);
        
        System.out.printf("%s is selected to recommend for %s\n", u2.getName(), name);
        
        for (int j = 0; j < u2.getMovies().size(); j++) {
            Movie m2 = u2.getMovies().get(j);
            for (int k = 0; k < u1.getMovies().size(); k++) {
                Movie m1 = u1.getMovies().get(k);
                if (m2.getName().equals(m1.getName())) {
                    if (m1.getRating() == 0 && m2.getRating() != 0) {
                        System.out.println("[" + m2.getName() + ": " + m2.getRating() + "]");
                    }
                }
            }
        }
    }

    public User getUserByName(String name) {
        for (int i = 0; i < mUsers.size(); i++) {
            User user = mUsers.get(i);
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
    
    private void quickSort(List<User> users, int left, int right) {
        int i = left, j = right;
        int middle = (left + right) / 2;
        double pivot = users.get(middle).getDistance();
        
        while (i < j) {
            while (users.get(i).getDistance() < pivot) {
                i++;
            }
            while (users.get(j).getDistance() > pivot) {
                j--;
            }
            if (i <= j) {
                User temp = users.get(i);
                users.set(i, users.get(j));
                users.set(j, temp);
                i++;
                j--;
            }
        }
        
        if (left < j) {
            quickSort(users, left, j);
        }
        if (right > i) {
            quickSort(users, i, right);
        }
        
    }

}
