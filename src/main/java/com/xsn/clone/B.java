package com.xsn.clone;

import lombok.Data;

import java.io.Serializable;

@Data
public class B implements Cloneable, Serializable {
    String b1;
    String b2;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (B) super.clone();
    }
}
