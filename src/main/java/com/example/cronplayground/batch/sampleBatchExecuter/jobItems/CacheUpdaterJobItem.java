package com.example.cronplayground.batch.sampleBatchExecuter.jobItems;


import com.example.cronplayground.batch.JobItem;
import com.example.cronplayground.batch.Step;
import com.example.cronplayground.batch.sampleBatchExecuter.request.SampleRequest;
import com.example.cronplayground.batch.sampleBatchExecuter.response.SampleResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
public class CacheUpdaterJobItem extends JobItem<SampleRequest,SampleResponse, List<String>> {

    public CacheUpdaterJobItem(SampleRequest sampleRequest, SampleResponse sampleResponse, Supplier<List<String>> previousJobItemSupplier) {
        super(sampleRequest, sampleResponse, previousJobItemSupplier);
    }

    @Override
    protected List<Step<?, SampleResponse>> getSteps(SampleRequest chunk, List<String> previousJobItemResponse) {
        return new ArrayList<>();
    }
}
