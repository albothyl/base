package base.domain.support.exception;

public class EntityNotFoundException extends DomainLayerException {

    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
