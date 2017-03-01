package cc.sion.config;


import com.netflix.spectator.api.Registry;
import com.netflix.spectator.api.Spectator;
import com.netflix.spectator.gc.GcLogger;
import com.netflix.spectator.jvm.Jmx;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.netflix.metrics.atlas.AtlasTagProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


/*

k,v,:eq   ==>>select * from time_series where k = v;
node,i-01,:eq,name,cpuUsage,:eq,:and   ==>>select * from time_series where node = 'i-01' and name ='cpuUsage';

举个栗子
http://localhost:7101/api/v1/graph?q=app,calc-service,:eq,statistic,count,:eq,:and&tz=Asia/Shanghai
http://localhost:7101/api/v1/graph?q=app,calc-service,:eq,name,jvm.memory.used,:eq,:and&tz=Asia/Shanghai
http://localhost:7101/api/v1/graph?q=app,calc-service,:eq,name,jvm.gc.promotionRate,:eq,:and&tz=Asia/Shanghai
http://localhost:7101/api/v1/graph?q=app,calc-service,:eq,name,jvm.gc.liveDataSize,:eq,:and&tz=Asia/Shanghai
http://localhost:7101/api/v1/graph?q=app,calc-service,:eq,name,jvm.gc.pause,:eq,:and&tz=Asia/Shanghai
http://localhost:7101/api/v1/graph?q=app,calc-service,:eq,name,jvm.gc.allocationRate,:eq,:and&tz=Asia/Shanghai
http://localhost:7101/api/v1/graph?q=app,calc-service,:eq,name,jvm.gc.pause,:eq,statistic,count,:eq,:and,:and&tz=Asia/Shanghai
*/

@Configuration
public class AtlasTagProviderConfigration {

    @Bean
    AtlasTagProvider atlasCommonTags(@Value("${spring.application.name}") String appName) {
        return () -> Collections.singletonMap("app", appName);
    }

//    @Bean
//    public CommandLineRunner registerExtMetrics(Registry registry) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                Jmx.registerStandardMXBeans(registry);
//
//                Spectator.globalRegistry().add(registry);
//                GcLogger gc = new GcLogger();
//                gc.start(null);
//            }
//        };
//    }



// 待整理
//    @Scheduled(fixedRate = 5000)
//    public void incrementCounter() {
//        registry.counter("mycounter").increment();
//    }
//
//    // TODO can be removed once https://github.com/spring-cloud/spring-cloud-netflix/pull/724 is merged
//    @Bean
//    MetricPoller poller() {
//        return new MonitorRegistryMetricPoller();
//    }
}
