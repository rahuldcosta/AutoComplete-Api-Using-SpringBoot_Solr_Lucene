package com.powerschool.course.autocomplete.solr.repository;

import com.powerschool.course.autocomplete.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends SolrCrudRepository<Course, Long> {

    Course findByCourseName(String courseName);

    @Query("courseName:*?0*")
    public List<Course> findByQueryAnnotation(String searchTerm, Pageable page);
}
