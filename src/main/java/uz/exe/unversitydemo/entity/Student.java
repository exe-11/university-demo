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
public class Student extends BaseEntity {
    private String name;

    @OneToMany
    private List<Subject> subjects = new LinkedList<>();

    @ManyToOne
    private Group group;

    private int averageMark;

    @JsonIgnore
    @OneToMany
    private List<Mark> marks = new LinkedList<>();

    public double getAverageMark() {
        if (marks.size() > 0) {
            int total = marks.stream().reduce(0, (acc, mark) -> acc + mark.getValue(), Integer::sum);
            return total / marks.size();
        }
        return 0;
    }
}
