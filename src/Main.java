import java.util.Random;

public class Main {
    //1、用isHeapfy2.用Add
    private static double testHeap(Integer[] testData, boolean isHeapfy) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapfy == true) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (int num : testData) {
                maxHeap.add(num);
            }
        }
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
//        int a = 10, b = 20;
//        swapInt(a, b);
//        System.out.println("a=" + a + ";b=" + b + ".");

        //测试我们的堆
        //对于100,0000的数据量
        /**
         *
         * int n = 1000000;
         *         MaxHeap<Integer> maxHeap = new MaxHeap<>();
         *         Random random = new Random();
         *         for (int i = 0; i < n; i++) {
         *             //nextInt方法：该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
         *             maxHeap.add(random.nextInt(100));
         *         }
         *         int[] arr = new int[n];
         *         for (int i = 0; i < n; i++) {
         *             arr[i] = maxHeap.extractMax();
         *         }
         *         for (int i = 1; i < n; i++) {
         *             if (arr[i - 1] < arr[i])
         *                 throw new IllegalArgumentException("Error");
         *         }
         *         System.out.println("Test MaxHeap completed.");
         */

        int n = 1000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        /**
         * WithOut heapify:1.5332627s.
         * With heapify:1.4535005s.
         * 时间复杂度感觉差异不大？？？
         */
        //时间复杂度O(log(n))
        double time1 = testHeap(testData, false);
        System.out.println("WithOut heapify:" + time1 + "s.");
        //时间复杂度O(n)
        double time2 = testHeap(testData, true);
        System.out.println("With heapify:" + time2 + "s.");
    }

    private static void swapInt(int a, int b) {
        int n = a;
        a = b;
        b = n;
        System.out.println("a=" + a + ";b=" + b + ".");
    }

}
