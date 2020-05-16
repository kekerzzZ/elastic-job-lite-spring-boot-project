package com.huitongjy.boot.elastic.config.autoconfigure;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.huitongjy.boot.elastic.config.properties.BootZookeeperConfigProperties;
import com.huitongjy.boot.elastic.config.util.ConfigPropertiesUtils;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Zookeeper Config {@link ImportBeanDefinitionRegistrar BeanDefinition Registrar}
 *
 * @author zhaoke
 * @since 2020/4/22
 **/
public class BootZookeeperBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //Register Zookeeper Config Beans.
        BootZookeeperConfigProperties zkConfigProperties = ConfigPropertiesUtils.buildZookeeperConfigProperties((ConfigurableEnvironment) environment);
        if (registry.containsBeanDefinition(zkConfigProperties.getId())) {
            return;
        }
        registryZookeeperConfig(zkConfigProperties, registry);
    }

    private void registryZookeeperConfig(BootZookeeperConfigProperties zkConfigProperties, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(ZookeeperRegistryCenter.class);
        factory.addConstructorArgValue(buildZookeeperConfigurationBeanDefinition(zkConfigProperties));
        factory.setInitMethodName("init");
        registry.registerBeanDefinition(zkConfigProperties.getId(), factory.getBeanDefinition());
    }

    private AbstractBeanDefinition buildZookeeperConfigurationBeanDefinition(BootZookeeperConfigProperties zkConfigProperties) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(ZookeeperConfiguration.class);

        factory.addConstructorArgValue(zkConfigProperties.getServerLists());
        factory.addConstructorArgValue(zkConfigProperties.getNamespace());
        addPropertyValueIfNotEmpty("baseSleepTimeMilliseconds", zkConfigProperties.getBaseSleepTimeMilliseconds(), factory);
        addPropertyValueIfNotEmpty("maxSleepTimeMilliseconds", zkConfigProperties.getMaxSleepTimeMilliseconds(), factory);
        addPropertyValueIfNotEmpty("maxRetries", zkConfigProperties.getMaxRetries(), factory);
        addPropertyValueIfNotEmpty("sessionTimeoutMilliseconds", zkConfigProperties.getSessionTimeoutMilliseconds(), factory);
        addPropertyValueIfNotEmpty("connectionTimeoutMilliseconds", zkConfigProperties.getConnectionTimeoutMilliseconds(), factory);
        addPropertyValueIfNotEmpty("digest", zkConfigProperties.getDigest(), factory);
        return factory.getBeanDefinition();
    }

    private void addPropertyValueIfNotEmpty(final String propertyName, final Object propertyValue, final BeanDefinitionBuilder factory) {
        if (null != propertyValue) {
            factory.addPropertyValue(propertyName, propertyValue);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
