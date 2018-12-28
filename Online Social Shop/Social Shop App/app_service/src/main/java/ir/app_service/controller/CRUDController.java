package ir.app_service.controller;

import ir.app_service.model.AppPageRequest;
import ir.app_service.model.entity.BaseEntity;
import ir.app_service.model.service.administration.CRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public abstract class CRUDController<E extends BaseEntity> extends Validator {

    private final CRUDService<E> crudService;

    public CRUDController(CRUDService<E> crudService) {
        this.crudService = crudService;
    }

    @GetMapping("read")
    public ResponseEntity read(AppPageRequest pageRequest) {

        return this.crudService.read(pageRequest.getPageable());
    }

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody E entity, BindingResult result) {

        return this.crudService.create(entity, resolveBindingResultMessages(result));
    }

    @PostMapping("update")
    public ResponseEntity update(@Valid @RequestBody E entity, BindingResult result) {

        return this.crudService.update(entity, resolveBindingResultMessages(result));
    }

    @DeleteMapping("delete")
    public ResponseEntity remove(Long id) {

        return this.crudService.remove(id);
    }
}
