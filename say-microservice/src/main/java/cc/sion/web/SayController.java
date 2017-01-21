package cc.sion.web;

import cc.sion.biz.ISayBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class SayController {

//    @Autowired
//    private Meter requestMeter;
//    @Autowired
//    private Histogram responseSizes;
//    @Autowired
//    private Counter pendingJobs;
//    @Autowired
//    private Timer responses;
//    @Autowired
//    private ListManager listManager;
//    @RequestMapping("/hello")
//    @ResponseBody
//    public String helloWorld() {
//        requestMeter.mark();
//        pendingJobs.inc();
//        responseSizes.update(new Random().nextInt(10));
//        listManager.getList().add(1);
//        final Timer.Context context = responses.time();
//        try {
//            return "Hello World";
//        } finally {
//            context.stop();
//        }
//    }




    @Autowired
    private DiscoveryClient client;
    @Autowired
    private ISayBiz sayBiz;



    @RequestMapping(value = "/sayHello/{name}" ,method = RequestMethod.GET)
    public String sayHello(@PathVariable String name) throws Exception {
        ServiceInstance instance = client.getLocalServiceInstance();

        System.out.println("/say hello("+name+")");
        System.out.println("    host:"+instance.getHost());
        System.out.println("    service_id:" + instance.getServiceId());

        return sayBiz.sayOk(name);
    }

    @RequestMapping(value = "/sayBye/{name}" ,method = RequestMethod.GET)
    public String sayBye(@PathVariable String name) throws Exception {
        ServiceInstance instance = client.getLocalServiceInstance();
        System.out.println("/say bye("+name+")");
        System.out.println("    host:"+instance.getHost());
        System.out.println("    service_id:" + instance.getServiceId());

        return sayBiz.sayFail(name);
    }

    @RequestMapping(value = "/sayWaiting/{name}" ,method = RequestMethod.GET)
    public String sayWaiting(@PathVariable String name) {
        try {
//            Thread.sleep(2*60*1000);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "I hate waiting in line.";
    }

}
