package com.powerschool.course.autocomplete.service;

import com.powerschool.course.autocomplete.model.Course;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SolrService {

    Course findByCourseId(Long courseId);

    Course findByCourseName(String courseName);

    public List<Course> findByQueryAnnotation(String searchTerm, Pageable page);
}
