import java.util.Objects;

/**
 * add和extractMax的时间复杂度都是O(log(n))级别，即二叉树的树的高度的级别
 * 但是对于堆来说，他是一棵完全二叉树，永远不会退化成一个链表
 * 完全二叉树的高度和节点的数量关系一定是log(n)级别的
 *
 * @author Zbh
 */
public class Array<E> {
    private E[] data;
    private int size;//表示数组中实际存储了多少元素。定义为private，不希望用户随便修改，避免data数组中的元素个数和被修改后的size不一致。

    /**
     * capacity，数组的总容量，就是开辟的数组的length，所有不必再单独维护这个变量，capacity>=size
     *
     * @param capacity 构造函数，传入数组的容量capacity构造Array
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参的构造函数，默认数组的容量capacity=10
     */
    public Array() {
        this(10);//特别声明：capacity:10 这不是java语法一部分，而是编辑器给我们的代码提示，参数10的语义是capacity
    }

    //更多的构造函数，例如传进来一个静态数据，生成一个array类的对象，可以自己扩展
    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size=arr.length;
    }

    /**
     * 获取数组中的元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e) {
        /**
         * 此块代码被注释,用下面的语句代替实现
         if(size==data.length)
         throw new IllegalArgumentException("AddLast failed.Array is full!");
         data[size++]=e;//这一句代码等同于如下两句 data[size]=e;size++;
         */
        //可以复用"向指定位置index位置添加元素e"的方法
        add(size, e);
    }

    // 向所有元素前添加一个元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 向指定位置index位置添加元素e
    public void add(int index, E e) {
        // if (size == data.length)
        // throw new IllegalArgumentException("AddList filed.Array is full.");
        if (index < 0 || index > size)//不希望数组存在空,中间有没有合法的元素的情况，希望数组是紧密排列的，所以index < size
            throw new IllegalArgumentException("AddList filed.Require index >=0 and index <size.");
        if (size == data.length)
            resize(2 * data.length);
        for (int i = size - 1; i >= index; i--)//将index后面索引位置的数据都往后移动一位
            data[i + 1] = data[i];
        data[index] = e;
        size++;
    }

    // 获取数组索引位置的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed.Index is Illegal");
        }
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    // 取出最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed.Index is Illegal");
        }
        data[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return true;
        }
        return false;
    }

    // 查找数组中是否有元素e
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return i;
        }
        return -1;
    }

    //从数组中删除index位置的元素，返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed.Index is Illegal");
        }
        E res = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        // 如果数据删除到一定程度，数组自动减小至原数组一半
        if (size == data.length / 2)
            resize(data.length / 2);
        return res;
    }

    // 从数组中删除第一个元素，返回删除的元素
    public E removeFirst() {
        return remove(0);
    }


    // 从数组中删除最后一个元素，返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素e,只能删除一个
    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    // 动态数组，数组扩容
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is Illegal");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size=%d,capacity=%d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(',');
            }
        }
        res.append(']');
        return res.toString();
    }
}