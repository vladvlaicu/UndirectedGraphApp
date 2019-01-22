/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author elev
 */
public class Edge {
    public int x, y;
    
    public Edge(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return " "+x+" "+y+" ";
    }
}
