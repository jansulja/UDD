package com.ftn.jan.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.jan.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByName(String string);

}
