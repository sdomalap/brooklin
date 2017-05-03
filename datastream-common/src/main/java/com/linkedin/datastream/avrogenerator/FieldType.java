package com.linkedin.datastream.avrogenerator;

/**
 * FieldType is an interface to the wrapper classes for different Database Types.
 * Using Oracle as an example:
 *
 * In Oracle there are 3 main types of data you can store:
 *
 * 1. Simple Primitive types
 *    These are the CHARS, VARCHAR2, TIMESTAMP etc. For these types we have the wrapper class
 *    {@link OraclePrimitiveType}
 *
 * 2. Struct Types
 *    Struct Types are better viewed as table with in a table. In some cases developers might
 *    want to build their own type and store it in Oracle. For example you might have a column
 *    with name: Settings, that stores a type: SETTING, (instead of the primitive CHARS or NCHARS).
 *    Now this type SETTING can be modeled with child types that are primitive or might be other
 *    Struct Types. The wrapper class for Struct types are {@link OracleStructType}
 *
 * 3. Array / Collection Types
 *    This is when developers choose to store an associative array with in a single column.
 *    For example you might have a column with name: PhoneNumbers, that stores a type: ARRAY.
 *    Each element in the Array might be type CHAR. The elements of this array can be of
 *    any type [CHARS (primitive), SETTING (struct), or more ARRAY's (array), .. etc]
 *    Note, that Oracle collections adhere to strict types, meaning a collection can only
 *    only one subtype. The wrapper class is {@link OracleCollectionType}
 *
 */
public interface FieldType {
  /* The key for the field type name stored in metadata */
  static final String FIELD_TYPE_NAME = "dbFieldType";

  /* The key for the precision of Number fields */
  static final String PRECISION = "numberPrecision";

  /* The key for the scale of Number fields */
  static final String SCALE = "numberScale";


  /**
   * @return the Schema Name of the Field type
   */
  String getSchemaName();

  /**
   * @return The Field type name in the Database. For example this might be CHAR, VARCHAR or CUSTOM_TYPE
   */
  String getFieldTypeName();

  /**
   * @return The associated Avro Field Name. For example "string", "long", "array"
   */
  String getAvroFieldName();

  /**
   * @return An AvroJson which is a simple HashMap to represent type information for this specific FieldType
   */
  AvroJson toAvro();

  /**
   * @return The metadata for this FieldType
   */
  String getMetadata();
}