package Leetcode0295;

import java.util.PriorityQueue;

public class MedianFinder_1 {
    PriorityQueue<Integer> maxHeap; //存小的一半值
    PriorityQueue<Integer> minHeap; //存大的一半值
    public MedianFinder_1() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>((a,b)->a-b);
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        if(maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }
        else if(minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if(maxHeap.size() == minHeap.size()) return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else return maxHeap.peek();
    }
}
