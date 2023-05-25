package sorting;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BubbleSort {

    public static void main(String[] args) {
        Integer arr[] = {8,-1,11,-2,23,-5,2,35};

         IntStream.iterate(0, i-> i+1).limit(arr.length)
                .forEach(i -> {
                    IntStream.iterate(i+1,j -> j+1)
                            .limit(arr.length-i-1)
                            .forEach(j -> {
                                if(arr[i] > arr[j]){
                                    swap(arr,i,j);
                                }
                            });
                        }
                );
        Stream.of(arr).forEach(elem -> System.out.print(elem + " "));
        System.out.println();

    }

    public static void swap(Integer arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        Stream.of(arr).forEach(elem -> System.out.print(elem + " "));
        System.out.println();

    }

}
