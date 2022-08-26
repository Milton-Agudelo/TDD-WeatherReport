package org.adaschool.tdd.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import org.adaschool.tdd.controller.weather.WeatherReportController;
import org.adaschool.tdd.controller.weather.dto.NearByWeatherReportsQueryDto;
import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.repository.WeatherReportRepository;
import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.adaschool.tdd.service.MongoWeatherService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class WeatherReportControllerTest {

	@Mock
	private WeatherReportController weatherReportController;

	WeatherReportDto weatherReportDto;

	WeatherReport weatherReport;

	@BeforeAll()
	public void setup() {
		MockitoAnnotations.openMocks(this);
		double lat = 4.7110;
		double lng = 74.0721;
		GeoLocation location = new GeoLocation( lat, lng );
		weatherReportDto = new WeatherReportDto(location, 35f, 22f, "tester");
		weatherReport = new WeatherReport(weatherReportDto);
	}

	@Test
	void createWeatherReportSuccessfully() {
		// Arrange
		when(weatherReportController.create(weatherReportDto)).thenReturn(weatherReport);

		// Act
		WeatherReport weatherReport = weatherReportController.create(weatherReportDto);

		// Assert
		//assertThat( weatherReport, isA(WeatherReport.class));
		verify(weatherReportController).create(any(WeatherReportDto.class));
	}

	@Test
	void findAllWeatherReportsSuccessfully() {
		// Arrange
		when(weatherReportController.findAll()).thenReturn(Arrays.asList(weatherReport));

		// Act
		List<WeatherReport> weatherReport = weatherReportController.findAll();

		// Assert
		assertThat( weatherReport.get(0), isA(WeatherReport.class));
	}

	@Test
	void findByIdWeatherReportSuccessfully() {
		// Arrange
		when(weatherReportController.findById(weatherReport.getId())).thenReturn(weatherReport);

		// Act
		WeatherReport returnedWeatherReport = weatherReportController.findById(weatherReport.getId());

		// Assert
		verify(weatherReportController).findById(weatherReport.getId());
		assertThat( returnedWeatherReport, isA(WeatherReport.class));

	}

	@Test
	void findNearLocationWeatherReportSuccessfully() {
		// Arrange
		NearByWeatherReportsQueryDto query = new NearByWeatherReportsQueryDto(weatherReport.getGeoLocation(), 100);
		when(weatherReportController.findNearByReports(query)).thenReturn(Arrays.asList(weatherReport));

		// Act
		List<WeatherReport> weatherReport = weatherReportController.findNearByReports(query);

		// Assert
		assertThat( weatherReport.get(0), isA(WeatherReport.class));

	}

	@Test
	void findWeatherReportsByNameWeatherReportSuccessfully() {
		// Arrange
		when(weatherReportController.findByReporterId("tester")).thenReturn(Arrays.asList(weatherReport));

		// Act
		List<WeatherReport> weatherReport = weatherReportController.findByReporterId("tester");

		// Assert
		assertThat( weatherReport.get(0), isA(WeatherReport.class));

	}

}
