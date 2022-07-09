package uz.exe.unversitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.exe.unversitydemo.entity.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Long> {

}
