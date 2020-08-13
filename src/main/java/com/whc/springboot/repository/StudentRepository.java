package com.whc.springboot.repository;

import com.whc.springboot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: StudentRepository <br/>
 * Description: <br/>
 * date: 2020/8/13 11:35<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
