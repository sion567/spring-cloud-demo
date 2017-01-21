package cc.sion567;

import cc.sion567.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableHystrixDashboard
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

    @RequestMapping("/h")
    String hystrix() {
        return "redirect:/hystrix.stream";
    }
    @RequestMapping("/t")
    String turbine() {
        return "redirect:/turbine.stream";
    }

    @Autowired
    private HystrixService service;
    @RequestMapping("/test/{name}")
    @ResponseBody
    public String callDependencyService(@PathVariable String name){
        return service.callDependencyService(name);
    }
}
/*

turbine:
  aggregator:
    clusterConfig: CALC-SERVICE
  appConfig: calc-service
  clusterNameExpression: metadata['cluster']
 */