package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.domain.Location;
import com.peploleum.insight.graphy.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl {
    private final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Long save(String name, String idMongo) {
        log.debug("Request to save Location : {}");
        Location location = new Location();

        location.setLocationName(name);
        location.setIdInsight(idMongo);

        location = locationRepository.save(location);
        Long result = location.getId();
        this.log.info("Vertex location saved: " + location.getId());

        return result;
    }

    public Optional<Location> findOne(Long id) {
        log.debug("Request to get location : {}", id);
        return locationRepository.findById(id);
    }

    /**
     * Delete the location by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete location : {}", id);
        locationRepository.deleteById(id);
    }

}
