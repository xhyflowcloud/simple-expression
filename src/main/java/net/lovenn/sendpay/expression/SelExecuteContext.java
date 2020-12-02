package net.lovenn.sendpay.expression;

import java.util.HashMap;

public class SelExecuteContext {
    private HashMap<String, Object> contextMap = new HashMap<String, Object>();

    public void add(String key, Object value) {
        contextMap.put(key, value);
    }

    public Object get(String key) {
        return contextMap.get(key);
    }
}
