package uz.exe.unversitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.exe.unversitydemo.entity.Subject;


@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
