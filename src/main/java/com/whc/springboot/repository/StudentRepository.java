package com.whc.springboot.repository;

import com.whc.springboot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: StudentRepository <br/>
 * Description: <br/>
 * date: 2020/8/13 10:33<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
