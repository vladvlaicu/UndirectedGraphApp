/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlad
 */
public class Table {
    List<Row> rows = new ArrayList<Row>();
    int nrValues;
    public Table(int nrValues,byte[][] rowsValues){
        initComponents(nrValues,rowsValues);
    }

    private void initComponents(int nrValues, byte[][] rowsValues) {
        this.nrValues = nrValues;
        for(int i=1;i<=nrValues;i++){
            byte[] rowV = new byte[100];
            for(int j=1;j<=nrValues;j++){
                rowV[j] = rowsValues[i][j]; 
            }
            Row row = new Row(nrValues, rowV);
            rows.add(row);
        }
    }

    public List<Row> getRows() {
        return rows;
    }

    public int getNrValues() {
        return nrValues;
    }
    
}
