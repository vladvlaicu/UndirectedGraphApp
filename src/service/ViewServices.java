/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author elev
 */
public class ViewServices {
       
    public static List<String> getDemands(){
        Path path = Paths.get("./data/Demands.txt");
        List<String> list = Arrays.asList();
        try {
            list = Files.lines(path).collect(Collectors.toList());
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static int processCommand(String command) {
      return Integer.parseInt(command.substring(0, 2).trim()) ;
    }
}
