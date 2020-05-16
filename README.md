### Elastic Job Lite Spring Boot Project

  由于目前项目基于Spring Boot开发逐渐成为趋势，因此开发此项目已达到简化与使用Spring Boot的外部化配置等特性。<br/>
  使用步骤如下:<br/>
  
  - 在pom文件增加依赖
  
   ````  
      <dependency>
         <groupId>com.huitongjy.elasticjob.boot</groupId>
         <artifactId>elastic-job-lite-spring-boot-starter</artifactId>
         <version>1.0.0</version>
      </dependency>
   ````
  - 创建自己的任务 <br/>
  ````
  @Component
  public class TestJob1 implements SimpleJob {
  
      @Override
      public void execute(ShardingContext shardingContext) {
          System.out.println("Test Job1 Executing...");
      }
  }

  @Component
  public class TestJob2 implements DataflowJob<String> {
    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        System.out.println("test job 2 fetch data execute...");
        return Lists.newArrayList("Hello World");
    }

    @Override
    public void processData(ShardingContext shardingContext, List data) {
        System.out.println("test job 2 process data execute...");
    }
  }

  ````
  - 配置相应的任务，参数可参考[ElasticJob 官网](http://elasticjob.io/docs/elastic-job-lite/02-guide/config-manual/)<br/>
  ````
   elastic.job.lite.config.simple-job-config.testSimpleJob.job-ref=testJob1
   elastic.job.lite.config.simple-job-config.testSimpleJob.registry-center-ref=regCenter
   elastic.job.lite.config.simple-job-config.testSimpleJob.overwrite=true
   elastic.job.lite.config.simple-job-config.testSimpleJob.cron=0 */1 * * * ?
   elastic.job.lite.config.simple-job-config.testSimpleJob.sharding-total-count=3
   
   elastic.job.lite.config.data-flow-job-config.testDataFlowJob.job-ref=testJob2
   elastic.job.lite.config.data-flow-job-config.testDataFlowJob.registry-center-ref=regCenter
   elastic.job.lite.config.data-flow-job-config.testDataFlowJob.overwrite=true
   elastic.job.lite.config.data-flow-job-config.testDataFlowJob.cron=0 */1 * * * ?
   elastic.job.lite.config.data-flow-job-config.testDataFlowJob.sharding-total-count=2
  ````

  - `Zookeeper`注册中心配置 <br/>
   可以在根据环境配置来分别配置，同时也兼容ElasticJob原有的`Java API`与`XML`配置。
  ````
    elastic.job.lite.config.zookeeper.id=regCenter
    elastic.job.lite.config.zookeeper.server-lists=localhost:2181,127.0.0.1:2181
    elastic.job.lite.config.zookeeper.namespace=exambase-elastic-job-dev
    elastic.job.lite.config.zookeeper.base-sleep-time-milliseconds=1000
    elastic.job.lite.config.zookeeper.max-sleep-time-milliseconds=3000
    elastic.job.lite.config.zookeeper.max-retries=3
  ````
  
  - 引入本项目同时兼容ElasticJob原来的特性与用法。 同时支持`Java API`、`XML`配置`Annotation`方式共存。
