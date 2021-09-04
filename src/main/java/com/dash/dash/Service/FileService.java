package com.dash.dash.Service;


import com.dash.dash.domain.File;
import com.dash.dash.dto.FileDto;
import com.dash.dash.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface FileService {

    void addFile(MultipartFile file , Long ClientId) ;
    File getFile(Long id) ;
    FileDto loadFile(Long id) ;
    Set<UserDto> loadAllFileUsers(Long id) ;

}

