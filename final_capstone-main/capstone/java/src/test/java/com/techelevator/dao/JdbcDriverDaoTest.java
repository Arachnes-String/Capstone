package com.techelevator.dao;

import com.techelevator.model.Driver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcDriverDaoTest extends BaseDaoTests {

    private static final Driver driver1 = new Driver(3, "user3", "guy", "33333", 50);
    private static final Driver driver2 = new Driver(4, "jeff", "geff", "44444", 25);


    private JdbcDriverDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //sut = new JdbcDriverDao(jdbcTemplate);
    }



    //can get a driver by driver_id
    @Test
    public void getDriverByDriverId_gets_correct_driver(){

        Driver expected = driver1;
        Driver actual = sut.getDriverById(1);

        compareDrivers(expected, actual);
    }


    //can get a list of drivers (admin)
    //to-do

    //can create a driver
    @Test
    public void createDriver_creates_new_driver(){
        Driver expected = new Driver(5, "jeff", "geff", "44444", 25);

        Assert.assertTrue(sut.createDriver(expected));

        Driver actual = sut.getDriverById(5);
        compareDrivers(expected, actual);

    }


    //can update a driver


    //can delete a driver



    //compare function...
    private void compareDrivers(Driver a, Driver b){

        Assert.assertEquals(a.getDriverId(), b.getDriverId());
        Assert.assertEquals(a.getFirstName(), b.getFirstName());
        Assert.assertEquals(a.getLastName(), b.getLastName());
        Assert.assertEquals(a.getZip(), b.getZip());
        Assert.assertEquals(a.getRadius(), b.getRadius(), 0.00);

    }

}
