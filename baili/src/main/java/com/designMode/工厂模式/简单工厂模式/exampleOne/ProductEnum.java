package com.designMode.工厂模式.简单工厂模式.exampleOne;

public enum ProductEnum {
    /**
     * 产品A
     */
    PRODUCT_A("A", "产品A"),
    /**
     * 产品B
     */
    PRODUCT_B("B", "产品B"),
    /**
     * 产品C
     */
    PRODUCT_C("C", "产品C"),
    ;

    private String value;
    private String name;

    ProductEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 根据中文说明获取值对象
     *
     * @param name 中文说明
     * @return 值对象
     */
    public static ProductEnum value(String name) {
        for (ProductEnum b : ProductEnum.values()) {
            if (b.getName()
                    .equals(name)) {
                return b;
            }
        }
        return null;
    }

    /**
     * 根据值获取中文说明对象
     *
     * @param value 值
     * @return 中文说明对象
     */
    public static ProductEnum name(String value) {
        for (ProductEnum b : ProductEnum.values()) {
            if (b.getValue()
                    .equals(value)) {
                return b;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
