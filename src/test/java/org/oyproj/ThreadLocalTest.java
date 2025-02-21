package org.oyproj;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {
        //create a ThreadLocal Object
        ThreadLocal tl = new ThreadLocal();

        //start two thread
        //ThreadLocal implements the Thread Isolation
        new Thread(() -> {
            tl.set("萧炎");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "红色").start();

        new Thread(() -> {
            tl.set("药尘");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "绿色").start();

    }
}
