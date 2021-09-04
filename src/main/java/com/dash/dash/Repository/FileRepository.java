package com.dash.dash.Repository;

import com.dash.dash.domain.File;
import com.dash.dash.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<File,Long> {

    File findFileById(Long id) ;


}
