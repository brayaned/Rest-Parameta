# 🌐 Microservicio REST – Empleados

Microservicio REST desarrollado en **Java con Spring Boot** encargado de **validar la información del empleado y orquestar la llamada a un servicio SOAP**.

Este servicio **no persiste datos directamente**.

---

## ⚙️ Tecnologías
- Java 17+
- Spring Boot
- Spring Web (REST)
- Spring Web Services (SOAP Client)
- JAXB (Jakarta)
- Maven

---

## 🌐 Endpoint REST
http://localhost:8081/empleados


---

## ✅ Validaciones
- Campos obligatorios
- Formato de fechas válido
- Empleado mayor de edad

---

## 🔗 Integración SOAP
- SOAP URL: `http://localhost:8080/ws`
- Operación: `guardarEmpleado`

---

## ▶️ Ejecución
```bash
mvn clean spring-boot:run

