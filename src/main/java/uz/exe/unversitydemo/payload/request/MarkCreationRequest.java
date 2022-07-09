package uz.exe.unversitydemo.payload.request;

import lombok.Getter;
import lombok.Setter;
import uz.exe.unversitydemo.service.core.Creatable;
import uz.exe.unversitydemo.service.core.Modifiable;


@Getter
@Setter
public class MarkCreationRequest implements Creatable {
    private int value;

    private long student;

    private long subject;
}
