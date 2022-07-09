package uz.exe.unversitydemo.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.exe.unversitydemo.entity.Faculty;
import uz.exe.unversitydemo.payload.Response;

@Getter
@Setter
public class FacultyGroupResponse implements Response {
    private String name;

    private int year;

    private int studentsCount;

}
