package com.example.cronplayground.batch.sampleBatchExecuter;


import com.example.cronplayground.batch.ChunkProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * stateful class
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyCP implements ChunkProvider<String> {


    private Pageable pageable;

    @Value("${batch.update.chunk}")
    private int chunkSize;

    @PostConstruct
    private void init(){
        pageable= PageRequest.of(0, chunkSize);
    }


    @Override
    public List<String> nextChunk() {

        long chunkProviderStartTime=System.currentTimeMillis();

        if (pageable != Pageable.unpaged()) {

            //joined query so @Transactional can be avoided
//            Slice<String> chunk = myRepo.findBySomething("some condition", pageable);

        }


        log.info("chunkProvider query time taken {}  ",(System.currentTimeMillis()-chunkProviderStartTime));


        return List.of();
    }
}
