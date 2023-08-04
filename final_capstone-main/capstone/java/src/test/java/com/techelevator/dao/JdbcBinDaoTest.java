package com.techelevator.dao;

import com.techelevator.model.Bin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcBinDaoTest extends BaseDaoTests {

    private static final Bin bin1 = new Bin(1, 2, 1, 1, 35.01, 35, 0);
    private static final Bin bin2 = new Bin(2, 0, 0, 1, 10.00, 10, 0);

    private JdbcBinDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcBinDao(jdbcTemplate);
    }


    //can get a bin by client_id...
    @Test
    public void getBinByClientId_gets_correct_bin(){
        Bin expected = bin1;

        Bin actual = sut.getBinByClientId(1);

        compareBins(expected, actual);
    }

    //can create an "empty" bin by client_id...

    @Test
    public void createEmptyBinForClientID_creates_empty_bin_at_client_id(){
        Bin expected = new Bin(3, 0, 0, 0, 0, 0, 0);

        Assert.assertTrue(sut.createBinForClientByClientID(3));

        Bin actual = sut.getBinByClientId(3);

        compareBins(expected, actual);

    }


    //can update a bin by client_id...
    @Test
    public void updateBin_updates_correct_bin(){
        bin1.setGreenBins(5);

        Bin actual = sut.updateBin(bin1);

        compareBins(bin1, actual);
    }

    //can delete a bin by client_id....

    //compare function
    private void compareBins(Bin a, Bin b){

        Assert.assertEquals(a.getClientId(), b.getClientId());
        Assert.assertEquals(a.getClearBins(), b.getClearBins());
        Assert.assertEquals(a.getGreenBins(), b.getGreenBins());
        Assert.assertEquals(a.getBrownBins(), b.getBrownBins());
        Assert.assertEquals(a.getTotalWeight(), b.getTotalWeight(), 0.00);
        Assert.assertEquals(a.getCredits(), b.getCredits());
        Assert.assertEquals(a.getRejections(), b.getRejections());

    }



}
