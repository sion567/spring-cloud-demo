package cc.sion567.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/h")
    String toHystrix() {
        System.out.println("goto hystrix.stream");
        return "redirect:/hystrix";
    }
    @RequestMapping("/t")
    String toTurbine() {
        System.out.println("goto turbine.stream");
        return "redirect:/turbine";
    }
}
