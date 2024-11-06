package com.unais.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unais.lms.entity.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long>  {

}
