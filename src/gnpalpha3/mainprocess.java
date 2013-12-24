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
        int[][] data = randominput.randomdb(attributeamount,dataamount,variation,testdate,range,cross,mutation);
        double[][] stat = statistics.getstatistics(data,testdate);
        int ruleamount = 10000;
        ruleset = new int[data.length][attributeamount][3];
        rulesetcoverage = new int[data.length];
        int[][][] rangeset = rule.rangeset(attributeamount,stat,data);
        int[][] rangelogset = rule.rangelogset(rangeset,data);
        do{
            rule.ruleset(ruleamount,attributeamount,stat,data,rangeset,testdate);
        }while(totalcoverage<data.length);
        ruleset = arraysearch.cleanarray3(ruleset,totalrule);
        rulesetcoverage = arraysearch.cleanarray1(rulesetcoverage,totalrule);
        filelog.array3csv(ruleset,"6.affectedrule.csv",testdate);
        filelog.patternlog(rulesetcoverage,"7.affectedrulecoverage.log",testdate,"affected");
        plot.datarangeset(rangelogset,testdate);
        plot.makeplot1(plot.plotstep(rulesetcoverage),"coverage","affectedcoverage","rule index","coverage",testdate);
        plot.makeplot1(arraysearch.listtoarray(iterationcoverage),"coverage","coverage","rule amount","coverage",testdate);
        String[] summary = {
            "iteration = "+iterationcoverage.size(),
            "rule amount = "+ruleset.length,
            "average coverage = "+arraysearch.avgarray(rulesetcoverage)};
        filelog.stringlog(summary,"summary",testdate);
        if(isplot==true){
            plot.datasplitbatch(data,2,testdate);
        }
        Runtime.getRuntime().exec("caja /home/wirarama/NetBeansProjects/gnpalpha3/log/"+testdate+"");
        knapsack.gnpknapsack(ruleset.length,testdate);
        System.exit(1);
    }
}
