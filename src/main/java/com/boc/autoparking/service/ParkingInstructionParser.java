package com.boc.autoparking.service;

import com.boc.autoparking.model.Coordinate;

import java.util.List;

public interface ParkingInstructionParser {

    /**
     * validate the parking instruction
     * @param instruction
     * @return
     */
    boolean isInstructionValid(String instruction);

    /**
     * extract the instructions for movement
     * @param instruction instruction
     * @return the list of moves (F, L, R)
     */
    List<Character> getMovementInstructions(String instruction);

    /**
     * Get initial position from the instruction parameter
     * @param instruction instruction
     * @return the initial position
     */
    Coordinate getInitialPos(String instruction);
}
