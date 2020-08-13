package com.whc.springboot.modules.test.controller;


import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.test.entity.Student;
import com.whc.springboot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: StudentController <br/>
 * Description: <br/>
 * date: 2020/8/13 13:58<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 127.0.0.1:81/studentController/student ---- post
     * {"studentName":"whc1","studentCard":{"cardId":"3"}}
     */
    @PostMapping(value = "student", consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }
}
