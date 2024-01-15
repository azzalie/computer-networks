public class InstanceThread implements Runnable {
    int a, b, count, sum;

    public InstanceThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        sum = 0;
        for (int i = a; i <= b; i++) {
            if (i % (11 * 13 * 17 * 19) == 0) {
                count++;
                sum += i;
//                System.out.println(i);
            }
        }
//        System.out.println("Всего чисел: " + count);
    }

    public int getCount() {
        return count;
    }

    public int getSum() {
        return sum;
    }
}
