package com.my.base.jar.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author michealyang
 * @version 1.0
 * @created 18/7/6
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class StreamTest {
    public static void listSum(){
        List<Integer> test = Lists.newArrayList(1, 2, 3, 4, 5);
        int sum = test.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    public static void mapToMap() {
        Map<Integer, KV> srcMap = Maps.newHashMap();
        srcMap.put(1, new KV(1, "one"));
        srcMap.put(2, new KV(2, "two"));
        srcMap.put(3, new KV(3, "three"));
        srcMap.put(4, new KV(4, "four"));
        srcMap.put(5, new KV(5, "five"));

        Map<Integer, String> desMap = srcMap.entrySet().stream().collect(Collectors.toMap(
                e -> e.getKey(),
                e -> e.getValue().getV()
        ));
        System.out.println(desMap);
    }
    public static void main(String[] args) {
        StreamTest.mapToMap();
    }

    static class KV {
        private Integer k;
        private String v;

        public KV(Integer k, String v) {
            this.k = k;
            this.v = v;
        }

        public Integer getK() {
            return k;
        }

        public void setK(Integer k) {
            this.k = k;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }

}
