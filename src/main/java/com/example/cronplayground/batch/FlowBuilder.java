package com.example.cronplayground.batch;


import java.util.List;

public interface FlowBuilder<I>{

    JobItem<?,?,?> buildFlow(List<I> chunk,BatchContext context);
}
