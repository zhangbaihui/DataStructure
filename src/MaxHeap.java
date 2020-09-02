

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        //heapify逻辑 对非叶子节点逐个进行siftDown中
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    //返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    //返回一个布尔值，表示堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * @param index 当前索引节点
     * @return 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     */
    private int parent(int index) {
        if (index == 0)//根节点没有父亲节点
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    /**
     * @param index
     * @return 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * @param index
     * @return 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        //维护堆的性质
        siftUp(data.getSize() - 1);
    }

    //看堆中最大元素
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Cannot findMax when heap is empty");
        return data.get(0);
    }

    public E extractMax() {
        E ret = findMax();//先暂存一下最大的元素
        //1.将最大的元素删除
        //2.维护一下堆的性质，下沉操作
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    //上沉
    private void siftUp(int k) {
        //只要上浮的元素不是根节点，当前的元素和它的父亲节点的值进行比较,只要是比父亲节点大就交换
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //下浮
    private void siftDown(int k) {
        //k所处的节点是叶子结点，左孩子的索引比data的总数还小时，循环结束
        //比较k这个节点和他左右孩子中最大的节点的值，是不是比它小，小则交换
        while (leftChild(k) < data.getSize()) {
            //不是叶子接点，肯定有左孩子
            int j = leftChild(k);
            //判断是否有右孩子
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
                //data[j]是leftChild和rightChild中的最大值
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    //取出堆中最大的元素，并且替换成元素e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);//堆顶放入e
        siftDown(0);
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size=%d,capacity=%d\n", data.getSize(), data.getCapacity()));
        res.append('[');
        for (int i = 0; i < data.getSize(); i++) {
            res.append(data.get(i));
            if (i != data.getSize() - 1) {
                res.append(',');
            }
        }
        res.append(']');
        return res.toString();
    }
}
