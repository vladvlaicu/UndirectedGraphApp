/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import utils.Constants;
import java.util.List;

/**
 *
 * @author elev
 */
public class UndirectedGraph {
    
    private int numEdges;
    private int numNodes;
    private List<Edge> edgesList;
    private byte[][] adjacencyMatrix;
    private int[] nodesGrades;
    
    public UndirectedGraph() {
        
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
