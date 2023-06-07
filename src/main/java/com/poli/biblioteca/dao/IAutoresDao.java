package com.poli.biblioteca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poli.biblioteca.entity.Autor;

@Repository
public interface IAutoresDao extends JpaRepository<Autor,Integer>{

}
