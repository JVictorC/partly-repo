package com.jvictorc.partly.demo;

import com.jvictorc.partly.demo.dto.ComponenteForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/componentes")
public class ComponentsController {
    @Autowired
    KabumServices services;

    @GetMapping
    public List<Component> getAllComponent(@Valid @RequestBody ComponenteForm form) {
        return KabumServices.getComponentInfos(form.type());
    }

}
