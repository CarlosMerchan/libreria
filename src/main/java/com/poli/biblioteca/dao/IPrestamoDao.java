package com.poli.biblioteca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poli.biblioteca.entity.PrestamoLibro;

@Repository
public interface IPrestamoDao extends JpaRepository<PrestamoLibro, Integer>{

}
