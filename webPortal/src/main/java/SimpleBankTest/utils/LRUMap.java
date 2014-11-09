package SimpleBankTest.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ifAJARD on 30/10/14.
 */
public class LRUMap<K,V> extends LinkedHashMap<K,V> {
    private int maxCapacity;

    public LRUMap(int initialCapacity, int maxCapacity) {
        super(initialCapacity);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>maxCapacity;
    }
}
