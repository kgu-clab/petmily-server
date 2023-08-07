package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.TradeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeBoardRepository extends JpaRepository<TradeBoard, Long> {

    List<TradeBoard> findByTitleContainingIgnoreCase(String title);

}
