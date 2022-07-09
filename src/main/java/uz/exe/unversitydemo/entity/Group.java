package uz.exe.unversitydemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends BaseEntity{
    @Column(nullable = false)
    private String name;

    private int year;

    @Column(nullable = false,columnDefinition = "integer default 0")
    private int studentsCount;

    @ManyToOne
    private Faculty faculty;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> students;

    public int getStudentsCount() {
        return this.students != null ? this.students.size() : 0;
    }



}
