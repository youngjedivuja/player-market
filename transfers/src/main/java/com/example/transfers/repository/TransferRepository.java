package com.example.transfers.repository;

import com.example.transfers.entity.Transfer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}