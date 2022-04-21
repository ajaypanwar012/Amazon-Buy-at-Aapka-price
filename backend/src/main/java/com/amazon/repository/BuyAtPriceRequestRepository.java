package com.amazon.repository;

import com.amazon.models.BuyAtPriceRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BuyAtPriceRequestRepository extends CrudRepository<BuyAtPriceRequest, Long> {

    Iterable<BuyAtPriceRequest> findAllByStatus(String status);
}