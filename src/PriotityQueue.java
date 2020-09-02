public class PriotityQueue<E extends Comparable<E>>  implements Queue<E>{
    private MaxHeap<E> maxHeap;
    public PriotityQueue(){
        maxHeap=new MaxHeap<>();
    }

    @Override
    //入队
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    //出队
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    //查看队首的元素
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
