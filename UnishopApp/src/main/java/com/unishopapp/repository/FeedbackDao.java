package com.unishopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishopapp.models.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer> {

}
