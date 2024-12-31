package deim.urv.cat.homework2;


import jakarta.enterprise.util.Nonbinding;
import jakarta.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Property {
    //  A @Nonbinding argument is not considered for matching.
    @Nonbinding String key() default "";
    @Nonbinding String fileName() default "Config.properties";
}