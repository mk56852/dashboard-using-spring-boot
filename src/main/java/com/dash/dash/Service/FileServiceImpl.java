package com.dash.dash.Service;


import com.dash.dash.Repository.FileRepository;
import com.dash.dash.domain.File;
import com.dash.dash.domain.User;
import com.dash.dash.dto.ClientDto;
import com.dash.dash.dto.FileDto;
import com.dash.dash.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor

public class FileServiceImpl implements FileService {
    private FileRepository fileRepository ;
    private ModelMapper modelMapper ;
    private  UserServiceImpl userService ;

    @Override
    public void addFile(MultipartFile file,Long clientId) {

        String filename = file.getOriginalFilename();
        File doc = null;
        try {
            doc = new File(clientId,filename,file.getBytes());
            fileRepository.save(doc) ;

        } catch (IOException e) {
            throw new IllegalStateException("file not uploaded");
        }

    }

    @Override
    public File getFile(Long id) {
        return fileRepository.findFileById(id);
    }

    @Override
    public FileDto loadFile(Long id) {
        File file = fileRepository.findFileById(id) ;
        return modelMapper.map(file,FileDto.class);
    }

    @Override
    public Set<UserDto> loadAllFileUsers(Long id) {
        File file = fileRepository.findFileById(id) ;
        Set<User> users = file.getUsersFile() ;
        Set<UserDto> usersDto = new HashSet<>();
        users.forEach(user -> usersDto.add(modelMapper.map(user,UserDto.class)));
        return usersDto;
    }
}

