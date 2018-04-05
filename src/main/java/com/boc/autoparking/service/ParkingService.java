package com.boc.autoparking.service;

import com.boc.autoparking.model.Coordinate;


public interface ParkingService {

    /**
     * find parking slot position given the instructions
     * @param instructions the instructions for car movement
     * @return the coordinate of the found car park slot
     */
    Coordinate findParkingSlot(String instructions);
}
