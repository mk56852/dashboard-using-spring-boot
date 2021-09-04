package com.dash.dash.Repository;


import com.dash.dash.domain.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional

public interface SpecialityRepository extends JpaRepository<Speciality,Long> {

    Speciality findSpecialityById(Long id) ;
    Speciality findSpecialityByName(String name);


}
