package com.worksApproval.admin.constant;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1.Spring的工具类,现在主要用来获取Spring配置文件中的bean 2.这个方法里比较遗憾的是没有用到implements
 * ApplicationContextAware 原因是在web.xml文件中没有配置正确
 * @author rocky
 *
 */
public class SpringHelper
{
    private static SpringHelper springHelper = new SpringHelper();
    private static ApplicationContext applicationContext;

    /**
     * 获取单个SpringHelper
     * 这个方法应该可以优化
     * @return SpringHelper
     */
    public final static SpringHelper getSpringHelper()
    {
        return springHelper;
    }
    /**
     * SpringHelper无参构造函数
     */
    @SuppressWarnings("static-access")
    public SpringHelper()
    {
        this.applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"dispatcher-servlet.xml","spring-hibernate.xml"});
    }
    /**
     * 
     * @param beanId 配置文件中的bean中的id
     * @return an instance of the bean
     */
    public  Object getBean(String beanId)
    {
        return applicationContext.getBean(beanId);
    }
}
