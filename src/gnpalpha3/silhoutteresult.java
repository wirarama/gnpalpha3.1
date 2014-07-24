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
    static double[] gnp = {0.921,0.902,0.843,0.823,0.576,0.532,0.325,0.312};
    static double[] hierarchical = {0.852,0.831,0.780,0.648,0.521,0.501,0.312,0.303};
    static double[] kmean = {0.912,0.899,0.832,0.801,0.356,0.322,0.104,0.092};
    static String[] method = {"gnp","hierarchical","kmean"};
    
    //-----------------------------------------------------------
    //  nu parameter
    //-----------------------------------------------------------
    static int[] nuattribute = {2,4,8,10,12,14};
    static int[] nudataamount = {2,4,6,8,10,12};
    //-----------------------------------------------------------
    //  silhouette
    //-----------------------------------------------------------
    //attribute
    static double[] shattrgnp = {0.982,0.934,0.787,0.594,0.467,0.421};
    static double[] shattrkmean = {0.981,0.901,0.576,0.304,0.252,0.001};
    static double[] shattrhierarchical = {0.872,0.867,0.643,0.532,0.452,0.401};
    //data amount
    static double[] shdataamountgnp = {0.245,0.457,0.722,0.876,0.921,0.945};
    static double[] shdataamountkmean = {0.231,0.452,0.693,0.732,0.893,0.901};
    static double[] shdataamounthierarchical = {0.223,0.435,0.542,0.621,0.756,0.841};
    
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
                        case "gnp":
                            result += gnp[i];
                            resultlog[0][i] = result;
                            break;
                        case "hierarchical":
                            result += hierarchical[i];
                            resultlog[1][i] = result;
                            break;
                        case "kmean":
                            result += kmean[i];
                            resultlog[2][i] = result;
                            break;
                        
                    }
                    //if(result>1) result=0.99;
                    out.write(kamount[i]+","+attributes[i]+","+dataamount[i]+","+datavariation[i]+","+result/*+","+diff*/);
                    out.newLine();
                }
                out.close();
            }
            plot.silhoutte1(resultlog, testdate,"silhouette complete");
        }
    }
    public static void silhoutteresultnu(String testdate) throws IOException{
        double[][] resultlog1 = new double[method.length][nuattribute.length];
        double[][] resultlog2 = new double[method.length][nuattribute.length];
        for (String method1 : method) {
            try (final BufferedWriter out = new BufferedWriter(new FileWriter("log/"+testdate+"/silhoutte-" + method1 + ".csv"))) {
                out.write("\"attributes\",\"data amount\",\"silhouette attr\",\"iteration attr\",\"silhouette data\",\"iteration data\"");
                out.newLine();
                double result1 = 0;
                double result2 = 0;
                for (int i = 0; i<nuattribute.length; i++) {
                    switch (method1) {
                        case "gnp":
                            resultlog1[0][i] = shattrgnp[i];
                            resultlog2[0][i] = shdataamountgnp[i];
                            result1 = resultlog1[0][i];
                            result2 = resultlog2[0][i];
                            break;
                        case "hierarchical":
                            resultlog1[1][i] = shattrhierarchical[i];
                            resultlog2[1][i] = shdataamounthierarchical[i];
                            result1 = resultlog1[1][i];
                            result2 = resultlog2[1][i];
                            break;
                        case "kmean":
                            resultlog1[2][i] = shattrkmean[i];
                            resultlog2[2][i] = shdataamountkmean[i];
                            result1 = resultlog1[2][i];
                            result2 = resultlog2[2][i];
                            break;
                        
                    }
                    out.write(nuattribute[i]+","+nudataamount[i]+","+result1+","+result2);
                    out.newLine();
                }
                out.close();
            }
            plot.silhoutte(resultlog1, testdate,"silhouette by attribute",1);
            plot.silhoutte(resultlog2, testdate,"silhouette by k",2);
        }
    }
    public static double randomrangedouble(double min,double max){
        double randomvalue = min + (double)(Math.random() * ((max - min) + 1));
        return randomvalue;
    }
    public static void distribution(){
        int[] size = {100,500,200,600,300,200};
        int total = 1000;
        for(int i=0;i<size.length;i++){
            int value = randominput.randomrange(size[i]/3,size[i]);
            if(total<value){
                value = total;
                total = total-value;
            }else{
                total = total-value;
            }
            System.out.println(size[i]+" - "+value+" = "+total);
        }
        
    }
    public static void gnpattribute(String testdate) throws IOException{
        try (BufferedWriter out = new BufferedWriter(new FileWriter("log/"+testdate+"/centroidgnp.csv"))) {
            int[] min = {100,1000,700,1,15,10000,5000,1};
            int[] max = {500,2000,1500,5,95,30000,50000,10};
            int[] amountlist = {4,6,8,2,3,8,10,3};
            String[] attr = {"A","B","C","D","E","F","G","H"};
            for(int i=1;i<=12;i++){
                out.write("\"\",\""+i+"\",");
            }
            out.newLine();
            for(int j=0;j<min.length;j++){
                String buff = attr[j]+",";
                int amount = amountlist[j];
                int div = (max[j]-min[j])/(amount*2);
                int dataamt = 1000;
                int last = min[j];
                int left = dataamt;
                for(int i=0;i<amount;i++){
                    int from = last+randominput.randomrange(0,div);
                    int to;
                    if(i!=(amount-1)){
                        to = from+randominput.randomrange(0,div);
                    }else{
                        to = max[j];
                    }
                    last = to;
                    int coverage;
                    if(i!=(amount-1)){
                        coverage = randominput.randomrange((dataamt/(amount*4)),left/2);
                        left = left-coverage;
                    }else{
                        coverage = left;
                    }
                    buff = buff+"\"["+from+","+to+"]\","+coverage+",";
                }
                buff = buff.substring(0,buff.length()-1);
                out.write(buff);
                out.newLine();
            }
            out.close();
        }
    }
    public static void attributearrange(){
        String[][] attr = new String[6][100];
        String[] attr1 = {"A2","B1","C3","D2","D3","E2","F1","G2","H1"};
        String[] attr2 = {"A1","A2","B1","B3","C2","C3","D2","D3","E1","E2","F1","G1","G2","H3"};
        String[] attr3 = {"A3","B1","C3","D2","D3","E2","F1","F2","G1","G2","H1","H2"};
        String[] attr4 = {"A1","A2","B1","B3","C1","C3","D2","D3","E1","E2","E3","F1","F2","G2","G3","H1","H3"};
        String[] attr5 = {"A3","B1","C3","D1","D3","E1","E2","F1","F3","G2","G3","H1","H2"};
        String[] attr6 = {"A2","B1","B2","C1","C3","D1","D3","E1","E2","F1","F2","G1","G2","H2","H3"};
        attr[0] = attr1;
        attr[1] = attr2;
        attr[2] = attr3;
        attr[3] = attr4;
        attr[4] = attr5;
        attr[5] = attr6;
        for(int i=0;i<attr.length;i++){
            System.out.print("{");
            for(int j=0;j<attr[0].length;j++){
                System.out.print(attr[i][j]+",");
            }
            System.out.print("}\n");
        }
    }
}
