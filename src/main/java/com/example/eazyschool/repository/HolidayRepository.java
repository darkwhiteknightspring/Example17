package com.example.eazyschool.repository;

import com.example.eazyschool.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */

@Repository
public class HolidayRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HolidayRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHolidays(){
        String sql = "Select * from HOLIDAYS";
        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return jdbcTemplate.query(sql,rowMapper);
    }


}
