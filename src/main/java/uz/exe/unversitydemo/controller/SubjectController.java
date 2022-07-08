package uz.exe.unversitydemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.exe.unversitydemo.controller.core.AbstractCRUDController;
import uz.exe.unversitydemo.payload.request.SubjectRequest;
import uz.exe.unversitydemo.service.SubjectService;

import static uz.exe.unversitydemo.controller.core.ControllerUtils.SUBJECT_URI;

@RestController
@RequestMapping(SUBJECT_URI)
public class SubjectController extends AbstractCRUDController<SubjectService,Long, SubjectRequest, SubjectRequest> {
    protected SubjectController(SubjectService service) {
        super(service);
    }
}
