@AutoValue
public abstract class Person {
    @ColumnName("name")
    abstract String name();
    @ColumnName("surname")
    abstract String surname();
    @ColumnName("age")
    abstract int age();

    public static Person create(final String psName, final String psSurname, final int piAge) {
        return new AutoValue_Person(psName, psSurname, piAge);
    }

    public static Person create(@NonNull final Cursor poCursor) {
        return AutoValue_Person.createFromCursor(poCursor);
    }

    abstract ContentValues toContentValues();
}