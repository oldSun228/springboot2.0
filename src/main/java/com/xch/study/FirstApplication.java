package com.xch.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication //配置启动类。
@ComponentScan("com.xch.study")//自动扫描并且装入bean容器
@MapperScan({"com.xch.study.biz.*.mapper"})//将项目中对应的mapper类的路径加进来就可以了
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解;开启事务等同于xml配置方式的 <tx:annotation-driven />
@ServletComponentScan //使用@ServletComponentScan 注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。
@EnableCaching//完成简单的缓存功能
@EnableRedisHttpSession//开启spring session支持
public class FirstApplication {

    public static void main(String[] args) { /**
     * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
     * 解决netty冲突后初始化client时还会抛出异常
     * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
     */
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(FirstApplication.class, args);
    }
}
