package uz.exe.unversitydemo.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.exe.unversitydemo.entity.Group;
import uz.exe.unversitydemo.entity.Student;
import uz.exe.unversitydemo.entity.Subject;
import uz.exe.unversitydemo.exception.DataNotFoundException;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.StudentRequest;
import uz.exe.unversitydemo.repository.GroupRepository;
import uz.exe.unversitydemo.repository.StudentRepository;
import uz.exe.unversitydemo.repository.SubjectRepository;
import uz.exe.unversitydemo.service.core.CRUDService;

import java.util.List;

import static uz.exe.unversitydemo.service.FacultyService.FACULTY;


@Service
public class StudentService implements CRUDService<APIResponse, StudentRequest, StudentRequest, Long> {
    protected static final String STUDENT = "Student";
    private final ModelMapper modelMapper;

    private final SubjectRepository subjectRepository;

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    public StudentService(ModelMapper modelMapper, SubjectRepository subjectRepository, StudentRepository studentRepository, GroupRepository groupRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public APIResponse getSubjects(Long userId){
        final Student student = studentRepository.findById(userId).orElseThrow(
                () -> DataNotFoundException.of(STUDENT, userId)
        );
        return APIResponse.success(student.getSubjects());
    }

    public APIResponse getStudentByName(String name){
        return APIResponse.success(studentRepository.findAllByName(name));
    }

    @Override
    public APIResponse create(StudentRequest studentRequest) {
        List<Subject> subjects = subjectRepository.findAllById(studentRequest.getSubjects());
        final Group group = groupRepository.findById(studentRequest.getGroup()).orElseThrow(() -> DataNotFoundException.of(FACULTY, studentRequest.getGroup()));
        final Student student = modelMapper.map(studentRequest, Student.class);
        student.setGroup(group);
        student.setSubjects(subjects);
        try {
            studentRepository.save(student);
        } catch (Exception exception) {
            return APIResponse.error(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return APIResponse.success(student);
    }

    @Override
    public APIResponse get(Long identity) {
        final Student student = studentRepository.findById(identity).orElseThrow(() -> DataNotFoundException.of(STUDENT, identity));
        return APIResponse.success(student);
    }

    @Override
    public APIResponse modify(Long identity, StudentRequest studentRequest) {

        final Group group = groupRepository.findById(studentRequest.getGroup()).orElseThrow(() -> DataNotFoundException.of(FACULTY, studentRequest.getGroup()));
        final Student student = modelMapper.map(studentRequest, Student.class);
        student.setGroup(group);
        modelMapper.map(studentRequest, student);
        try {
            studentRepository.save(student);
        } catch (Exception exception) {
            return APIResponse.error(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return APIResponse.success(student);
    }

    @Override
    public APIResponse delete(Long identity) {
        if (!studentRepository.existsById(identity)) {
            throw DataNotFoundException.of(STUDENT, identity);
        }
        studentRepository.deleteById(identity);
        return APIResponse.success(HttpStatus.OK.name());
    }
}
