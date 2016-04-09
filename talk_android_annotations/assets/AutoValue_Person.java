final class AutoValue_Person extends $AutoValue_Person {
  AutoValue_Person(String name, String surname, int age) {
    super(name, surname, age);
  }

  static AutoValue_Person createFromCursor(Cursor cursor) {
    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
    String surname = cursor.getString(cursor.getColumnIndexOrThrow("surname"));
    int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
    return new AutoValue_Person(name, surname, age);
  }

  public ContentValues toContentValues() {
    ContentValues values = new ContentValues(3);
    values.put("name", name());
    values.put("surname", surname());
    values.put("age", age());
    return values;
  }
}