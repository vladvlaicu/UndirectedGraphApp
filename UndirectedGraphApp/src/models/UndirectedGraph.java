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
import java.util.ArrayList;
import java.util.Arrays;
import utils.Constants;
import utils.Utilitis;
import java.util.List;
import java.util.stream.Collectors;

public class UndirectedGraph {
    
    private int numEdges;
    private int numNodes;
    private List<Edge> edgesList = Arrays.asList();
    private byte[][] adjacencyMatrix;
    private int[] nodesGrades;
    private int[][] chainMatrix;
    
    public UndirectedGraph(){
   
    }
  
    public static UndirectedGraph getInitGraph(){
        return initGraph();
    }
  
    public int getNumEdges() {
        return numEdges;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    public List<Edge> getEdgesList() {
        return edgesList;
    }

    public void setEdgesList(List<Edge> edgesList) {
        this.edgesList = edgesList;
    }
    
    public static UndirectedGraph initGraph(){
        Path pathEdge = Paths.get("./GraphData/GraphDataEdges.txt");
        Path pathNumNode = Paths.get("./GraphData/GraphDataNrNodes.txt");
        UndirectedGraph graph = new UndirectedGraph();
        try {
            int  numNodes = Integer.parseInt(Files.readAllLines(pathNumNode).get(0));
            graph.setNumNodes(numNodes);
            initEdges(pathEdge);
            int numEdges =(int) Files.lines(pathEdge)
                               .count();
            graph.setNumEdges(numEdges);
            List<Edge> edgesList = UndirectedGraph.initEdges(pathEdge);
            for(int i = 0; i < edgesList.size() - 1; i++) {
                for(int j = i + 1; j < edgesList.size(); j++) {
                    if(edgesList.get(i).equals(edgesList.get(j))) {
                        edgesList.remove(j);
                    }
                }
            }
            graph.setEdgesList(edgesList);
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
    
    public void generateAdjacencyMatrix() {
        this.adjacencyMatrix = new byte[Constants.NUM_MAX_NODES][Constants.NUM_MAX_NODES];
        for(Edge edge : this.edgesList) {
            this.adjacencyMatrix[edge.x][edge.y] = 1;
            this.adjacencyMatrix[edge.y][edge.x] = 1;
        }  
    }
    
    public byte[][] getAdjacencyMatrix() {
        if(this.adjacencyMatrix == null) {
            generateAdjacencyMatrix();
        }
        return adjacencyMatrix;   
    }
    
    public void generateNodesGrades() {
        this.nodesGrades = new int[Constants.NUM_MAX_NODES];
        for(Edge edge : this.edgesList) {
            this.nodesGrades[edge.x]++;
            this.nodesGrades[edge.y]++;
        }
    }
    
    public int[] getNodesGrades() {
        if(this.nodesGrades == null) {
            generateNodesGrades();
        }
        return this.nodesGrades;
    }
    
    public int getNodeGrade(int node) {
        if(this.nodesGrades == null) {
            generateNodesGrades();
        }
        return this.nodesGrades[node];
    }
    
    public List<Integer> getNodesWithMaxGrade() {
        if(this.nodesGrades == null) {
            generateNodesGrades();
        }
        return Utilitis.getIntEqualsList(
                nodesGrades,
                this.numNodes,
                Utilitis.getMaxValueFromIntList(nodesGrades, this.numNodes)
        );
    }
   
    public List<Integer> getIsolatedNodes() {
        if(this.nodesGrades == null) {
            generateNodesGrades();
        }
        return Utilitis.getIntEqualsList(nodesGrades, this.numNodes, 1);
    }
    
    public int[] getAdjacentNodesForEdge(int index) {
        return new int[]{this.edgesList.get(index).x, this.edgesList.get(index).y};
    }
    
    public List<Integer> getAdjacentNodesForNode(int node) {
        List<Integer> adjacentNodes = new ArrayList<Integer>();
        for(Edge edge : this.edgesList) {
            int other = (edge.x == node) ? edge.y : edge.x;
            adjacentNodes.add(other);
        }
        return adjacentNodes;
    }
    
    public List<Integer> getChainBetweenTwoNodes(int x, int y ){
        List<Integer> chain = new ArrayList<Integer>();
        int[] fathers = new int[Constants.NUM_MAX_NODES];
        int[] used = new int[Constants.NUM_MAX_NODES];
        if(this.adjacencyMatrix == null) {
            generateAdjacencyMatrix();
        }
        used[x] = 1;
        for(int i = 1; i <= this.numNodes; i++) {
            if(this.adjacencyMatrix[i][x] == 1) {
                fathers[i] = x;
            }
        }
        while(fathers[y] == 0) {
            for(int i = 1 ; i <= this.numNodes; i++)
                if(used[i] == 0 && fathers[i] != 0)
                {
                    used[i] = 1;
                    for(int j = 1; j <= this.numNodes; j++)
                        if(this.adjacencyMatrix[i][j] == 1) {
                            fathers[j] = i;
                        }
                }
        }
        int node = y;
        while(node != 0) {
            chain.add(node);
            node = fathers[node];
        }
        return chain;
    }
    
    public void generateChainMatrix() {
        if(this.chainMatrix == null) {
            this.chainMatrix = new int[Constants.NUM_MAX_NODES][Constants.NUM_MAX_NODES];
            for(int k = 1; k <= this.numNodes; k++) {
                for(int i = 1; i <= this.numNodes; i++) {
                    if(this.adjacencyMatrix[i][k] == 1) {
                        for(int j = 1; j <= this.numNodes; j++) {
                            if(this.adjacencyMatrix[k][j] == 1) {
                                this.chainMatrix[i][j] = 1;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public int[][] getChainMatrix() {
        if(this.chainMatrix == null) {
            generateChainMatrix();
        }
        return this.chainMatrix;
    }
    
    public List<Integer> getChainListForNode(int node) {
        List<Integer> nodesList = new ArrayList<Integer>();
        if(this.chainMatrix == null) {
            generateChainMatrix();
        }
        for(int i = 1; i <= this.numNodes; i++) {
            if(this.chainMatrix[node][i] == 1){
                nodesList.add(i);
            }
        }
        return nodesList;
    }
    
    public Boolean graphHasCycles() {
        int[] usedNodes = new int[Constants.NUM_MAX_NODES];
        for(Edge edge : this.edgesList) {
            if(usedNodes[edge.x] == 1 && usedNodes[edge.y] == 1) {
                return true;
            } else {
                usedNodes[edge.x] = usedNodes[edge.y] = 1;
            }
        }
        return false;
    }
    
    public void generateAllCyclesOfLengthN(int n, List<List<Integer>> cyclesList, List<Integer> currentCycle, int[] usedNodes) {
        for(int i = 1; i <= this.numNodes; i++) {
            int pos = currentCycle.size();
            if((pos == 0 || this.adjacencyMatrix[i][currentCycle.get(pos - 1)] == 1) && usedNodes[i] == 0) {
                usedNodes[i] = 1;
                currentCycle.add(i);
                if(pos == n - 1) {
                    if(this.adjacencyMatrix[currentCycle.get(pos)][currentCycle.get(0)] == 1) {
                        currentCycle.add(currentCycle.get(0));
                        cyclesList.add(new ArrayList<Integer>(currentCycle));
                        currentCycle.remove(pos + 1);
                    }
                } else {
                    generateAllCyclesOfLengthN(n, cyclesList, currentCycle, usedNodes);
                }
                currentCycle.remove(pos);
                usedNodes[i] = 0;
            }
        }
    }
    
    public List<List<Integer>> getAllCyclesOfLengthN(int n) {
        if(this.adjacencyMatrix == null) {
            generateAdjacencyMatrix();
        }
        List<List<Integer>> cyclesList = new ArrayList<List<Integer>>();
        generateAllCyclesOfLengthN(n, cyclesList, new ArrayList<Integer>(), new int[Constants.NUM_MAX_NODES]);
        System.out.println(cyclesList);
        return cyclesList;
    }
   
}
