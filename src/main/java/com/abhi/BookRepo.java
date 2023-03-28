package com.abhi;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Serializable>{

}
