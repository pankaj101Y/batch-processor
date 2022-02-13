package com.example.cronplayground.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public abstract class BatchJobExecutor<T> {

    protected ChunkProvider<T> chunkProvider;
    protected FlowBuilder<T> flowBuilder;
    protected final BatchContext context=new BatchContext();

    public static final boolean DEBUG=false;
    public static final int STEPS_EXECUTORS_THREADS = 6;
    public static final long STEPS_EXECUTORS_TIMEOUT = 20000;
    public static final ExecutorService STEPS_EXECUTOR_SERVICE = Executors.newFixedThreadPool(STEPS_EXECUTORS_THREADS);


    public BatchJobExecutor(ChunkProvider<T> chunkProvider, FlowBuilder<T> flowBuilder) {
        this.chunkProvider = chunkProvider;
        this.flowBuilder = flowBuilder;
    }

    public void execute() {
        preExecute();
        List<T> data;

        String batchTackId = UUID.randomUUID().toString();
        long startTime=System.currentTimeMillis();

        log.info("starting batch {} .....", batchTackId);
        while (!(data = (chunkProvider.nextChunk())).isEmpty()) {
            executeChunk(data);
        }

        log.info("batch end {} ..... timeTaken {} ", batchTackId, (System.currentTimeMillis() - startTime) / 1000);
        postExecute();
    }

    public void executeChunk(List<T> data) {
        if (!CollectionUtils.isEmpty(data)) {
            long chunkStartTime =System.currentTimeMillis();
            flowBuilder.buildFlow(data, context).get();

            log.info("chunk end time {} ",(System.currentTimeMillis()-chunkStartTime)/1000);
        }
    }

    protected void preExecute(){

    }

    protected void postExecute(){

    }


}