package com.suburb.app;

import com.suburb.app.dao.SuburbDao;
import com.suburb.app.domain.Suburb;
import com.suburb.app.service.SuburbService;
import com.suburb.app.service.SuburbServiceImpl;
import com.suburb.app.wrapper.PostalCodeRangeResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SuburbServiceTest {

    @Mock
    SuburbDao suburbDao;

    @InjectMocks
    SuburbService suburbService = new SuburbServiceImpl(suburbDao);

    List<Suburb> suburbs;

    @Before
    public void init() {
        suburbs = new ArrayList<Suburb>(){{
            add(new Suburb("A G C R",110002L));
            add(new Suburb("Anand Parbat",110005L));
            add(new Suburb("A P S Colony",	110010L));
            add(new Suburb("Ashram",	110016L));
            add(new Suburb("Ashok Nagar",	110018L));
        }};
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_findSuburbs_between_postalCodes_rows_return(){
        when(suburbDao.findBetweenPostalCode(anyLong(),anyLong())).thenReturn(suburbs.subList(0,3).stream());

        PostalCodeRangeResponse postalCodeRangeResponse = suburbService.findSuburbsBetweenPostalCodes(110002L,110010L);
        System.out.println(postalCodeRangeResponse);
        Assert.assertEquals(3,postalCodeRangeResponse.getNames().size());
    }

    @Test
    public void test_findSuburbs_between_postalCodes_null_return(){
        when(suburbDao.findBetweenPostalCode(anyLong(),anyLong())).thenReturn(Stream.empty());
        PostalCodeRangeResponse postalCodeRangeResponse = suburbService.findSuburbsBetweenPostalCodes(110000L,110001L);
        Assert.assertArrayEquals(Collections.EMPTY_LIST.toArray(),postalCodeRangeResponse.getNames().toArray());
    }


    @Test
    public void test_findSuburbs_between_postalCodes_ok_word_count_return(){
        when(suburbDao.findBetweenPostalCode(anyLong(),anyLong())).thenReturn(suburbs.subList(1,2).stream());
        PostalCodeRangeResponse postalCodeRangeResponse = suburbService.findSuburbsBetweenPostalCodes(110005L,110005L);
        System.out.println(postalCodeRangeResponse);
        Assert.assertEquals(11,postalCodeRangeResponse.getWordCount());
    }
}
