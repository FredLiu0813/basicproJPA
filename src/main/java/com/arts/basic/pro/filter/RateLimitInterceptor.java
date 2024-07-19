package com.arts.basic.pro.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author
 * @date 2024/5/29
 * @apiNote
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    // 令牌桶容量
    private static final int BUCKET_CAPACITY = 100;

    // 每秒放入令牌的速率
    private static final int REFILL_RATE = 10;

    // 太多的请求状态码
    private static final int SC_TOO_MANY_REQUESTS = 429;

    // 令牌桶
    private final ConcurrentLinkedQueue<Long> tokenBucket = new ConcurrentLinkedQueue<>();

    public RateLimitInterceptor() {
        // 初始化令牌桶
        for (int i = 0; i < BUCKET_CAPACITY; i++) {
            tokenBucket.add(System.currentTimeMillis());
        }

        // 启动一个线程，定时放入令牌
        new Thread(() -> {
            while (true) {
                // 每隔100毫秒放入一个令牌
                tokenBucket.add(System.currentTimeMillis());
                try {
                    Thread.sleep(1000 / REFILL_RATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 尝试从令牌桶中取出一个令牌
        Long token = tokenBucket.poll();
        if (token != null) {
            // 处理请求
            return true;
        } else {
            // 令牌桶为空，拒绝请求
            response.sendError(SC_TOO_MANY_REQUESTS, "Too Many Requests");
            return false;
        }
    }
}
