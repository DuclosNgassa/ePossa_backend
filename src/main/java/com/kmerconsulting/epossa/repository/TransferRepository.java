package com.kmerconsulting.epossa.repository;

import com.kmerconsulting.epossa.model.Transfer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    Optional<List<Transfer>> findBySender(String sender);

    Optional<List<Transfer>> findByReceiver(String receiver);
}
