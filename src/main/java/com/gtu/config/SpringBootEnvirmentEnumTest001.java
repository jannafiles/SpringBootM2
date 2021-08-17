package com.gtu.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.web.WebApplicationInitializer;

@Configuration
@org.springframework.context.annotation.PropertySource("classpath:/application.yml")
public class SpringBootEnvirmentEnumTest001 implements WebApplicationInitializer, CommandLineRunner {

    @Autowired
    private Environment env;

    public static Map<String, Object> getAllEnvToMap(Environment env) {
        Map<String, Object> map = new HashMap();
        for (Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext();) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                map.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
        return map;
    }

    public static void showAllProps(Environment env) {
        Map<String, Object> map = getAllEnvToMap(env);
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            System.out.println("===========  " + key + "\t" + map.get(key));
        }
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        showAllProps(env);
    }

    @Override
    public void run(String... args) throws Exception {
        showAllProps(env);
    }
}
