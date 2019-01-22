/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import view.MenuFrame;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UndirectedGraph;



/**
 *
 * @author elev
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MenuFrame menuFrame = new MenuFrame();

        UndirectedGraph graph = UndirectedGraph.getInitGraph();
        System.out.println(graph);
//        byte[][] matrix = graph.getAdjacencyMatrix();
//        for(int i = 1; i <= graph.getNumNodes(); i++) {
//            for(int j = 1; j <= graph.getNumNodes(); j++) {
//                System.out.print(matrix[i][j]);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
        
//        for(int i : graph.getNodesGrades()) {
//            System.out.print(String.valueOf(i) + " ");
//        }
//        System.out.println(graph.getNodeGrade(2));

        System.out.println(graph.getAdjacentNodesForEdge(0)[0]);
        
    }
    
}
