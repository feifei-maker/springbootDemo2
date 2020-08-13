package com.whc.springboot.repository;

import com.whc.springboot.modules.test.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: StudentRepository <br/>
 * Description: <br/>
 * date: 2020/8/13 13:55<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    //根据名字精确查询
    List<Student> findByStudentName(String studentName);
    //根据名字模糊查询
    List<Student> findByStudentNameLike(String studentName);
    //根据名字模糊查询并只显示前两条
    List<Student> findTop2ByStudentNameLike(String studentName);
    //多条件查询
    @Query(nativeQuery = true,
            value = "select * from h_student where student_name = :studentName " +
                    "and card_id = :cardId")
    List<Student> getStudentsByParams(@Param("studentName") String studentName,
                                      @Param("cardId") int cardId);
}
