package com.suburb.app.wrapper;

import lombok.Data;

import java.util.List;

@Data
public class PostalCodeRangeResponse {
    List<String> names;
    int wordCount;
}
