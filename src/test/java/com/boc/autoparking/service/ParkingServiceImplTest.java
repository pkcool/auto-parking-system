package com.boc.autoparking.service;

import com.boc.autoparking.exception.OutOfGridBoundaryException;
import com.boc.autoparking.model.Coordinate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParkingServiceImplTest {

    private ParkingService parkingService;


    @Before
    public void setUp() {
        ParkingInstructionParser instructionParser = new ParkingInstructionParserImpl();

        parkingService = new ParkingServiceImpl(instructionParser);
    }

    @After
    public void tearDown()  {
    }

    @Test
    public void find_Parking_Slot_Successfully_1() {
        String instruction = "5,5:RFLFRFLF";

        Coordinate actualPos = parkingService.findParkingSlot(instruction);

        assertThat(actualPos.getPosX()).isEqualTo(7);
        assertThat(actualPos.getPosY()).isEqualTo(7);
    }

    @Test
    public void find_Parking_Slot_Successfully_2() {
        String instruction = "6,6:FFLFFLFFLFF";

        Coordinate actualPos = parkingService.findParkingSlot(instruction);

        assertThat(actualPos.getPosX()).isEqualTo(6);
        assertThat(actualPos.getPosY()).isEqualTo(6);
    }

    @Test
    public void find_Parking_Slot_Successfully_3() {
        String instruction = "5,5:FLFLFFRFFF";

        Coordinate actualPos = parkingService.findParkingSlot(instruction);

        assertThat(actualPos.getPosX()).isEqualTo(1);
        assertThat(actualPos.getPosY()).isEqualTo(4);
    }

    @Test(expected = OutOfGridBoundaryException.class)
    public void exception_on_moving_out_of_boundary() {
        String instruction = "5,5:FFFFFFFFFFF";

        parkingService.findParkingSlot(instruction);
    }
}