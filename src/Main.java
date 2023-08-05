import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> resultList = new ArrayList<>();
        Future<List<Integer>> fibonacciFuture;

        for (int i = 0; i < 10; i++) {
            Future<Integer> result = executorService.submit(new PrimeCalculator((i + 1) * 500000));
            resultList.add(result);
        }

        fibonacciFuture = executorService.submit(new FibonacciCalculator(50000));

        while (resultList.stream().anyMatch(future -> !future.isDone() || !fibonacciFuture.isDone())) {
            System.out.println("At least one task is still processing.");
            Thread.sleep(1000);
        }

        int totalPrimes = 0;
        for (Future<Integer> future : resultList) {
            totalPrimes += future.get();
        }

        List<Integer> fibSeries = fibonacciFuture.get();

        executorService.shutdown();

        System.out.println("Total number of prime numbers: " + totalPrimes);
        System.out.println("Fibonacci series up to 50000: " + fibSeries);
    }
}