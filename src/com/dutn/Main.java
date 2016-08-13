/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dutn;

import com.dutn.dto.User;
import java.util.List;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReadData readData = new ReadData();
        readData.read("Movie_Ratings.csv");
        List<User> users = readData.getUsers();
        Distance distance = new Distance(users);
//        List<User> sort = distance.computeNearestNeighbor("\"Bryan\"", users);
        distance.recommendation("\"Jeff\"");
    }
    
}
