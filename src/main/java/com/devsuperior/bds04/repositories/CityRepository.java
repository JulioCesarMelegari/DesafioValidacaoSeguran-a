package com.devsuperior.bds04.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.bds04.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
	
	@Query(nativeQuery = true, value = """
			SELECT * FROM TB_CITY ORDER BY NAME
		""")
	List<City> listAllOrderByName();

}
