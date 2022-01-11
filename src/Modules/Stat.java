package Modules;

import java.util.Arrays;

public class Stat {
    public double mean;
    public double mode;
    public double median;

    public Stat(int[] a){
        mean = findMean(a);
        median = findMedian(a);
        mode = findMode(a);
    }

    public  double findMean(int[] a){
        int sum = 0;
        for(int i=0; i<a.length;i++){
            sum += a[i];
        }
        return sum/a.length;
    }

    public double findMedian(int[] a){
        int[] cpy = Arrays.copyOf(a, a.length);
        Arrays.sort(cpy);
        if(cpy.length%2==0){
            return (cpy[cpy.length/2] + cpy[cpy.length/2 -1])*1.0/2;
        } else {
            return cpy[cpy.length/2];
        }
    }

    public double findMode(int[] a){
        int[] cpy = Arrays.copyOf(a,a.length);
        int maxValue = 0, maxCount = 0, i, j;

        for (i = 0; i < cpy.length; ++i) {
            int count = 0;
            for (j = 0; j < cpy.length; ++j) {
                if (cpy[j] == cpy[i])
                    ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = a[i];
            }
        }
        return maxValue;

    }

}
