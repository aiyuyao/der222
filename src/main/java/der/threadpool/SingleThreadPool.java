package der.threadpool;

import java.util.concurrent.*;

/**
 * Created by dev2 on 2018/5/29.
 */
public class SingleThreadPool {
    static int time = 500;
    public void getPool(){
        Executor executor = Executors.newSingleThreadExecutor();
        FutureTask futureTask = new FutureTask(()-> print());
        executor.execute(futureTask);
        try {
            futureTask.get(time, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
    public int print() throws InterruptedException {
        Thread.sleep(300);
        System.out.println("a task");
        return 1;
    }
}
