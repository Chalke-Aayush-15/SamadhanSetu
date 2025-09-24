package com.example.SamadhanSetu.dao.Repository;

import com.example.SamadhanSetu.dao.Entity.EducationVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationVideoRepository extends JpaRepository<EducationVideo,Long> {


    @Query(value = "SELECT * FROM education_video ev WHERE LOWER(ev.category) = LOWER(:category)", nativeQuery = true)
    List<EducationVideo> findByCategoryIgnoreCase(@Param("category") String category);

}
