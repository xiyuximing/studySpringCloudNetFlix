package com.cy.spcdemo.fliter;

import com.cy.spcdemo.client.UserClient;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class TokenFilter implements GatewayFilter, Ordered {

    @Autowired
    private UserClient userClient;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response = serverWebExchange.getResponse();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        Optional<Map.Entry<String, List<HttpCookie>>> token = cookies.entrySet().stream().filter(entry -> Objects.equals(entry.getKey(), "token")).findAny();

        if (!token.isPresent()) {
            response.setStatusCode(HttpStatus.SEE_OTHER);
            String data = "请先登录";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes(Charsets.UTF_8));
            return response.writeWith(Mono.just(wrap));
        }
        Map.Entry<String, List<HttpCookie>> stringListEntry = token.get();
        List<HttpCookie> list = stringListEntry.getValue();
        if (list == null || list.isEmpty()) {
            response.setStatusCode(HttpStatus.SEE_OTHER);
            String data = "请先登录";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes(Charsets.UTF_8));
            return response.writeWith(Mono.just(wrap));
        }
        HttpCookie cookie = list.get(0);
        String tokenStr = cookie.getValue();
        String email = userClient.info(tokenStr);
        if (email == null || Objects.equals(email, "")) {
            response.setStatusCode(HttpStatus.SEE_OTHER);
            String data = "请先登录";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes(Charsets.UTF_8));
            return response.writeWith(Mono.just(wrap));
        }
        return gatewayFilterChain.filter(serverWebExchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}