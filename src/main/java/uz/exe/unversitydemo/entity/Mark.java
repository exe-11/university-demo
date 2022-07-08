package uz.exe.unversitydemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Mark extends BaseEntity{

    private int value;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Student student;
}
