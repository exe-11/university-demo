package uz.exe.unversitydemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Group extends BaseEntity{
    @Column(nullable = false)
    private String name;

    private int year;

    @ManyToOne
    private Faculty faculty;

}
