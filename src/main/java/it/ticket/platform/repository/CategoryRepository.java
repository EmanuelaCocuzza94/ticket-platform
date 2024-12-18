package it.ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ticket.platform.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>{
}
