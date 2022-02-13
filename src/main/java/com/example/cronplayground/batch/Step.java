package com.example.cronplayground.batch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Data
@Slf4j
public abstract class Step<I, O> implements Callable<Object> {

    private I input;
    private O output;
    private BatchContext context;

    public Step(I input, O output) {
        this.input = input;
        this.output = output;
    }

    public Step<I, O> setContext(BatchContext context) {
        this.context = context;
        return this;
    }

    public final Object call() {
        long startTime=System.currentTimeMillis();
        if (BatchJobExecutor.DEBUG) {
            logStepData("step input", getInput());
        }

        Object result;
        try {
            result = executeStep();
        } catch (Exception e) {
            logStepData("ERROR !!! ", e);
            return null;
        }

        if (BatchJobExecutor.DEBUG){
            logStepData("step call result", result);
            logStepData("step output", getOutput());
        }
        logStepData("time taken ", (System.currentTimeMillis()-startTime));
        return result;
    }

    protected abstract Object executeStep() throws Exception;


    private void logStepData(String text, Object data) {
        log.info("Thread {} , Step {} , msg {} , data {} ",
                Thread.currentThread().getName(),
                this.getClass().getSimpleName(),
                text,
                data
        );
    }
}