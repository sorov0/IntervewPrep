package LLDProblems.Youtube.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K , V> {

    private final int capacity;
    private final LinkedHashMap<K, V> cache;


    public LRUCache(int capacity) {

        this.capacity = capacity;

        this.cache = new LinkedHashMap<K,  V>(capacity, 0.75f, true){

            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
                return size() > LRUCache.this.capacity;
            }
        };
    }

    private V get(K key){
        return cache.getOrDefault(key, null);
    }

    public void put(K key, V value){
        cache.put(key, value);
    }

    public void displayCache() {
        System.out.println(cache);
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.displayCache(); // {1=A, 2=B, 3=C}

        lruCache.get(1); // Access 1, making it most recently used
        lruCache.put(4, "D"); // Evicts key 2 (Least Recently Used)
        lruCache.displayCache(); // {3=C, 1=A, 4=D}
    }
}
