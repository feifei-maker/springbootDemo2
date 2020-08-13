package com.whc.springboot.modules.test.service;

import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * ClassName: StudentService <br/>
 * Description: <br/>
 * date: 2020/8/13 13:54<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
public interface StudentService {
    Result<Student> insertStudent(Student student);

    Student getStudentByStudentId(int studentId);

    Page<Student> getStudentBySearchVo(SearchVo searchVo);

    List<Student> getStudents();

    List<Student> findByStudentName(String studentName);

    List<Student> findByStudentNameLike(String studentName);

    List<Student> findTop2ByStudentNameLike(String studentName);

    List<Student> getStudentsByStudentName(String studentName, int cardId);
}
