package uz.exe.unversitydemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Faculty extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private University university;
}
