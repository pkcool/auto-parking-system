package com.boc.autoparking.service;

import com.boc.autoparking.model.Coordinate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParkingInstructionParserImplTest {

    private ParkingInstructionParser instructionParser;
    private String instruction;

    @Before
    public void setUp() throws Exception {
        instructionParser = new ParkingInstructionParserImpl();

        instruction = "5,5:RFLFRFLF";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_valid_instruction() {
        Boolean isInstructionValid = instructionParser.isInstructionValid(instruction);

        assertThat(isInstructionValid).isTrue();
    }

    @Test
    public void test_invalid_initial_pos() {
        String instruction = "55:RFLFRFLF";

        Boolean isInstructionValid = instructionParser.isInstructionValid(instruction);

        assertThat(isInstructionValid).isFalse();
    }

    @Test
    public void test_invalid_moves() {
        String instruction = "5,5:ABCFLFRFLF";

        Boolean isInstructionValid = instructionParser.isInstructionValid(instruction);

        assertThat(isInstructionValid).isFalse();
    }

    @Test
    public void test_invalid_moves_lowercase() {
        String instruction = "5,5:flr";

        Boolean isInstructionValid = instructionParser.isInstructionValid(instruction);

        assertThat(isInstructionValid).isFalse();
    }

    @Test
    public void exception_expected_without_movement() {
        String instruction = "5,5:";

        Boolean isInstructionValid = instructionParser.isInstructionValid(instruction);

        assertThat(isInstructionValid).isFalse();
    }


    @Test
    public void test_extract_moves() {
        List<Character> moves = instructionParser.getMovementInstructions(instruction);

        assertThat(moves).isEqualTo(Arrays.asList('R', 'F', 'L', 'F', 'R', 'F', 'L', 'F'));
    }

    @Test
    public void test_get_initial_pos() {
        Coordinate initPos = instructionParser.getInitialPos(instruction);

        assertThat(initPos.getPosX()).isEqualTo(5);
        assertThat(initPos.getPosY()).isEqualTo(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exception_expected_on_invalid_instruction_for_getting_moves () {
        String instruction = "5,5:ABCFLFRFLF";

        List<Character> moves = instructionParser.getMovementInstructions(instruction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exception_expected_on_invalid_instruction_for_getting_init_pos () {
        String instruction = "5.5,5:FLFRFLF";

        instructionParser.getInitialPos(instruction);
    }

}