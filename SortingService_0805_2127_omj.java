// 代码生成时间: 2025-08-05 21:27:43
package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class SortingService {

    private static final String INVALID_INPUT = "Input list cannot be null or empty";

    /**<ol>
     * Sorts an integer list using the QuickSort algorithm.
     * 
     * @param numbers The list of integers to sort.
     * @return A sorted list of integers.
     * @throws IllegalArgumentException If the input list is null or empty.
     */
    public List<Integer> quickSort(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        return Arrays.asList(quickSortHelper(numbers, 0, numbers.size() - 1));
    }

    /**<ol>
     * Helper method for QuickSort algorithm.
     * 
     * @param numbers The list to sort.
     * @param low The starting index of the list.
     * @param high The ending index of the list.
     * @return A sorted array.
     */
    private Integer[] quickSortHelper(List<Integer> numbers, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(numbers, low, high);
            quickSortHelper(numbers, low, pivotIndex - 1);
            quickSortHelper(numbers, pivotIndex + 1, high);
        }
        return numbers.toArray(new Integer[0]);
    }

    /**<ol>
     * Partitions the list around a chosen pivot element.
     * 
     * @param numbers The list to partition.
     * @param low The starting index of the list.
     * @param high The ending index of the list.
     * @return The index of the pivot element.
     */
    private int partition(List<Integer> numbers, int low, int high) {
        int pivot = numbers.get(high);
        int index = low;

        for (int i = low; i < high; i++) {
            if (numbers.get(i) < pivot) {
                swap(numbers, i, index);
                index++;
            }
        }

        swap(numbers, index, high);
        return index;
    }

    /**<ol>
     * Swaps two elements in the list.
     * 
     * @param numbers The list containing the elements to swap.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(List<Integer> numbers, int i, int j) {
        Integer temp = numbers.get(i);
        numbers.set(i, numbers.get(j));
        numbers.set(j, temp);
    }

    // Main method for testing
    public static void main(String[] args) {
        SortingService service = new SortingService();
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        try {
            List<Integer> sortedNumbers = service.quickSort(numbers);
            System.out.println("Sorted Numbers: " + sortedNumbers);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
