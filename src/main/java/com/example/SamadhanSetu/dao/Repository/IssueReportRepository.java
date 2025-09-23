package com.example.SamadhanSetu.dao.Repository;

import com.example.SamadhanSetu.dao.Entity.IssueReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueReportRepository extends JpaRepository<IssueReport, Long> {

    // 🔍 Find by Category
    @Query(value = "SELECT * FROM issue_reports WHERE category = :category", nativeQuery = true)
    List<IssueReport> findByCategory(@Param("category") String category);

    // 🔍 Find by Status
    @Query(value = "SELECT * FROM issue_reports WHERE status = :status", nativeQuery = true)
    List<IssueReport> findByStatus(@Param("status") String status);

    // 🔍 Find by Reporter Phone
    @Query(value = "SELECT * FROM issue_reports WHERE reporter_phone = :phone", nativeQuery = true)
    List<IssueReport> findByReporterPhone(@Param("phone") String phone);

    // 🔍 Find by Location (text search)
    @Query(value = "SELECT * FROM issue_reports WHERE LOWER(location_text) LIKE LOWER(CONCAT('%', :location, '%'))", nativeQuery = true)
    List<IssueReport> findByLocationText(@Param("location") String location);

    // 🔍 Flexible Search with Multiple Filters
    @Query(value = "SELECT * FROM issue_reports " +
            "WHERE (:category IS NULL OR category = :category) " +
            "AND (:status IS NULL OR status = :status) " +
            "AND (:location IS NULL OR LOWER(location_text) LIKE LOWER(CONCAT('%', :location, '%'))) " +
            "AND (:phone IS NULL OR reporter_phone = :phone)",
            nativeQuery = true)
    List<IssueReport> searchIssues(
            @Param("category") String category,
            @Param("status") String status,
            @Param("location") String location,
            @Param("phone") String phone);

    @Query(value = "SELECT category, COUNT(*) FROM issue_reports GROUP BY category", nativeQuery = true)
    List<Object[]> countIssuesByCategory();

    @Query(value = "SELECT status, COUNT(*) FROM issue_reports GROUP BY status", nativeQuery = true)
    List<Object[]> countIssuesByStatus();

    @Query(value = "SELECT * FROM issue_reports ORDER BY submitted_at DESC LIMIT 10", nativeQuery = true)
    List<IssueReport> findRecentIssues();

}

