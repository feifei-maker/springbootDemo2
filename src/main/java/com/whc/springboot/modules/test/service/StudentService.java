package com.whc.springboot.modules.test.service;

import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.test.entity.Student;

import javax.naming.spi.DirStateFactory;

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
}
