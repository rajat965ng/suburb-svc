package com.suburb.app;

import com.suburb.app.dao.SuburbDao;
import com.suburb.app.domain.Suburb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SuburbDaoTest {

    @Autowired
    private SuburbDao suburbDao;

    @Before
    public void setup(){
        List<Suburb> suburbs = new ArrayList<Suburb>(){{
            add(new Suburb("A G C R",110002L));
            add(new Suburb("Anand Parbat",110005L));
            add(new Suburb("A P S Colony",	110010L));
            add(new Suburb("Ashram",	110016L));
            add(new Suburb("Ashok Nagar",	110018L));
        }};
        suburbDao.saveAllAndFlush(suburbs);
    }

    @Test
    public void test_suburb_save(){
        Suburb suburb = suburbDao.save(new Suburb("Civil Lines",	110054L));
        Optional<Suburb> suburbResponse = suburbDao.findOne(Example.of(suburb));
        Assert.assertEquals(suburb.getId(),suburbResponse.orElseThrow(() -> new NullPointerException("PostalCode not found")).getId());
    }

    @Test
    public void test_suburbs_between_postalcodes_find(){
      try(Stream<Suburb> stream = suburbDao.findBetweenPostalCode(110010L,110018L)){
          List<Suburb> suburbs = stream.collect(Collectors.toList());
          suburbs.forEach(System.out::println);
          Assert.assertEquals(3,suburbs.size());
      }
    }

}
