package der;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dev2 on 2018/6/21.
 */
public class MyLock {
    private final Lock lock = new ReentrantLock();

    void print(){
        lock.tryLock();
    }

    public static void main(String[] args) {

    }
}
