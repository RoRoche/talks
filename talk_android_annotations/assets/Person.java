@AutoValue
public abstract class Person {
    @ColumnName("name")
    abstract String name();
    @ColumnName("surname")
    abstract String surname();
    @ColumnName("age")
    abstract int age();

    public static Person create(Cursor cursor) {
        return AutoValue_Person.createFromCursor(cursor);
    }

    abstract ContentValues toContentValues();
}