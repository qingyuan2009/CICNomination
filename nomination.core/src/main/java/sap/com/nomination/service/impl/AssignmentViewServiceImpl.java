package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Assignment;
import sap.com.nomination.pojo.Period;
import sap.com.nomination.pojo.Prize;
import sap.com.nomination.repository.AssignmentRepository;
import sap.com.nomination.repository.PeriodRepository;
import sap.com.nomination.repository.PrizeRepository;
import sap.com.nomination.service.AssignmentService;
import sap.com.nomination.service.AssignmentViewService;
import sap.com.nomination.view.AssignmentView;

import java.util.*;

@Service
public class AssignmentViewServiceImpl implements AssignmentViewService {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Override
    public Iterable<AssignmentView> findAll() {
        Iterable<Assignment> assignments = assignmentRepository.findAll();
        List<AssignmentView> assignmentsView = new ArrayList<>();
        for (Assignment assignment: assignments) {
            AssignmentView av = new AssignmentView();
            av.setId(assignment.getId());
            av.setVersion(assignment.getVersion());
            av.setCreatedBy(assignment.getCreatedBy());
            av.setCreatedDate(assignment.getCreatedDate());
            av.setModifiedBy(assignment.getModifiedBy());
            av.setModifiedDate(assignment.getModifiedDate());
            if (!assignment.getPeriodId().equals("") ){
                Optional<Period> period = periodRepository.findById(assignment.getPeriodId());
                av.setPeriod(period.get());
            }
            if (!assignment.getPrizeId().equals("") ){
                Optional<Prize> prize = prizeRepository.findById(assignment.getPrizeId());
                av.setPrize(prize.get());
            }
            assignmentsView.add(av);
        }
        return assignmentsView;
    }

    @Override
    public Optional<AssignmentView> findById(UUID uuid) throws EntityNotFoundException {

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
        Assignment assignment = assignmentData.get();
        AssignmentView av = new AssignmentView();
        av.setId(assignment.getId());
        av.setVersion(assignment.getVersion());
        av.setCreatedBy(assignment.getCreatedBy());
        av.setCreatedDate(assignment.getCreatedDate());
        av.setModifiedBy(assignment.getModifiedBy());
        av.setModifiedDate(assignment.getModifiedDate());
        av.setPeriod(periodData.get());
        av.setPrize(prizeData.get());
        return Optional.of(av);
    }

    @Override
    public AssignmentView create(AssignmentView assignmentView) throws EntityNotFoundException{
        Optional<Period> period = periodRepository.findById(assignmentView.getPeriod().getId());
        if (!period.isPresent()){
            throw new EntityNotFoundException(assignmentView.getPeriod().getId(), "Period");
        }
        Optional<Prize> prize = prizeRepository.findById(assignmentView.getPrize().getId());
        if (!prize.isPresent()){
            throw new EntityNotFoundException(assignmentView.getPrize().getId(), "Prize");
        }
        Assignment _assignment = new Assignment();
        //_assignment.setCreatedBy(assignmentView.getCreatedBy());
        _assignment.setCreatedDate(new Date());
        _assignment.setPeriodId(assignmentView.getPeriod().getId());
        _assignment.setPrizeId(assignmentView.getPrize().getId());
        Assignment result = assignmentRepository.save(_assignment);
        return findById(result.getId()).get();
    }

    @Override
    @Transactional
    public AssignmentView update(AssignmentView assignmentView) throws EntityNotFoundException {
        Optional<Period> period = periodRepository.findById(assignmentView.getPeriod().getId());
        if (!period.isPresent()){
            throw new EntityNotFoundException(assignmentView.getPeriod().getId(), "Period");
        }
        Optional<Prize> prize = prizeRepository.findById(assignmentView.getPrize().getId());
        if (!prize.isPresent()){
            throw new EntityNotFoundException(assignmentView.getPrize().getId(), "Prize");
        }
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentView.getId());
        if (!assignment.isPresent()){
            throw new EntityNotFoundException(assignmentView.getId(), "Assignment");
        }
        Assignment _assignment = new Assignment();
        _assignment.setId(assignmentView.getId());
        _assignment.setVersion(assignmentView.getVersion());
        //_assignment.setModifiedBy(assignmentView.getModifiedBy());
        _assignment.setModifiedDate(new Date());
        _assignment.setPeriodId(assignmentView.getPeriod().getId());
        _assignment.setPrizeId(assignmentView.getPrize().getId());
        Assignment result = assignmentRepository.save(_assignment);
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
