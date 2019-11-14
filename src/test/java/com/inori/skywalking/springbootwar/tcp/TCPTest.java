package com.inori.skywalking.springbootwar.tcp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

@Slf4j
public class TCPTest {

    @Test
    public void sendTCPMessage() throws IOException {
        Socket socket = new Socket("localhost", 16990);

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "GBK");
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write("消息之一！");
        bw.flush();

        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "GBK");
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null) {
            log.info(line);
        }

        br.close();
        isr.close();
        is.close();
        bw.close();
        osw.close();
        os.close();
        socket.close();
    }
}
