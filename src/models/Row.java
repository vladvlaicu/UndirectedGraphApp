/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;


public class Row {
    List<Byte> rowValues = new ArrayList<Byte>();
    int nrElements;

    public Row(int nrElements,byte[] values) {
        
        initValues(nrElements,values);
    }

    private void initValues(int nrElements, byte[] values) {
        this.nrElements = nrElements;
        for(int i=1;i<=nrElements;i++){
            rowValues.add(values[i]);
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(byte b:rowValues){
            sb.append(" "+b+" ");
        }
     return sb.toString();
    }
    
}
