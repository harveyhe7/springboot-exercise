package com.oocl.training.practise;
public class SingletonSafe {

    // 构建静态私有成员变量，即单例对象
    private static SingletonSafe singletonSafe;

    // 构建私有构造函数，防止被实例化
    private SingletonSafe() {
    }

    // 提供一个用于获取该单例对象的静态方法
    public static SingletonSafe getInstance() {
        if (singletonSafe == null) {
            synchronized (SingletonSafe.class) {
                if (singletonSafe == null) {
                    singletonSafe = new SingletonSafe();
                }
            }
        }
        return singletonSafe;
    }
}
