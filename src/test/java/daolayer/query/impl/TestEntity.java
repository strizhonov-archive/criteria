package daolayer.query.impl;

import daolayer.model.Column;

public class TestEntity {

    private String stringTestField;

    @Column(name = "int_field")
    private int intTestField;

}
