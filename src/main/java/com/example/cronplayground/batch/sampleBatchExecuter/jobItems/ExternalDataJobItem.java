package com.example.cronplayground.batch.sampleBatchExecuter.jobItems;



import com.example.cronplayground.batch.JobItem;
import com.example.cronplayground.batch.Step;
import com.example.cronplayground.batch.sampleBatchExecuter.steps.StepOne;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
public class ExternalDataJobItem extends JobItem<String, List<String>,List<String>> {


    public ExternalDataJobItem(String s, List<String> strings, Supplier<List<String>> previousJobItemSupplier) {
        super(s, strings, previousJobItemSupplier);
    }

    @Override
    protected List<Step<?, List<String>>> getSteps(String chunk, List<String> previousJobItemResponse) {
        List<Step<?,List<String>>> stepList=new ArrayList<>();

        stepList.add(new StepOne("my input",getOutput()).setContext(getBatchContext()));

        return stepList;
    }
}



