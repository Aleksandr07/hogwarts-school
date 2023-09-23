package ru.school.hogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.school.hogwarts.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findAvatarByStudentId(Long id);
}
