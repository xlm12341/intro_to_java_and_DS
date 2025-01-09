import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MySort {
    public static void main(String[] args) {
        Integer[] integers = new Integer[10];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = i;
        }
        List<Integer> list = Arrays.asList(integers);
        Collections.shuffle(list);
        list.toArray(integers);
        System.out.println(Arrays.toString(integers));

//        InsertSort(integers);
//        BubbleSort(integers);
//        BubbleSortImproved(integers);
        MergeSort(integers);
        System.out.println(Arrays.toString(integers));

    }

    public static void InsertSort(Integer[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (arr[j] > arr[i]) {
//                    //插入
//                    int tmp = arr[i];
//                    for (int k = i-1; k >= j; k--) {
//                        arr[k+1] = arr[k];
//                    }
//                    arr[j] = tmp;
//
//                }
//            }
//        }

        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            int k;
            for (k = i - 1; k >= 0 && arr[k] > tmp; k--) {
                arr[k + 1] = arr[k];
            }

            arr[k + 1] = tmp;
        }
    }

    public static void BubbleSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    //如果在一次冒泡中没有发生交换则不必进行下一次遍历，因为所有元素已有序
    public static void BubbleSortImproved(Integer[] arr) {
        boolean needNextPass = true;
        for (int i = 1; i < arr.length && needNextPass; i++) {
            needNextPass = false;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    needNextPass = true;
                }
            }
        }
    }


    public static void MergeSort(Integer[] arr) {
        mergeSort(arr, 0, arr.length);

    }

    public static void mergeSort(Integer[] arr, int start, int end) {
        int mid = (start + end) / 2;
        int sortLen = end-start;
        if (sortLen > 1) {
            mergeSort(arr, start, mid);
            mergeSort(arr, mid, end);
        }else if (sortLen == 1)
            return;

        Integer[] tmpArr = new Integer[end - start];
        int current1 = 0;
        int current2 = start;
        int current3 = mid;
        while (current2 < mid && current3 < end) {
            if (arr[current2] < arr[current3]) {
                tmpArr[current1] = arr[current2];
                current2++;
            } else {
                tmpArr[current1] = arr[current3];
                current3++;
            }
            current1++;
        }

        if (current2 == mid) {
            while (current3 < end) {
                tmpArr[current1] = arr[current3];
                current3++;
                current1++;
            }
        } else if (current3 == end) {
            while (current2 < mid) {
                tmpArr[current1] = arr[current2];
                current2++;
                current1++;
            }

        }

        System.arraycopy(tmpArr,0, arr,start, end-start);

    }
}
