package com.poli.biblioteca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poli.biblioteca.entity.Cliente;

@Repository
public interface IClienteDao extends JpaRepository<Cliente, Long>{

}
