package uz.exe.unversitydemo.payload.request;

import lombok.Getter;
import lombok.Setter;
import uz.exe.unversitydemo.service.core.Modifiable;


@Getter
@Setter
public class MarkUpdateRequest implements Modifiable {
    private double averageMark;
}
