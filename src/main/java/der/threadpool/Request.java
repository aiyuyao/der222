package der.threadpool;

/**
 * Created by dev2 on 2018/5/29.
 */
public class Request {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        for (int i=0;i<2000;i++){
            new Thread(thread).start();
        }
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {
        new SingleThreadPool().getPool();
    }
}
