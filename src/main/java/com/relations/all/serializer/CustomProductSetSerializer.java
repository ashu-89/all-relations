package com.relations.all.serializer;



import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.relations.all.model.Product;

import java.io.IOException;
import java.util.Set;

public class CustomProductSetSerializer extends JsonSerializer<Set<Product>> {

    @Override
    public void serialize(Set<Product> productSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Product product : productSet) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", product.getId().toString());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
