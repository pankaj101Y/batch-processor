package com.example.cronplayground.batch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


@Data
@Slf4j
public abstract class JobItem<Input, Output, PreJobItemResponse> implements Supplier<Output> {


    private Input input;

    private Output output;// each step updates this object in its call function;

    private Supplier<PreJobItemResponse> previousJobItemSupplier;

    private BatchContext batchContext;


    public JobItem(Input input, Output output, Supplier<PreJobItemResponse> previousJobItemSupplier) {
        this.output = output;
        this.previousJobItemSupplier = previousJobItemSupplier;
        this.input = input;
    }

    @Override
    public final Output get() {
        PreJobItemResponse previousResponse = getPreviousJobItemSupplier().get();
        executeJobItem(getSteps(input, previousResponse));
        return buildOutput(input, output,previousResponse);
    }

    protected abstract List<Step<?,Output>> getSteps(Input chunk, PreJobItemResponse previousJobItemResponse);


    private void executeJobItem( List<Step<?,Output>> steps){
        try {
            BatchJobExecutor.STEPS_EXECUTOR_SERVICE.invokeAll(steps, BatchJobExecutor.STEPS_EXECUTORS_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("error while executing JobItem {} ",getClass().getSimpleName(),e);
        }
    }


    public Output buildOutput(Input input, Output output, PreJobItemResponse previousResponse){
        return output;
    }


    public JobItem<Input, Output, PreJobItemResponse> setBatchContext(BatchContext context){
        this.batchContext=context;
        return this;
    }
}