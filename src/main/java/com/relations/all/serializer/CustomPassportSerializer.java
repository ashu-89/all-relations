package com.relations.all.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.relations.all.model.Employee;
import com.relations.all.model.Passport;

import java.io.IOException;

public class CustomPassportSerializer extends JsonSerializer<Passport> {
    @Override
    public void serialize(Passport passport, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", passport.getId().toString());
        jsonGenerator.writeStringField("passportId", passport.getPassportId());
        jsonGenerator.writeStringField("name", passport.getName());
        jsonGenerator.writeStringField("dateIssued", passport.getDateIssued().toString());
        jsonGenerator.writeStringField("dateOfExpiry", passport.getDateOfExpiry().toString());

        // Serialize only the necessary information from the Employee
        Employee employee = passport.getEmployee();
        if (employee != null) {
            jsonGenerator.writeObjectFieldStart("employee");
            jsonGenerator.writeStringField("id", employee.getId().toString());
            jsonGenerator.writeStringField("name", employee.getName());
            // Add other fields as needed
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndObject();
    }
}
