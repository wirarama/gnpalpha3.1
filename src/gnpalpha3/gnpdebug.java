/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gnpalpha3;

/**
 *
 * @author wirarama
 */
public class gnpdebug {
    public static void coverage(int dataamount,int dataconst,int coverageconst){
        int[][] attribute = new int[dataamount][2];
        int[] attribute_coverage = new int[dataamount];
        System.out.println("coverage");
        for(int i=0;i<dataamount;i++){
            int j=i+1;
            int last=0;
            if(i!=0){
                last=attribute[i-0][1];
            }
            attribute[i][0] = last+(j*randominput.randomrange(j,j+dataconst));
            attribute[i][1] = attribute[i][0]+(j*randominput.randomrange(j,j+dataconst));
            attribute_coverage[i] = randominput.randomrange(j,j+coverageconst);
            System.out.println(attribute[i][0]+"-"+attribute[i][1]+"="+attribute_coverage[i]);
        }
    }
    public static void partialrandom(int dataamount,int coverageconst){
        int[][] attribute = new int[dataamount][2];
        int[] attribute_coverage = new int[dataamount];
        int[] stddev = new int[dataamount];
        System.out.println("partialrandom");
        for(int i=0;i<dataamount;i++){
            int dataconst = randominput.randomrange(0,1000);
            int j=i+1;
            int last=0;
            if(i!=0){
                last=attribute[i-0][1];
            }
            attribute[i][0] = last+(j*randominput.randomrange(j,j+dataconst));
            attribute[i][1] = attribute[i][0]+(j*randominput.randomrange(j,j+dataconst));
            attribute_coverage[i] = randominput.randomrange(j,j+coverageconst);
            stddev[i] = randominput.randomrange(10,50);
            System.out.println(attribute[i][0]+"-"+attribute[i][1]+"="+attribute_coverage[i]+";"+stddev[i]);
        }
    }
}
