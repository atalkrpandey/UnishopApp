package com.unishopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishopapp.models.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

}
