package sap.com.nomination.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Nomination;
import sap.com.nomination.pojo.Period;
import sap.com.nomination.pojo.Prize;
import sap.com.nomination.pojo.User;
import sap.com.nomination.repository.NominationRepository;
import sap.com.nomination.repository.PeriodRepository;
import sap.com.nomination.repository.PrizeRepository;
import sap.com.nomination.repository.UserRepository;
import sap.com.nomination.service.NominationService;
import sap.com.nomination.service.NominationViewService;
import sap.com.nomination.view.NominationView;

import java.util.*;

@Service
public class NominationViewServiceImpl implements NominationViewService {

    @Autowired
    NominationRepository nominationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    PeriodRepository periodRepository;

    @Override
    public Iterable<NominationView> findAll() {
        Iterable<Nomination> nominations = nominationRepository.findAll();
        List<NominationView> nominationsView = new ArrayList<>();
        for (Nomination nomination: nominations) {
            NominationView nv = new NominationView();
            nv.setId(nomination.getId());
            nv.setComment(nomination.getComment());
            nv.setVersion(nomination.getVersion());
            nv.setCreatedBy(nomination.getCreatedBy());
            nv.setCreatedDate(nomination.getCreatedDate());
            nv.setModifiedBy(nomination.getModifiedBy());
            nv.setModifiedDate(nomination.getModifiedDate());
            if (!nomination.getNomineeId().equals("") ){
                Optional<User> nominee = userRepository.findById(nomination.getNomineeId());
                nv.setNominee(nominee.get());
            }
            if (!nomination.getPrizeId().equals("") ){
                Optional<Prize> prize = prizeRepository.findById(nomination.getPrizeId());
                nv.setPrize(prize.get());
            }
            if (!nomination.getPeriodId().equals("") ){
                Optional<Period> period = periodRepository.findById(nomination.getPeriodId());
                nv.setPeriod(period.get());
            }
            if (!nomination.getNominatorId().equals("") ){
                Optional<User> nominator = userRepository.findById(nomination.getNominatorId());
                nv.setNominator(nominator.get());
            }
            nominationsView.add(nv);
        }
        return nominationsView;
    }

    @Override
    public Optional<NominationView> findById(UUID uuid) throws EntityNotFoundException {
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
        Nomination nomination = nominationData.get();
        NominationView nv = new NominationView();
        nv.setId(nomination.getId());
        nv.setNominee(nomineeData.get());
        nv.setPrize(prizeData.get());
        nv.setPeriod(periodData.get());
        nv.setNominator(nominatorData.get());
        nv.setComment(nomination.getComment());
        return Optional.of(nv);
    }


    @Override
    public NominationView create(NominationView nominationView) throws EntityNotFoundException{
        Optional<User> nominee = userRepository.findById(nominationView.getNominee().getId());
        if (!nominee.isPresent()){
            throw new EntityNotFoundException(nominationView.getNominee().getId(), "Nominee");
        }
        Optional<Prize> prize = prizeRepository.findById(nominationView.getPrize().getId());
        if (!prize.isPresent()){
            throw new EntityNotFoundException(nominationView.getPrize().getId(), "Prize");
        }
        Optional<Period> period = periodRepository.findById(nominationView.getPeriod().getId());
        if (!period.isPresent()){
            throw new EntityNotFoundException(nominationView.getPeriod().getId(), "Period");
        }
        Optional<User> nominator = userRepository.findById(nominationView.getNominator().getId());
        if (!nominator.isPresent()){
            throw new EntityNotFoundException(nominationView.getNominator().getId(), "Nominator");
        }
        Nomination _nomination = new Nomination();
        //_nomination.setCreatedBy(nominationView.getCreatedBy());
        _nomination.setCreatedDate(new Date());
        _nomination.setNomineeId(nominationView.getNominee().getId());
        _nomination.setPrizeId(nominationView.getPrize().getId());
        _nomination.setPeriodId(nominationView.getPeriod().getId());
        _nomination.setNominatorId(nominationView.getNominator().getId());
        _nomination.setComment(nominationView.getComment());
        Nomination result = nominationRepository.save(_nomination);
        return findById(result.getId()).get();
    }

    @Override
    @Transactional
    public NominationView update(NominationView nominationView) throws EntityNotFoundException {
        Optional<Nomination> nomination = nominationRepository.findById(nominationView.getId());
        if (!nomination.isPresent()){
            throw new EntityNotFoundException(nominationView.getId(), "Nomination");
        }
        Optional<User> nominee = userRepository.findById(nominationView.getNominee().getId());
        if (!nominee.isPresent()){
            throw new EntityNotFoundException(nominationView.getNominee().getId(), "Nominee");
        }
        Optional<Prize> prize = prizeRepository.findById(nominationView.getPrize().getId());
        if (!prize.isPresent()){
            throw new EntityNotFoundException(nominationView.getPrize().getId(), "Prize");
        }
        Optional<Period> period = periodRepository.findById(nominationView.getPeriod().getId());
        if (!period.isPresent()){
            throw new EntityNotFoundException(nominationView.getPeriod().getId(), "Period");
        }
        Optional<User> nominator = userRepository.findById(nominationView.getNominator().getId());
        if (!nominator.isPresent()){
            throw new EntityNotFoundException(nominationView.getNominator().getId(), "Nominator");
        }
        Nomination _nomination = new Nomination();
        _nomination.setId(nominationView.getId());
        _nomination.setVersion(nominationView.getVersion());
        //_nomination.setModifiedBy(nominationView.getModifiedBy());
        _nomination.setModifiedDate(new Date());
        _nomination.setNomineeId(nominationView.getNominee().getId());
        _nomination.setPrizeId(nominationView.getPrize().getId());
        _nomination.setPeriodId(nominationView.getPeriod().getId());
        _nomination.setNominatorId(nominationView.getNominator().getId());
        _nomination.setComment(nominationView.getComment());
        Nomination result = nominationRepository.save(_nomination);
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
