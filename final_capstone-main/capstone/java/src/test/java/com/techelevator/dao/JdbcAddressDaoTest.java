package com.techelevator.dao;

import com.techelevator.model.Address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcAddressDaoTest extends BaseDaoTests {

    private static final Address address1 = new Address(1, 1, "street", "city", "ST", "11111");
    private static final Address address2 = new Address(2, 2, "street2", "city2", "AT", "22222");

    private JdbcAddressDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAddressDao(jdbcTemplate);
    }


    //get an address by id
    @Test
    public void getAddressByClientID_returns_correct_address(){
        Address expected = address1;
        Address actual = sut.getAddressById(1);

        compareAddresses(expected, actual);
    }


    //get a list of addresses (admin) (maybe only 'active' addresses, i.e. with a user_id asssociated)
    //to-do...

    //add an address
    @Test
    public void createAddress_creates_new_address(){
        Address expected = new Address(4, 3, "street4", "city2", "AT", "22222");
        Address actual = sut.createAddress(expected);

        compareAddresses(expected, actual);

    }




    //compare function
    private void compareAddresses(Address a, Address b){

        Assert.assertEquals(a.getAddressId(), b.getAddressId());
        Assert.assertEquals(a.getClientId(), b.getClientId());
        Assert.assertEquals(a.getStreetAddress(), b.getStreetAddress());
        Assert.assertEquals(a.getCity(), b.getCity());
        Assert.assertEquals(a.getState(), b.getState());
        Assert.assertEquals(a.getZip(), b.getZip());

    }



}
