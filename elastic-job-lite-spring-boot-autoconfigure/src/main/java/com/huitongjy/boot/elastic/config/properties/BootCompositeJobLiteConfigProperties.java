package com.huitongjy.boot.elastic.config.properties;

import com.huitongjy.boot.elastic.config.constant.ConfigConstants;
import com.huitongjy.elastic.job.spring.properties.CompositeJobLiteConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} {@link CompositeJobLiteConfigProperties} For Spring Boot.
 *
 * @author zhaoke
 * @since 2020/4/28
 **/
@Getter
@Setter
@ToString(callSuper = true)
@ConfigurationProperties(ConfigConstants.PREFIX)
public class BootCompositeJobLiteConfigProperties extends CompositeJobLiteConfigProperties {
}
