package cc.sion567.web;

import cc.sion567.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private HystrixService service;

    @RequestMapping("/test/{name}")
    public String callDependencyService(@PathVariable String name){
        return service.callDependencyService(name);
    }


}
