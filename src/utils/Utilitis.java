/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author elev
 */
public class Utilitis {
    public static int[] GetIntEqualsList(int[] v, int value) {
        int[] result = new int[Constants.NUM_MAX_NODES];
        for(int i = 0 ; i < v.length; i++) {
            if(v[i] == value) {
                result[result.length] = i;
            }
        }
        return result;
    }
    
    public static int GetMaxValueFromIntList(int[] v) {
        int maxValue = 0;
        for(int i = 0 ; i < v.length; i++) {
            if(maxValue > v[i]) {
                maxValue = v[i];
            }
        }
        return maxValue;
    }
}
