package com.powerschool.course.autocomplete.controller;

import com.powerschool.course.autocomplete.model.Course;
import com.powerschool.course.autocomplete.service.impl.SolrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {


    @Value("${max.results.per.search}")
    int maxResults;

    @Autowired
    SolrServiceImpl solrService;

    @GetMapping("/")
    public String home() {
        return "Welcome to PowerSchool Course AutoComplete API";
    }

    @PostMapping("/course/create")
    public String createCourse(@RequestBody Course course) {
        String description = "Course Created";
        if (solrService.getCourseRepository().count() == 0)
            course.setCourseId(1L);
        else
            course.setCourseId(solrService.getCourseRepository().count() + 1);
        solrService.getCourseRepository().save(course);
        return description;
    }

    @PostMapping("/course/update")
    public String updateCourse(@RequestBody Course course) {
        Course courseFromRead = readCourse(course.getCourseId());
        if (!ObjectUtils.isEmpty(courseFromRead)) {
            deleteCourse(course.getCourseId());
            solrService.getCourseRepository().save(course);
            return "Course Updated";
        }
        return "Course Update Failed";
    }

    @GetMapping("/course/read/{courseId}")
    public Course readCourse(@PathVariable Long courseId) {
        return solrService.findByCourseId(courseId);
    }

    @DeleteMapping("/course/delete/{courseId}")
    public String deleteCourse(@PathVariable Long courseId) {
        String description = "Course Deleted";
        solrService.getCourseRepository().delete(solrService.findByCourseId(courseId));
        return description;
    }

    @DeleteMapping("/course/clearAllCourses")
    public String deleteCourse() {
        String description = "All Courses Deleted";
        solrService.getCourseRepository().deleteAll();
        return description;
    }

    @GetMapping("/course/search/name/{courseName}")
    public List<Course> findCourseByAutoComplete(@PathVariable String courseName) {
        return solrService.findByQueryAnnotation(courseName, PageRequest.of(0, maxResults));
    }

    @GetMapping("/course/search/name/{courseName}/{page}")
    public List<Course> findCourseByAutoComplete(@PathVariable String courseName, @PathVariable int page) {
        return solrService.findByQueryAnnotation(courseName, PageRequest.of(page, maxResults));
    }

    @GetMapping("/course/search/exactMatch/name/{courseName}")
    public Course findCourse(@PathVariable String courseName) {
        return solrService.findByCourseName(courseName);
    }
}
