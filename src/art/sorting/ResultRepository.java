package art.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.String.format;

public class ResultRepository {
    public static final int TEST_COUNT = 1000;
    private final SortedMap<Integer, List<Long>> results = new TreeMap<>();
    private static ResultRepository instance;

    public static ResultRepository getInstance() {
        if (instance == null) instance = new ResultRepository();
        return instance;
    }

    public void addResult(final ResultRecord resultRecord) {
        if (results.containsKey(resultRecord.key())) {
            results.get(resultRecord.key()).add(resultRecord.nanoseconds());
        } else {
            final List<Long> list = new ArrayList<>();
            list.add(resultRecord.nanoseconds());
            results.put(resultRecord.key(), list);
        }
    }

    public String getAverage() {
        if (results.size() == 0) return "";
        final StringBuilder sb = new StringBuilder("Sorting average results")
                .append("\n")
                .append("Number of tests run : ")
                .append(TEST_COUNT).append("\n");
        results.forEach((key, value) -> sb
                .append(format("%15d", key))
                .append("\t")
                .append(format("%15.2f", value.stream().mapToInt(Long::intValue).average().orElse(0)))
                .append("\n"));
        return sb.toString();
    }

    public String toString() {
        if (results.size() == 0) return "";
        final StringBuilder sb = new StringBuilder("Sorting results").append("\n").append("Element count").append("\t");
        for (int i = 0; i < TEST_COUNT; i++) {
            sb.append(format("Execution nr%3d\t", i + 1));
        }
        sb.append("\n");
        for (Map.Entry<Integer, List<Long>> entry : results.entrySet()) {
            sb.append(format("%13d", entry.getKey())).append("\t");
            for (long value : entry.getValue()) {
                sb.append(format("%15d", value)).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
