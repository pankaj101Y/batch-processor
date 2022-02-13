package com.example.cronplayground.batch;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<P, Q> {
    private P first;
    private Q second;
}