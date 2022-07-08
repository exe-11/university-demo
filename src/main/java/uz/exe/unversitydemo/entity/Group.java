package uz.exe.unversitydemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends BaseEntity{
    @Column(nullable = false)
    private String name;

    private int year;

    private int studentsCount;

    @ManyToOne
    private Faculty faculty;

//    @JsonIgnore
    @OneToMany
    private List<Student> students = new LinkedList<>();

    public int getStudentsCount() {
        return this.students.size();
    }


}
