package der;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by dev2 on 2018/6/22.
 */
public class TransferQueueExample {
    TransferQueue transferQueue = new LinkedTransferQueue<>();
    Executor executor = Executors.newFixedThreadPool(2);

}

class Producer implements Runnable{

    @Override
    public void run() {

    }
}

class Consumer implements Runnable{

    @Override
    public void run() {

    }
}
