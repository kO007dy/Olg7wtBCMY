// 代码生成时间: 2025-10-13 18:33:50
package com.example.studentprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class StudentProfileSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentProfileSystemApplication.class, args);
    }
}

// Service that handles student profile operations
package com.example.studentprofile.service;

import com.example.studentprofile.model.StudentProfile;
import com.example.studentprofile.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    // Fetch all student profiles
    public List<StudentProfile> getAllStudentProfiles() {
        return studentProfileRepository.findAll();
    }

    // Fetch a student profile by ID
    public Optional<StudentProfile> getStudentProfileById(Long id) {
        return studentProfileRepository.findById(id);
    }

    // Create a new student profile
    @Transactional
    public StudentProfile createStudentProfile(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    // Update an existing student profile
    @Transactional
    public StudentProfile updateStudentProfile(Long id, StudentProfile studentProfile) {
        return studentProfileRepository.findById(id).map(profile -> {
            profile.setName(studentProfile.getName());
            profile.setAge(studentProfile.getAge());
            profile.setGrade(studentProfile.getGrade());
            profile.setInterests(studentProfile.getInterests());
            return studentProfileRepository.save(profile);
        }).orElseThrow(() -> new RuntimeException("Student Profile not found with id " + id));
    }

    // Delete a student profile by ID
    @Transactional
    public void deleteStudentProfile(Long id) {
        studentProfileRepository.findById(id).map(profile -> {
            studentProfileRepository.delete(profile);
            return profile;
        }).orElseThrow(() -> new RuntimeException("Student Profile not found with id " + id));
    }
}

// Repository interface for student profile
package com.example.studentprofile.repository;

import com.example.studentprofile.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    // Additional query methods can be added here
}

// Model representing a student profile
package com.example.studentprofile.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    private Long id;
    private String name;
    private int age;
    private double grade;
    private List<String> interests;

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
    public List<String> getInterests() {
        return interests;
    }
    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
