package com.oocl.training.practise;

public class SingletonSimple {
    // 构建静态私有成员变量，即单例对象
    private static SingletonSimple singletonSimple = new SingletonSimple();

    // 构建私有构造函数，防止被实例化
    private SingletonSimple() {
    }

    // 提供一个用于获取该单例对象的静态方法
    public static SingletonSimple getInstance() {
        return singletonSimple;
    }
}
