package cc.sion.web;

import cc.sion.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;

@Controller
@RequestMapping(value = "/admin/say")
public class SayWebController {
    @Autowired
    RemoteService remoteService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model,ServletRequest request) throws Exception {
        return "say/view";
    }


    @RequestMapping(value = "hello/{name}",method = RequestMethod.GET)
    public String hello(@PathVariable String name,Model model,ServletRequest request) throws Exception {
        model.addAttribute("message", remoteService.sayHello(name));
        return "say/view";
    }
    @RequestMapping(value = "bye/{name}",method = RequestMethod.GET)
    public String bye(@PathVariable String name,Model model,ServletRequest request) throws Exception {
        model.addAttribute("message", remoteService.sayBye(name));
        return "say/view";
    }
    @RequestMapping(value = "waiting/{name}",method = RequestMethod.GET)
    public String waiting(@PathVariable String name,Model model,ServletRequest request) throws Exception {
        model.addAttribute("message", remoteService.sayWaiting(name));
        return "say/view";
    }

}
