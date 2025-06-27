package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository,
                         StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public byte[] getAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId)
                .map(Avatar::getData)
                .orElseThrow(() -> new NoSuchElementException("Аватар не найден для студента с ID: " + studentId));
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentService.findStudent(studentId);

        Avatar avatar = avatarRepository.findByStudentId(studentId)
                .orElseGet(() -> {
                    Avatar newAvatar = new Avatar();
                    newAvatar.setStudent(student);
                    return newAvatar;
                });

        avatar.setMediaType(avatarFile.getContentType());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setFilePath(generateFilePath(avatarFile.getOriginalFilename()));
        avatar.setData(avatarFile.getBytes());

        avatarRepository.save(avatar);
    }

    private String generateFilePath(String originalFilename) {

        return "../avatars/" + System.currentTimeMillis() + "_" + originalFilename;
    }
}