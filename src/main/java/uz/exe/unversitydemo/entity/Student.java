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

    @ManyToMany
    private List<Subject> subjects;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    private int averageMark;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Mark> marks;

    public double getAverageMark() {
        if (marks != null && marks.size()>0) {
            int total = marks.stream().reduce(0, (acc, mark) -> acc + mark.getValue(), Integer::sum);
            return total / marks.size();
        }
        return 0;
    }

    public void setAverageMark(int averageMark) {
        int total = 0;
        if (marks != null && marks.size()>0) {
            total = marks.stream().reduce(0, (acc, mark) -> acc + mark.getValue(), Integer::sum);
            this.averageMark = total / marks.size();
        }
    }
}
