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
import static gnpalpha3.mainprocess.iterationindex;
import static gnpalpha3.mainprocess.added;
import static gnpalpha3.mainprocess.addedindex;
import static gnpalpha3.mainprocess.addedrule;
import static gnpalpha3.mainprocess.addedindexrule;
import static gnpalpha3.mainprocess.data;
/**
 *
 * @author test
 */
public class rule {
    public static void ruleset(
            int ruleamount,
            int attributeamount,
            double[][] stat,
            int[][][] rangeset,
            String testdate
    ) throws IOException{
        iterationindex = iterationindex+1;
        for(int i=0;i<ruleamount;i++){
            int[][] ruletemp = randomrule(attributeamount,rangeset);
            int rulecoverage = rulecoverage(ruletemp);
            if(rulecoverage!=0){
                ruleset[totalrule] = ruletemp;
                rulesetcoverage[totalrule] = rulecoverage;
                totalrule=totalrule+1;
            }
            totalcoverage = totalcoverage+rulecoverage;
            iterationcoverage.add(totalcoverage);
            double percentpre = ((double)totalcoverage/(double)data.length);
            int percent = (int)(percentpre*100);
            btn.setText(""+iterationindex+"["+i+"]"+totalcoverage+"("+percent+"%)");
            if(totalcoverage>=(data.length-1)) break;
        }
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
    public static int[][][] rangeset(int attributeamount,double[][] stat){
        int[][][] range = new int[attributeamount][4][2];
        for(int i=0;i<attributeamount;i++){
            range[i] = rangegeneratorsub(stat[i][0],stat[i][1],i);
        }
        return range;
    }
    public static int[][] rangegeneratorsub(double max,double min,int index){
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
    public static int[][] rangelogset(int[][][] rangeset){
        int[][] rangelogset = new int[rangeset.length][4];
        for(int i=0;i<rangeset.length;i++){
            added = new int[data.length];
            addedindex = 0;
            for(int j=0;j<4;j++){
                rangelogset[i][j] = rangelog(rangeset[i][j][0],rangeset[i][j][1],i);
            }
        }
        return rangelogset;
    }
    public static int rangelog(int min,int max,int index){
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
    public static int rulecoverage(int[][] rule){
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
