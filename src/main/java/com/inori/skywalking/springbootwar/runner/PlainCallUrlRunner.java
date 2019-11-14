package com.inori.skywalking.springbootwar.runner;

import com.inori.skywalking.springbootwar.thread.ExecutorServiceManager;
import com.inori.skywalking.springbootwar.thread.NamedRunnable;
import com.inori.skywalking.springbootwar.util.HttpClientUtils;
import com.inori.skywalking.springbootwar.util.JavaBrowser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class PlainCallUrlRunner implements ApplicationRunner {

    private static final String API_URL = "/home/list/user";

    private static final List<Integer> ports = new LinkedList<>();

    static {
        for (int i = 9000; i < 9010; i++) {
            ports.add(i);
        }
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) {
        ScheduledExecutorService taskPlain = ExecutorServiceManager.getScheduledThreadPool(2, "URL访问");

        for (int port : ports) {
            taskPlain.scheduleAtFixedRate(new NamedRunnable() {
                @Override
                public void runAfter() {
                    log.info("开始访问请求");

                    String url = new StringBuilder("http://10.10.20.198:")
                            .append(port)
                            .append("/springboot")
                            .append(API_URL)
                            .toString();

                    log.debug("本次请求的Url[{}]", url);

                    String result = HttpClientUtils.getInstance().get(url);
                    log.info(result);
                }
            }.setName("url[" + port + "]"),2, 2, TimeUnit.MINUTES);

            taskPlain.scheduleWithFixedDelay(new NamedRunnable() {
                @Override
                public void runAfter() throws Exception {
                    log.info("开始brower访问请求");

                    String url = new StringBuilder("http://10.10.20.198:")
                            .append(port)
                            .append("/springboot")
                            .append(API_URL)
                            .toString();

                    log.debug("本次请求的Url[{}]", url);

                    JavaBrowser.getInstance().visitWebsite(url);
                }
            }.setName("url[brower -> " + port + "]"), 0, 5, TimeUnit.MINUTES);
        }
    }
}
