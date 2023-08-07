package com.clab.securecoding.repository;

import com.clab.securecoding.type.entity.TradeBoard;
import com.clab.securecoding.type.entity.TradeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeCommentRepository extends JpaRepository<TradeComment, Long> {

    List<TradeComment> findByTradeBoard(TradeBoard tradeBoard);

    List<TradeComment> findByTradeBoardOrderByCreatedAtAsc(TradeBoard tradeBoard);

}
