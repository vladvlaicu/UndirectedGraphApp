/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author elev
 */
public class Graph {
    
    private int nEdges;
    private int nNodes;
    private List<Edge> list = Arrays.asList();
    
   public Graph(){
   
   }
    
    public static Graph getInitGraph(){
        return initGraph();
    }

    public int getnEdges() {
        return nEdges;
    }

    public void setnEdges(int nEdges) {
        this.nEdges = nEdges;
    }

    public int getnNodes() {
        return nNodes;
    }

    public void setnNodes(int nNodes) {
        this.nNodes = nNodes;
    }

    public List<Edge> getList() {
        return list;
    }

    public void setList(List<Edge> list) {
        this.list = list;
    }
    
    public static Graph initGraph(){
        Path pathEdge = Paths.get("./GraphData/GraphDataEdges.txt");
        Path pathNumNode = Paths.get("./GraphData/GraphDataNrNodes.txt");
        Graph graph = new Graph();
        try {
            int  nrNodes = Integer.parseInt(Files.readAllLines(pathNumNode).get(0));
            graph.setnNodes(nrNodes);
            initEdges(pathEdge);
            int nrEdges =(int) Files.lines(pathEdge)
                               .count();
            graph.setnEdges(nrEdges);
            graph.setList(Graph.initEdges(pathEdge));
        } catch (IOException | IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return graph;
    }

    private static List<Edge> initEdges(Path pathEdge) throws IOException {
        List<Edge> list = Files.lines(pathEdge)
                                .map(s -> toEdge(s))
                                .collect(Collectors.toList());
         return list;
                                
    }
    
    private static Edge toEdge(String line){
        
        String vars[] = line.split("\\s+");
      return new Edge(Integer.parseInt(vars[0]),Integer.parseInt(vars[1]));
      
      
    }
    
    @Override
    public String toString(){
        String string = ""+this.nNodes+" "+this.nEdges+" "+this.list;
        return string;
    }
    
}
