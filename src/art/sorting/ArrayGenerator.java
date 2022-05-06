package art.sorting;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ArrayGenerator {
    public static int[] fillWithRandomValues(final int size, final int randomBound) {
        int[] arr = createArray(size);
        final Random random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException ignored) {
            return arr;
        }
        return Arrays.stream(arr)
                .map(value -> random.nextInt(randomBound))
                .toArray();
    }

    public static int[] fillWithRandomValues(final int size) {
        return fillWithRandomValues(size, 1000);
    }

    public static int[] fillSorted(final int size) {
        return IntStream.range(0, size).toArray();
    }

    private static int[] createArray(final int size) {
        return new int[size];
    }
}
