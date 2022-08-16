package sap.com.nomination.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Period;
import sap.com.nomination.pojo.User;
import sap.com.nomination.pojo.Prize;
import sap.com.nomination.pojo.Nomination;
import sap.com.nomination.repository.PeriodRepository;
import sap.com.nomination.repository.UserRepository;
import sap.com.nomination.repository.PrizeRepository;
import sap.com.nomination.repository.NominationRepository;
import sap.com.nomination.service.NominationService;


import java.util.*;

@Service
public class NominationServiceImpl implements NominationService {

    @Autowired
    NominationRepository nominationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    PeriodRepository periodRepository;

    @Override
    public Iterable<Nomination> findAll() {
       return nominationRepository.findAll();
    }

    @Override
    public Optional<Nomination> findById(UUID uuid) throws EntityNotFoundException {
        Optional<Nomination> nominationData = nominationRepository.findById(uuid);
        if (!nominationData.isPresent()){
            throw new EntityNotFoundException(uuid, "Nomination");
        }
        Optional<User> nomineeData = userRepository.findById(nominationData.get().getNomineeId());
        if (!nomineeData.isPresent()){
            throw new EntityNotFoundException(nominationData.get().getNomineeId(), "Nominee");
        }
        Optional<Prize> prizeData = prizeRepository.findById(nominationData.get().getPrizeId());
        if (!prizeData.isPresent()){
            throw new EntityNotFoundException(nominationData.get().getPrizeId(), "Prize");
        }
        Optional<Period> periodData = periodRepository.findById(nominationData.get().getPeriodId());
        if (!periodData.isPresent()){
            throw new EntityNotFoundException(nominationData.get().getPeriodId(), "Period");
        }
        Optional<User> nominatorData = userRepository.findById(nominationData.get().getNominatorId());
        if (!nominatorData.isPresent()){
            throw new EntityNotFoundException(nominationData.get().getNominatorId(), "Nominator");
        }
        return nominationData;
    }

    @Override
    public Nomination create(Nomination nomination) throws EntityNotFoundException{
        Optional<User> nominee = userRepository.findById(nomination.getNomineeId());
        if (!nominee.isPresent()){
            throw new EntityNotFoundException(nomination.getNomineeId(), "Nominee");
        }
        Optional<Prize> prize = prizeRepository.findById(nomination.getPrizeId());
        if (!prize.isPresent()){
            throw new EntityNotFoundException(nomination.getPrizeId(), "Prize");
        }
        Optional<Period> period = periodRepository.findById(nomination.getPeriodId());
        if (!period.isPresent()){
            throw new EntityNotFoundException(nomination.getPeriodId(), "Period");
        }
        Optional<User> nominator = userRepository.findById(nomination.getNominatorId());
        if (!nominator.isPresent()){
            throw new EntityNotFoundException(nomination.getNominatorId(), "Nominator");
        }
        nomination.setId(UUID.randomUUID());
        //nomination.setCreatedBy(nomination.getCreatedBy());
        nomination.setCreatedDate(new Date());
        Nomination result = nominationRepository.save(nomination);
        return findById(result.getId()).get();
    }

    @Override
    @Transactional
    public Nomination update(Nomination nomination) throws EntityNotFoundException {
        Optional<Nomination> nominationData = nominationRepository.findById(nomination.getId());
        if (!nominationData.isPresent()){
            throw new EntityNotFoundException(nomination.getId(), "Nomination");
        }
        Optional<User> nominee = userRepository.findById(nomination.getNomineeId());
        if (!nominee.isPresent()){
            throw new EntityNotFoundException(nomination.getNomineeId(), "Nominee");
        }
        Optional<Prize> prize = prizeRepository.findById(nomination.getPrizeId());
        if (!prize.isPresent()){
            throw new EntityNotFoundException(nomination.getPrizeId(), "Prize");
        }
        Optional<Period> period = periodRepository.findById(nomination.getPeriodId());
        if (!period.isPresent()){
            throw new EntityNotFoundException(nomination.getPeriodId(), "Period");
        }
        Optional<User> nominator = userRepository.findById(nomination.getNominatorId());
        if (!nominator.isPresent()){
            throw new EntityNotFoundException(nomination.getNominatorId(), "Nominator");
        }
        //nomination.setModifiedBy(nomination.getModifiedBy());
        nomination.setModifiedDate(new Date());
        Nomination result = nominationRepository.save(nomination);
        return findById(result.getId()).get();
    }

    @Override
    public void deleteById(UUID uuid) {
        Optional<Nomination> nominationData = nominationRepository.findById(uuid);
        if (!nominationData.isPresent()){
            throw new EntityNotFoundException(uuid, "Nomination");
        }
        nominationRepository.deleteById(uuid);
    }

}
