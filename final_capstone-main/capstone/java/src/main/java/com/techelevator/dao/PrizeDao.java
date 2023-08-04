package com.techelevator.dao;

import com.techelevator.model.Prize;

import java.util.List;

public interface PrizeDao {

    List<Prize> getAllPrizes();

    Prize getPrizeById(int id);

    Prize getPrizeByName(String name);

    boolean createPrize(Prize prize);

    boolean updatePrize (Prize prize);

    boolean deletedPrize(int id);

}
