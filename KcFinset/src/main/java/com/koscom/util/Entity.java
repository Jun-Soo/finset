package com.koscom.util;

import java.util.HashMap;

public class Entity extends HashMap {
    public Object get(Object key) {
        Object result = super.get(key);
        if(result == null) {
            result = "";
        }
        return result;
    }
}
