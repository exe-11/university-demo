package uz.exe.unversitydemo.controller.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.exe.unversitydemo.service.core.Creatable;
import uz.exe.unversitydemo.service.core.Modifiable;

import javax.validation.Valid;


public interface CRUDController<ID, C extends Creatable, U extends Modifiable> {

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid C c);

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable ID id);

    @PutMapping("/{id}")
    ResponseEntity<?> modify(@PathVariable ID id, @RequestBody @Valid U u);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable ID id);


}
