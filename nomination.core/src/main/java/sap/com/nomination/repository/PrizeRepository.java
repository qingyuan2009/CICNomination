package sap.com.nomination.repository;

import org.springframework.data.repository.CrudRepository;
import sap.com.nomination.pojo.Prize;

import java.util.UUID;

public interface PrizeRepository extends CrudRepository<Prize, UUID> {
}
