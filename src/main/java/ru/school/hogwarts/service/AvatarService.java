package ru.school.hogwarts.service;

import org.springframework.web.multipart.MultipartFile;
import ru.school.hogwarts.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Avatar find(Long studentId);


    List<Avatar> getAllAvatar(Integer pageNumber, Integer pageSize);
}
