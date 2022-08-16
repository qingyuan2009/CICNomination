package sap.com.nomination.repository;

import org.springframework.data.repository.CrudRepository;
import sap.com.nomination.pojo.Period;

import java.util.UUID;

public interface PeriodRepository extends CrudRepository<Period, UUID> {
}
