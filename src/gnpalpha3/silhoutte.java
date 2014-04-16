/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gnpalpha3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author wirarama
 */
public class silhoutte {
    public static int[][] centroid(double[][] data,int k){
        int[][] centroid = new int[k][data.length];
        for(int i=0;i<data.length;i++){
            int diff = (int) (data[i][0]-data[i][1]);
            int diff1 = diff/k;
            int inc = (int) data[i][1];
            for(int j=0;j<k;j++){
                centroid[j][i] = inc;
                inc = inc+diff1;
            }
        }
        return centroid;
    }
    public static void centroidkmeans(int[][] centroid,String testdate) throws IOException{
        try (BufferedWriter out = new BufferedWriter(new FileWriter("log/"+testdate+"/centroidkmean.csv"))) {
            for (int[] data1 : centroid) {
                String buff="";
                for (int j = 0; j<centroid[0].length; j++) {
                    int randvalue = data1[j]/8;
                    buff = buff+randominput.randomrange((data1[j]-randvalue),(data1[j]+randvalue))+",";
                }
                buff = buff.substring(0,buff.length()-1);
                out.write(buff);
                out.newLine();
            }
            out.close();
        }
    }
    public static void centroidgnp(int[][] centroid,String testdate) throws IOException{
        try (BufferedWriter out = new BufferedWriter(new FileWriter("log/"+testdate+"/centroidgnp.csv"))) {
            int[] attr = new int[centroid[0].length];
            for(int i=0;i<centroid[0].length;i++){
                attr[i] = i;
            }
            for (int[] centroid1 : centroid) {
                arraysearch.shufflearray(attr);
                String buff="";
                for (int j = 0; j<centroid[0].length; j++) {
                    int cent1 = centroid1[attr[j]];
                    int randvalue = cent1/8;
                    buff = buff+"["+attr[j]+"]"+randominput.randomrange((cent1-randvalue),(cent1+randvalue))+",";
                }
                buff = buff.substring(0,buff.length()-1);
                out.write(buff);
                out.newLine();
            }
            out.close();
        }
    }
    public static void centroidgnp2(int[][] centroid,String testdate) throws IOException{
        try (BufferedWriter out = new BufferedWriter(new FileWriter("log/"+testdate+"/centroidkmean.csv"))) {
            for (int[] data1 : centroid) {
                String buff="";
                for (int j = 0; j<centroid[0].length; j++) {
                    int randvalue = data1[j]/8;
                    buff = buff+randominput.randomrange((data1[j]-randvalue),(data1[j]+randvalue))+",";
                }
                buff = buff.substring(0,buff.length()-1);
                out.write(buff);
                out.newLine();
            }
            out.close();
        }
    }
}
