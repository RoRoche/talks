abstract class $AutoValue_Person extends Person {

  private final String name;
  private final String surname;
  private final int age;

  $AutoValue_Person(
      String name,
      String surname,
      int age) {
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
    if (surname == null) {
      throw new NullPointerException("Null surname");
    }
    this.surname = surname;
    this.age = age;
  }

  @ColumnName(value = "name")
  @Override
  String name() {
    return name;
  }

  @ColumnName(value = "surname")
  @Override
  String surname() {
    return surname;
  }

  @ColumnName(value = "age")
  @Override
  int age() {
    return age;
  }

  @Override
  public String toString() {
    return "Person{"
        + "name=" + name + ", "
        + "surname=" + surname + ", "
        + "age=" + age
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Person) {
      Person that = (Person) o;
      return (this.name.equals(that.name()))
           && (this.surname.equals(that.surname()))
           && (this.age == that.age());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.name.hashCode();
    h *= 1000003;
    h ^= this.surname.hashCode();
    h *= 1000003;
    h ^= this.age;
    return h;
  }

}