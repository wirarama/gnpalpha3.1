/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gnpalpha3;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author test
 */
public class mainprocess {
    static int totalcoverage = 0;
    static int iterationindex = 0;
    static int totalrule = 0;
    static int[][][] ruleset;
    static int[] rulesetcoverage;
    static List iterationcoverage = new ArrayList();
    static int[] added;
    static int addedindex;
    static int[] addedrule;
    static int addedindexrule;
    static int[][] data;
    public static void mainprocess(
            int attributeamount,
            int dataamount,
            int cross,
            int mutation,
            int range,
            int variation,
            boolean isplot
    ) throws IOException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String testdate = dateFormat.format(date);
        (new File("log/"+testdate+"")).mkdirs();
        /*gnpdebug.coverage(5,100,100);
        gnpdebug.partialrandom(4,100);
        System.out.println("1.1="+randominput.randomrange(5,28));
        System.out.println("1.2="+randominput.randomrange(5,28));
        System.out.println("2.1="+randominput.randomrange(5,28));
        System.out.println("2.2="+randominput.randomrange(5,24));
        System.out.println("2.3="+randominput.randomrange(5,30));
        System.out.println("3.1="+randominput.randomrange(5,24));
        System.out.println("3.2="+randominput.randomrange(5,24));
        System.out.println("4.1="+randominput.randomrange(5,28));
        System.out.println("4.2="+randominput.randomrange(5,30));
        System.out.println("4.2="+randominput.randomrange(5,24));*/
        /*silhoutteresult.gnpattribute(testdate);
        silhoutteresult.distribution();
        silhoutteresult.attributearrange();*/
        silhoutteresult.silhoutteresultnu(testdate);
        
        /*data = randominput.randomdb(attributeamount,dataamount,variation,testdate,range,cross,mutation);
        double[][] stat = statistics.getstatistics(data,testdate);
        int[][] centroid = silhoutte.centroid(stat,6);
        silhoutte.centroidkmeans(centroid,testdate);
        silhoutte.centroidgnp(centroid,testdate);
        addedindex = 0;
        addedindexrule = 0;
        addedrule = new int[data.length];
        int ruleamount = 10000;
        ruleset = new int[data.length][attributeamount][3];
        rulesetcoverage = new int[data.length];
        int[][][] rangeset = rule.rangeset(attributeamount,stat);
        int[][] rangelogset = rule.rangelogset(rangeset);
        //start auto fill
        int maxdata = data.length-1;
        //maxdata = maxdata-50;
        //end auto fill
        rulesetcoverage[totalrule+1] = data.length;
        do{
            rule.ruleset(ruleamount,attributeamount,stat,rangeset,testdate);
        }while(totalcoverage<(maxdata));
        ruleset = arraysearch.cleanarray3(ruleset,totalrule);
        rulesetcoverage = arraysearch.cleanarray1(rulesetcoverage,totalrule);
        filelog.array3csv(ruleset,"6.affectedrule.csv",testdate);
        filelog.patternlog(rulesetcoverage,"7.affectedrulecoverage.log",testdate,"affected");
        plot.datarangeset(rangelogset,testdate);
        plot.makeplot1(plot.plotstep(rulesetcoverage),"coverage","affectedcoverage","rule index","coverage",testdate);
        int[][] iteration = arraysearch.listtoarray(iterationcoverage);
        filelog.arraycsv(iteration,"5.iteration.csv",testdate,"\"index\",\"coverage\"");
        plot.makeplot1(iteration,"coverage","coverage","rule amount","coverage",testdate);
        String[] summary = {
            "iteration = "+iterationcoverage.size(),
            "rule amount = "+ruleset.length,
            "average coverage = "+arraysearch.avgarray(rulesetcoverage)};
        filelog.stringlog(summary,"summary",testdate);
        silhoutteresult.silhoutteresult(testdate);
        if(isplot==true){
            plot.datasplitbatch(data,2,testdate);
        }
        */
        Runtime.getRuntime().exec("nemo /home/wirarama/NetBeansProjects/gnpalpha3.1/log/"+testdate+"");
        //knapsack.gnpknapsack(ruleset.length,testdate);
        System.exit(1);
    }
}
