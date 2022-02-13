package com.example.cronplayground.batch.sampleBatchExecuter.steps;


import com.example.cronplayground.batch.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class StepOne extends Step<String, List<String>> {


    public StepOne(String input, List<String> output) {
        super(input, output);
    }

    @Override
    protected Object executeStep() throws Exception {
        return null;
    }
}