/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gnpalpha3;

import java.io.IOException;
import static gnpalpha3.gui.btn;
import static gnpalpha3.mainprocess.totalcoverage;
import static gnpalpha3.mainprocess.totalrule;
import static gnpalpha3.mainprocess.ruleset;
import static gnpalpha3.mainprocess.rulesetcoverage;
import static gnpalpha3.mainprocess.iterationcoverage;
/**
 *
 * @author test
 */
public class rule {
    static int[] added;
    static int addedindex;
    static int[] addedrule;
    static int addedindexrule;
    static int[][][] affectedrule;
    static int[] affectedrulecoverage;
    
    public static void ruleset(
            int ruleamount,
            int attributeamount,
            double[][] stat,
            int[][] data,
            int[][][] rangeset,
            String testdate
    ) throws IOException{
        addedindex = 0;
        addedindexrule = 0;
        addedrule = new int[data.length];
        addedindexrule = 0;
        for(int i=0;i<ruleamount;i++){
            int[][] ruletemp = randomrule(attributeamount,rangeset);
            int rulecoverage = rulecoverage(ruletemp,data);
            if(rulecoverage!=0){
                ruleset[totalrule] = ruletemp;
                rulesetcoverage[totalrule] = rulecoverage;
                totalrule=totalrule+1;
            }
            totalcoverage = totalcoverage+rulecoverage;
            iterationcoverage.add(totalcoverage);
            double percentpre = ((double)totalcoverage/(double)data.length);
            int percent = (int)(percentpre*100);
            btn.setText("["+i+"]"+totalcoverage+"("+percent+"%)");
            if(totalcoverage>=(data.length-1)) break;
        }
        /*ruleset = arraysearch.cleanarray3(ruleset,totalrule);
        affectedrule = arraysearch.cleanarray3(affectedrule,j);
        affectedrulecoverage = arraysearch.cleanarray1(affectedrulecoverage,j);
        rulecoverage = arraysearch.cleanarray1(rulecoverage,totalrule);
        totalcoveragelog = arraysearch.cleanarray1(totalcoveragelog,totalrule);
        int[][] rangelogset = rangelogset(rangeset,data);
        filelog.array3csv(ruleset,"1.ruleset.csv",testdate);
        filelog.array3csv(rangeset,"2.rangeset.csv",testdate);
        filelog.patternlog(rulecoverage,"3.rulecoverage.log",testdate,"rule");
        filelog.patternlog(totalcoveragelog,"4.rulecoveragesum.log",testdate,"covered");
        filelog.arraycsv(rangelogset,"5.rangecoverage.csv",testdate);
        filelog.array3csv(affectedrule,"6.affectedrule.csv",testdate);
        filelog.patternlog(affectedrulecoverage,"7.affectedrulecoverage.log",testdate,"affected");
        plot.makeplot1(plot.plotstep(totalcoveragelog),"coverage","coverage","rule amount","coverage",testdate);
        plot.datarangeset(rangelogset,testdate);
        plot.makeplot1(plot.plotstep(affectedrulecoverage),"coverage","affectedcoverage","rule index","coverage",testdate);
        String[] summary = {
            "iteration = "+totalcoveragelog.length,
            "rule amount = "+affectedrule.length,
            "average coverage = "+arraysearch.avgarray(affectedrulecoverage)};
        filelog.stringlog(summary,"summary",testdate);
        return affectedrule;*/
    }
    public static int[][] randomrule(int attributeamount,int[][][] range){
        int[][] rule = new int[attributeamount][3];
        for(int i=0;i<attributeamount;i++){
            int rand = randominput.randomrange(0,3);
            rule[i][0] = range[i][rand][0];
            rule[i][1] = range[i][rand][1];
            rule[i][2] = rand;
        }
        return rule;
    }
    public static int[][][] rangeset(int attributeamount,double[][] stat,int[][] data){
        int[][][] range = new int[attributeamount][4][2];
        for(int i=0;i<attributeamount;i++){
            range[i] = rangegeneratorsub(stat[i][0],stat[i][1],data,i);
        }
        return range;
    }
    public static int[][] rangegeneratorsub(double max,double min,int[][] data,int index){
        int[][] subrange = new int[4][2];
        double deviation = (max-min)/4;
        int j = 0;
        for(double i=min;i<max;i=i+deviation){
            subrange[j][0] = (int) i;
            subrange[j][1] = (int) (i+deviation);
            j++;
        }
        return subrange;
    }
    public static int[][] rangelogset(int[][][] rangeset,int[][] data){
        int[][] rangelogset = new int[rangeset.length][4];
        for(int i=0;i<rangeset.length;i++){
            added = new int[data.length];
            addedindex = 0;
            for(int j=0;j<4;j++){
                rangelogset[i][j] = rangelog(rangeset[i][j][0],rangeset[i][j][1],data,i);
            }
        }
        return rangelogset;
    }
    public static int rangelog(int min,int max,int[][] data,int index){
        int count = 0;
        for (int i=0;i<data.length;i++) {
            if (data[i][index] >= min && data[i][index] <= max) {
                if(arraysearch.inarray(added,i)==false){
                    added[addedindex]=i;
                    count=count+1;
                    addedindex=addedindex+1;
                }
            }
        }
        return count;
    }
    public static int rulecoverage(int[][] rule,int[][] data){
        int count=0;
        for (int i=0;i<data.length;i++) {
            int support=0;
            for (int j = 0; j<data[0].length; j++) {
                if (data[i][j] >= rule[j][0] && data[i][j] <= rule[j][1]) {
                    support=support+1;
                }
            }
            if(support==data[0].length){
                if(arraysearch.inarray(addedrule,i)==false){
                    addedrule[addedindexrule]=i;
                    count=count+1;
                    addedindexrule=addedindexrule+1;
                }
            }
        }
        return count;
    }
}
