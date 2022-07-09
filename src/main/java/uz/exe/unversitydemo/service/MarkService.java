package uz.exe.unversitydemo.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.exe.unversitydemo.entity.Mark;
import uz.exe.unversitydemo.entity.Student;
import uz.exe.unversitydemo.entity.Subject;
import uz.exe.unversitydemo.exception.DataNotFoundException;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.MarkCreationRequest;
import uz.exe.unversitydemo.payload.request.MarkUpdateRequest;
import uz.exe.unversitydemo.repository.MarkRepository;
import uz.exe.unversitydemo.repository.StudentRepository;
import uz.exe.unversitydemo.repository.SubjectRepository;
import uz.exe.unversitydemo.service.core.CRUDService;

import static uz.exe.unversitydemo.service.StudentService.STUDENT;
import static uz.exe.unversitydemo.service.SubjectService.SUBJECT;


@Service
public class MarkService implements CRUDService<APIResponse, MarkCreationRequest, MarkUpdateRequest, Long> {

    protected static final String MARK = "Mark";

    private static final String SUBJECT_NOT_VALID_FOR_USER = "Subject is not in student subject-list";

    private final ModelMapper modelMapper;

    private final MarkRepository markRepository;

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    public MarkService(ModelMapper modelMapper, MarkRepository markRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.modelMapper = modelMapper;
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public APIResponse create(MarkCreationRequest markCreationRequest) {
        final Student student = studentRepository.findById(markCreationRequest.getStudent()).orElseThrow(
                        () -> DataNotFoundException.of(STUDENT, markCreationRequest.getStudent())
        );

        final Subject subject = student.getSubjects().stream()
                .filter(s -> s.getId() == markCreationRequest.getSubject())
                .findFirst().orElseThrow(() -> new DataNotFoundException(SUBJECT_NOT_VALID_FOR_USER));

        final Mark mark = modelMapper.map(markCreationRequest, Mark.class);
        mark.setStudent(student);
        mark.setSubject(subject);
        try {
            markRepository.save(mark);
        }catch (Exception exception){
            return APIResponse.error(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return APIResponse.success(mark);
    }

    @Override
    public APIResponse get(Long identity) {
        final Mark mark = markRepository.findById(identity).orElseThrow(
                () -> DataNotFoundException.of(MARK, identity)
        );
        return APIResponse.success(mark);
    }

    @Override
    public APIResponse modify(Long identity, MarkUpdateRequest markUpdateRequest) {
        Mark mark = markRepository.findById(identity).orElseThrow(
                () -> DataNotFoundException.of(MARK, identity)
        );
        mark.setValue(mark.getValue());
        markRepository.save(mark);
        return APIResponse.success(mark);
    }

    @Override
    public APIResponse delete(Long identity) {
        if(!markRepository.existsById(identity)){
            throw DataNotFoundException.of(MARK, identity);
        }
        markRepository.deleteById(identity);
        return APIResponse.success(HttpStatus.OK.name());
    }
}
