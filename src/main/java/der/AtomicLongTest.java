package der;

/**
 * Created by dev2 on 2018/6/15.
 */
public class AtomicLongTest implements Runnable{
    volatile static int atomicLong = 0;
//    static AtomicLong atomicLong = new AtomicLong(0);
    public static void main(String[] args) {
        AtomicLongTest atomicLongTest = new AtomicLongTest();
        for (int i = 0;i<1000;i++){
            new Thread(atomicLongTest).start();
        }
        System.out.println(atomicLong);
    }

    @Override
    public void run() {
        atomicLong++;
//        atomicLong.incrementAndGet();
    }
}
