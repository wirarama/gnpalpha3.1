/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gnpalpha3;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.ImageTerminal;
import com.panayotis.gnuplot.terminal.PostscriptTerminal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

/**
 *
 * @author test
 */
public class plot {
    static boolean fileexport=false;
    public static void makeplot(ArrayList<int[][]> data,String[] label,String pngname,String xlabel,String ylabel,String testdate){
        JavaPlot p = new JavaPlot();
        p.setTitle(pngname);
        p.setKey(JavaPlot.Key.BELOW);
        p.getAxis("x").setLabel(xlabel);
        p.getAxis("y").setLabel(ylabel);
        int k=0;
        for(int i=0;i<data.size();i++){
            int[][] data1 = data.get(i);
            p.addPlot(data1);
            ((AbstractPlot) p.getPlots().get(k)).setTitle(label[k]);
            PlotStyle stl = ((AbstractPlot) p.getPlots().get(k)).getPlotStyle();
            if("rangecoverage".equals(pngname)){
                stl.setStyle(Style.BOXERRORBARS);
            }else{
                stl.setStyle(Style.LINES);
            }
            if(fileexport==true){
                pngexport(p,"log/"+testdate+"/"+pngname+testdate+".png");
            }else{
                epsexport(p,"log/"+testdate+"/"+pngname+testdate+".eps");
            }
            k++;
        }
    }
    public static void makeplot1(int[][] data,String label,String pngname,String xlabel,String ylabel,String testdate){
        JavaPlot p = new JavaPlot();
        p.setTitle(pngname);
        p.setKey(JavaPlot.Key.BELOW);
        p.getAxis("x").setLabel(xlabel);
        p.getAxis("y").setLabel(ylabel);
        p.addPlot(data);
        ((AbstractPlot) p.getPlots().get(0)).setTitle(label);
        PlotStyle stl = ((AbstractPlot) p.getPlots().get(0)).getPlotStyle();
        stl.setStyle(Style.LINES);
        if(fileexport==true){
            pngexport(p,"log/"+testdate+"/"+pngname+testdate+".png");
        }else{
            epsexport(p,"log/"+testdate+"/"+pngname+testdate+".eps");
        }
    }
    public static void makeplot2(ArrayList<double[][]> data,String[] label,String pngname,String xlabel,String ylabel,String testdate){
        JavaPlot p = new JavaPlot();
        p.setTitle(pngname);
        p.setKey(JavaPlot.Key.BELOW);
        p.getAxis("x").setLabel(xlabel);
        p.getAxis("y").setLabel(ylabel);
        int k=0;
        for(int i=0;i<data.size();i++){
            double[][] data1 = data.get(i);
            p.addPlot(data1);
            ((AbstractPlot) p.getPlots().get(k)).setTitle(label[k]);
            PlotStyle stl = ((AbstractPlot) p.getPlots().get(k)).getPlotStyle();
            if("rangecoverage".equals(pngname)){
                stl.setStyle(Style.BOXERRORBARS);
            }else{
                stl.setStyle(Style.LINES);
            }
            if(fileexport==true){
                pngexport(p,"log/"+testdate+"/"+pngname+testdate+".png");
            }else{
                epsexport(p,"log/"+testdate+"/"+pngname+testdate+".eps");
            }
            k++;
        }
    }
    public static void pngexport(JavaPlot p,String pngname){
        ImageTerminal png = new ImageTerminal();
        File file = new File(pngname);
        try {
            file.createNewFile();
            png.processOutput(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            System.err.print(ex);
        } catch (IOException ex) {
            System.err.print(ex);
        }
        p.setTerminal(png);
        p.plot();
        try {
            ImageIO.write(png.getImage(), "png", file);
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }
    public static void epsexport(JavaPlot p,String epsname){
        PostscriptTerminal epsf = new PostscriptTerminal(epsname);
        epsf.setColor(true);
        p.setTerminal(epsf);
        p.plot();
    }
    public static int[][] datasplit(int[][] data,int index){
        int[][] out = new int[data.length][2];
        for(int i=0;i<data.length;i++){
            out[i][0]=i+1;
            out[i][1]=data[i][index];
        }
        return out;
    }
    public static void datasplitbatch(int[][] data,int limit,String testdate){
        ArrayList<int[][]> dataplot;
        String[] label;
        int l = 1;
        for(int i=0;i<data[0].length;i=i+limit){
            int k = i;
            dataplot = new ArrayList<>();
            label = new String[5];
            LOOP:for(int j=0;j<limit;j++){
                label[j] = "attr"+(k+1);
                int[][] dataplot1 = datasplit(data,k);
                dataplot.add(dataplot1);
                k++;
                if(k>=data[0].length) break;
            }
            makeplot(dataplot,label,"data("+l+")","data","value",testdate);
            l++;
        }
    }
    public static void datarangeset(int[][] data,String testdate){
        ArrayList<int[][]> dataplot = new ArrayList<>();
        String[] label = new String[data.length];
        for(int i=0;i<data.length;i++){
            int[][] data1 = new int[data[0].length][2];
            for(int j=0;j<data[0].length;j++){
                data1[j][0] = j+1;
                data1[j][1] = data[i][j];
            }
            label[i] = "attr "+i;
            dataplot.add(data1);
        }
        makeplot(dataplot,label,"rangecoverage","range index","value",testdate);
    }
    public static void silhoutte(double[][] data,String testdate,String add,int subject){
        int[] nuattribute = {2,4,8,16,24,32};
        int[] nudataamount = {2,4,6,8,10,12};
        ArrayList<double[][]> dataplot = new ArrayList<>();
        String[] label = {"gnp","hierarchical","kmean"};
        for (double[] data2 : data) {
            double[][] data1 = new double[data[0].length][2];
            for (int j = 0; j<data[0].length; j++) {
                if(subject==1){
                    data1[j][0] = nuattribute[j];
                }else{
                    data1[j][0] = nudataamount[j];
                }
                data1[j][1] = data2[j];
            }
            dataplot.add(data1);
        }
        if(subject==1){
            makeplot2(dataplot,label,add,"attribute","silhouette value",testdate);
        }else{
            makeplot2(dataplot,label,add,"k","silhouette value",testdate);
        }
    }
    public static void silhoutte1(double[][] data,String testdate,String add){
        int[] nuattribute = {1,2,3,4,5,6,7,8};
        ArrayList<double[][]> dataplot = new ArrayList<>();
        String[] label = {"gnp","hierarchical","kmean"};
        for (double[] data2 : data) {
            double[][] data1 = new double[data[0].length][2];
            for (int j = 0; j<data[0].length; j++) {
                data1[j][0] = nuattribute[j];
                data1[j][1] = data2[j];
            }
            dataplot.add(data1);
        }
        makeplot2(dataplot,label,add,"case","silhouette value",testdate);
    }
    public static int[][] plotstep(int[] data){
        int[][] out = new int[data.length][2];
        for(int i=0;i<data.length;i++){
            out[i][0] = i;
            out[i][1] = data[i];
        }
        return out;
    }
}
