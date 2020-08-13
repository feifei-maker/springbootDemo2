package com.whc.springboot.modules.test.controller;


import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.modules.test.entity.Student;
import com.whc.springboot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping(value = "/student", consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }

    /**
     * 127.0.0.1:81/studentController/student/3 ---- get
     */
    @GetMapping(value = "/students/{studentId}", consumes = "application/json")
    public Student getStudentByStudentId(@PathVariable int studentId) {
        return studentService.getStudentByStudentId(studentId);
    }
    /**
     * 127.0.0.1:81/studentController/students ---- post
     * {"currentPage":"1","pageSize":"5","keyWord":"whc","orderBy":"studentName","sort":"desc"}
     */
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> getStudentBySearchVo(@RequestBody SearchVo searchVo) {
        System.err.println(studentService.getStudentBySearchVo(searchVo));
        return studentService.getStudentBySearchVo(searchVo);
    }
    /**
     * 127.0.0.1:81/studentController/students ---- get
     */
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
    /**
     * 127.0.0.1:81/studentController/studentByParams?studentName=whc2 ---- get
     */
    @GetMapping("/studentByParams")
    public List<Student> getStudentsByParams(
            @RequestParam String studentName,
            @RequestParam(required = false, defaultValue = "0") Integer cardId) {
        return studentService.getStudentsByStudentName(studentName, cardId);
    }
    /**
     * 127.0.0.1:81/studentController/findByStudentName?studentName=whc2 ---- get
     */
   @GetMapping("/findByStudentName")
    public List<Student> findByStudentName(@RequestParam String studentName) {
        return studentService.findByStudentName(studentName);
    }

    /**
     * 127.0.0.1:81/studentController/findByStudentNameLike?studentName=whc ---- get
     */
    @GetMapping("/findByStudentNameLike")
    public List<Student> findByStudentNameLike(@RequestParam String studentName) {
        return studentService.findByStudentNameLike(studentName);
    }

    /**
     * 127.0.0.1:81/studentController/findTop2ByStudentNameLike?studentName=whc ---- get
     */
    @GetMapping("/findTop2ByStudentNameLike")
    public List<Student> findTop2ByStudentNameLike(@RequestParam String studentName) {
        return studentService.findTop2ByStudentNameLike(studentName);
    }
}
