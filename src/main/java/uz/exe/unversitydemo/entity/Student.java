package uz.exe.unversitydemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Student extends BaseEntity{
    private String name;

    @OneToMany
    private List<Subject> subjects;

    @ManyToOne
    private Group group;

    @OneToMany
    private List<Mark> marks;

}
