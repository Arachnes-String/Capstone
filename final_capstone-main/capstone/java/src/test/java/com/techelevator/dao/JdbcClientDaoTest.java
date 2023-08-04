package com.techelevator.dao;

import com.techelevator.model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcClientDaoTest extends BaseDaoTests {

    private static final Client CLIENT_1 = new Client(1, "first", "last", 1, "phone", "user1@gmail.com");
    private static final Client CLIENT_2 = new Client(2, "first1", "last1", 2, "phone", "user2@gmail.com");

    private JdbcClientDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcClientDao(jdbcTemplate);
    }

    //can get a client by client_id
    @Test
    public void getClientByClientId_returns_correct_client(){
        Client expected = CLIENT_1;
        Client actual = sut.getClientById(1);

        compareClients(expected, actual);
    }

    //can get a list of clients (admin)
    //to-do

    //can create a client
    @Test
    public void createClient_creates_new_client(){
        Client expected = new Client(4, "first1", "last1", 2, "phone", "user2@gmail.com");

        Assert.assertTrue(sut.createClient(expected));

        Client actual = sut.getClientById(4);

        compareClients(expected, actual);

    }

    //can update a client
    @Test
    public void updateClient_updates_client_correctly(){
        CLIENT_2.setEmailAddress("Test@Value.com");

        Client actual = sut.updateClient(CLIENT_2);

        compareClients(CLIENT_2, actual);


    }



    //can delete a client
    //to-do


    //compare function...
    private void compareClients(Client a, Client b){

        Assert.assertEquals(a.getClientId(), b.getClientId());
        Assert.assertEquals(a.getFirstName(), b.getFirstName());
        Assert.assertEquals(a.getLastName(), b.getLastName());
        Assert.assertEquals(a.getAddressId(), b.getAddressId());
        Assert.assertEquals(a.getPhoneNumber(), b.getPhoneNumber());
        Assert.assertEquals(a.getEmailAddress(), b.getEmailAddress());

    }


}
