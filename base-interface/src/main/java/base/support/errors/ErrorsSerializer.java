package base.support.errors;

import base.support.errors.exception.ErrorsSerializerException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
@Slf4j
public class ErrorsSerializer extends JsonSerializer<Errors> {

    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();

        errors.getFieldErrors().forEach(e -> {
            try {
                gen.writeStartObject();

                gen.writeStringField(ErrorsProperty.FIELD.getPropertyName(), e.getField());
                gen.writeStringField(ErrorsProperty.OBJECT_NAME.getPropertyName(), e.getObjectName());
                gen.writeStringField(ErrorsProperty.CODE.getPropertyName(), e.getCode());
                gen.writeStringField(ErrorsProperty.DEFAULT_MESSAGE.getPropertyName(), e.getDefaultMessage());

                Object rejectedValue = e.getRejectedValue();

                if (rejectedValue != null) {
                    gen.writeStringField(ErrorsProperty.REJECTED_VALUE.getPropertyName(), rejectedValue.toString());
                }

                gen.writeEndObject();
            } catch (IOException exception) {
                throw new ErrorsSerializerException(exception);
            }
        });

        errors.getGlobalErrors().forEach(e -> {
            try {
                gen.writeStartObject();

                gen.writeStringField(ErrorsProperty.OBJECT_NAME.getPropertyName(), e.getObjectName());
                gen.writeStringField(ErrorsProperty.CODE.getPropertyName(), e.getCode());
                gen.writeStringField(ErrorsProperty.DEFAULT_MESSAGE.getPropertyName(), e.getDefaultMessage());

                gen.writeEndObject();
            } catch (IOException exception) {
                throw new ErrorsSerializerException(exception);
            }
        });

        gen.writeEndArray();
    }
}