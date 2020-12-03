package net.lovenn.expression.sel;

import java.util.concurrent.ConcurrentHashMap;

public class SelParserContext {
    private ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<String, String>();

    public void addValue(String key, String value) {
        cache.put(key, value);
    }

    public String findValue(String key) {
        return cache.get(key);
    }
}
