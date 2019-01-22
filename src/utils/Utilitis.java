/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elev
 */
public class Utilitis {
    public static List<Integer> getIntEqualsList(int[] v, int length, int value) {
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 1 ; i <= length; i++) {
            if(v[i] == value) {
                result.add(i);
            }
        }
        return result;
    }
    
    public static int getMaxValueFromIntList(int[] v, int length) {
        int maxValue = 0;
        for(int i = 1 ; i <= length; i++) {
            if(maxValue < v[i]) {
                maxValue = v[i];
            }
        }
        return maxValue;
    }
}
