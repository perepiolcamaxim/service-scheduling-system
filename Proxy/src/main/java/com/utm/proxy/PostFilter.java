package com.utm.proxy;

import javax.servlet.http.HttpServletRequest;
import com.google.common.io.CharStreams;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PostFilter extends ZuulFilter
{
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
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
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        if(request.getMethod().equals("GET"))
        {
            RedisClient redisClient = RedisClient.create("redis://@localhost:6379");
            StatefulRedisConnection<String, String> connection = redisClient.connect();
            RedisCommands<String, String> syncCommands = connection.sync();

            if (syncCommands.exists("services") == 0)
            {
                try
                {
                    final InputStream responseDataStream = context.getResponseDataStream();
                    String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, StandardCharsets.UTF_8));
                    syncCommands.set("services", responseData);
                    syncCommands.expire("services", 60);

                    context.setResponseBody(responseData);
                    context.getResponse().setContentType("application/json;charset=UTF-8");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            connection.close();
            redisClient.shutdown();
        }
        return null;
    }
}