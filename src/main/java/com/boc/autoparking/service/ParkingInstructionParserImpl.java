package com.boc.autoparking.service;

import com.boc.autoparking.model.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ParkingInstructionParserImpl implements ParkingInstructionParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingInstructionParserImpl.class);

    private static Pattern INSTRUCTION_PATTERN;

    static {
        INSTRUCTION_PATTERN = Pattern.compile("([0-9]+),([0-9]+):([FLR]+)");
    }


    @Override
    public boolean isInstructionValid(String instruction) {
        LOGGER.debug("Check instruction {} validity", instruction);

        Matcher matcher = INSTRUCTION_PATTERN.matcher(instruction);

        return matcher.matches();
    }

    @Override
    public List<Character> getMovementInstructions(String instruction) {
        Matcher matcher = INSTRUCTION_PATTERN.matcher(instruction);

        if (matcher.matches()) {
            return matcher.group(3).chars().mapToObj(e->(char)e).collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("invalid instruction: " + instruction);
        }

    }

    @Override
    public Coordinate getInitialPos(String instruction) {
        Matcher matcher = INSTRUCTION_PATTERN.matcher(instruction);

        if (matcher.matches()) {
            int posX = Integer.parseInt(matcher.group(1));
            int posY = Integer.parseInt(matcher.group(2));
            return new Coordinate(posX, posY);
        } else {
            throw new IllegalArgumentException("invalid instruction: " + instruction);
        }
    }


}
