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
import uz.exe.unversitydemo.payload.response.GroupResponse;
import uz.exe.unversitydemo.payload.response.StudentAverageMarkResponse;
import uz.exe.unversitydemo.repository.FacultyRepository;
import uz.exe.unversitydemo.repository.GroupRepository;
import uz.exe.unversitydemo.repository.StudentRepository;
import uz.exe.unversitydemo.repository.SubjectRepository;
import uz.exe.unversitydemo.service.core.CRUDService;

import java.util.*;

import static uz.exe.unversitydemo.service.FacultyService.FACULTY;

@Service
public class GroupService implements CRUDService<APIResponse, GroupRequest, GroupRequest, Long> {
    protected static final String GROUP = "Group";
    private final ModelMapper modelMapper;

    private final FacultyRepository facultyRepository;

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    public GroupService(ModelMapper modelMapper, FacultyRepository facultyRepository, SubjectRepository subjectRepository, StudentRepository studentRepository, GroupRepository groupRepository) {
        this.modelMapper = modelMapper;
        this.facultyRepository = facultyRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public APIResponse getGroupStudentByMarksOrder(Long id){
//        final Group group = groupRepository.findById(id).orElseThrow(() -> DataNotFoundException.of(GROUP, id));
        final List<Student> students = studentRepository.findStudentsByGroup_Id(id);
        List<StudentAverageMarkResponse> responses = new ArrayList<>(List.of(modelMapper.map(students, StudentAverageMarkResponse[].class)));
        responses.sort(Comparator.comparing(StudentAverageMarkResponse::getAverageMark, (s1,s2)->{
            return s2.compareTo(s1);
        }));
        return APIResponse.success(responses);
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
