package com.study.spring.framework;

/**
 * @author zhaohz
 * @Date 2020-08-25
 * @Discribtion Spring扫描包后将带有注解的Bean生成的对象
 */
public class BeanDefinition {

    private String scope;

    private boolean lazy;

    private Class beanClass;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
