package BinaryHeap;

import java.util.*;

public class BinaryHeap {
    private int[] heapArray;
    private int size;
    private int swaps;

    public BinaryHeap(int capacity) {
        heapArray = new int[capacity];
        size = 0;
        swaps = 0;
    }

    public void addElementOneByOne(int[] elements) {
        long startTime = System.nanoTime();
        for (int element : elements) {
            heapArray[size] = element;
            int current = size;
            while (heapArray[current] < heapArray[(current - 1) / 2]) {
                swap(current, (current - 1) / 2);
                current = (current - 1) / 2;
                swaps++;
            }
            size++;
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Time taken for adding elements one by one: " + executionTime + " nanoseconds");
        System.out.println("Total number of swaps: " + swaps);
        System.out.println("Heap elements after adding one by one:");
        iteratorPreOrder(0);
    }

    public void addElementLinearTime(int[] elements) {
        long startTime = System.nanoTime();
        for (int i = 0; i < elements.length; i++) {
            heapArray[i] = elements[i];
        }
        size = elements.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
        
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("\nTime taken for adding elements using linear-time algorithm: " + executionTime + " nanoseconds");
        System.out.println("Total number of swaps: " + swaps);
        System.out.println("Heap elements after linear-time algorithm:");
        iteratorPreOrder(0);
    }

    private void heapify(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && heapArray[left] < heapArray[smallest]) {
            smallest = left;
        }

        if (right < size && heapArray[right] < heapArray[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void swap(int a, int b) {
        int temp = heapArray[a];
        heapArray[a] = heapArray[b];
        heapArray[b] = temp;
        swaps++;
    }

    public void iteratorPreOrder(int index) {
        if (index >= size) {
            return;
        }
        System.out.print(heapArray[index] + " ");
        iteratorPreOrder(2 * index + 1);
        iteratorPreOrder(2 * index + 2);
    }

    public static void main(String[] args) {
        int[] randomNumbers = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            randomNumbers[i] = random.nextInt(501); // Random numbers between 0 and 500
        }

        BinaryHeap binaryHeap = new BinaryHeap(100);
        binaryHeap.addElementOneByOne(randomNumbers);

        BinaryHeap binaryHeap2 = new BinaryHeap(100);
        binaryHeap2.addElementLinearTime(randomNumbers);
    }
}
