package com.googlecode.aviator.runtime.function.math;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.aviator.runtime.type.AviatorJavaType;
import com.googlecode.aviator.runtime.type.AviatorNumber;


public class MathAbsFunctionUnitTest extends BaseMathFunctionUnitTestForOneArgument {
    @Before
    public void setUp() {
        this.function = new MathAbsFunction();
    }


    @Test
    public void testCall() {
        assertEquals(3, this.function.call(null, AviatorNumber.valueOf(-3)).getValue(null));
        assertEquals(3.9, this.function.call(null, AviatorNumber.valueOf(-3.9)).getValue(null));
        assertEquals(400, this.function.call(null, AviatorNumber.valueOf(400)).getValue(null));
        assertEquals(new BigInteger("300000000000000000000000000000000"),
            this.function.call(null, AviatorNumber.valueOf(new BigInteger("-300000000000000000000000000000000")))
            .getValue(null));
        assertEquals(
            new BigDecimal("300000000000000000000000000000000.0000002223333"),
            this.function.call(null,
                AviatorNumber.valueOf(new BigDecimal("-300000000000000000000000000000000.0000002223333"))).getValue(
                    null));
        assertEquals(400, this.function.call(null, AviatorNumber.valueOf(400)).getValue(null));

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", 300);
        env.put("b", -3.14);
        env.put("c", new BigInteger("-300000000000000000000000000000000"));
        env.put("d", new BigDecimal("-300000000000000000000000000000000.0000002223333"));

        assertEquals(300, this.function.call(env, new AviatorJavaType("a")).getValue(null));
        assertEquals(3.14, this.function.call(env, new AviatorJavaType("b")).getValue(null));
        assertEquals(new BigInteger("300000000000000000000000000000000"),
            this.function.call(env, new AviatorJavaType("c")).getValue(null));
        assertEquals(new BigDecimal("300000000000000000000000000000000.0000002223333"),
            this.function.call(env, new AviatorJavaType("d")).getValue(null));
    }
}
