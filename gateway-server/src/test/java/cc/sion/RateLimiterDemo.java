package cc.sion;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo {
    public static void main(String[] args) {
        testNoRateLimiter();
        System.out.println("********************************");
        testWithRateLimiter();
    }

    public static void testNoRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println("call execute.." + i);

        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void testWithRateLimiter() {
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(10.0); // 每秒不超过10个任务被提交
        for (int i = 0; i < 100; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}