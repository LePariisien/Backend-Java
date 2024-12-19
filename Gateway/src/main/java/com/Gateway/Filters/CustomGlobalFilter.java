package com.Gateway.Filters;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(org.springframework.web.server.ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        exchange.getResponse().getHeaders().add("X-Custom-Header", "MyCustomHeaderValue");
        return chain.filter(exchange);
    }
}
