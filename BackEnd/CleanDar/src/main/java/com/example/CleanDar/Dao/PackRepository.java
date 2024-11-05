package com.example.CleanDar.Dao;


import com.example.CleanDar.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PackRepository extends JpaRepository<Pack, Long> {
//    @Query("select *  from pack p inner join reservation r where p.reservation_id=pac ")
}
