package art.sorting;

import static art.sorting.ArrayGenerator.fillWithRandomValues;
import static art.sorting.ResultRepository.TEST_COUNT;

public class Shell {

    private static final int WARMING_UP = 5;

    public static void sort(int[] arr) {
        int length = arr.length;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int index = gap; index < length; index += 1) {
                int temp = arr[index];
                int j;
                for (j = index; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }
    }

    public static void print(final int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%5d\t", arr[i]);
            if ((i + 1) % 20 == 0) System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        final ResultRepository resultRepository = ResultRepository.getInstance();
        final Runnable runnable = () -> execute(resultRepository);
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        System.out.println(resultRepository);
        System.out.println(resultRepository.getAverage());
    }

    private static void execute(final ResultRepository resultRepository) {
        final int[] base = new int[]{50, 10_000, 100_000};
        for (final int value : base) {
            for (int i = 1; i <= 3; i++) {
                final int size = value * i;
                for (int k = 0; k < TEST_COUNT + WARMING_UP; k++) {
                    final int[] arr = fillWithRandomValues(size);
                    long start = System.nanoTime();
                    sort(arr);
                    final ResultRecord resultRecord = new ResultRecord(size, System.nanoTime() - start);
                    if (k >= WARMING_UP) {
                        resultRepository.addResult(resultRecord);
                    }
                }
            }
        }
    }

}
