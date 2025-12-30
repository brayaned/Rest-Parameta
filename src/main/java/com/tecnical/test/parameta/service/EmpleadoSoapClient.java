package com.tecnical.test.parameta.service;

import com.tecnical.test.parameta.dto.EmpleadoRequest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

@Service
public class EmpleadoSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public EmpleadoSoapClient(Jaxb2Marshaller marshaller) {
        this.webServiceTemplate = new WebServiceTemplate(marshaller);
        this.webServiceTemplate.setDefaultUri("http://localhost:8080/ws");
    }

    public void enviarEmpleado(EmpleadoRequest dto) {
        com.tecnical.test.parameta.generated.GuardarEmpleadoRequest request = new com.tecnical.test.parameta.generated.GuardarEmpleadoRequest();

        request.setPrimerNombre(dto.getPrimerNombre());
        request.setSegundoNombre(dto.getSegundoNombre());
        request.setPrimerApellido(dto.getPrimerApellido());
        request.setSegundoApellido(dto.getSegundoApellido());
        request.setTipoDocumento(dto.getTipoDocumento());
        request.setDocumento(dto.getDocumento());
        request.setFechaNacimiento(toXmlDate(dto.getFechaNacimiento()));
        request.setFechaIngreso(toXmlDate(dto.getFechaIngreso()));
        request.setCargo(dto.getCargo());
        request.setSalario(dto.getSalario());

        webServiceTemplate.marshalSendAndReceive(request);
    }

    private XMLGregorianCalendar toXmlDate(String fecha) {
        try {
            GregorianCalendar cal = GregorianCalendar
                    .from(LocalDate.parse(fecha).atStartOfDay(ZoneId.systemDefault()));

            return DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            throw new RuntimeException("Error creando fecha SOAP");
        }
    }
}