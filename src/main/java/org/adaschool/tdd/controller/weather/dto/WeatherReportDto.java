package org.adaschool.tdd.controller.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.adaschool.tdd.repository.document.GeoLocation;

import java.util.Date;

@Getter
@AllArgsConstructor
public class WeatherReportDto {

    private GeoLocation geoLocation;

    private double temperature;

    private double humidity;

    private String reporter;

    private Date created;

    public WeatherReportDto( GeoLocation geoLocation, double temperature, double humidity, String reporter)
    {
        this.geoLocation = geoLocation;
        this.temperature = temperature;
        this.humidity = humidity;
        this.reporter = reporter;
    }

    public GeoLocation getGeoLocation()
    {
        return geoLocation;
    }

    public double getTemperature()
    {
        return temperature;
    }

    public double getHumidity()
    {
        return humidity;
    }

    public String getReporter()
    {
        return reporter;
    }// */

}
