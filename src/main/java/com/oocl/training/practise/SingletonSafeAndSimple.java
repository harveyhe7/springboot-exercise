package com.oocl.training.practise;

public class SingletonSafeAndSimple {
    private SingletonSafeAndSimple() {}

    // 静态内部类
    private static class Holder {
        static final SingletonSafeAndSimple INSTANCE = new SingletonSafeAndSimple();
    }

    public static SingletonSafeAndSimple getInstance() {
        return Holder.INSTANCE;
    }
}