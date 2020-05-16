package com.huitongjy.boot.elastic.config.autoconfigure;

import com.huitongjy.boot.elastic.config.constant.ConfigConstants;
import com.huitongjy.boot.elastic.config.properties.BootCompositeJobLiteConfigProperties;
import com.huitongjy.boot.elastic.config.properties.BootZookeeperConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Elastic Job Lite Auto Configuration {@link Configuration}
 *
 * @author zhaoke
 * @since 2020/4/22
 **/
@ConditionalOnProperty(name = ConfigConstants.ENABLED, matchIfMissing = true)
@ConditionalOnMissingBean(name = "compositeJobLiteBeanDefinitionRegistrar")
@ConditionalOnClass(name = "org.springframework.boot.context.properties.bind.Binder")
@EnableConfigurationProperties(value = {BootZookeeperConfigProperties.class, BootCompositeJobLiteConfigProperties.class})
@Import({BootZookeeperBeanDefinitionRegistrar.class, BootCompositeBeanDefinitionRegistrar.class})
public class ElasticJobLiteAutoConfiguration {

}
