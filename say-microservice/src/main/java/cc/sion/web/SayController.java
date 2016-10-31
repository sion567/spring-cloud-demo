package cc.sion.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class SayController {
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/sayHello/{name}" ,method = RequestMethod.GET)
    public String sayHello(@PathVariable String name) {
        ServiceInstance instance = client.getLocalServiceInstance();

        System.out.println("/say hello");
        System.out.println("    host:"+instance.getHost());
        System.out.println("    service_id:" + instance.getServiceId());

        return "hello:"+name;
    }

    @RequestMapping(value = "/sayBye" ,method = RequestMethod.GET)
    public String sayBye() {
        throw new RuntimeException("sya bye bye~~");
    }


}
