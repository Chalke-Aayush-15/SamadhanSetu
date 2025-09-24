package com.example.SamadhanSetu.dao.Repository;

import com.example.SamadhanSetu.dao.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
