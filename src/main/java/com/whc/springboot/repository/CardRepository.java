package com.whc.springboot.repository;

import com.whc.springboot.modules.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: CardRepository <br/>
 * Description: <br/>
 * date: 2020/8/13 13:55<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}