package com.tecnical.test.parameta.controller;

import com.tecnical.test.parameta.dto.EmpleadoRequest;
import com.tecnical.test.parameta.dto.EmpleadoResponse;
import com.tecnical.test.parameta.service.EmpleadoService;
import com.tecnical.test.parameta.service.EmpleadoSoapClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService service;
    private final EmpleadoSoapClient soapClient;

    public EmpleadoController(EmpleadoService service,
                              EmpleadoSoapClient soapClient) {
        this.service = service;
        this.soapClient = soapClient;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> guardarEmpleado(
            @RequestBody EmpleadoRequest request) {

        service.validar(request);

        soapClient.enviarEmpleado(request);
        EmpleadoResponse response = new EmpleadoResponse();
        response.setPrimerNombre(request.getPrimerNombre());
        response.setSegundoNombre(request.getSegundoNombre());
        response.setPrimerApellido(request.getPrimerApellido());
        response.setSegundoApellido(request.getSegundoApellido());
        response.setTipoDocumento(request.getTipoDocumento());
        response.setDocumento(request.getDocumento());
        response.setFechaNacimiento(request.getFechaNacimiento());
        response.setFechaIngreso(request.getFechaIngreso());
        response.setCargo(request.getCargo());
        response.setSalario(request.getSalario());
        response.setTiempoVinculacion(service.tiempoTranscurrido(request.getFechaIngreso()));
        response.setEdad(service.tiempoTranscurrido(request.getFechaNacimiento()));

        return ResponseEntity.ok(response);
    }
}