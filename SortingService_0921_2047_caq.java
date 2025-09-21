// 代码生成时间: 2025-09-21 20:47:45
package com.example.sorting;

import org.springframework.stereotype.Service;

@Service
public class SortingService {
    // Helper method to swap elements in an array
    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    // Bubble Sort implementation
    public void bubbleSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    // Insertion Sort implementation
    public void insertionSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    // Selection Sort implementation
    public void selectionSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }

            swap(array, minIdx, i);
        }
    }

    // Main method to test the sorting service
    public static void main(String[] args) {
        SortingService service = new SortingService();
        int[] array = {64, 25, 12, 22, 11};

        System.out.println("Original array: ");
        printArray(array);

        service.bubbleSort(array);
        System.out.println("Array after Bubble Sort: ");
        printArray(array);

        array = new int[]{64, 25, 12, 22, 11};
        service.insertionSort(array);
        System.out.println("Array after Insertion Sort: ");
        printArray(array);

        array = new int[]{64, 25, 12, 22, 11};
        service.selectionSort(array);
        System.out.println("Array after Selection Sort: ");
        printArray(array);
    }

    // Helper method to print array
    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
