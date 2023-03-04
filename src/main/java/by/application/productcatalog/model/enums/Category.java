package by.application.productcatalog.model.enums;

public enum Category {
    COMPUTERS(1),
    ELECTRONICS(2),
    ACCESSORIES(3);

    private int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}