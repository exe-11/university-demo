package uz.exe.unversitydemo.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.exe.unversitydemo.payload.Response;

@Getter
@Setter
public class StudentAverageMarkResponse implements Response {
    private long id;

    private String name;

    private int averageMark;

}
