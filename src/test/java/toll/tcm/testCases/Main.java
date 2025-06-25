package toll.tcm.testCases;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        System.out.println("Keys (accessed sequentially):");
        Object[] keysArray = map.keySet().toArray();
        for (int i = 0; i < keysArray.length; i++) {
            String key = (String) keysArray[i];
            String value = map.get(key);
            System.out.println("Index " + i + ": Key = " + key + ", Value = " + value);
        }
    }
}

