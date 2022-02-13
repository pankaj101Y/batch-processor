package com.example.cronplayground.batch;

import java.util.concurrent.ConcurrentHashMap;

public class BatchContext {

    private final ConcurrentHashMap<String,String> context=new ConcurrentHashMap<>();

    public void add(String key,String value){
        context.put(key,value);
    }

    public String get(String key){
        return context.get(key);
    }
}
