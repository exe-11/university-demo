package uz.exe.unversitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.exe.unversitydemo.entity.Student;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByName(String name);

    List<Student> findStudentsByGroup_IdOrderByAverageMarkDesc(Long groupId);
}
