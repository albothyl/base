package base.support.errors;

import lombok.Getter;

@Getter
public enum ErrorsProperty {

    FIELD("field"),
    OBJECT_NAME("objectName"),
    CODE("code"),
    DEFAULT_MESSAGE("defaultMessage"),
    REJECTED_VALUE("rejectedValue");

    private String propertyName;

    ErrorsProperty(String propertyName) {
        this.propertyName = propertyName;
    }
}
