package com.techelevator.dao;

import com.techelevator.model.Bin;

public interface BinDao {


    //get a bin by client_id
    Bin getBinByClientId(int clientId);

    //get the sum of the total weights in every bin
    Double getSumOfAllBins();

    //get the sum of all clear bins collected
    int getSumOfAllClearBinsCollected();

    //get the sum of all brown bins collected
    int getSumOfAllBrownBinsCollected();

    //get the sum of all green bins collected
    int getSumOfAllGreenBinsCollected();

    //get the sum of all of the bins collected from all clients
    int getSumOfAllBinsCollected();

    //create a new (empty) bin for a client
    boolean createBinForClientByClientID(int clientId);


    //update a bin by clientId...
    Bin updateBin(Bin bin);


    //delete a bin... (when a client is deleted!)
    boolean deleteBinByClientId(int clientId);
}
