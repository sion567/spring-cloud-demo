package cc.sion567.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CallDependencyService {

    private Random random = new Random();

    @HystrixCommand(fallbackMethod = "fallback")
    public String mockGetUserInfo(){
        int randomInt= random.nextInt(10) ;
        System.out.println("random:"+randomInt);
        if(randomInt<8){  //模拟调用失败情况
            throw new RuntimeException("call dependency service fail.");
        }else{
            return "UserName:h2;number:"+randomInt;
        }
    }

    public String fallback(){
        return "some exception occur call fallback method.";
    }
}