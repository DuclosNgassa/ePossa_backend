package com.kmerconsulting.epossa.controller;

import com.kmerconsulting.epossa.dao.TransferService;
import com.kmerconsulting.epossa.dao.UserService;
import com.kmerconsulting.epossa.model.Transfer;
import com.kmerconsulting.epossa.model.TransferOperation;
import com.kmerconsulting.epossa.model.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransferController {

    @Autowired
    TransferService transferService;
    @Autowired
    UserService userService;

    @PostMapping("/transfers")
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer) {
        //TODO implements validator sender and receiver must be different

        String sender = transfer.getSender();
        User userSender = userService.findByPhone(sender);
        if(userSender == null){
            return null; // Fehlermeldung Sender existiert nicht
        }

        String receiver = transfer.getReceiver();
        User userReceiver = userService.findByPhone(receiver);
        if(userReceiver == null){
            return null; // Fehlermeldung Receiver existiert nicht
        }

        updateUserForTransfer(userSender, transfer.getAmount(), TransferOperation.SUBTRACT);
        updateUserForTransfer(userReceiver, transfer.getAmount(), TransferOperation.ADD);

        return transferService.save(transfer);
    }

    @GetMapping("/transfers")
    public List<Transfer> getAllTransfers() {
        return transferService.findAll();
    }

    @GetMapping("/transfers/{id}")
    public ResponseEntity<Transfer> getTransferById(@PathVariable(value = "id") Long id) {
        Transfer transfer = transferService.findById(id);
        if (transfer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transfer);
    }

    @GetMapping("/transfers/sender/{sender}")
    public List<Transfer> getTransferBySender(@PathVariable(value = "sender") Long sender) {
        return transferService.findBySender(sender);
    }

    @GetMapping("/transfers/receiver/{receiver}")
    public List<Transfer> getTransferByReceiver(@PathVariable(value = "receiver") Long receiver) {
        return transferService.findByReceiver(receiver);
    }

    @GetMapping("/transfers/user/{user}")
    public List<Transfer> getTransferByUser(@PathVariable(value = "user") Long user) {
        List<Transfer> sentTransfers = transferService.findBySender(user);
        List<Transfer> receivedTransfers = transferService.findByReceiver(user);

        List<Transfer> sentAndReceivedTransfers = new ArrayList<>();
        if (sentTransfers != null) {
            sentAndReceivedTransfers.addAll(sentTransfers);
        }
        if (receivedTransfers != null) {
            sentAndReceivedTransfers.addAll(receivedTransfers);
        }

        return sentAndReceivedTransfers;
    }

    @PutMapping("/transfers/{id}")
    public ResponseEntity<Transfer> updateTransfer(@PathVariable(value = "id") Long id, @Valid @RequestBody Transfer transferDetail) {
        //TODO überlegen ob dies sinn Macht -> Besser wäre vielleicht eine Reklamierung vom Sender über ein Formular
        Transfer transfer = transferService.findById(id);
        if (transfer == null) {
            return ResponseEntity.notFound().build();
        }

        transfer.setSender(transferDetail.getSender());
        transfer.setReceiver(transferDetail.getReceiver());
        transfer.setAmount(transferDetail.getAmount());
        transfer.setDescription(transferDetail.getDescription());

        Transfer updatedTransfer = transferService.save(transfer);

        return ResponseEntity.ok().body(updatedTransfer);
    }

    @DeleteMapping("/transfers/{id}")
    public ResponseEntity<User> deleteTransfer(@PathVariable(value = "id") Long id) {
        Transfer transfer = transferService.findById(id);
        if (transfer == null) {
            return ResponseEntity.notFound().build();
        }
        transferService.delete(id);

        return ResponseEntity.ok().build();
    }

    private void updateUserForTransfer(User user, BigDecimal amount, TransferOperation operation){
        BigDecimal newBalance = BigDecimal.ZERO;

        if(TransferOperation.ADD.equals(operation)) {
            newBalance = user.getBalance().add(amount);
        } else if(TransferOperation.SUBTRACT.equals(operation)) {
            newBalance = user.getBalance().subtract(amount);
        }

        user.setBalance(newBalance);
        userService.save(user);
    }

}
