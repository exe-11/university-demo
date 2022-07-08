package uz.exe.unversitydemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.exe.unversitydemo.controller.core.AbstractCRUDController;
import uz.exe.unversitydemo.payload.request.UniversityRequest;
import uz.exe.unversitydemo.service.UniversityService;

import static uz.exe.unversitydemo.controller.core.ControllerUtils.UNIVERSITY_URI;

@RestController
@RequestMapping(UNIVERSITY_URI)
public class UniversityController extends AbstractCRUDController<UniversityService,Long, UniversityRequest, UniversityRequest> {
    protected UniversityController(UniversityService service) {
        super(service);
    }
}
