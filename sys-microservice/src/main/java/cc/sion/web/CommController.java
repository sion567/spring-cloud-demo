package cc.sion.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class CommController {

    @RequestMapping(value = "/mail2" ,method = RequestMethod.POST)
    public String send(@RequestBody final String content) {
        System.out.println("send mail title:"+content+".");
        return "99";
    }

    @RequestMapping(value = "/mail" ,method = RequestMethod.POST)
    public Callable<String> asyncSend(@RequestBody final String content) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("send mail title:"+content+".");
                return "99";
            }
        };
    }

    @RequestMapping(value = "/log" ,method = RequestMethod.POST)
    public Callable<String> asyncAddLog(@RequestBody final String content) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("add log content:"+content+".");
                return "88";
            }
        };
    }
}
