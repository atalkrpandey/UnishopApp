package com.unishopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishopapp.models.Report;

@Repository
public interface ReportDao extends JpaRepository<Report, Integer> {

}
