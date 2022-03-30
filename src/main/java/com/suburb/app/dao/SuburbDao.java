package com.suburb.app.dao;

import com.suburb.app.domain.Suburb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface SuburbDao extends JpaRepository<Suburb, Long> {

    @Query("select s from Suburb s where s.postalCode between :startRange and :endRange")
    Stream<Suburb> findBetweenPostalCode(Long startRange, Long endRange);
}
