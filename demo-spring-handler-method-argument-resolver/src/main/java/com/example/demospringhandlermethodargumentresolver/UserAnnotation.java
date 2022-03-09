package com.example.demospringhandlermethodargumentresolver;


import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserAnnotation {

}
