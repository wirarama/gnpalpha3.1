/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gnpalpha3;

import java.util.List;

/**
 *
 * @author test
 */
public class arraysearch {
    public static boolean inarray(int[] data,int key){
        boolean exist = false;
        for(int i=0;i<data.length;i++){
            if(data[i]==key){
                exist = true;
                break;
            }
        }
        return exist;
    }
    public static int sumarray(int[] data){
        int sum = 0;
        for(int i=0;i<data.length;i++){
            sum = sum+data[i];
        }
        return sum+1;
    }
    public static double avgarray(int[] data){
        double avg = (double)sumarray(data)/(double)data.length;
        return avg;
    }
    public static int[][] cleanarray(int[][] data,int length){
        int[][] newdata = new int[length][data[0].length];
        System.arraycopy(data, 0, newdata, 0, length);
        return newdata;
    }
    public static int[][][] cleanarray3(int[][][] data,int length){
        int[][][] newdata = new int[length][data[0].length][data[0][0].length];
        System.arraycopy(data, 0, newdata, 0, length);
        return newdata;
    }
    public static int[] cleanarray1(int[] data,int length){
        int[] newdata = new int[length];
        System.arraycopy(data, 0, newdata, 0, length);
        return newdata;
    }
    public static int[][] listtoarray(List data){
        int[][] out = new int[data.size()][2];
        int i = 0;
        for (Object j: data){
            out[i][0] = i;
            out[i][1] = (int)j;
            i++;
        }
        return out;
    }
}
