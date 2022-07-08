package uz.exe.unversitydemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.exe.unversitydemo.controller.core.AbstractCRUDController;
import uz.exe.unversitydemo.payload.request.MarkCreationRequest;
import uz.exe.unversitydemo.payload.request.MarkUpdateRequest;
import uz.exe.unversitydemo.service.MarkService;

import static uz.exe.unversitydemo.controller.core.ControllerUtils.MARK_URI;

@RestController
@RequestMapping(MARK_URI)
public class MarkController extends AbstractCRUDController<MarkService, Long, MarkCreationRequest, MarkUpdateRequest> {
    protected MarkController(MarkService service) {
        super(service);
    }
}
