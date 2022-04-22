package com.amazon.service;

import com.amazon.models.BuyAtPriceRequest;
import com.amazon.models.BuyAtPriceRequestStatus;
import com.amazon.repository.BuyAtPriceRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class BuyAtPriceRequestServiceImpl implements BuyAtPriceRequestService {

    private BuyAtPriceRequestRepository buyAtPriceRequestRepository;

    public BuyAtPriceRequestServiceImpl(BuyAtPriceRequestRepository buyAtPriceRequestRepository) {
        this.buyAtPriceRequestRepository = buyAtPriceRequestRepository;
    }

    @Override
    public Iterable<BuyAtPriceRequest> getAllActiveBuyAtPriceRequests() {
        return buyAtPriceRequestRepository.findAllByStatus(BuyAtPriceRequestStatus.ACTIVE.name());
    }

    @Override
    public BuyAtPriceRequest create(BuyAtPriceRequest buyAtPriceRequest) {
        buyAtPriceRequest.setDateCreated(LocalDate.now());
        BuyAtPriceRequest buyAtPriceRequestExisting = buyAtPriceRequestRepository.findFirstByProductAndStatus(buyAtPriceRequest.getProduct(),
                BuyAtPriceRequestStatus.ACTIVE.name());
        if (buyAtPriceRequestExisting != null) {
            buyAtPriceRequestExisting.setPrice(buyAtPriceRequest.getPrice());
            buyAtPriceRequestExisting.setQuantity(buyAtPriceRequest.getQuantity());
            return this.buyAtPriceRequestRepository.save(buyAtPriceRequestExisting);
        }
        return this.buyAtPriceRequestRepository.save(buyAtPriceRequest);
    }

    @Override
    public void update(BuyAtPriceRequest buyAtPriceRequest) {
        buyAtPriceRequest.setDateCreated(LocalDate.now());
        this.buyAtPriceRequestRepository.save(buyAtPriceRequest);
    }
}