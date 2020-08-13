package com.whc.springboot.modules.test.service.impl;

import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.modules.test.entity.Student;
import com.whc.springboot.modules.test.service.StudentService;
import com.whc.springboot.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: StudentServiceImpl <br/>
 * Description: <br/>
 * date: 2020/8/13 13:54<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,
                "Insert success", student);
    }

    @Override
    public Student getStudentByStudentId(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    //带条件的分页查询
    public Page<Student> getStudentBySearchVo(SearchVo searchVo) {
        //equalsIgnoreCase忽略大小写的比较，将字符串写在前面可以避免空指针异常
        Sort.Direction direction = "desc".equalsIgnoreCase(searchVo.getSort()) ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        //StringUtils.isBlank()校验一个String类型的变量是否为空
        String orderBy = StringUtils.isBlank(searchVo.getOrderBy()) ?
                "studentId" : searchVo.getOrderBy();
        Sort sort = new Sort(direction, orderBy);
        //因为从前端传过来的首页是1，但是底层是从0开始
        Pageable pageable = PageRequest.of(searchVo.getCurrentPage() - 1, searchVo.getPageSize(), sort);
        // 如果 keyWord 为 null，则设置的 studentName 不参与查询条件
        Student student = new Student();
        student.setStudentName(searchVo.getKeyWord());
        // 创建匹配器，进行动态查询匹配
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("studentName", match -> match.contains())//全部模糊查询，即%studentName%
                .withIgnoreCase("studentId");//忽略id进行查询
        //获取数据列表
        Example<Student> example = Example.of(student, exampleMatcher);
        return studentRepository.findAll(example, pageable);
    }

    @Override
    public List<Student> getStudents() {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = new Sort(direction, "studentName");
        return studentRepository.findAll(sort);
    }

    @Override
    public List<Student> findByStudentName(String studentName) {
        return studentRepository.findByStudentName(studentName);
    }

    @Override
    public List<Student> findByStudentNameLike(String studentName) {
        return Optional
                .ofNullable(studentRepository.findByStudentNameLike(
                        String.format("%s%S%s", "%", studentName, "%")))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<Student> findTop2ByStudentNameLike(String studentName) {
        return Optional
                .ofNullable(studentRepository.findTop2ByStudentNameLike(
                        String.format("%s%S%s", "%", studentName, "%")))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<Student> getStudentsByStudentName(String studentName, int cardId) {
        if (cardId > 0) {
            return studentRepository.getStudentsByParams(studentName, cardId);
        } else {
            return Optional
                    .ofNullable(studentRepository.findTop2ByStudentNameLike(
                            String.format("%s%S%s", "%", studentName, "%")))
                    .orElse(Collections.emptyList());
        }
    }
}
