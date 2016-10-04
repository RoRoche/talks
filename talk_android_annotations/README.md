---
title: "Android: annotations to the rescue"
author:
- Romain Rochegude
fontsize: 12pt
date: 2016.03.30
output: 
  beamer_presentation:
    theme: "metropolis"
    highlight: tango
---

# Introduction

## Java Annotations

* Since Java 5, JSR 175
* Metadata information into source code
* Compile time and runtime processing
* The `@` character
* Markers, single value annotations, full annotations

\begin{alertblock}{Examples}
`@Override`, `@Deprecated`, etc.
\end{alertblock}

## Goals

* Gain of productivity
    * Don't (re)write boilerplate code
    * Automatic code generation
* Code quality improvements
    * Widely tested and documented third-party libraries
    * Less code to write, less bugs to fix
* What about performances?

## Android compliance

* [android-apt plugin](https://bitbucket.org/hvisser/android-apt) for Android Studio
    * Allows developers to configure a compile time annotation processor as a dependency in the `build.gradle` file
    * Runs annotation processing
    * Example:

~~~groovy
dependencies {
    compile 'a.group:annotation:x.x.x'
    apt 'a.group:processor:x.x.x'
}
~~~

# Views

## [Butter Knife](http://jakewharton.github.io/butterknife/)

* No `findViewById` anymore, elegant binding mechanism
* Simple way to bind resources
* Bind anything: activities, fragments, views, view holders
* Event binding (OnClick, OnCheckedChanged, OnTextChanged, etc.)

\begin{alertblock}{Examples}
\end{alertblock}

~~~java
class ExampleActivity extends Activity {
  @BindView(R.id.title) TextView title;
  @BindString(R.string.subtitle) String subtitle;
  @BindColor(R.color.red) int red;
  // ...
}
~~~

---

* The power of view lists (actions, setters)

\begin{alertblock}{Examples}
\end{alertblock}

~~~java
@BindViews({ R.id.first_name, R.id.middle_name, R.id.last_name })
List<EditText> nameViews;

// ...

ButterKnife.apply(nameViews, View.VISIBILITY, View.GONE);
~~~

---

![FragmentRepoDetail.java (Butter Knife)](assets/FragmentRepoDetail_Butterknife.png)

---

![FragmentRepoDetail&#36;&#36;ViewBinder.java](assets/FragmentRepoDetail__ViewBinder.png)

# Navigation

## [IntentBuilder](https://github.com/emilsjolander/IntentBuilder) and [FragmentArgs](https://github.com/sockeqwe/fragmentargs)

* Problem:
    * Boilerplate and unsafe code to declare a new screen
* Solution:
    * Annotations to declare an Activity/Fragment
    * Annotations to declare (optional) parameter(s) to pass
    * Class generation following the [Builder pattern](http://www.oodesign.com/builder-pattern.html)
    * Method to inject parameter(s) in the target class

---

~~~java
@IntentBuilder
public class ActivityRepoDetail extends Activity {
 @Extra
 Long mItemId;
    
 @Override
 protected void onCreate(final Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_repo_detail);
  ActivityRepoDetailIntentBuilder.inject(getIntent(), this);
 }
}
~~~

---

![ActivityRepoDetailIntentBuilder.java](assets/ActivityRepoDetailIntentBuilder.png)

---

~~~java
@FragmentWithArgs
public class FragmentRepoDetail extends Fragment {
  @Arg
  Long mItemId;
 
  public FragmentRepoDetail() {}
 
  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentArgs.inject(this);
  }
}
~~~

---

![FragmentRepoDetailBuilder.java](assets/FragmentRepoDetailBuilder.png)

# Interactions

## [AutoValue: Cursor Extension](https://github.com/gabrielittner/auto-value-cursor)

* Abstract class with the `@AutoValue` to define POJO
* Simple `@ColumnName` to define the binding between column names and class fields

---

~~~java
@AutoValue
public abstract class Person {
  @ColumnName("name")
  abstract String name();
  @ColumnName("surname")
  abstract String surname();
  @ColumnName("age")
  abstract int age();
  
  public static Person create(Cursor pCursor) {
    return AutoValue_Person.createFromCursor(pCursor);
  }

  abstract ContentValues toContentValues();
}
~~~

---

![&#36;AutoValue_Person.java](assets/_AutoValue_Person.png)

---

![AutoValue_Person.java](assets/AutoValue_Person.png)

# Others

## Others

* ORM: [requery](https://github.com/requery/requery)

* JSON parsing: [LoganSquare](https://github.com/bluelinelabs/LoganSquare)

* Bus: [EventBus](https://github.com/greenrobot/EventBus)

* Saving and restoring instance state: [Icepick](https://github.com/frankiesardo/icepick)

* Easily deal with the result from an activity started for result: [OnActivityResult](https://github.com/vanniktech/OnActivityResult)

* Runtime permissions handling: [PermissionsDispatcher](https://github.com/hotchemi/PermissionsDispatcher)

* and so on: <http://android-arsenal.com/tag/166>

# Write custom annotation processors

## Concepts

\begin{alertblock}{Provide a robust annotations API}
- Define annotations using `@interface`

\end{alertblock}

\begin{alertblock}{Implement the algorithm to parse annotations}
- Define a class that extends `AbstractProcessor`

- Define an entry containing this processor name in `META-INF/services/javax.annotation.processing.Processor`
\end{alertblock}

---

## Generate Java source files: [JavaPoet](https://github.com/square/javapoet)

* Powerful and complete API to describe:
    * static imports,
    * classes, interfaces, enums, anonymous inner classes,
    * fields, parameters, variables,
    * methods, constructors,
    * annotations, javadoc

* Specific wildcards to format the output code 

* Test generated files with Google's _[Compile Testing](https://github.com/google/compile-testing)_ and _[Truth](https://google.github.io/truth/)_

---

~~~java
MethodSpec main = MethodSpec
  .methodBuilder("main")
  .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
  .returns(void.class)
  .addParameter(String[].class, "args")
  .addStatement("$T.out.println($S)", 
                    System.class, 
                    "Hello, JavaPoet!")
  .build();
~~~

---

to

~~~java
public static void main(String[] args) {
    System.out.println("Hello, JavaPoet!");
}
~~~

---

### A must-read article: [ANNOTATION PROCESSING 101
by Hannes Dorfmann](http://hannesdorfmann.com/annotation-processing/annotationprocessing101)

### Another article, including a JCenter publication section: [Android Annotation Processing: POJO string generator](http://brianattwell.com/android-annotation-processing-pojo-string-generator/)

# Conclusion

## Conclusion

* Performance: machine and/or human

* Readable

* Maintainable

