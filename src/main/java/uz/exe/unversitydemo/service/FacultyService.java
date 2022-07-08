package uz.exe.unversitydemo.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.exe.unversitydemo.entity.Faculty;
import uz.exe.unversitydemo.exception.DataNotFoundException;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.FacultyRequest;
import uz.exe.unversitydemo.repository.FacultyRepository;
import uz.exe.unversitydemo.service.core.CRUDService;

@Service
public class FacultyService implements CRUDService<APIResponse, FacultyRequest, FacultyRequest, Long> {

    protected static final String FACULTY = "Faculty";
    private final ModelMapper modelMapper;

    private final FacultyRepository facultyRepository;

    public FacultyService(ModelMapper modelMapper, FacultyRepository facultyRepository) {
        this.modelMapper = modelMapper;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public APIResponse create(FacultyRequest facultyRequest) {
        final Faculty faculty = modelMapper.map(facultyRequest, Faculty.class);
        try {
            facultyRepository.save(faculty);
        } catch (Exception exception) {
            return APIResponse.error(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return APIResponse.success(faculty);
    }

    @Override
    public APIResponse get(Long identity) {
        final Faculty faculty = facultyRepository.findById(identity).orElseThrow(() -> DataNotFoundException.of(FACULTY, identity));
        return APIResponse.success(faculty);
    }

    @Override
    public APIResponse modify(Long identity, FacultyRequest facultyRequest) {
        final Faculty faculty = facultyRepository.findById(identity).orElseThrow(() -> DataNotFoundException.of(FACULTY, identity));
        modelMapper.map(facultyRequest, faculty);
        return APIResponse.success(faculty);
    }

    @Override
    public APIResponse delete(Long identity) {
        if (!facultyRepository.existsById(identity)) {
            throw DataNotFoundException.of(FACULTY, identity);
        }
        facultyRepository.deleteById(identity);
        return APIResponse.success(HttpStatus.OK.name());
    }
}
