package com.whc.springboot.modules.test.service;

import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.test.entity.Card;

/**
 * ClassName: CardService <br/>
 * Description: <br/>
 * date: 2020/8/13 10:35<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
public interface CardService {

    Result<Card> insertCard(Card card);
}
