package cc.sion.web;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoWel() {
        return "redirect:/admin/wel";
    }

    @RequestMapping(value = "/admin/wel", method = RequestMethod.GET)
    public String wel() {
        System.out.println("welcome user:"+getCurrentUser());
        return "main";
    }


    private String getCurrentUser() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }


}
