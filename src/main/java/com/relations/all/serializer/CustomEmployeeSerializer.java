package com.relations.all.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.relations.all.model.Employee;

import java.io.IOException;

public class CustomEmployeeSerializer extends JsonSerializer<Employee> {
    @Override
    public void serialize(Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("id", employee.getId().toString());
        jsonGenerator.writeStringField("name", employee.getName());
        jsonGenerator.writeNumberField("age", employee.getAge());
        jsonGenerator.writeStringField("sex", employee.getSex());
        jsonGenerator.writeStringField("city", employee.getCity());
        jsonGenerator.writeObjectField("company", employee.getCompany()); // Assuming you want to include the whole company details.

        // Avoid infinite recursion by skipping the passport field here.
        jsonGenerator.writeObjectFieldStart("passport");
        jsonGenerator.writeStringField("employeeId", employee.getPassport().getEmployee().getId().toString());
        jsonGenerator.writeStringField("employeeName", employee.getPassport().getEmployee().getName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
