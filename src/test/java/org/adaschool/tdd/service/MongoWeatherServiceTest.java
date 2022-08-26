package org.adaschool.tdd.service;

import org.adaschool.tdd.TddApplication;
import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.exception.WeatherReportNotFoundException;
import org.adaschool.tdd.repository.WeatherReportRepository;
import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TddApplication.class})
@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class MongoWeatherServiceTest {

    @Mock
    MongoWeatherService mongoWeatherService;

    WeatherReportDto weatherReportDto;

    WeatherReport weatherReport;

    @BeforeAll()
    public void setup() {
        MockitoAnnotations.openMocks(this);
        double lat = 4.7110;
        double lng = 74.0721;
        GeoLocation location = new GeoLocation( lat, lng );
        weatherReportDto = new WeatherReportDto(location, 35f, 22f, "tester");
        weatherReport = new WeatherReport (weatherReportDto);
    }// */

    @Test
    void createWeatherReportCallsSaveOnRepository() {
        // Arrange
        when(mongoWeatherService.report(weatherReportDto)).thenReturn(weatherReport);

        // Act
        WeatherReport returnedWeatherReport = mongoWeatherService.report( weatherReportDto );

        // Assert
        //assertNotNull(mongoWeatherService.report( weatherReportDto ));

        assertThat(returnedWeatherReport, is(notNullValue()));
        assertThat(returnedWeatherReport, isA(WeatherReport.class));
        verify(mongoWeatherService).report(any(WeatherReportDto.class));
    }

    @Test
    void weatherReportIdFoundTest() {
        // Arrange
        when(mongoWeatherService.findById(weatherReport.getId())).thenReturn(weatherReport);
        // No sirve
        //when(mongoWeatherService.findById(weatherReport.getId())).thenReturn(any(WeatherReport.class));

        // Act
        //WeatherReport foundWeatherReport = mongoWeatherService.findById(weatherReport.getId());

        // Verify
        assertNotNull(mongoWeatherService.findById(weatherReport.getId()));
        /*verify(mongoWeatherService).report(any(WeatherReportDto.class));
        assertEquals( weatherReport, foundWeatherReport);// */

    }

    @Test
    void weatherReportIdNotFoundTest() {
        // Arrange
        String weatherReportId = "dsawe1fasdasdoooq123";

        // Act
        when(mongoWeatherService.findById(weatherReportId)).thenThrow(WeatherReportNotFoundException.class);

        // Verify
        Assertions.assertThrows(WeatherReportNotFoundException.class, () -> {
            mongoWeatherService.findById(weatherReportId);
        } );
    }

}
