package com.study.spring;

import com.study.spring.comment.*;
import com.study.spring.framework.BeanDefinition;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaohz
 * @Date 2020-08-25
 * @Discribtion 模仿SpringContext类
 */
public class DemoApplicationContext {

    // AnnotationConfigApplicationContext  基于注解的扫描类,仔细理解该类的作用

    // ClassPathXmlApplicationContext  基于配置文件的扫描

    private Class configClass;

    // 构造方法，加载配置信息,在构造方法中完成Spring的启动
    /**
     * Spring启动需要完成的步骤：
     * 1.解析ConfigClass得到包路径
     * 2.扫描类，解析类上的注解,得到BeanDefinition,并放到一个map中
     * 3.生成非懒加载的单例Bean:bean的生命周期(实例化--填充属性--?---?---?---将对象放入单例池中)
     */

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, Object> singletonBeanMap = new ConcurrentHashMap<>();

    public DemoApplicationContext(Class configClass){
        this.configClass = configClass;
        // 1.解析ConfigClass得到包路径
        //    1.1    configClass.isAnnotationPresent(ComponentScan.class); 判断configClass是否有CompomentScan注解
        //    解析ConfigClass类中ComponentScan得到包路径
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String packagePath = componentScanAnnotation.value();//  得到包路径 come.study.spring  需要进行转换 com/study/spring



        //2.扫描类文件(扫描点的是编译后的target下的class文件)
        // 2.1 对得到的包路径进行转换，转换成目录格式
        packagePath = packagePath.replace(".","/");
        //  2.2 通过ClassLoader进行加载
        // AppClassLoader --> classpath   ExtetionClassLoader -->ext/lib  BootStrapClassLoader-->lib目录
        ClassLoader classLoader = DemoApplicationContext.class.getClassLoader();  //
        URL resource = classLoader.getResource(packagePath); // 基于packagePath目录得到资源
        File file = new File(resource.getFile()); // 得到目录
        for(File listFile : file.listFiles()){  //遍历目录  listFile为文件
             String absulutePath = listFile.getAbsolutePath();  // 得到绝对路径
            String path = absulutePath.substring(absulutePath.indexOf("com"),absulutePath.indexOf(".class"));
            path = path.replace("/",".");
             //System.out.println(path);
             //Class f = classLoader.loadClass(""); //loadClass的参数为加载的类的全限定名
            try {
                Class clazz = classLoader.loadClass(path);
                if(clazz.isAnnotationPresent(Component.class)){  //判断类上是否有注解，如果有注解创建一个BeanDefinition，并且获取CompenentAnnitation
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setBeanClass(clazz);
                    Component componentAnnotation = (Component) clazz.getAnnotation(Component.class);
                    String beanName = componentAnnotation.value();  //获取定义的bean的名字

                    /**判断是否有单例注解*/
                    if(clazz.isAnnotationPresent(Lazy.class)){
                        beanDefinition.setLazy(true);
                    }else{
                        beanDefinition.setLazy(false);
                    }

                    // 得到BeanDefinition,并放到一个map中
                    /**查看是否有Scope注解*/
                    if(clazz.isAnnotationPresent(Scope.class)){
                        Scope scopeAnnotation = (Scope) clazz.getAnnotation(Scope.class);
                        String scopeName = scopeAnnotation.value();
                        beanDefinition.setScope(scopeName);
                    }else{
                        beanDefinition.setScope("singelton");
                    }
                    beanDefinitionMap.put(beanName,beanDefinition);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        // 3.生成非懒加载单例bean (bean的生命周期)

        for(String beanName:beanDefinitionMap.keySet()){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            //非懒加载的单例bean
            if(!beanDefinition.isLazy() && beanDefinition.getScope().equals("singleton")){

                // 创建Bean
                Object bean = createBean(beanDefinition);
                if(bean == null){
                    bean = createBean(beanDefinition);
                }
                singletonBeanMap.put(beanName,bean);
            }
        }
    }


    public Object createBean(BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getBeanClass();

        Object instance = null;
        try {
            instance = beanClass.getDeclaredConstructor().newInstance(); //通过反射的方式创建Bean

            //填充bean的属性
            for(Field filed :beanClass.getDeclaredFields()){

                if(filed.isAnnotationPresent(Autowired.class)){
                    String beanName =  filed.getName();
                    Object bean = getBean(beanName);
                    filed.setAccessible(true);
                    filed.set(instance,bean);
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }

    // 获取Bean的方法
       public Object getBean(String beamName){
           /**
            * 判断对应的bean是懒加载的单例Bean 还是 原形
            *  单例： 生成bean放入单例池
            *  原形bean，不需要放入单例池
            *
            *   通过名字从beanDefinitionMap中获取类,如果是单例模式，则从单例对象池中获取
            */
        BeanDefinition beanDefinition = beanDefinitionMap.get(beamName);
        if(beanDefinition.getScope().equals("singleton")){
            Object bean = singletonBeanMap.get(beamName);
            return bean;
        }else{
            Object bean = createBean(beanDefinition);
            return bean;
        }
    }
}
