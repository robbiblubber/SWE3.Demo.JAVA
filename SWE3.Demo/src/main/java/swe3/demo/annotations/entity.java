package swe3.demo.annotations;

import java.lang.annotation.*;



/** This marks an entity class. */
@Retention(RetentionPolicy.RUNTIME)
public @interface entity 
{
    /** Table name. */
    public String tableName() default "";
}
