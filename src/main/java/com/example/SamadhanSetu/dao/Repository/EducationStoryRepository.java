package com.example.SamadhanSetu.dao.Repository;

import com.example.SamadhanSetu.dao.Entity.EducationStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationStoryRepository extends JpaRepository<EducationStory,Long> {


    @Query(value = "SELECT * FROM education_story es WHERE LOWER(es.category) = LOWER(:category)", nativeQuery = true)
    List<EducationStory> findByCategoryIgnoreCase(@Param("category") String category);

}
