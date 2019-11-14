package com.inori.skywalking.springbootwar.socket;

import com.inori.skywalking.springbootwar.thread.ExecutorServiceManager;
import com.inori.skywalking.springbootwar.thread.NamedRunnable;
import com.inori.skywalking.springbootwar.thread.PingForkJoinWorkerThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class TCPServer {

    private int port = 16990;

    @PostConstruct
    public void startTCPServer() {
        log.info("启动一个TCP服务");
        ExecutorService tcpServer = ExecutorServiceManager.getSingleExecutorService("TCPServer");
        ExecutorService taskServer = ExecutorServiceManager.getForkWorkerJoinServer(4, "TCP消息处理");

        tcpServer.submit(new NamedRunnable() {
            @Override
            public void runAfter() throws Exception {
                log.debug("绑定[{}]端口服务", port);
                ServerSocket server = new ServerSocket(port);

                taskServer.submit(new NamedRunnable() {
                    @Override
                    public void runAfter() throws Exception {
                        while (true) {
                            try {
                                Socket socket = server.accept();

                                log.info("接收到链接: {}", socket.getRemoteSocketAddress().toString());
                                InputStream is = socket.getInputStream();
                                     InputStreamReader isr = new InputStreamReader(is, "GBK");
                                     BufferedReader br = new BufferedReader(isr);
                                    log.info("开始读取接收的数据");

                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        log.info(line);
                                    }

                                OutputStream os = socket.getOutputStream();
                                     OutputStreamWriter osw = new OutputStreamWriter(os);
                                     BufferedWriter bw = new BufferedWriter(osw);
                                    log.info("读取完毕，返回消息");

                                    bw.write("哈哈！");
                                    bw.flush();

                                socket.close();
                            } catch (IOException e) {
                                log.error("I/O异常", e);
                            }
                        }
                    }
                });
            }
        }.setName("TCP[" + port + "]服务"));
    }

}
