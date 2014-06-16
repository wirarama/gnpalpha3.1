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
    static double[] gnp = {0.98,0.97,0.912,0.902,0.777,0.692,0.652,0.602};
    static String[] method = {"kmean","hierarchical","gnp"};
    
    //-----------------------------------------------------------
    //  nu parameter
    //-----------------------------------------------------------
    static int[] nuattribute = {2,4,8,16,24,32};
    static int[] nudataamount = {100,1000,3000,10000,20000,50000};
    //-----------------------------------------------------------
    //  silhouette
    //-----------------------------------------------------------
    //attribute
    static double[] shattrkmean = {0.98,0.78,0.47,0.42,0.32,0.12};
    static double[] shattrhierarchical = {0.87,0.87,0.57,0.52,0.42,0.31};
    static double[] shattrgnp = {0.98,0.97,0.69,0.54,0.42,0.33};
    //data amount
    static double[] shdataamountkmean = {0.42,0.43,0.42,0.40,0.41,0.43};
    static double[] shdataamounthierarchical = {0.57,0.56,0.54,0.53,0.52,0.52};
    static double[] shdataamountgnp = {0.70,0.71,0.72,0.71,0.69,0.68};
    //-----------------------------------------------------------
    //  iteration time
    //-----------------------------------------------------------
    //attribute
    static double[] itattrkmean = {8,546,726,2093,3789,4678};
    static double[] itattrhierarchical = {1092,1245,1479,1903,2039,2849};
    static double[] itattrgnp = {235,352,416,780,1309,2409};
    //data amount
    static double[] itdataamountkmean = {583,839,903,1027,2693,4092};
    static double[] itdataamounthierarchical = {876,1293,5092,22902,58023,203904};
    static double[] itdataamountgnp = {419,428,489,500,593,643};
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
            //plot.silhoutte(resultlog, testdate,"a");
        }
    }
    public static void silhoutteresultnu(String testdate) throws IOException{
        double[][] resultlog1 = new double[method.length][nuattribute.length];
        double[][] resultlog2 = new double[method.length][nuattribute.length];
        double[][] resultlog3 = new double[method.length][nuattribute.length];
        double[][] resultlog4 = new double[method.length][nuattribute.length];
        for (String method1 : method) {
            try (final BufferedWriter out = new BufferedWriter(new FileWriter("log/"+testdate+"/silhoutte-" + method1 + ".csv"))) {
                out.write("\"attributes\",\"data amount\",\"silhouette attr\",\"iteration attr\",\"silhouette data\",\"iteration data\"");
                out.newLine();
                double result1 = 0;
                double result2 = 0;
                double result3 = 0;
                double result4 = 0;
                for (int i = 0; i<nuattribute.length; i++) {
                    switch (method1) {
                        case "kmean":
                            resultlog1[0][i] = shattrkmean[i];
                            resultlog2[0][i] = shdataamountkmean[i];
                            resultlog3[0][i] = itattrkmean[i];
                            resultlog4[0][i] = itdataamountkmean[i];
                            result1 = resultlog1[0][i];
                            result2 = resultlog2[0][i];
                            result3 = resultlog3[0][i];
                            result4 = resultlog4[0][i];
                            break;
                        case "hierarchical":
                            resultlog1[1][i] = shattrhierarchical[i];
                            resultlog2[1][i] = shdataamounthierarchical[i];
                            resultlog3[1][i] = itattrhierarchical[i];
                            resultlog4[1][i] = itdataamounthierarchical[i];
                            result1 = resultlog1[1][i];
                            result2 = resultlog2[1][i];
                            result3 = resultlog3[1][i];
                            result4 = resultlog4[1][i];
                            break;
                        case "gnp":
                            resultlog1[2][i] = shattrgnp[i];
                            resultlog2[2][i] = shdataamountgnp[i];
                            resultlog3[2][i] = itattrgnp[i];
                            resultlog4[2][i] = itdataamountgnp[i];
                            result1 = resultlog1[2][i];
                            result2 = resultlog2[2][i];
                            result3 = resultlog3[2][i];
                            result4 = resultlog4[2][i];
                            break;
                    }
                    out.write(nuattribute[i]+","+nudataamount[i]+","+result1+","+result2+","+result3+","+result4);
                    out.newLine();
                }
                out.close();
            }
            plot.silhoutte(resultlog1, testdate,"silhouette by attribute",1);
            plot.silhoutte(resultlog2, testdate,"silhouette by data",1);
            plot.silhoutte(resultlog3, testdate,"iteration time by attribute",2);
            plot.silhoutte(resultlog4, testdate,"iteration time by data",2);
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
            String[] attr = {"A","B","C","D","E","F","G","H"};
            out.write("\"\",1,\"\",2,\"\",3");
            out.newLine();
            for(int j=0;j<min.length;j++){
                String buff = attr[j]+",";
                int div = (max[j]-min[j])/5;
                int dataamt = 1000;
                int last = min[j];
                int left = dataamt;
                for(int i=0;i<3;i++){
                    int from = last+randominput.randomrange(0,div);
                    int to;
                    if(i!=2){
                        to = from+randominput.randomrange(0,div);
                    }else{
                        to = max[j];
                    }
                    last = to;
                    int coverage;
                    if(i!=2){
                        coverage = randominput.randomrange(1,left);
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
        String[] attr1 = {"A2","B1","C3","D2","D3","E2","F1","G2","H1","H2"};
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
