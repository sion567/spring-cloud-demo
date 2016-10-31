package cc.sion.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubtractController {
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/subtract" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a - b;
        System.out.println("/subtract");
        System.out.println("    host:"+instance.getHost());
        System.out.println("    service_id:"+instance.getServiceId());
        System.out.println("    result:"+r);

        return r;
    }
}
