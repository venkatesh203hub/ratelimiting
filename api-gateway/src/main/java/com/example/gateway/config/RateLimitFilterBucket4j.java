package com.example.gateway.config;

//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Refill;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.time.Duration;
//
//@Component
//public class RateLimitFilter_bucket4j implements Filter {
//
//    private final Bucket bucket;
//
//    public RateLimitFilter_bucket4j() {
//        Bandwidth limit = Bandwidth.classic(
//                10,                        // capacity
//                Refill.intervally(10, Duration.ofSeconds(1)) // 10 req/sec
//        );
//        this.bucket = Bucket.builder()
//                .addLimit(limit)
//                .build();
//    }
//
//    @Override
//    public void doFilter(
//            ServletRequest request,
//            ServletResponse response,
//            FilterChain chain
//    ) throws IOException, ServletException {
//
//        if (bucket.tryConsume(1)) {
//            chain.doFilter(request, response);
//        } else {
//            HttpServletResponse httpResp = (HttpServletResponse) response;
//            httpResp.setStatus(429);
//            httpResp.getWriter().write("Too many requests");
//        }
//    }
//}

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class RateLimitFilterBucket4j implements GlobalFilter, Ordered {

    private final Bucket bucket;

    public RateLimitFilterBucket4j() {
        Bandwidth limit = Bandwidth.classic(
                5,											//  capacity
                Refill.intervally(10, Duration.ofSeconds(1))// 10 req/sec
        );
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        if (bucket.tryConsume(1)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1; // run early
    }
}


