package cc.sion567;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}
/*


hystrix:
  stream:
    maxConcurrentConnections: 20

turbine:
  aggregator:
    clusterConfig: CALC-SERVICE
  appConfig: calc-service
  clusterNameExpression: metadata['cluster']
 */