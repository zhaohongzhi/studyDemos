package com.study.spring;

/**
 * @author zhaohz
 * @Date
 * @Discribtion 描述
 */
public class TestClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        Class clazz = classLoader.loadClass("/Users/zhaohongzhi/Downloads/javaLearningSpace/studyDemos/target/classes/com/study/spring/service/UserService.class");

    }
}
