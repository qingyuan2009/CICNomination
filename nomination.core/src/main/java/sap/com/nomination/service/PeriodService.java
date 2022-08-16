package sap.com.nomination.service;

import sap.com.nomination.pojo.Period;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PeriodService {

    Iterable<Period> findAll();

    Optional<Period> findById(UUID uuid);

    Period create(Period period);

    Period update(Period period);

    void deleteById(UUID uuid);

}
