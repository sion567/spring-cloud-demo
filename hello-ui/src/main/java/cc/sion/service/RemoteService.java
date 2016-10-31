package cc.sion.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

}
