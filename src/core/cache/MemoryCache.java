package core.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Create a simple in memory cache
 * Items will expire based on a time to live period.
 * Cache will keep most recently used items if you will try to add more items then max specified.
 * For the expiration of items we can timestamp the last access and in a separate thread remove the items when the time to live limit is reached. 
 * This is nice for reducing memory pressure for applications that have long idle time in between accessing the cached objects.
 * @author whurys@gmail.com
 */
 
public class MemoryCache<K, T> {
 
    private long timeToLive;
    private Map<Object,Object> cacheMap;
 
    protected class CacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public T value;
 
        protected CacheObject(T value) {
            this.value = value;
        }
    }
 
    public MemoryCache(long timeToLive, final long timerInterval, int maxItems) {
        this.timeToLive = timeToLive * 1000;
 
        cacheMap = createLRUMap(maxItems);
 
        if (timeToLive > 0 && timerInterval > 0) {
 
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(timerInterval * 1000);
                        } catch (InterruptedException ex) {
                        }
                        cleanup();
                    }
                }
            });
 
            t.setDaemon(true);
            t.start();
        }
    }
 
    public void put(K key, T value) {
        synchronized (cacheMap) {
            cacheMap.put(key, new CacheObject(value));
        }
    }
 
    @SuppressWarnings("unchecked")
    public T get(K key) {
        synchronized (cacheMap) {
            CacheObject c = (CacheObject) cacheMap.get(key);
 
            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }
 
    public void remove(K key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }
 
    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }
 
    @SuppressWarnings("unchecked")
    public void cleanup() {
 
        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;
 
        synchronized (cacheMap) {
            Iterator itr = cacheMap.keySet().iterator();
 
            deleteKey = new ArrayList<K>((cacheMap.size() / 2) + 1);
            K key = null;
            CacheObject c = null;
 
            while (itr.hasNext()) {
            	Map.Entry entry = (Map.Entry) itr.next();
            	
                key = (K) entry.getKey();
                c = (CacheObject) entry.getValue();
 
                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }
 
        for (K key : deleteKey) {
            synchronized (cacheMap) {
                cacheMap.remove(key);
            }
 
            Thread.yield();
        }
    }
    
    
    public static <K, V> Map<K, V> createLRUMap(final int maxEntries) {
        return new LinkedHashMap<K, V>(maxEntries*10/7, 0.7f, true) {
  
			private static final long serialVersionUID = 8313163147087073006L;

			@Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxEntries;
            }
        };
    }
}