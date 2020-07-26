package com.xsn.cglib;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.ImmutableBean;
import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test1 {

    @Test
    public void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("1");
                return "2";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        proxy.action("dd");
    }

    @Test
    public void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("1");
                return null;
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        proxy.action("dd");
    }

    @Test
    public void test3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                methodProxy.invokeSuper(o, objects);
                System.out.println("post");
                return null;
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        proxy.action("dd");
    }

    @Test
    public void test4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);

        CallbackFilter callbackFilter = new CallbackHelper(SampleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getName().equals("action")) {
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            System.out.println("1");
                            return null;
                        }
                    };
                }

                return NoOp.INSTANCE;
            }
        };

        enhancer.setCallbackFilter(callbackFilter);
        enhancer.setCallbacks(((CallbackHelper) callbackFilter).getCallbacks());
        SampleClass proxy = (SampleClass) enhancer.create();
        proxy.action("dd");
        proxy.toString();
    }

    @Test
    public void test5() {
        SampleClass sampleClass = new SampleClass(3);
        SampleClass proxy = (SampleClass) ImmutableBean.create(sampleClass);

        proxy.action("1");
        // proxy.setA(1);

        sampleClass.action("2");
        sampleClass.setA(2);
    }

    @Test
    public void test6() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("a", int.class);

        Object proxy = beanGenerator.create();

        Method set = proxy.getClass().getMethod("setA", int.class);
        set.invoke(proxy, 1);

        Method get = proxy.getClass().getMethod("getA");
        System.out.println(get.invoke(proxy));
    }
}
