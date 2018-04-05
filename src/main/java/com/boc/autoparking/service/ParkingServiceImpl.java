package com.boc.autoparking.service;

import com.boc.autoparking.exception.OutOfGridBoundaryException;
import com.boc.autoparking.model.Coordinate;
import com.boc.autoparking.model.Direction;
import com.boc.autoparking.model.Rotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.boc.autoparking.model.Direction.*;
import static com.boc.autoparking.model.Rotation.CLOCKWISE;
import static com.boc.autoparking.model.Rotation.COUNTERCLOCKWISE;
import static java.lang.StrictMath.abs;

@Service
public class ParkingServiceImpl implements ParkingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingServiceImpl.class);

    private ParkingInstructionParser instructionParser;

    private static final int MAX_GRID_SIZE = 15;

    public ParkingServiceImpl(ParkingInstructionParser instructionParser) {
        this.instructionParser = instructionParser;
    }

    @Override
    public Coordinate findParkingSlot(final String instructions) {
        LOGGER.info("find parking slot by the instruction {}", instructions);

        if (!instructionParser.isInstructionValid(instructions)) {
            throw new IllegalArgumentException(instructions);
        }

        Coordinate currentPos = instructionParser.getInitialPos(instructions);
        Direction currentDirection = NORTH;

        Collection<Character> moves = instructionParser.getMovementInstructions(instructions);

        LOGGER.debug("Start moves with initial position: {}", currentPos);

        for(Character move : moves) {
            switch (move) {
                case 'F':
                    moveForward(currentPos, currentDirection);
                    logCurrentPostion(currentPos, currentDirection);
                    break;
                case 'L':
                    currentDirection = rotateDirection(currentDirection, COUNTERCLOCKWISE);
                    logCurrentPostion(currentPos, currentDirection);

                    break;
                case 'R':
                    currentDirection = rotateDirection(currentDirection, CLOCKWISE);
                    logCurrentPostion(currentPos, currentDirection);

                    break;
                default:
                    break;
            }
        }

        LOGGER.info("final position: {}, direction: {}", currentPos, currentDirection);

        return currentPos;
    }

    private Direction rotateDirection(Direction direction, Rotation rotation) {
        switch (direction) {
            case NORTH:
                return Rotation.CLOCKWISE == rotation ? EAST : WEST;
            case EAST:
                return Rotation.CLOCKWISE == rotation ? SOUTH : NORTH;
            case SOUTH:
                return Rotation.CLOCKWISE == rotation ? WEST : EAST;
            case WEST:
                return Rotation.CLOCKWISE == rotation ? NORTH : SOUTH;
            default:
                throw new IllegalArgumentException("invalid direction");
        }

    }

    private void moveForward(Coordinate currentPos, Direction currentDirection) {
        switch (currentDirection) {
            case NORTH:
                currentPos.changeVertically(1);
                break;
            case EAST:
                currentPos.changeHorizontally(1);
                break;
            case SOUTH:
                currentPos.changeVertically(-1);
                break;
            case WEST:
                currentPos.changeHorizontally(-1);
                break;
        }

        if (abs(currentPos.getPosX()) > MAX_GRID_SIZE || abs(currentPos.getPosY()) > MAX_GRID_SIZE) {
            throw new OutOfGridBoundaryException(currentPos.toString());
        }
    }

    private void logCurrentPostion(Coordinate pos, Direction direction) {
        LOGGER.info("Current position x: {}, y: {}, direction: {}", pos.getPosX(), pos.getPosY(), direction);
    }


    public ParkingInstructionParser getInstructionParser() {
        return instructionParser;
    }

    public void setInstructionParser(ParkingInstructionParser instructionParser) {
        this.instructionParser = instructionParser;
    }
}
