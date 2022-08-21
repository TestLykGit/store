package com.lyk.store.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class JavastackListener implements ApplicationListener<WebServerInitializedEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        log.info("Swagger地址：http://localhost:"+event.getWebServer().getPort()+"/swagger-ui/index.html");
    }

}