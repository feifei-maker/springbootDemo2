package com.whc.springboot.repository;

import com.whc.springboot.modules.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: CardRepository <br/>
 * Description: <br/>
 * date: 2020/8/13 10:33<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
//使用jpa自动生成表方式的dao层
public interface CardRepository extends JpaRepository<Card,Integer> {
}
