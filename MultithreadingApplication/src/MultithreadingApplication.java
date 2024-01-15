public class MultithreadingApplication {
    public static void main(String[] args) {

        int a = 100000000;
        int b = 400000000;
        int h, countThreads, sumThreads;
        long startTime, endTime, executionTime;

        System.out.println("Один поток");
        InstanceThread st1 = new InstanceThread(a, b);
        countThreads = 0;
        sumThreads = 0;
        startTime = System.currentTimeMillis();
        Thread thread11 = new Thread(st1);
        thread11.start();
        try {
            thread11.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countThreads = st1.getCount();
        sumThreads = st1.getSum();
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println("Время: " + executionTime);
        System.out.println("Найденных чисел: " + countThreads);
        System.out.println("Сумма найденных чисел: " + sumThreads);
        System.out.println();

        System.out.println("Три потока");
        h = (b - a) / 3;
        countThreads = 0;
        sumThreads = 0;
        InstanceThread mt1 = new InstanceThread(a, a + h - 1);
        InstanceThread mt2 = new InstanceThread(a + h, a + h * 2 - 1);
        InstanceThread mt3 = new InstanceThread(a + h * 2, b);
        startTime = System.currentTimeMillis();
        Thread thread21 = new Thread(mt1);
        thread21.start();
        Thread thread22 = new Thread(mt2);
        thread22.start();
        Thread thread23 = new Thread(mt3);
        thread23.start();
        try {
            thread21.join();
            thread22.join();
            thread23.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countThreads += mt1.getCount();
        countThreads += mt2.getCount();
        countThreads += mt3.getCount();
        sumThreads += mt1.getSum();
        sumThreads += mt2.getSum();
        sumThreads += mt3.getSum();
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println("Время: " + executionTime);
        System.out.println("Найденных чисел: " + countThreads);
        System.out.println("Сумма найденных чисел: " + sumThreads);
        System.out.println();

//        int N = 7993;
        int N = 8001;
        System.out.printf("%d потока%s", N, "\n");
        h = (b - a) / N;
        int start, end;
        countThreads = 0;
        sumThreads = 0;
        startTime = System.currentTimeMillis();
        Thread[] arr = new Thread[N];
        InstanceThread[] cnt = new InstanceThread[N];
        for (int i = 0; i < arr.length; i++) {
            start = a + i * h;
            if (i < N - 1) {
                end = a + (i + 1) * h - 1;
            } else {
                end = b;
            }
//            System.out.printf("%d, %d%s", start, end, "\n");
            cnt[i] = new InstanceThread(start, end);
            arr[i] = new Thread(cnt[i]);
//            arr[i] = new Thread(new InstanceThread(start, end));
            arr[i].start();
        }
        try {
            for (Thread thread : arr) {
                thread.join();
            }
            for (InstanceThread task : cnt) {
                countThreads += task.getCount();
                sumThreads += task.getSum();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println("Время: " + executionTime);
        System.out.println("Найденных чисел: " + countThreads);
        System.out.println("Сумма найденных чисел: " + sumThreads);
    }
}
