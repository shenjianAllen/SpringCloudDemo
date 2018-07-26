package com.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@SpringBootApplication
//用于开启向注册中心注册的注解
@EnableEurekaClient
@RestController
public class EurekaclientNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaclientNodeApplication.class, args);
	}
	
    /**自定义配置ribbon负载均衡算法
     * @return
     */
    @Bean
    public IRule myRule(){
//        new RoundRobinRule();//轮询
//        new RetryRule();//重试
//    	AvailabilityFilteringRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数超过阈值的服务，然后对剩余的服务列表进行轮询
//    	WeightedResponseTimeRule 权重 根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高。刚启动时，如果统计信息不足，则使用轮询策略，等信息足够，切换到 WeightedResponseTimeRule
//    	RetryRule 重试 先按照轮询策略获取服务，如果获取失败则在指定时间内重试，获取可用服务
//    	BestAvailableRule 选过滤掉多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
//    	ZoneAvoidanceRule 符合判断server所在区域的性能和server的可用性选择服务
        return new RoundRobinRule();
    }
	
    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) throws InterruptedException {
    	System.out.println("到我了，集群节点第002个");
    	Thread.sleep(5000);
        return "hi " + name + " ,i am from port:" + port;
    }
}
