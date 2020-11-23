package swe3.demo.annotations;

import java.lang.annotation.*;



/** This marks a getter or setter as a field. */
@Retention(RetentionPolicy.RUNTIME)
public @interface field 
{
    /** Field name. */
    public String fieldName() default "";
    
    /** Column name. */
    public String columnName() default "";
    
    /** Column type. */
    public Class columnType() default Void.class;
    
    /** Nullable flag. */
    public boolean nullable() default false;
}
