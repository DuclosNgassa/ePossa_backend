package com.kmerconsulting.epossa.service;

import com.kmerconsulting.epossa.model.Transfer;
import com.kmerconsulting.epossa.repository.TransferRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    public Transfer save(Transfer transfer) {
        transfer.setCreated_at(LocalDateTime.now());
        return transferRepository.save(transfer);
    }

    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    public Transfer findById(Long id) {
        return transferRepository.findById(id).orElse(null);
    }

    public List<Transfer> findBySender(String sender) {
        return transferRepository.findBySender(sender).orElse(null);
    }

    public List<Transfer> findByReceiver(String receiver) {
        return transferRepository.findByReceiver(receiver).orElse(null);
    }

    public void delete(Long id) {
        transferRepository.deleteById(id);
    }

}
