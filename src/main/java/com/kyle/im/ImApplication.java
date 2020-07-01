package com.kyle.im;

import com.kyle.im.server.BootNettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
public class ImApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);
    }

    @Async
    @Override
    public void run(String... args) throws Exception {
        /**
         * 使用异步注解方式启动netty服务端服务
         */
        new BootNettyServer().bind(8888);

    }
}
