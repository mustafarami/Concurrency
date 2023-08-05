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

}
