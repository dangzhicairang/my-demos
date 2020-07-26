package com.xsn.cglib;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleClass {
    int a;

    public void action(String name) {
        System.out.println(name);
    }
}
