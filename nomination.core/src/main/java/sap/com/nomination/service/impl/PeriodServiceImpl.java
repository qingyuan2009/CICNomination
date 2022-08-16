package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Period;
import sap.com.nomination.repository.PeriodRepository;
import sap.com.nomination.service.PeriodService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    PeriodRepository periodRepository;

    @Override
    public Iterable<Period> findAll() {
        return periodRepository.findAll();
    }

    @Override
    public Optional<Period> findById(UUID uuid) throws EntityNotFoundException {
        Optional<Period> period = periodRepository.findById(uuid);
        if (!period.isPresent()){
            throw new EntityNotFoundException(uuid, "Period");
        }
        return period;
    }

    @Override
    public Period create(Period period) {
        period.setId(UUID.randomUUID());
        period.setCreatedDate(new Date());
        //_period.setCreatedBy("");
        return  periodRepository.save(period);
    }

    @Override
    public Period update(Period period) throws EntityNotFoundException {
        Optional<Period> periodData = periodRepository.findById(period.getId());
        if (!periodData.isPresent()){
            throw new EntityNotFoundException(period.getId(), "Period");
        }
        period.setModifiedDate(new Date());
        //period.setModifiedBy("");
        return  periodRepository.save(period);
    }

    @Override
    public void deleteById(UUID uuid) throws EntityNotFoundException{
        Optional<Period> periodData = periodRepository.findById(uuid);
        if (!periodData.isPresent()){
            throw new EntityNotFoundException(uuid, "Period");
        }
        periodRepository.deleteById(uuid);
    }

}
