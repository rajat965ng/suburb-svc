package com.suburb.app.service;

import com.suburb.app.domain.Suburb;
import com.suburb.app.wrapper.PostalCodeRangeResponse;

import java.util.List;


public interface SuburbService {
    public PostalCodeRangeResponse findSuburbsBetweenPostalCodes(Long start, Long end);
    public List<Suburb> save(List<Suburb> suburbs);
}
