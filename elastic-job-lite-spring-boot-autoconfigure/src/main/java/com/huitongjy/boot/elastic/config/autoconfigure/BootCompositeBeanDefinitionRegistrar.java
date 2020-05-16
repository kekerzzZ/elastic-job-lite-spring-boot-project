package com.huitongjy.boot.elastic.config.autoconfigure;

import com.huitongjy.boot.elastic.config.util.ConfigPropertiesUtils;
import com.huitongjy.elastic.job.spring.annotation.CompositeBeanDefinitionRegistrar;
import com.huitongjy.elastic.job.spring.properties.CompositeJobLiteConfigProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link CompositeJobLiteConfigProperties} For Spring Boot
 *
 * @author zhaoke
 * @since 2020/4/28
 **/
public class BootCompositeBeanDefinitionRegistrar extends CompositeBeanDefinitionRegistrar implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void setCompositeJobLiteConfigProperties() {
        super.compositeJobLiteConfigProperties = ConfigPropertiesUtils.buildCompositeJobLiteConfigProperties((ConfigurableEnvironment) environment);
    }
}
