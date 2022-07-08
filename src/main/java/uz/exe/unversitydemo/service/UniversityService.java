package uz.exe.unversitydemo.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.exe.unversitydemo.entity.University;
import uz.exe.unversitydemo.exception.DataNotFoundException;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.UniversityRequest;
import uz.exe.unversitydemo.repository.UniversityRepository;
import uz.exe.unversitydemo.service.core.CRUDService;

@Service
public class UniversityService implements CRUDService<APIResponse, UniversityRequest, UniversityRequest, Long> {

    protected static final String UNIVERSITY = "University";
    private final UniversityRepository universityRepository;
    private final ModelMapper modelMapper;

    protected UniversityService(UniversityRepository universityRepository, ModelMapper modelMapper, UniversityRepository universityRepository1, ModelMapper modelMapper1) {
        this.universityRepository = universityRepository1;
        this.modelMapper = modelMapper1;
    }

    @Override
    public APIResponse create(UniversityRequest universityRequest) {
        final University university = modelMapper.map(universityRequest, University.class);
        try {
            universityRepository.save(university);
        } catch (Exception exception) {
            return APIResponse.error(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return APIResponse.success(university);
    }

    @Override
    public APIResponse get(Long identity) {
        final University university = universityRepository.findById(identity).orElseThrow(() -> DataNotFoundException.of(UNIVERSITY, identity));
        return APIResponse.success(university);
    }

    @Override
    public APIResponse modify(Long identity, UniversityRequest universityRequest) {
        final University university = universityRepository.findById(identity).orElseThrow(() -> DataNotFoundException.of(UNIVERSITY, identity));
        modelMapper.map(universityRequest, university);
        return APIResponse.success(university);
    }

    @Override
    public APIResponse delete(Long identity) {
        if (!universityRepository.existsById(identity)) {
            throw DataNotFoundException.of(UNIVERSITY, identity);
        }
        universityRepository.deleteById(identity);
        return APIResponse.success(HttpStatus.OK.name());
    }
}
