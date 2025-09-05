// 代码生成时间: 2025-09-06 00:53:31
import org.springframework.stereotype.Service;

@Service
public class SortingService {

    // 冒泡排序算法
    public int[] bubbleSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // 交换相邻元素
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    // 快速排序算法
    public int[] quickSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        return quickSort(array, 0, array.length - 1);
    }

    private int[] quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1); // 排序左子数组
            quickSort(array, pi + 1, high); // 排序右子数组
        }
        return array;
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // 插入排序算法
    public int[] insertionSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            // 将选定元素插入已排序序列中的正确位置
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    // 选择排序算法
    public int[] selectionSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
        return array;
    }

    // 合并排序算法
    public int[] mergeSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        return mergeSort(array, 0, array.length - 1);
    }

    private int[] merge(int[] array, int l, int m, int r) {
        int[] tempArray = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) {
            if (array[i] <= array[j]) {
                tempArray[k++] = array[i++];
            } else {
                tempArray[k++] = array[j++];
            }
        }
        while (i <= m) {
            tempArray[k++] = array[i++];
        }
        while (j <= r) {
            tempArray[k++] = array[j++];
        }
        for (i = l, k = 0; i <= r; i++, k++) {
            array[i] = tempArray[k];
        }
        return array;
    }

    private int[] mergeSort(int[] array, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(array, l, m); // 排序左子数组
            mergeSort(array, m + 1, r); // 排序右子数组
            merge(array, l, m, r); // 合并两个已排序的子数组
        }
        return array;
    }
}
