package base.support;

import base.interfaces.member.exception.ErrorsSerializerException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
@Slf4j
public class ErrorsSerializer extends JsonSerializer<Errors> {

    private static final String FIELD = "field";
    private static final String OBJECT_NAME = "objectName";
    private static final String CODE = "code";
    private static final String DEFAULT_MESSAGE = "defaultMessage";
    private static final String REJECTED_VALUE = "rejectedValue";

    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();

        errors.getFieldErrors().forEach(e -> {
            try {
                gen.writeStartObject();

                gen.writeStringField(FIELD, e.getField());
                gen.writeStringField(OBJECT_NAME, e.getObjectName());
                gen.writeStringField(CODE, e.getCode());
                gen.writeStringField(DEFAULT_MESSAGE, e.getDefaultMessage());

                Object rejectedValue = e.getRejectedValue();

                if (rejectedValue != null) {
                    gen.writeStringField(REJECTED_VALUE, rejectedValue.toString());
                }

                gen.writeEndObject();
            } catch (IOException exception) {
                throw new ErrorsSerializerException(exception);
            }
        });

        errors.getGlobalErrors().forEach(e -> {
            try {
                gen.writeStartObject();

                gen.writeStringField(OBJECT_NAME, e.getObjectName());
                gen.writeStringField(CODE, e.getCode());
                gen.writeStringField(DEFAULT_MESSAGE, e.getDefaultMessage());

                gen.writeEndObject();
            } catch (IOException exception) {
                throw new ErrorsSerializerException(exception);
            }
        });

        gen.writeEndArray();
    }
}