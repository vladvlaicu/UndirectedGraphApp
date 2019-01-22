/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import utils.Constants;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author elev
 */
public class UndirectedGraph {
    
    private int numEdges;
    private int numNodes;
    private List<Edge> edgesList = Arrays.asList();
    private byte[][] adjacencyMatrix;
    private int[] nodesGrades;
    
   public UndirectedGraph(){
   
   }
    
    public static UndirectedGraph getInitGraph(){
        return initGraph();
    }

    public int getnumEdges() {
        return numEdges;
    }

    public void setnumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    public int getnumNodes() {
        return numNodes;
    }

    public void setnumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    public List<Edge> getList() {
        return edgesList;
    }

    public void setList(List<Edge> edgesList) {
        this.edgesList = edgesList;
    }
    
    public static UndirectedGraph initGraph(){
        Path pathEdge = Paths.get("./GraphData/GraphDataEdges.txt");
        Path pathNumNode = Paths.get("./GraphData/GraphDataNrNodes.txt");
        UndirectedGraph graph = new UndirectedGraph();
        try {
            int  nrNodes = Integer.parseInt(Files.readAllLines(pathNumNode).get(0));
            graph.setnumNodes(nrNodes);
            initEdges(pathEdge);
            int nrEdges =(int) Files.lines(pathEdge)
                               .count();
            graph.setnumEdges(nrEdges);
            graph.setList(UndirectedGraph.initEdges(pathEdge));
        } catch (IOException | IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return graph;
    }

    private static List<Edge> initEdges(Path pathEdge) throws IOException {
        List<Edge> edgesList = Files.lines(pathEdge)
                                .map(s -> toEdge(s))
                                .collect(Collectors.toList());
         return edgesList;
                                
    }
    
    private static Edge toEdge(String line){
        
        String vars[] = line.split("\\s+");
        return new Edge(Integer.parseInt(vars[0]),Integer.parseInt(vars[1]));


    }
    
    @Override
    public String toString(){
        String string = ""+this.numNodes+" "+this.numEdges+" "+this.edgesList;
        return string;
    }
    
    public void SetAdjacencyMatrix() {
        this.adjacencyMatrix = new byte[Constants.NUM_MAX_NODES][Constants.NUM_MAX_NODES];
        for(Edge edge : this.edgesList) {
            this.adjacencyMatrix[edge.x][edge.y] = 1;
            this.adjacencyMatrix[edge.y][edge.x] = 1;
        }  
    }
    
    public void SetNodesGrades() {
        for(Edge edge : this.edgesList) {
            this.nodesGrades[edge.x]++;
            this.nodesGrades[edge.y]++;
        }
    }
    
    public byte[][] GetAdjacencyMatrix() {
        return adjacencyMatrix;   
    }
    
    public int GetNodeGrade(int node) {
        return this.nodesGrades[node];
    }
    
    public int[] GetNodesGrades() {
        return this.nodesGrades;
    }
    
    public int[] GetNodesWithMaxGrade() {
        int[] nodes = new int[Constants.NUM_MAX_NODES];
        return nodes;
    }
    
    public int[] GetAdjacentNodesForNode(int node) {
        int[] adjacentNodes = new int[Constants.NUM_MAX_NODES];
        for(Edge edge : this.edgesList) {
            int other = (edge.x == node) ? edge.y : edge.x;
            adjacentNodes[adjacentNodes.length] = other;
        }
        return adjacentNodes;
    }
    
    public int[] GetAdjacentNodesForEdge(int index) {
        return new int[]{this.edgesList.get(index).x, this.edgesList.get(index).y};
    }
    
    
    
}
