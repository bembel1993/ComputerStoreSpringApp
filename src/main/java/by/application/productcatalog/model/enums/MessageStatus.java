package by.application.productcatalog.model.enums;

public enum MessageStatus {
    CREATE("Без ответа"),
    DONE("С ответом");

    private String value;

    MessageStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
