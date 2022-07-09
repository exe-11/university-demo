package uz.exe.unversitydemo.payload.request;

import lombok.Getter;
import lombok.Setter;
import uz.exe.unversitydemo.service.core.Creatable;
import uz.exe.unversitydemo.service.core.Modifiable;

import java.util.List;


@Getter
@Setter
public class StudentRequest implements Creatable, Modifiable {
    private String name;

    private List<Long> subjects;

    private long group;
}
