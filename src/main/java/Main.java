import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Callable<String> callable = new MyCallable();
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        List<Future<String>> list = new ArrayList<>();
        List<Callable<String>> list2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            final Future<String> task = threadPool.submit(callable);
            list.add(task);
        }
        for (Future<String> fut : list) {
            try{
                Thread.sleep(2500);
                System.out.println(fut.get() + " Всем привет");
            } catch (InterruptedException |
                    ExecutionException e) {}
        }
        for (int i = 0; i < 4; i++) {
            list2.add(new MyCallable());
        }

        System.out.println(threadPool.invokeAny(list2) + " Снова привет");

        threadPool.shutdown();
    }


}

