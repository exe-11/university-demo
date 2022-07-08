package uz.exe.unversitydemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Subject extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;

}
