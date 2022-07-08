package uz.exe.unversitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.exe.unversitydemo.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
