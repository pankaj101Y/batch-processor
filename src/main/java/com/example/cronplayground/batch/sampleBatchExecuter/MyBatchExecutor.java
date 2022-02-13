package com.example.cronplayground.batch.sampleBatchExecuter;



import com.example.cronplayground.batch.BatchJobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyBatchExecutor extends BatchJobExecutor<String> {

    public MyBatchExecutor(MyCP chunkProvider, MyFB flowBuilder) {
        super(chunkProvider,flowBuilder);
    }
}


