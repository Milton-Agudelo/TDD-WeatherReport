package org.adaschool.tdd.service;

import java.util.Optional;
import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.exception.WeatherReportNotFoundException;
import org.adaschool.tdd.repository.WeatherReportRepository;
import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoWeatherService implements WeatherService {

    @Autowired
    private WeatherReportRepository repository;

    @Override
    public WeatherReport report( WeatherReportDto weatherReportDto ) {
        return repository.save(new WeatherReport(weatherReportDto));
    }

    @Override
    public WeatherReport findById( String id )
    {
        Optional<WeatherReport> weatherReport = repository.findById(id);
        if (weatherReport.equals(Optional.empty())) {
            throw new WeatherReportNotFoundException();
        }
        return weatherReport.get();
    }

    @Override
    public List<WeatherReport> findAll() {
        return repository.findAll();
    }

    @Override
    public List<WeatherReport> findNearLocation( GeoLocation geoLocation, float distanceRangeInMeters )
    {
        return null;
    }

    @Override
    public List<WeatherReport> findWeatherReportsByName( String reporter )
    {
        return null;
    }
}
