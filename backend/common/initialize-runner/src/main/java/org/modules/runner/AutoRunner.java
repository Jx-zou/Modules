package org.modules.runner;

import org.modules.handler.InitializeHandler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

/**
 * The type LocalRepositoryAutoRunner.
 *
 * @author Jx-zou
 */
@Component
public class AutoRunner implements ApplicationRunner {

    private static final Logger log = Logger.getLogger(AutoRunner.class.getName());

    private final Map<String, InitializeHandler> handlerList;

    public AutoRunner(ApplicationContext applicationContext) {
        this.handlerList = applicationContext.getBeansOfType(InitializeHandler.class);
    }

    private void execute() {
        try {
            for (Map.Entry<String, InitializeHandler> handlerEntry : handlerList.entrySet()) {
                String name = handlerEntry.getKey();
                InitializeHandler handler = handlerEntry.getValue();
                Thread thread = new Thread(() -> {
                    log.info(name + "Thread start");
                    handler.handle();
                });
                thread.setName(name + "Thread");
                thread.start();
            }
        } catch (Throwable e) {
            throw new RuntimeException("初始化数据失败.", e);
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        execute();
    }
}
