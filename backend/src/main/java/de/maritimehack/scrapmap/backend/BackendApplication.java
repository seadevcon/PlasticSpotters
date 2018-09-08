package de.maritimehack.scrapmap.backend;

import de.maritimehack.scrapmap.backend.controller.SeaRoutesController;
import de.maritimehack.scrapmap.backend.logic.PollutionPredictor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
/*
		SeaRoutesController seaRoutesController = new SeaRoutesController();
		seaRoutesController.getWeatherData(52.3844f, 4.94f);*/

		PollutionPredictor pollutionPredictor = new PollutionPredictor();
		//pollutionPredictor.calcPosition(52.3844f, 4.94f);
	}
}
