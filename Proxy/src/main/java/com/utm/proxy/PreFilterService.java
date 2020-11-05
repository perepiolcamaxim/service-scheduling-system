package com.utm.proxy;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class PreFilterService extends ZuulFilter
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
    public boolean shouldFilter()
    {
        return RequestContext.getCurrentContext().getRequest()
                .getRequestURI()
                .matches("/services");
    }

    @Override
    public Object run()
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if(request.getMethod().equals("GET"))
        {
            RedisClient redisClient = RedisClient.create("redis://@localhost:6379");
            StatefulRedisConnection<String, String> connection = redisClient.connect();
            RedisCommands<String, String> syncCommands = connection.sync();

            if (syncCommands.exists("services") == 1)
            {
                ctx.setResponseBody(syncCommands.get("services"));
                ctx.setResponseStatusCode(200);
                ctx.setSendZuulResponse(true);
                ctx.getResponse().setContentType("application/json;charset=UTF-8");
            }
            connection.close();
            redisClient.shutdown();
        }
        return null;
    }
}