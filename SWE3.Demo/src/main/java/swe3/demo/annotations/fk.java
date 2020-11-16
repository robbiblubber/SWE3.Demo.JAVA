package swe3.demo.annotations;

import java.lang.annotation.*;



/** This marks a field as foreign key. */
@Retention(RetentionPolicy.RUNTIME)
public @interface fk 
{
    /** Field name. */
    public String fieldName() default "";
    
    /** Column name. */
    public String columnName() default "";
    
    /** Column type. */
    public Class columnType() default Void.class;
    
    /** Assignment table. */
    public String assignmentTable() default "";
        
    /** Remote column name. */
    public String remoteColumnName() default "";
}
