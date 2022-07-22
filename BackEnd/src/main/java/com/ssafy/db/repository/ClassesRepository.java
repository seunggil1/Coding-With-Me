package com.ssafy.db.repository;

import com.ssafy.db.entity.Classes;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository <Classes, Long>{

}
