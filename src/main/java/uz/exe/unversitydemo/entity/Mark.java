package uz.exe.unversitydemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Mark extends BaseEntity{

    private double averageMark;

    @ManyToOne
    private Subject subject;
}
