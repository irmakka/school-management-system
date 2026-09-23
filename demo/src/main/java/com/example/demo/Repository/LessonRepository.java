package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Lesson;


public interface LessonRepository extends JpaRepository<Lesson, Long>{
 public Optional<Lesson> findByLessonCode(String lessonCode);
}
