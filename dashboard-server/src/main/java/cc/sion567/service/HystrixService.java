package cc.sion567.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HystrixService {

    @Autowired
    private CallDependencyService dependencyService;
    public String callDependencyService() {
        return dependencyService.mockGetUserInfo();
    }
}