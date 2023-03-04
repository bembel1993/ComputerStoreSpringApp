package by.application.productcatalog.model.enums;

public enum AccessModeType {
    LOGIN(1),
    LOGOUT(0);

    private int value;

    AccessModeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
