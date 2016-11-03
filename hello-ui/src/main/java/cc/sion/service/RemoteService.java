package cc.sion.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService extends ServiceHelper {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "calcFallback")
    public int add(int a,int b) {
        return restTemplate.getForObject(calc_url+"/add?a="+a+"&b="+b, Integer.class);
    }


    @HystrixCommand(fallbackMethod = "sayFallback")
    public String sayHello(String name) {
        return restTemplate.getForObject(hello_url+"/sayHello/"+name, String.class);
    }

    @HystrixCommand(fallbackMethod = "sayFallback")
    public String sayBye(String name) {
        return restTemplate.getForObject(hello_url+"/sayBye/"+name, String.class);
    }
    @HystrixCommand(fallbackMethod = "sayFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            }
    )
    public String sayWaiting(String name) {
        return restTemplate.getForObject(hello_url+"/sayWaiting/"+name, String.class);
    }

}
