package swe3.demo.annotations;

import java.lang.annotation.*;



/** This marks a field as primary key. */
@Retention(RetentionPolicy.RUNTIME)
public @interface pk
{
    /** Field name. */
    public String fieldName() default "";
    
    /** Column name. */
    public String columnName() default "";
    
    /** Column type. */
    public Class columnType() default Void.class;
}
