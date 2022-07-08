package uz.exe.unversitydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.exe.unversitydemo.controller.core.AbstractCRUDController;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.StudentRequest;
import uz.exe.unversitydemo.service.StudentService;

import static uz.exe.unversitydemo.controller.core.ControllerUtils.STUDENT_URI;

@RestController
@RequestMapping(STUDENT_URI)
public class StudentController extends AbstractCRUDController<StudentService, Long, StudentRequest, StudentRequest> {
    protected StudentController(StudentService service) {
        super(service);
    }

    @GetMapping("/subs/{userId}")
    public ResponseEntity<APIResponse> getStudentSubjects(
            @PathVariable(name = "userId") Long userId
    ){
        return ResponseEntity.ok(service.getSubjects(userId));
    }

    @GetMapping("/details/{name}")
    public ResponseEntity<APIResponse> getStudentsByName(
            @PathVariable String name
    ){
        return ResponseEntity.ok(service.getStudentByName(name));
    }
}
