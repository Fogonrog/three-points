package com.example.myapplication.serialization;

public class Template {
    public static String replace(String text, float width, float height){
        text = text.replace("{{WIDTH_PLACEHOLDER}}", String.valueOf(width))
                .replace("{{HEIGHT_PLACEHOLDER}}", String.valueOf(height))
                .replace("{{WIDTH_HALF}}", String.valueOf(width / 2))
                .replace("{{HEIGHT_HALF}}", String.valueOf(height / 2))
                .replace("{{INDENT}}", String.valueOf(20));
        return text;
    }
}
