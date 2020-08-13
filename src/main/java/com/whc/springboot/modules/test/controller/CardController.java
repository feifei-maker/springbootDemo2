package com.whc.springboot.modules.test.controller;

import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.test.entity.Card;
import com.whc.springboot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CardController <br/>
 * Description: <br/>
 * date: 2020/8/13 13:58<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/cardController")
public class CardController {

    @Autowired
    private CardService cardService;

    /**
     * 127.0.0.1:81/cardController/card ---- post
     * {"cardNo":"cdascdas687dsa80"}
     */
    @PostMapping(value = "/card", consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card) {
        return cardService.insertCard(card);
    }
}

