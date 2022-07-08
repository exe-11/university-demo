package uz.exe.unversitydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.exe.unversitydemo.controller.core.AbstractCRUDController;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.FacultyRequest;
import uz.exe.unversitydemo.service.FacultyService;

import static uz.exe.unversitydemo.controller.core.ControllerUtils.FACULTY_URI;

@RestController
@RequestMapping(FACULTY_URI)
public class FacultyController extends AbstractCRUDController<FacultyService,Long, FacultyRequest, FacultyRequest> {
    protected FacultyController(FacultyService service) {
        super(service);
    }

    @GetMapping("/groups/{facultyId}")
    public ResponseEntity<APIResponse> getGroupsByFaculty(
            @PathVariable Long facultyId
    ){
        return ResponseEntity.ok(service.getGroups(facultyId));
    }
}
