package sap.com.nomination.service;

import sap.com.nomination.pojo.Prize;

import java.util.Optional;
import java.util.UUID;

public interface PrizeService {

    Iterable<Prize> findAll();

    Optional<Prize> findById(UUID uuid);

    Prize create(Prize prize);

    Prize update(Prize prize);

    void deleteById(UUID uuid);
}
