package base.support.errors;

import base.support.errors.exception.ErrorsSerializerException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

/*
@JsonComponent

커스텀 Serializer를 ObjectMapper 의 Module에 등록시켜주는 역할
 */
@JsonComponent
@Slf4j
public class ErrorsSerializer extends JsonSerializer<Errors> {

    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();

        writeFiledError(errors, gen);
        writeGlobalError(errors, gen);

        gen.writeEndArray();
    }

    private void writeFiledError(Errors errors, JsonGenerator gen) {
        errors.getFieldErrors().forEach(e -> {
            try {
                gen.writeStartObject();

                writeCommonErrors(gen, ErrorsProperty.FIELD, e.getField(), ErrorsProperty.OBJECT_NAME, e.getObjectName(), ErrorsProperty.CODE, e.getCode());
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
    }

    private void writeCommonErrors(JsonGenerator gen, ErrorsProperty objectName, String objectName2, ErrorsProperty code, String code2, ErrorsProperty defaultMessage, String defaultMessage2) throws IOException {
        gen.writeStringField(objectName.getPropertyName(), objectName2);
        gen.writeStringField(code.getPropertyName(), code2);
        gen.writeStringField(defaultMessage.getPropertyName(), defaultMessage2);
    }

    private void writeGlobalError(Errors errors, JsonGenerator gen) {
        errors.getGlobalErrors().forEach(e -> {
            try {
                gen.writeStartObject();

                writeCommonErrors(gen, ErrorsProperty.OBJECT_NAME, e.getObjectName(), ErrorsProperty.CODE, e.getCode(), ErrorsProperty.DEFAULT_MESSAGE, e.getDefaultMessage());

                gen.writeEndObject();
            } catch (IOException exception) {
                throw new ErrorsSerializerException(exception);
            }
        });
    }
}