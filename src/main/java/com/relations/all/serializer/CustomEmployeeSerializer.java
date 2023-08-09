package com.relations.all.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.relations.all.model.Employee;

import java.io.IOException;

public class CustomEmployeeSerializer extends JsonSerializer<Employee> {
    @Override
    public void serialize(Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject(); //{

        jsonGenerator.writeObjectField("id", employee.getId().toString());
        jsonGenerator.writeStringField("name", employee.getName());
        jsonGenerator.writeNumberField("age", employee.getAge());
        jsonGenerator.writeStringField("sex", employee.getSex());
        jsonGenerator.writeStringField("city", employee.getCity());

        //Avoid infinite recursion by skipping employee field in company object here
        jsonGenerator.writeObjectFieldStart("company");
        jsonGenerator.writeObjectField("companyId", employee.getCompany().getId().toString());
        jsonGenerator.writeObjectField("name", employee.getCompany().getName());
        //jsonGenerator.writeObjectField("products", employee.getCompany().getProducts());
        jsonGenerator.writeEndObject();

        //Employee and passport have 1 to 1 mapping
        // Avoid infinite recursion by skipping passport field of Employee object when serializing the passport object


        jsonGenerator.writeObjectFieldStart("passport");
        jsonGenerator.writeStringField("id", employee.getPassport().getId().toString());
        jsonGenerator.writeStringField("passportId", employee.getPassport().getPassportId());
        jsonGenerator.writeStringField("name", employee.getPassport().getName());
        jsonGenerator.writeStringField("dateIssued", employee.getPassport().getDateIssued().toString());
        jsonGenerator.writeStringField("dateOfExpiry", employee.getPassport().getDateOfExpiry().toString());

        //All employee fields except passport itself
        //This is just for testing purposes, in reality, no need of any employee fields, coz fields like id, name, etc
        //are already present outside the passport object

        //Tested. It works even without commenting these fields
        //But no point again printing employee details here
        //hence commenting
//        jsonGenerator.writeStringField("employeeId", employee.getPassport().getEmployee().getId().toString());
//        jsonGenerator.writeStringField("employeeName", employee.getPassport().getEmployee().getName());
//        jsonGenerator.writeStringField("age", employee.getPassport().getEmployee().getAge().toString());
//        jsonGenerator.writeStringField("sex", employee.getPassport().getEmployee().getSex());
//        jsonGenerator.writeStringField("city", employee.getCity());
//
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject(); //}
    }
}
