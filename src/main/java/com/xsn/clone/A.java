package com.xsn.clone;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Data
@Slf4j
public class A implements Cloneable, Serializable {
    String a1;
    String a2;

    B b;

    @Override
    public Object clone() throws CloneNotSupportedException {
        A a = new A();
        a = (A) super.clone();
        a.setB((B) b.clone());
        return a;
    }

    public A myClone() {
        A clone = new A();

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(this);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            clone = (A) objectInputStream.readObject();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return clone;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        A a1 = new A();
        a1.setA1("1");
        a1.setB(new B() {
            {
                setB1("b1");
            }
        });

        A a2 = (A) a1.clone();
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a2 == a1);

        a1.getB().setB1("b2");
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a2 == a1);

        A a3 = a1.myClone();
        System.out.println(a1);
        System.out.println(a3);
        System.out.println(a3 == a1);
    }
}
