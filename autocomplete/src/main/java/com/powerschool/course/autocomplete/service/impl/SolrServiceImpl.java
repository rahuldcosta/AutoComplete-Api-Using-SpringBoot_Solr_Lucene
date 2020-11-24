package com.powerschool.course.autocomplete.service.impl;

import com.powerschool.course.autocomplete.model.Course;
import com.powerschool.course.autocomplete.service.SolrService;
import com.powerschool.course.autocomplete.solr.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    CourseRepository courseRepository;

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    @Override
    public Course findByCourseId(Long courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public Course findByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public List<Course> findByQueryAnnotation(String searchTerm, Pageable page) {
        return courseRepository.findByQueryAnnotation(searchTerm, page);
    }
}
