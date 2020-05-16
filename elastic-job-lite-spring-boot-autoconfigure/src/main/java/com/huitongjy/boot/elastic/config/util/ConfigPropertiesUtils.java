package com.huitongjy.boot.elastic.config.util;

import com.huitongjy.boot.elastic.config.constant.ConfigConstants;
import com.huitongjy.boot.elastic.config.properties.BootZookeeperConfigProperties;
import com.huitongjy.elastic.job.spring.properties.CompositeJobLiteConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Springboot used to own property binding configured binding
 *
 * @author zhaoke
 * @since 2020/4/22
 **/
public class ConfigPropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConfigPropertiesUtils.class);

    public static BootZookeeperConfigProperties buildZookeeperConfigProperties(ConfigurableEnvironment environment) {
        BootZookeeperConfigProperties bootZookeeperConfigProperties = new BootZookeeperConfigProperties();
        Binder binder = Binder.get(environment);
        ResolvableType type = ResolvableType.forClass(BootZookeeperConfigProperties.class);
        Bindable<?> target = Bindable.of(type).withExistingValue(bootZookeeperConfigProperties);
        binder.bind(ConfigConstants.PREFIX_ZK, target);
        logger.info("zookeeperConfigProperties : {}", bootZookeeperConfigProperties);
        return bootZookeeperConfigProperties;
    }

    public static CompositeJobLiteConfigProperties buildCompositeJobLiteConfigProperties(ConfigurableEnvironment environment) {
        CompositeJobLiteConfigProperties compositeJobLiteConfigProperties = new CompositeJobLiteConfigProperties();
        Binder binder = Binder.get(environment);
        ResolvableType type = ResolvableType.forClass(CompositeJobLiteConfigProperties.class);
        Bindable<?> target = Bindable.of(type).withExistingValue(compositeJobLiteConfigProperties);
        binder.bind(ConfigConstants.PREFIX, target);
        logger.info("compositeJobLiteConfigProperties : {}", compositeJobLiteConfigProperties);
        return compositeJobLiteConfigProperties;
    }
}
