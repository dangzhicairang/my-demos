package com.xsn.container;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        /*Map temp = new HashMap(2) {
            {
                put("test", "test");
            }
        };*/

        Map map = new HashMap<>();

        map.put("1", "1");

        map.get("1");

        map.remove("1");

        map.containsKey("test");

        map.containsValue("test");

        map.keySet();

        map.values();

        map.entrySet();

        map.clear();
    }
}
