package net.lovenn.expression.sel;

import java.util.HashMap;

public class SelExecuteContext {

    private SelExpression selExpression;

    private HashMap<String, Object> contextMap = new HashMap<String, Object>();

    public void add(String key, Object value) {
        contextMap.put(key, value);
    }

    public Object get(String key) {
        return contextMap.get(key);
    }

    public SelExpression getSelExpression() {
        return selExpression;
    }

    public void setSelExpression(SelExpression selExpression) {
        this.selExpression = selExpression;
    }
}
