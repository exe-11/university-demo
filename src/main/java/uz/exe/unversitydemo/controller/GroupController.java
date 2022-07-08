package uz.exe.unversitydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.exe.unversitydemo.controller.core.AbstractCRUDController;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.GroupRequest;
import uz.exe.unversitydemo.service.GroupService;

import static uz.exe.unversitydemo.controller.core.ControllerUtils.GROUP_URI;

@RestController
@RequestMapping(GROUP_URI)
public class GroupController extends AbstractCRUDController<GroupService,Long, GroupRequest, GroupRequest> {
    protected GroupController(GroupService service) {
        super(service);
    }

    @GetMapping("/details/{groupId}")
    public ResponseEntity<APIResponse> getGroupDetailsByStudentMarksOrder(
            @PathVariable Long groupId
    ){
        return ResponseEntity.ok(service.getGroupByStudentMarksOrder(groupId));
    }
}
