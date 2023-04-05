package com.jvictorc.partly.demo.dto;

import com.jvictorc.partly.demo.ComponentsType;
import jakarta.validation.constraints.NotNull;

public record ComponenteForm(
        @NotNull
        ComponentsType type
) {
}
