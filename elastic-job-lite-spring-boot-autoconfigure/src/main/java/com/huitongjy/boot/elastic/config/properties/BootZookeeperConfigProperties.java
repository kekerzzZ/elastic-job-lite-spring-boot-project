package com.huitongjy.boot.elastic.config.properties;

import com.huitongjy.boot.elastic.config.constant.ConfigConstants;
import com.huitongjy.elastic.job.spring.properties.ZookeeperConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} {@link ZookeeperConfigProperties} For Spring Boot.
 *
 * @author zhaoke
 * @since 2020/4/22
 **/
@Getter
@Setter
@ToString(callSuper = true)
@ConfigurationProperties(ConfigConstants.PREFIX_ZK)
public class BootZookeeperConfigProperties extends ZookeeperConfigProperties {

}
