package com.whc.springboot.modules.test.service.impl;

import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.test.entity.Card;
import com.whc.springboot.modules.test.service.CardService;
import com.whc.springboot.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: CardServiceImpl <br/>
 * Description: <br/>
 * date: 2020/8/13 13:53<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Override
    @Transactional
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(
                Result.ResultStatus.SUCCESS.status,
                "Insert success.", card);
    }
}
