package uz.exe.unversitydemo.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.exe.unversitydemo.entity.Faculty;
import uz.exe.unversitydemo.entity.Group;
import uz.exe.unversitydemo.entity.Student;
import uz.exe.unversitydemo.exception.DataNotFoundException;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.GroupRequest;
import uz.exe.unversitydemo.repository.FacultyRepository;
import uz.exe.unversitydemo.repository.GroupRepository;
import uz.exe.unversitydemo.repository.StudentRepository;
import uz.exe.unversitydemo.service.core.CRUDService;

import java.util.List;

import static uz.exe.unversitydemo.service.FacultyService.FACULTY;

@Service
public class GroupService implements CRUDService<APIResponse, GroupRequest, GroupRequest, Long> {
    protected static final String GROUP = "Group";
    private final ModelMapper modelMapper;

    private final FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    public GroupService(ModelMapper modelMapper, FacultyRepository facultyRepository, StudentRepository studentRepository, GroupRepository groupRepository) {
        this.modelMapper = modelMapper;
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public APIResponse getGroupByStudentMarksOrder(Long id){
        final Group group = groupRepository.findById(id).orElseThrow(() -> DataNotFoundException.of(GROUP, id));
        final List<Student> students = studentRepository.findStudentsByGroup_IdOrderByAverageMarkDesc(id);
        group.setStudents(students);
        return APIResponse.success(group);
    }

    @Override
    public APIResponse create(GroupRequest groupRequest) {
        final Faculty faculty = facultyRepository.findById(groupRequest.getFaculty()).orElseThrow(
                () -> DataNotFoundException.of(FACULTY, groupRequest.getFaculty()));
        final Group group = modelMapper.map(groupRequest, Group.class);
        group.setFaculty(faculty);
        try {
            groupRepository.save(group);
        } catch (Exception exception) {
            return APIResponse.error(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return APIResponse.success(group);
    }

    @Override
    public APIResponse get(Long identity) {
        final Group group = groupRepository.findById(identity).orElseThrow(() -> DataNotFoundException.of(GROUP, identity));
        return APIResponse.success(group);
    }

    @Override
    public APIResponse modify(Long identity, GroupRequest groupRequest) {
        final Faculty faculty = facultyRepository.findById(groupRequest.getFaculty()).orElseThrow(
                () -> DataNotFoundException.of(FACULTY, groupRequest.getFaculty()));
        final Group group = modelMapper.map(groupRequest, Group.class);
        group.setFaculty(faculty);
        modelMapper.map(groupRequest, group);
        try {
            groupRepository.save(group);
        } catch (Exception exception) {
            return APIResponse.error(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return APIResponse.success(group);
    }

    @Override
    public APIResponse delete(Long identity) {
        if (!groupRepository.existsById(identity)) {
            throw DataNotFoundException.of(GROUP, identity);
        }
        groupRepository.deleteById(identity);
        return APIResponse.success(HttpStatus.OK.name());
    }
}
