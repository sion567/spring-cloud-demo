package cc.sion567.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CallDependencyService {

    private Random random = new Random();

    @HystrixCommand(fallbackMethod = "onFailedToSayHello")
    public String sayHello(String name) {
        int randomInt= random.nextInt(10) ;
        if(randomInt<8){
            throw new RuntimeException("I failed because you told me to");
        }
        return "Hello,"+name;
    }

    private String onFailedToSayHello(String name) {
        return "Bye:"+name+"~";
    }

}