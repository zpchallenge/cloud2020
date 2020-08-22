package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    /**
     *  配置了一个id为route-name的路由规则，当访问地址http://localhost:9527/guonei时
     *  会自动转发到地址http://news.baidu.com/guonei
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes ();
        routes.route ("path_route_atguigu", r -> r.path ("/guonei").uri ("http://news.baidu.com/guonei")).build ();
        return routes.build ();
    }

    @Bean
    public RouteLocator customerRouteLocator2(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes ();
        routes.route ("path_route_atguigu2", r -> r.path ("/ent").uri ("http://news.baidu.com/ent")).build ();
        return routes.build ();
    }
}
