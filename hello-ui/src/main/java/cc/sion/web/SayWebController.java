package cc.sion.web;

import cc.sion.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
