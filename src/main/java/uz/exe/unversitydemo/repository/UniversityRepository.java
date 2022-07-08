package uz.exe.unversitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.exe.unversitydemo.entity.University;


@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
