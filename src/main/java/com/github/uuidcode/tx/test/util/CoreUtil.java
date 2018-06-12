package com.github.uuidcode.tx.test.util;

import java.util.UUID;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CoreUtil {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    public static String firstCharLowerCase(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    public static SqlSessionFactory sqlSessionFactory(DataSource dataSource, Class daoClass) throws Exception {
        String daoClassPath = ClassUtils.convertClassNameToResourcePath(daoClass.getPackage().getName());
        Resource[] resources = new PathMatchingResourcePatternResolver()
            .getResources("classpath:" + daoClassPath + "/*.xml");

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(resources);

        SqlSessionFactory factory = factoryBean.getObject();
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);

        return factory;
    }

}
