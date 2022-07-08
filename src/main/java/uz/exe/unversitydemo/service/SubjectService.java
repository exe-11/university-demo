package uz.exe.unversitydemo.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.exe.unversitydemo.entity.Subject;
import uz.exe.unversitydemo.exception.DataNotFoundException;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.SubjectRequest;
import uz.exe.unversitydemo.repository.SubjectRepository;
import uz.exe.unversitydemo.service.core.CRUDService;

@Service
public class SubjectService implements CRUDService<APIResponse, SubjectRequest, SubjectRequest, Long> {

    protected static final String SUBJECT = "Subject";

    private final ModelMapper modelMapper;

    private final SubjectRepository subjectRepository;

    public SubjectService(ModelMapper modelMapper, SubjectRepository subjectRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public APIResponse create(SubjectRequest subjectRequest) {
        final Subject subject = modelMapper.map(subjectRequest, Subject.class);
        try {
            subjectRepository.save(subject);
        } catch (Exception exception) {
            return APIResponse.error(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return APIResponse.success(subject);
    }

    @Override
    public APIResponse get(Long identity) {
        final Subject subject = subjectRepository.findById(identity).orElseThrow(() -> DataNotFoundException.of(SUBJECT, identity));
        return APIResponse.success(subject);
    }

    @Override
    public APIResponse modify(Long identity, SubjectRequest subjectRequest) {
        final Subject subject = subjectRepository.findById(identity).orElseThrow(() -> DataNotFoundException.of(SUBJECT, identity));
        modelMapper.map(subjectRequest, subject);
        return APIResponse.success(subject);
    }

    @Override
    public APIResponse delete(Long identity) {
        if (!subjectRepository.existsById(identity)) {
            throw DataNotFoundException.of(SUBJECT, identity);
        }
        subjectRepository.deleteById(identity);
        return APIResponse.success(HttpStatus.OK.name());
    }
}
