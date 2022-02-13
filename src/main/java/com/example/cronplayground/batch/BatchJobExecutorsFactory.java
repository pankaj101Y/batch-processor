package com.example.cronplayground.batch;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Component
@Slf4j
public class BatchJobExecutorsFactory {


    private final static Map<String, Pair<Class<? extends BatchJobExecutor>,Class<?>[]>> BATCH_EXECUTORS_MAP=new HashMap<>();

    @Autowired
    private List<BatchJobExecutor> batchJobExecutors;

    @Autowired
    ApplicationContext applicationContext;


    @PostConstruct
    public void init(){
        batchJobExecutors.forEach(executor-> BATCH_EXECUTORS_MAP.put(executor.getClass().getSimpleName(), getConstructionPair(executor)));

        log.info("batch map = {} ",batchJobExecutors);

    }

    private Pair<Class<? extends BatchJobExecutor>, Class<?>[]> getConstructionPair(BatchJobExecutor executor) {
        return new Pair<>(executor.getClass(),executor.getClass().getConstructors()[0].getParameterTypes());
    }

    public BatchJobExecutor getBatchJobExecutor(String name) {
        Pair<Class<? extends BatchJobExecutor>, Class<?>[]> constructionPair = BATCH_EXECUTORS_MAP.get(name);
        return applicationContext.getBean(constructionPair.getFirst(),
                Arrays.stream(constructionPair.getSecond())
                        .map(bean->applicationContext.getBean(bean))
                        .toArray()
        );
    }
}
