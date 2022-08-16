package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Prize;
import sap.com.nomination.repository.PrizeRepository;
import sap.com.nomination.service.PrizeService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    PrizeRepository prizeRepository;

    @Override
    public Iterable<Prize> findAll() {
        return prizeRepository.findAll();
    }

    @Override
    public Optional<Prize> findById(UUID uuid) throws EntityNotFoundException {
        Optional<Prize> prize = prizeRepository.findById(uuid);
        if (!prize.isPresent()){
            throw new EntityNotFoundException(uuid, "Prize");
        }
        return prize;
    }

    @Override
    public Prize create(Prize prize) {
        prize.setId(UUID.randomUUID());
        prize.setCreatedDate(new Date());
        //_prize.setCreatedBy("");
        return  prizeRepository.save(prize);
    }

    @Override
    public Prize update(Prize prize) throws EntityNotFoundException {
        Optional<Prize> prizeData = prizeRepository.findById(prize.getId());
        if (!prizeData.isPresent()){
            throw new EntityNotFoundException(prize.getId(), "Prize");
        }
        prize.setModifiedDate(new Date());
        //prize.setModifiedBy("");
        return  prizeRepository.save(prize);
    }

    @Override
    public void deleteById(UUID uuid) throws EntityNotFoundException{
        Optional<Prize> prizeData = prizeRepository.findById(uuid);
        if (!prizeData.isPresent()){
            throw new EntityNotFoundException(uuid, "Prize");
        }
        prizeRepository.deleteById(uuid);
    }

}
