package cc.sion.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class SimpleFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /*
        see:com.netflix.zuul.ZuulFilter.filterType()
filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
pre：可以在请求被路由之前调用
route：在路由请求时候被调用
post：在routing和error过滤器之后被调用
error：处理请求时发生错误时被调用
         */
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤器的执行顺序
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //判断该过滤器是否要执行
        return true;
    }

    @Override
    public Object run() {
        //过滤器的具体逻辑
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }
}
