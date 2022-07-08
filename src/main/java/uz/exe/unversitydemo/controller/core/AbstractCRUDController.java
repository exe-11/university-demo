package uz.exe.unversitydemo.controller.core;

import org.springframework.http.ResponseEntity;
import uz.exe.unversitydemo.service.core.CRUDService;
import uz.exe.unversitydemo.service.core.Creatable;
import uz.exe.unversitydemo.service.core.Modifiable;

public abstract class AbstractCRUDController<
        S extends CRUDService,
        ID,
        C extends Creatable,
        U extends Modifiable> implements CRUDController<ID, C, U>{

    protected final S service;

    protected AbstractCRUDController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> create(C c) {
        return ResponseEntity.ok(service.create(c));
    }

    @Override
    public ResponseEntity<?> get(ID id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<?> modify(ID id, U u) {
        return ResponseEntity.ok(service.modify(id, u));
    }

    @Override
    public ResponseEntity<?> delete(ID id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
