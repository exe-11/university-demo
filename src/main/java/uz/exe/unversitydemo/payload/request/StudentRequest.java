package uz.exe.unversitydemo.payload.request;

import lombok.Getter;
import lombok.Setter;
import uz.exe.unversitydemo.service.core.Creatable;
import uz.exe.unversitydemo.service.core.Modifiable;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class StudentRequest implements Creatable, Modifiable {
    private String name;

    List<Long> subjects = new LinkedList<>();

    private long group;
}
