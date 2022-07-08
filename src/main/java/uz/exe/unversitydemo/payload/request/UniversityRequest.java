package uz.exe.unversitydemo.payload.request;

import lombok.Getter;
import lombok.Setter;
import uz.exe.unversitydemo.service.core.Creatable;
import uz.exe.unversitydemo.service.core.Modifiable;

@Getter
@Setter
public class UniversityRequest implements Creatable, Modifiable {

    private String name;

    private String address;

    private int openYear;
}
