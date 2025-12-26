package collections.mapInterface;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V>  extends LinkedHashMap<K, V> {

    int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.7f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {

        LRUCache<String, Integer> lruCache = new LRUCache<>(3);

        lruCache.put("Bob", 1);
        lruCache.put("Alice",2);
        lruCache.put("Mathew",3);

        lruCache.put("Rahil",4);

        System.out.println(lruCache);

    }


}


