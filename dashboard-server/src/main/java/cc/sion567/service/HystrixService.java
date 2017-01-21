package cc.sion567.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HystrixService {

    @Autowired
    private CallDependencyService dependencyService;
    public String callDependencyService(String name) {
        return dependencyService.sayHello(name);
    }
}