package com.tecnical.test.parameta.service;

import com.tecnical.test.parameta.dto.EmpleadoRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class EmpleadoService {

    public void validar(EmpleadoRequest dto) {

        if (dto.getPrimerNombre() == null || dto.getPrimerNombre().isBlank()) {
            throw new IllegalArgumentException("El primer nombre es obligatorio");
        }

        LocalDate fechaNacimiento = parseDate(dto.getFechaNacimiento());
        LocalDate fechaIngreso = parseDate(dto.getFechaIngreso());

        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < 18) {
            throw new IllegalArgumentException("El empleado debe ser mayor de edad");
        }
    }

    public String tiempoTranscurrido(String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaLocal, hoy);
        return periodo.getYears() + " Años, " + periodo.getMonths() + " Meses y " + periodo.getDays() + " Dias";
    }

    private LocalDate parseDate(String fecha) {
        try {
            return LocalDate.parse(fecha);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha inválido: " + fecha);
        }
    }
}
