package com.utm.proxy;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

public class SimpleFilter extends ZuulFilter
{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getRequest()
                .getRequestURI()
                .matches("/services");
    }

    @Override
    public Object run()
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(request.getRequestURI());

        ctx.setResponseBody("Hello from proxy");
        return null;
    }

}
