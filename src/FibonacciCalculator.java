import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class FibonacciCalculator implements Callable<List<Integer>> {
    private int number;

    public FibonacciCalculator(int number) {
        this.number = number;
    }

    @Override
    public List<Integer> call() {
        List<Integer> fibonacciSeries = new ArrayList<>();
        int a = 0;
        int b = 1;
        fibonacciSeries.add(a);
        fibonacciSeries.add(b);

        while (b <= this.number) {
            int temp = a + b;
            a = b;
            b = temp;
            fibonacciSeries.add(b);
        }
        return fibonacciSeries;
    }
}