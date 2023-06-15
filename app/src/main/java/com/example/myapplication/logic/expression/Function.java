package com.example.myapplication.logic.expression;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbsoluteValue.class, name = "AbsoluteValue"),
        @JsonSubTypes.Type(value = Argument.class, name = "Argument"),
        @JsonSubTypes.Type(value = Cosine.class, name = "Cosine"),
        @JsonSubTypes.Type(value = Cotangent.class, name = "Cotangent"),
        @JsonSubTypes.Type(value = Division.class, name = "Division"),
        @JsonSubTypes.Type(value = Multiplication.class, name = "Multiplication"),
        @JsonSubTypes.Type(value = Num.class, name = "Num"),
        @JsonSubTypes.Type(value = Power.class, name = "Power"),
        @JsonSubTypes.Type(value = Sinus.class, name = "Sinus"),
        @JsonSubTypes.Type(value = Sqrt.class, name = "Sqrt"),
        @JsonSubTypes.Type(value = Sub.class, name = "Sub"),
        @JsonSubTypes.Type(value = Sum.class, name = "Sum"),
        @JsonSubTypes.Type(value = Tangent.class, name = "Tangent"),
        @JsonSubTypes.Type(value = Exp.class, name = "Exp")
})
public interface Function extends Cloneable {
    @NonNull
    Function clone() throws CloneNotSupportedException;

    float evaluate(float x);

    String asString();

    String getStrSingleFunction();

    void setCurrentFunction(Function function);
}
