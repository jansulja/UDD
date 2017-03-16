package com.ftn.jan.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.jan.model.Language;
@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

	Language findByName(String string);

}
