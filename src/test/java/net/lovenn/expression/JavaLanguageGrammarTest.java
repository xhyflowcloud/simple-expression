package net.lovenn.expression;

import net.lovenn.expression.domain.SimpleObject;

public class JavaLanguageGrammarTest {

    public static void main(String[] args) {
        system$arraycopyTest();
    }

    /**
     * System.arraycopy 复制的数组的值，基本类型是值 Component类型为引用地址
     */
    public static void system$arraycopyTest() {
        SimpleObject[] srcArray = new SimpleObject[1];
        srcArray[0] = new SimpleObject();
        srcArray[0].setValue("1");
        SimpleObject[] tarArray = new SimpleObject[1];
        System.arraycopy(srcArray, 0, tarArray, 0, 1);
        srcArray[0].setValue("2");
        System.out.println(tarArray[0].getValue());
    }
}
