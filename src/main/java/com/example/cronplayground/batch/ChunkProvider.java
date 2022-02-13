package com.example.cronplayground.batch;

import java.util.List;

public interface ChunkProvider<T> {


    List<T> nextChunk();

}
