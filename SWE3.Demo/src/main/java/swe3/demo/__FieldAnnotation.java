package swe3.demo;

import java.lang.reflect.Method;
import swe3.demo.annotations.*;
import swe3.demo.show.Program;



/** Field annotation helper class. */
final class __FieldAnnotation 
{
    Class _xxx = Program.class; // TODO: remove!
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // private members                                                                                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Field name. */
    private String _fieldName = "";
    
    /** Column name. */
    private String _columnName = "";
    
    /** Column type. */
    private Class _columnType = Void.class;
    
    /** Assignment table. */
    private String _assignmentTable = null;
        
    /** Remote column name. */
    private String _remoteColumnName = null;
    
    /** Field flag. */
    private boolean _isField = false;
    
    /** Primary key flag. */
    private boolean _isPk = false;
    
    /** Foreign key flag. */
    private boolean _isFk = false;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // constructors                                                                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Creates a new instance of this class.
     * @param m Method. */
    public __FieldAnnotation(Method m)
    {
        field fann = m.getAnnotation(field.class);
        if(fann != null)
        {
            _isField = true;
            if((fann.fieldName()  != null) && (!fann.fieldName().equals("")))  { _fieldName = fann.fieldName(); }
            if((fann.columnName() != null) && (!fann.columnName().equals(""))) { _columnName = fann.columnName(); }
            if((fann.columnType() != null) && (!fann.columnType().equals(Void.class))) { _columnType = fann.columnType(); }
        }
        
        pk pkann = m.getAnnotation(pk.class);
        if(pkann != null)
        {
            _isField = _isPk = true;
            if((pkann.fieldName()  != null) && (!pkann.fieldName().equals("")))  { _fieldName = pkann.fieldName(); }
            if((pkann.columnName() != null) && (!pkann.columnName().equals(""))) { _columnName = pkann.columnName(); }
            if((pkann.columnType() != null) && (!pkann.columnType().equals(Void.class))) { _columnType = pkann.columnType(); }
        }
        
        fk fkann = m.getAnnotation(fk.class);
        if(fkann != null)
        {
            _isField = _isFk = true;
            if((fkann.fieldName()  != null) && (!fkann.fieldName().equals(""))) { _fieldName = fkann.fieldName(); }
            if((fkann.columnName() != null) && (!fkann.columnName().equals(""))) { _columnName = fkann.columnName(); }
            if((fkann.columnType() != null) && (!fkann.columnType().equals(Void.class))) 
            { 
                _columnType = fkann.columnType();
                _xxx = fkann.columnType();
            }
        }
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the field name.
     * @return Field name. */
    public String getFieldName()
    {
        return _fieldName;
    }
    
    
    /** Gets the column name.
     * @return Column name. */
    public String getColumnName()
    {
        return _columnName;
    }
    
    
    /** Gets the column type.
     * @return Column type. */
    public Class getColumnType()
    {
        return _columnType;
    }
    
    
    /** Gets the assignment table name.
     * @return Table name. */
    public String getAssignmentTable()
    {
        return _assignmentTable;
    }
    
    
    /** Gets the remote column name.
     * @return Column name. */
    public String getRemoteColumnName()
    {
        return _remoteColumnName;
    }
    
    
    /** Gets if the method is part of a field.
     * @return Returns TRUE for fields, otherwise FALSE. */
    public boolean isField()
    {
        return _isField;
    }
    
    
    /** Gets if the method is part of a primary key.
     * @return Returns TRUE for primary keys, otherwise FALSE. */
    public boolean isPrimaryKey()
    {
        return _isPk;
    }
    
    
    /** Gets if the method is part of a foreign key.
     * @return Returns TRUE for foreign keys, otherwise FALSE. */
    public boolean isForeignKey()
    {
        return _isFk;
    }
}
