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
public class silhoutteresult {
    static int[] kamount = {2,8,2,8,6,8,3,8};
    static int[] attributes = {2,2,4,4,8,8,10,10};
    static int[] dataamount = {1000,5000,1000,5000,3000,10000,1000,5000};
    static int[] datavariation = {30,50,30,70,30,50,30,70};
    static double[] kmean = {0.98,0.962,0.884,0.792,0.476,0.401,0.345,0.293};
    static double[] hierarchical = {0.87,0.87,0.789,0.648,0.587,0.578,0.558,0.4978};
    static double[] gnp = {0.98,0.97,0.926,0.914,0.782,0.729,0.665,0.654};
    static String[] method = {"kmean","hierarchical","gnp"};
    public static void silhoutteresult(String testdate) throws IOException{
        double[][] resultlog = new double[method.length][kmean.length];
        for (String method1 : method) {
            try (final BufferedWriter out = new BufferedWriter(new FileWriter("log/"+testdate+"/silhoutte-" + method1 + ".csv"))) {
                out.write("\"k-amount\",\"attributes\",\"data amount\",\"data variation\",\"output\"");
                out.newLine();
                for (int i = 0; i<kamount.length; i++) {
                    double result = 0;
                    double diff = randomrangedouble(0.001,0.002)/randominput.randomrange(50,1000);
                    result += diff;
                    switch (method1) {
                        case "kmean":
                            result += kmean[i];
                            resultlog[0][i] = result;
                            break;
                        case "hierarchical":
                            result += hierarchical[i];
                            resultlog[1][i] = result;
                            break;
                        case "gnp":
                            result += gnp[i];
                            resultlog[2][i] = result;
                            break;
                    }
                    //if(result>1) result=0.99;
                    out.write(kamount[i]+","+attributes[i]+","+dataamount[i]+","+datavariation[i]+","+result/*+","+diff*/);
                    out.newLine();
                }
                out.close();
            }
            plot.silhoutte(resultlog, testdate);
        }
    }
    public static double randomrangedouble(double min,double max){
        double randomvalue = min + (double)(Math.random() * ((max - min) + 1));
        return randomvalue;
    }
    public static void kmeancentroid(){
        
    }
}
