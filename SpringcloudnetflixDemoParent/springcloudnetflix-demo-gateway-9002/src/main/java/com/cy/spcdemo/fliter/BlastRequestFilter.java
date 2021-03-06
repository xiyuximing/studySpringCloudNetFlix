package com.cy.spcdemo.fliter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class BlastRequestFilter implements GatewayFilter, Ordered {

    public static final String REDIS_KEY = "clientip";

    @Value("${custom.gateway.maxCount}")
    private Integer maxCount;

    @Value("${custom.gateway.timeWin}")
    private long timeWin;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String clientIp = request.getRemoteAddress().getHostString();
        long now = System.currentTimeMillis();
        String redisKey = REDIS_KEY + clientIp;
        ZSetOperations opsForZSet = redisTemplate.opsForZSet();
        Set<Long> set = opsForZSet.rangeByScore(redisKey, now - timeWin, now);

        if (set.size() > maxCount) {
            response.setStatusCode(HttpStatus.SEE_OTHER);
            String data = "您频繁进行注册，请求已被拒绝";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
            return  response.writeWith(Mono.just(wrap));
        }
        opsForZSet.add(redisKey, now, now);
        opsForZSet.removeRangeByScore(redisKey, 0, now - timeWin);
        redisTemplate.expire(redisKey, timeWin, TimeUnit.SECONDS);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}