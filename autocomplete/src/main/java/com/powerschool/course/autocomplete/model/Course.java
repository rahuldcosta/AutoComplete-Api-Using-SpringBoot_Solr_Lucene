package com.powerschool.course.autocomplete.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@SolrDocument(collection = "Course")
public class Course {
    @Id
    @Indexed(name = "courseId", type = "int")
    private Long courseId;
    @Indexed(name = "courseName", type = "string")
    private String courseName;
    @Indexed(name = "courseDescription", type = "string")
    private String courseDescription;
    @Indexed(name = "instructorName", type = "string")
    private String instructorName;
    @Indexed(name = "gradeLevel", type = "int")
    @Min(value = 1, message = "must be equal or greater than 1")
    @Max(value = 12, message = "must be equal or less than 12")
    private int gradeLevel;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

}
