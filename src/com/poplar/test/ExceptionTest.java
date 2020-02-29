package com.poplar.test;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * by poplar created on 2020/1/10
 * 参考来链接：这个是我工作中遇到后个人工作体会分享 - Piao飘的文章 - 知乎
 * https://zhuanlan.zhihu.com/p/101555662
 */
public class ExceptionTest implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("close method.");
    }

    @Test
    public void testClose() {
        List<String> list = Arrays.asList("!", "@", "3", "4");
        NullPointerException ex = new NullPointerException("Test");
        try (Stream<String> s = list.stream()) {
            s.onClose(() -> {
                System.out.println("first close");
//                throw ex; // 对于同一个异常对象在onClose抛出，不会出现suppressed异常
                throw new RuntimeException("first exception"); // 对于不同的异常，只有一个是不是suppressed异常
            }).onClose(() -> {
                System.out.println("second close");
//                throw ex; // 同一个异常第二次就不会在抛出了
                throw new RuntimeException("first exception"); // 剩下的全部都归到suppressed中
            }).forEach(System.out::println);
        } finally {
            System.out.println("finally");
        }

    }

}

/*

java.lang.RuntimeException: first exception

	at com.poplar.test.ExceptionTest.lambda$testClose$0(ExceptionTest.java:32)
	at java.util.stream.Streams$1.run(Streams.java:850)
	at java.util.stream.AbstractPipeline.close(AbstractPipeline.java:323)
	at com.poplar.test.ExceptionTest.testClose(ExceptionTest.java:38)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:45)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:42)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:263)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:68)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:47)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:231)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:60)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:229)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:50)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:222)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:300)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:157)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
	Suppressed: java.lang.RuntimeException: first exception
		at com.poplar.test.ExceptionTest.lambda$testClose$1(ExceptionTest.java:36)
		at java.util.stream.Streams$1.run(Streams.java:854)
		... 24 more

 */