package com.ucoltis.jv2f2.hgarcia.bookshop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
