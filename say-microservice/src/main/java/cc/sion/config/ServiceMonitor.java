package cc.sion.config;

import cc.sion.biz.SayBizImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

    @Autowired
    private CounterService counterService;
    @Autowired
    private GaugeService gaugeService;




    @Before("execution(* cc.sion.biz.*.*(..))")
    public void countService(JoinPoint joinPoint) {
        System.out.println("***@Before******");
        SayBizImpl target = (SayBizImpl) joinPoint.getTarget();
        String stepCode = joinPoint.getSignature().getName();

        System.out.println("target:"+target);
        System.out.println("method:"+joinPoint.getSignature().toString());
        System.out.println("stepCode:"+stepCode);

        counterService.increment(joinPoint.getSignature().toString());
    }


    @Around("execution(* cc.sion.biz.*.*(..))")
    public Object latencyService(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("***@Around------");
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        gaugeService.submit(pjp.getSignature().toString(), end - start);
        System.out.println("***@Around======");
        return obj;
    }
}