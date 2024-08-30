/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.editors;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author trucn
 */
public class LocalDateTimeEditor extends PropertyEditorSupport {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(LocalDateTime.parse(text, formatter));
    }

    @Override
    public String getAsText() {
        LocalDateTime value = (LocalDateTime) getValue();
        return value != null ? value.format(formatter) : "";
    }
}
