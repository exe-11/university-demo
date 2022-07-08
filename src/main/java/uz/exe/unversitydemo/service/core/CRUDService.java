package uz.exe.unversitydemo.service.core;

import uz.exe.unversitydemo.payload.Response;

public interface CRUDService<R extends Response, C extends Creatable, M extends Modifiable, ID> {
    R create(C c);

    R get(ID identity);

    R modify(ID identity, M m);

    R delete(ID identity);
}
