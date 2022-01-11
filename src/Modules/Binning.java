package Modules;

import java.util.Arrays;

public class Binning {
    public int number_of_bins;
    public int bin_size;
    public int[][] bins;
    public int[] sample;

    public Binning(int number_of_bins, int bin_size, int[] a){

        try {

            this.bins = new int[number_of_bins][bin_size];
            this.sample = new int[number_of_bins * bin_size];
            this.number_of_bins = number_of_bins;
            this.bin_size = bin_size;

            if (a.length > number_of_bins * bin_size) {
                //throw exception
                throw new CustomException("Bin Overflow");
            }

            //padding
            int[] zero = new int[(number_of_bins * bin_size) % a.length];
            for (int j = 0, i = (number_of_bins * bin_size) % a.length; i < sample.length; i++, j++) {
                sample[i] = a[j];
            }

            //sort sample
            Arrays.sort(sample);

            //copy to bins
            for (int i = 0, k = 0; i < number_of_bins; i++) {
                for (int j = 0; j < bin_size; j++, k++) {
                    bins[i][j] = sample[k];
                }
            }
        } catch(CustomException ce){
            System.out.println(ce.getMessage());
            System.exit(1);
        }

    }

    public void showBins(){
        System.out.println("Bins");
        for(int i=0; i<number_of_bins;i++){
            System.out.println("bin:"+i+": "+Arrays.toString(bins[i]));
        }
    }

    public void showSample(){
        System.out.println("sample: "+Arrays.toString(sample));
    }

    public void smoothenByBinMean(){
        System.out.println("Smoothening by bin mean");
        float[][] smoothen_bin_mean = new float[number_of_bins][bin_size];
        for(int i=0; i<number_of_bins;i++){
            float bin_mean = 0;
            for(int j=0; j<bin_size; j++){
                bin_mean += bins[i][j];
            }
            for(int j=0; j<bin_size; j++){
                smoothen_bin_mean[i][j] = bin_mean/bin_size;
            }

            System.out.println("bin:"+i+": "+Arrays.toString(smoothen_bin_mean[i]));
        }
    }

    public void smoothenByBinBoundary(){
        System.out.println("Smoothening by bin boundary");
        float[][] smoothen_bin_boundary = new float[number_of_bins][bin_size];
        for(int i=0; i<number_of_bins;i++){
            int min_boundary = bins[i][0];
            int max_boundary = bins[i][bin_size-1];

            for(int j=0; j<bin_size;j++){
                if(j-0 <= bin_size-1-j){
                    smoothen_bin_boundary[i][j] = min_boundary;
                } else {
                    smoothen_bin_boundary[i][j] = max_boundary;
                }
            }
            System.out.println("bin:"+i+": "+Arrays.toString(smoothen_bin_boundary[i]));
        }
    }


}
