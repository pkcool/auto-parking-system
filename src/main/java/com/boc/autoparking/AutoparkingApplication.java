package com.boc.autoparking;

import com.boc.autoparking.model.Coordinate;
import com.boc.autoparking.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class AutoparkingApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoparkingApplication.class);

	@Autowired
	private ParkingService parkingService;


	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AutoparkingApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}

	//access command line arguments
	@Override
	public void run(String... args) {

        LOGGER.info("Auto parking application started with instruction: {}.", Arrays.toString(args));

        if (args.length != 1) {
            LOGGER.error("Invalid argument: {}, Please enter an argument, i.e. 5,5:LFLF", Arrays.toString(args));
            return;
        }

        Coordinate parkingPos = parkingService.findParkingSlot(args[0]);

        LOGGER.info("Located parking slot, X: {}, Y: {}", parkingPos.getPosX(), parkingPos.getPosY());

	}
}
