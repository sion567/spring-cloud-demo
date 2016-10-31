package cc.sion.web;

import cc.sion.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;

@Controller
@RequestMapping(value = "/admin/calc")
public class CalcWebController {

    @Autowired
    RemoteService remoteService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model,ServletRequest request) throws Exception {
        return "calc/view";
    }

    @RequestMapping(value = "add" ,method = RequestMethod.GET)
    public String showAdd(Model model,ServletRequest request) throws Exception {
        String a = request.getParameter("a")==null?"":request.getParameter("a");
        String b = request.getParameter("b")==null?"":request.getParameter("b");
        String c = request.getParameter("c")==null?"":request.getParameter("c");
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        model.addAttribute("result",c);
        return "calc/add-view";
    }
    @RequestMapping(value = "subtract" ,method = RequestMethod.GET)
    public String showSubtract(Model model,ServletRequest request) throws Exception {
        return "calc/subtract-view";
    }
    @RequestMapping(value = "multiply" ,method = RequestMethod.GET)
    public String showMultiply(Model model,ServletRequest request) throws Exception {
        return "calc/multiply-view";
    }
    @RequestMapping(value = "divide" ,method = RequestMethod.GET)
    public String showDivide(Model model,ServletRequest request) throws Exception {
        return "calc/divide-view";
    }



    @RequestMapping(value = "add" ,method = RequestMethod.POST)
    public String showAdd(@RequestParam("a")int a,@RequestParam("b")int b,
                          Model model,ServletRequest request) throws Exception {

        System.out.println("a:" + a + ",b:" + b);
        int c = remoteService.add(a,b);
        System.out.println("result:"+c);

        return "redirect:/admin/calc/add?a="+a+"&b="+b+"&c="+c;
    }

}
