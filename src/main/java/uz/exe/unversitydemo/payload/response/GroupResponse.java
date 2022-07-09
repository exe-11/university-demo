package uz.exe.unversitydemo.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uz.exe.unversitydemo.entity.Faculty;
import uz.exe.unversitydemo.entity.Student;
import uz.exe.unversitydemo.payload.Response;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
public class GroupResponse implements Response {
    private String name;

    private int year;

    private int studentsCount;

    private Faculty faculty;

    private List<Student> students;
}
