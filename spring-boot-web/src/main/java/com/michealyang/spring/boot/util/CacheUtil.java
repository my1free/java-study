package com.michealyang.spring.boot.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author michealyang
 * @version 1.0
 * @created 18/5/5
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class CacheUtil {

    private static ConcurrentHashMap<String, CacheItem> cache = new ConcurrentHashMap<>(5000);

    public static boolean set(String key, String value, int expire) {
        CacheItem item = new CacheItem(value, expire);
        if (cache.putIfAbsent(key, item) != null) {
            return false;
        }
        return true;
    }

    public static String get(String key) {
        CacheItem item = cache.get(key);
        if (item == null) {
            return null;
        }
        if (now() >= item.getExpire()) {
            return null;
        }
        return item.getValue();
    }

    static class CacheItem{
        private String value;
        private int expire;

        public CacheItem(String value, Integer expire) {
            this.value = value;
            this.expire = now() + expire;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getExpire() {
            return expire;
        }

        public void setExpire(Integer expire) {
            this.expire = expire;
        }
    }

    private static int now() {
        return (int)(System.currentTimeMillis() / 1000);
    }
}
