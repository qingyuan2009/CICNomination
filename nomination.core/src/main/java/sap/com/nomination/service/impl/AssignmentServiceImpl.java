package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Period;
import sap.com.nomination.pojo.Prize;
import sap.com.nomination.pojo.Assignment;
import sap.com.nomination.repository.AssignmentRepository;
import sap.com.nomination.repository.PeriodRepository;
import sap.com.nomination.repository.PrizeRepository;
import sap.com.nomination.service.AssignmentService;
import java.util.*;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Override
    public Iterable<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    @Override
    public Optional<Assignment> findById(UUID uuid) throws EntityNotFoundException {

        Optional<Assignment> assignmentData = assignmentRepository.findById(uuid);
        if (!assignmentData.isPresent()){
            throw new EntityNotFoundException(uuid, "Assignment");
        }
        Optional<Period> periodData = periodRepository.findById(assignmentData.get().getPeriodId());
        if (!periodData.isPresent()){
            throw new EntityNotFoundException(assignmentData.get().getPeriodId(), "Period");
        }
        Optional<Prize> prizeData = prizeRepository.findById(assignmentData.get().getPrizeId());
        if (!prizeData.isPresent()){
            throw new EntityNotFoundException(assignmentData.get().getPrizeId(), "Prize");
        }
        return assignmentData;
    }

    @Override
    public Assignment create(Assignment assignment) throws EntityNotFoundException{
        Optional<Period> period = periodRepository.findById(assignment.getPeriodId());
        if (!period.isPresent()){
            throw new EntityNotFoundException(assignment.getPeriodId(), "Period");
        }
        Optional<Prize> prize = prizeRepository.findById(assignment.getPrizeId());
        if (!prize.isPresent()){
            throw new EntityNotFoundException(assignment.getPrizeId(), "Prize");
        }
        assignment.setId(UUID.randomUUID());
        //assignment.setCreatedBy(assignment.getCreatedBy());
        assignment.setCreatedDate(new Date());
        Assignment result = assignmentRepository.save(assignment);
        return findById(result.getId()).get();
    }

    @Override
    @Transactional
    public Assignment update(Assignment assignment) throws EntityNotFoundException {
        Optional<Period> period = periodRepository.findById(assignment.getPeriodId());
        if (!period.isPresent()){
            throw new EntityNotFoundException(assignment.getPeriodId(), "Period");
        }
        Optional<Prize> prize = prizeRepository.findById(assignment.getPrizeId());
        if (!prize.isPresent()){
            throw new EntityNotFoundException(assignment.getPrizeId(), "Prize");
        }
        Optional<Assignment> assignmentData = assignmentRepository.findById(assignment.getId());
        if (!assignmentData.isPresent()){
            throw new EntityNotFoundException(assignment.getId(), "Assignment");
        }
        //assignment.setModifiedBy(assignment.getModifiedBy());
        assignment.setModifiedDate(new Date());
        Assignment result = assignmentRepository.save(assignment);
        return findById(result.getId()).get();
    }

    @Override
    public void deleteById(UUID uuid) {
        Optional<Assignment> assignmentData = assignmentRepository.findById(uuid);
        if (!assignmentData.isPresent()){
            throw new EntityNotFoundException(uuid, "Assignment");
        }
        assignmentRepository.deleteById(uuid);
    }

}
