package ergasia_sls;

import java.util.ArrayList;
import java.util.List;
public class Queue {
    private Process[] heap;
    private int size;
    private int capacity;

    public Queue(int capacity){
        this.capacity = capacity;
        size=0;
        heap = new Process[capacity+1];
    }

    public void insert(Process process){
        if (size == capacity)
            return;

        size++;
        heap[size]= process;
        int current = size;

        while (current > 1 && heap[current].getPriority() < heap[parent(current)].getPriority()){
            swap(current, parent(current));
            current = parent(current);
        }
    }

    private int parent(int i) {return i/2;}

    private void swap(int i,int j){
        Process temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public Process extractMax(){
        if (size == 0) return null;
        return heap[1];
    }

    public void removeMax(){
        if (size == 0) return;
        heap[1] = heap[size];
        size--;
        heapifyDown(1);
    }

    private void heapifyDown(int index){
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        int largest = index;

        if (leftChild <= size && heap[leftChild].getPriority() < heap[largest].getPriority())
            largest = leftChild;
        if (rightChild <= size && heap[rightChild].getPriority() < heap[largest].getPriority())
            largest = rightChild;

        if (index != largest){
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private int leftChild(int i) {return 2*i;}
    private int rightChild(int i) {return 2*i + 1;}
    public int getSize() {return size;}


}
