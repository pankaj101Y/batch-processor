package com.example.cronplayground.batch.sampleBatchExecuter;


import com.example.cronplayground.batch.BatchContext;
import com.example.cronplayground.batch.FlowBuilder;
import com.example.cronplayground.batch.JobItem;
import com.example.cronplayground.batch.sampleBatchExecuter.jobItems.CacheUpdaterJobItem;
import com.example.cronplayground.batch.sampleBatchExecuter.jobItems.ExternalDataJobItem;
import com.example.cronplayground.batch.sampleBatchExecuter.request.SampleRequest;
import com.example.cronplayground.batch.sampleBatchExecuter.response.SampleResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * stateless class
 */
@Component
public class MyFB implements FlowBuilder<String> {


//    @Transactional
    @Override
    public JobItem<?, ?, ?> buildFlow(List<String> chunk, BatchContext context) {

        JobItem<String, List<String>,List<String>> externalDataJobItem
                = new ExternalDataJobItem(
                "my string",
                chunk,
                () -> chunk)
                .setBatchContext(context);


        return new CacheUpdaterJobItem(new SampleRequest(),new SampleResponse(), externalDataJobItem).setBatchContext(context);
    }
}
