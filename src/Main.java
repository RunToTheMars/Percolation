import java.util.Arrays;
import java.util.Random;

public class Main {

    private static int m_tests_count = 1000;
    private static int m_N = 1000;

    private static double Variance(double[] array, double average)
    {
        double sum = 0.;

        for (double val : array)
            sum += (val - average) * (val - average);

        if (array.length == 0)
            return 0.;

        return sum / array.length;
    }

    public static void main(String[] args) {

        double[] opened_in_test = new double[m_tests_count];

        for (int test_i = 0; test_i < m_tests_count; test_i++)
        {
            Random rng = new Random();
            Grid grid = new Grid(m_N);
            while (!grid.HasPercolation())
            {
                int i = rng.nextInt(m_N);
                int j = rng.nextInt(m_N);
                grid.Open(i, j);
            }
            opened_in_test[test_i] = ((double) grid.OpenedCount()) / (m_N * m_N);
        }

        double average = Arrays.stream(opened_in_test).average().getAsDouble();
        System.out.println("Threshold Mean: " + average);
        System.out.println("Threshold Variance: " + Variance(opened_in_test, average));
    }
}