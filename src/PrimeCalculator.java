import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class PrimeCalculator implements Callable<Integer> {
    private int number;

    public PrimeCalculator(int number) {
        this.number = number;
    }

    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer call() {
        int count = 0;
        for (int i = 2; i <= this.number; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> resultList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> result = executorService.submit(new PrimeCalculator((i + 1) * 500000));
            resultList.add(result);
        }

        while (resultList.stream().anyMatch(future -> !future.isDone())) {
            System.out.println("At least one task is still processing.");
            Thread.sleep(1000);
        }

        int totalPrimes = 0;
        for (Future<Integer> future : resultList) {
            totalPrimes += future.get();
        }

        executorService.shutdown();

        System.out.println("Total number of prime numbers: " + totalPrimes);
    }
}

