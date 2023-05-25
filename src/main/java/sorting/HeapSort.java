package sorting;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int arr[] = {38, 27, 43, 3, 9, 82, 10};
        heapSort(arr);
        System.out.println("Sorted array");
        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    private static void heapSort(int[] arr) {
        for(int i= arr.length-1; i >= 0; i--){
            heapify(arr,0,i);
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
        }
    }

    private static void heapify(int[] arr, int low, int high) {
        int mid = high/2;
        for(int i = mid; i >=0;i--){
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;

            if(leftChildIndex <= high && arr[i] < arr[leftChildIndex]){
                int temp = arr[i];
                arr[i] = arr[leftChildIndex];
                arr[leftChildIndex] = temp;
            }
            if(rightChildIndex <= high && arr[i] < arr[rightChildIndex]){
                int temp = arr[i];
                arr[i] = arr[rightChildIndex];
                arr[rightChildIndex] = temp;
            }
        }
    }
}
