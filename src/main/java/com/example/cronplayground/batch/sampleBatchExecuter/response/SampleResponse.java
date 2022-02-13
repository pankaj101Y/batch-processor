package com.example.cronplayground.batch.sampleBatchExecuter.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleResponse {

    private List<String> responseList=new ArrayList<>();
}
