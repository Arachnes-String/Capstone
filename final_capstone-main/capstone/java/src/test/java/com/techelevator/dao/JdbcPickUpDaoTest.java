package com.techelevator.dao;

import com.techelevator.model.PickUp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import java.time.LocalDateTime;
import java.util.List;

public class JdbcPickUpDaoTest extends BaseDaoTests {

    private static final PickUp PICKUP_1 = new PickUp(1, LocalDateTime.of(2023,6,2,18,7,30),
    LocalDateTime.of(2023,6,2,19,20,0), LocalDateTime.of(2023,6,3,19,20,0),
            1,5,1, "pending",true, true, true, 13412.0);

    private JdbcPickUpDao dao;
    private PickUpDao pickUpDao;
    private JdbcTemplate jdbcTemplate;
    private AddressDao addressDao;

    @Before
    public void setup() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        addressDao = new JdbcAddressDao(jdbcTemplate);
        pickUpDao = new JdbcPickUpDao(jdbcTemplate, addressDao);
        dao = (JdbcPickUpDao) pickUpDao;
    }

    @Test
    public void findAll_returnsAllPickUps() {
        List<PickUp> allPickUps = dao.findAll();
        Assert.assertEquals(0, allPickUps.size());
    }

    @Test
    public void getPickUpById_returnsCorrectPickUp() {
        PickUp expected = PICKUP_1;
        PickUp actual = dao.getPickUpById(0);
        assertPickUpsMatch(actual, expected);

    }

    @Test
    public void getPickUpByClientId_returnsPickUpsForClientId() {
        List<PickUp> pickUps = dao.getPickUpByClientId(5);
        Assert.assertEquals(1, pickUps.size());
    }


    private void assertPickUpsMatch(PickUp actual, PickUp expected) {

        Assert.assertEquals(actual.getPickUpId(), expected.getPickUpId());
        Assert.assertEquals(actual.getDateTimeCreated(), expected.getDateTimeCreated());
        Assert.assertEquals(actual.getDateTimeRequested(), expected.getDateTimeRequested());
        Assert.assertEquals(actual.getDateTimeCompleted(), expected.getDateTimeCompleted());
        Assert.assertEquals(actual.getAddressId(), expected.getAddressId());
        Assert.assertEquals(actual.getClientId(), expected.getClientId());
        Assert.assertEquals(actual.getDriverId(), expected.getDriverId());
        Assert.assertEquals(actual.isBrownBinFull(), expected.isBrownBinFull());
        Assert.assertEquals(actual.isClearBinFull(), expected.isClearBinFull());
        Assert.assertEquals(actual.isGreenBinFull(), expected.isGreenBinFull());
        Assert.assertEquals(actual.isGreenBinFull(), expected.isGreenBinFull());
        Assert.assertEquals(actual.getWeight(), expected.getWeight());
        Assert.assertEquals(actual.getStatus(), expected.getStatus());


    }

}
