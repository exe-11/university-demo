package uz.exe.unversitydemo.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.exe.unversitydemo.entity.Faculty;
import uz.exe.unversitydemo.entity.Group;
import uz.exe.unversitydemo.entity.University;
import uz.exe.unversitydemo.exception.DataNotFoundException;
import uz.exe.unversitydemo.payload.APIResponse;
import uz.exe.unversitydemo.payload.request.FacultyRequest;
import uz.exe.unversitydemo.repository.FacultyRepository;
import uz.exe.unversitydemo.repository.GroupRepository;
import uz.exe.unversitydemo.repository.UniversityRepository;
import uz.exe.unversitydemo.service.core.CRUDService;

import java.util.List;

import static uz.exe.unversitydemo.service.UniversityService.UNIVERSITY;

@Service
public class FacultyService implements CRUDService<APIResponse, FacultyRequest, FacultyRequest, Long> {

    protected static final String FACULTY = "Faculty";
    private final ModelMapper modelMapper;

    private final UniversityRepository universityRepository;

    private final GroupRepository groupRepository;

    private final FacultyRepository facultyRepository;

    public FacultyService(ModelMapper modelMapper, UniversityRepository universityRepository, GroupRepository groupRepository, FacultyRepository facultyRepository) {
        this.modelMapper = modelMapper;
        this.universityRepository = universityRepository;
        this.groupRepository = groupRepository;
        this.facultyRepository = facultyRepository;
    }


    public APIResponse getGroups(Long facultyId) {
        if (!facultyRepository.existsById(facultyId)) {
            throw DataNotFoundException.of(FACULTY, facultyId);
        }
        List<Group> groups = groupRepository.findGroupsByFaculty_Id(facultyId);
        return APIResponse.success(groups);
    }

    @Override
    public APIResponse create(FacultyRequest facultyRequest) {
        final University university = universityRepository.findById(facultyRequest.getUniversity()).orElseThrow(
                () -> DataNotFoundException.of(UNIVERSITY, facultyRequest.getUniversity()));
        final Faculty faculty = modelMapper.map(facultyRequest, Faculty.class);
        faculty.setUniversity(university);
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
