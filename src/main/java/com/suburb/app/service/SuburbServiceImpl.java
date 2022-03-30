package com.suburb.app.service;


import com.suburb.app.dao.SuburbDao;
import com.suburb.app.domain.Suburb;
import com.suburb.app.wrapper.PostalCodeRangeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SuburbServiceImpl implements SuburbService{

    SuburbDao suburbDao;

    public SuburbServiceImpl(SuburbDao suburbDao) {
        this.suburbDao = suburbDao;
    }

    @Transactional
    @Override
    public PostalCodeRangeResponse findSuburbsBetweenPostalCodes(Long start, Long end) {
        PostalCodeRangeResponse postalCodeRangeResponse = new PostalCodeRangeResponse();
        try(Stream<Suburb> stream =  suburbDao.findBetweenPostalCode(start,end)){
            List<Suburb> suburbs = stream.collect(Collectors.toList());
            List<String> names = suburbs.stream().map(Suburb::getName).sorted().collect(Collectors.toList());
            int charCount = names.stream().map(s -> s.replaceAll("\\s","").length()).reduce(0,Integer::sum);
            postalCodeRangeResponse.setNames(names);
            postalCodeRangeResponse.setWordCount(charCount);
        }
        return postalCodeRangeResponse;
    }

    @Transactional
    @Override
    public List<Suburb> save(List<Suburb> suburbs) {
        return suburbDao.saveAllAndFlush(suburbs);
    }
}
