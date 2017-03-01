package cc.sion.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService extends ServiceHelper {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AsyncRestTemplate asyncRestTemplate;


    @HystrixCommand(fallbackMethod = "calcFallback")
    public int add(int a,int b) {
        System.out.println("add:"+a+"+"+b);
        return restTemplate.getForObject(calc_url+"/add?a="+a+"&b="+b, Integer.class);
    }


    @HystrixCommand(fallbackMethod = "sayFallback")
    public String sayHello(String name) {
        System.out.println("**************************");

        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<String> entity = new HttpEntity<String>("test12345",headers);
//        System.out.println("step1.....");
//        ListenableFuture<ResponseEntity<String>> futureTest = asyncRestTemplate.postForEntity(utils_url + "/mail",entity, String.class);
//        futureTest.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
//            @Override
//            public void onSuccess(ResponseEntity<String> result) {
//                System.out.println("success");
//                System.out.println("    statusCode:" + result.getStatusCode());
//                System.out.println("    result:" + result.getBody());
//            }
//            @Override
//            public void onFailure(Throwable e) {
//                System.out.println("error!!!");
//            }
//        });
//        restTemplate.postForObject(utils_url + "/mail2",entity, String.class);
        System.out.println("step2.....");

        ResponseEntity<String> result = restTemplate.exchange(hello_url + "/sayHello/" + name, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getStatusCodeValue());
        System.out.println(result.getBody());
        return result.getBody();



//        return restTemplate.getForObject(hello_url+"/sayHello/"+name, String.class);
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
