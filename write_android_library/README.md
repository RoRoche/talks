<!--
$theme: gaia
template: gaia
$size: 16:9
-->

Write an Android library
===

# ![](assets/logo.png)

###### by [Romain Rochegude](https://github.com/RoRoche)

---
<!-- page_number: true -->

# ==Introduction==

---

## Multiple purposes

&nbsp;

* [Database](https://android-arsenal.com/tag/25)
* [Networking](https://android-arsenal.com/tag/65)
* [JSON](https://android-arsenal.com/tag/50)
* UI
* etc.

---

## Multiple types

&nbsp;

* Helper (ex.: [retrofit](http://square.github.io/retrofit/), [jackson](http://wiki.fasterxml.com/JacksonHome), [ButterKnife](http://jakewharton.github.io/butterknife/))


* Structural (ex. : [mosby](http://hannesdorfmann.com/mosby/), [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html))


* Complete feature, customizable with theme (ex.: [ZXing](https://github.com/zxing/zxing), [Android DirectoryChooser](https://github.com/passy/Android-DirectoryChooser))

* UI (custom views or animations, ex.: [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart))

---

## Multiple implementations

&nbsp;

* Pure code (classes and API, ex.: [mosby](http://hannesdorfmann.com/mosby/))

&nbsp;

* Annotation processing at compile time (ex.: [ButterKnife](http://jakewharton.github.io/butterknife/))

&nbsp;

* Annotation processing at runtime and [dynamic proxy](http://docs.oracle.com/javase/7/docs/technotes/guides/reflection/proxy.html) (ex.: [retrofit](http://square.github.io/retrofit/))

---

# ==1.== Common sense

###### Follow OOP principles

---

## Write immutable objets

&nbsp;

###### Why? Because [Objects Should Be Immutable](http://www.yegor256.com/2014/06/09/objects-should-be-immutable.html)

&nbsp;

* <https://github.com/google/auto/tree/master/value>
* <https://github.com/gabrielittner/auto-value-with>
* <https://immutables.github.io/>

---

## Don't use `NULL` references

###### [Why NULL is Bad?](http://www.yegor256.com/2014/05/13/why-null-is-bad.html)

* Use of Optional?
	* <http://fernandocejas.com/2016/02/20/how-to-use-optional-on-android-and-java/>
	* <http://blog.jhades.org/java-8-how-to-use-optional/>
	* <http://www.vavr.io/vavr-docs/#_option>
* or create new object instead of returning `null`

---

## Lazy

&nbsp;

* <http://www.vavr.io/vavr-docs/#_lazy>

&nbsp;

* <http://liviutudor.com/2012/06/06/simplify-your-singletons/>

&nbsp;

* Native in Kotlin

---

## Failure strategy (fail fast vs. fail safe)

* Defensive programming
* Fail fast with preconditions
	* <https://github.com/android10/arrow>
* Fail safe with resilience (recover, retry)
	* <http://www.vavr.io/vavr-docs/#_try>
	* <https://github.com/jhalterman/failsafe>
* [Need Robust Software? Make It Fragile](http://www.yegor256.com/2015/08/25/fail-fast.html)

---

# ==2.== Improve code quality

---

## Automate what’s possible

* Pojomatic
	* <http://www.pojomatic.org/>
	> configurable implementations of the `equals(Object)`, `hashCode()` and `toString()` methods inherited from `java.lang.Object`
* Pojo-tester
	* <http://www.pojo.pl>
	> test your POJO against `equals`, `hashCode`, `toString`, `getters`, `setters` and even `constructors`

---

## Testing strategy

&nbsp;

* Fluent assertions (ex.: [AssertJ](http://joel-costigliola.github.io/assertj/), [truth](https://github.com/google/truth))

&nbsp;

* BDD frameworks (ex.: [JGiven](http://jgiven.org/), [Cucumber](https://cucumber.io/))

&nbsp;

* Code coverage and mutation testing (ex.: [Zester](https://github.com/zalando/zester))

---

## Static analysis

* [Sonar](https://plugins.jetbrains.com/plugin/7238-sonarqube-community-plugin)
* [Lint](http://www.sonarlint.org/intellij/)
* [FindBugs](https://plugins.jetbrains.com/plugin/3847-findbugs-idea)
* [PMD/CPD](http://pmd.sourceforge.net/pmd-4.3.0/cpd.html)
* [Error Prone](http://errorprone.info/index)
* [Android support annotations](https://developer.android.com/studio/write/annotations.html)

---

## Embrace Java ecosystem with existing libraries

&nbsp;

#### <https://github.com/cxxr/better-java>

---

# ==3.== Reactive programming

---

* [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)
	> RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
	
	> RxAndroid - RxJava bindings for Android

&nbsp;

* Observables, subscribers
* Asynchronous programming (schedulers)
* Functional operators

---

## Benefits

&nbsp;

* Responsive

&nbsp;

* Resilient

&nbsp;

* Message-driven

---

##### Make RxJava debugging easier
	
###### <https://github.com/T-Spoon/Traceur>
	
> Easier RxJava2 debugging with better stacktraces

&nbsp;

##### A pretty good example of a rx-based library

###### <https://github.com/tbruyelle/RxPermissions>

&nbsp;

##### And more...

###### <https://android-arsenal.com/tag/38>

---

# ==4.== Annotations and compile-time processing

---

## Structure

&nbsp;

* Java module containing annotation(s)

&nbsp;

* Java module containing processor

&nbsp;

* Android application module to demonstrate it

---

## Useful libraries

* [JavaPoet](https://github.com/square/javapoet) (and now [KotlinPoet](https://github.com/square/kotlinpoet))
	> A Java API for generating .java source files.
* [AutoService](https://github.com/google/auto/tree/master/service)
	> A configuration/metadata generator for java.util.ServiceLoader-style service providers
* [Compile Testing](https://github.com/google/compile-testing)
	> Testing tools for javac and annotation processors

---

## Useful resources

&nbsp;

* <http://hannesdorfmann.com/annotation-processing/annotationprocessing101>
&nbsp;

* <https://github.com/RoRoche/AnnotationProcessorStarter>
&nbsp;

* More implementation examples...
	* <https://android-arsenal.com/tag/166>

---

# ==5.== Extended toolkit

---

&nbsp;

* Modeling using [PlantUML](http://plantuml.com/)

&nbsp;

* Documenting using Markdown

&nbsp;

* Generate [Javadoc](https://github.com/vanniktech/gradle-android-javadoc-plugin)

---

# ==6.== Publication/distribution

---

&nbsp;

* The raw way: `svn externals`, `libs/*.jar`, `libs/*.aar`

&nbsp;

* The modern way: upload files to a repository
	* Private repository (ex.: [Nexus](https://www.sonatype.com/nexus-repository-sonatype))
	* Public repository (ex.: [JCenter](https://bintray.com/bintray/jcenter))
	* Use of [Gradle tasks](https://docs.gradle.org/current/userguide/more_about_tasks.html) (generate JARs/javadoc, sign, upload)

---

# ==Conclusion==

---

&nbsp;

* Follow OOP principles
&nbsp;

* Enjoy the ecosystem (RxJava, APT, etc.)
&nbsp;

* Provide a robust set of tests...
&nbsp;

* ...and a clear JavaDoc and/or manual and/or demo application
&nbsp;

* Automate whatever is possible with [Gradle](https://gradle.org/)

---

# Bibliography

* <http://www.yegor256.com/elegant-objects.html>
* <http://slides.com/pivovarit/javaslang-functional-java-done-right#/>
* <https://jobs.zalando.com/tech/blog/zester-mutation-testing/>
* <http://www.slideshare.net/allegrotech/rxjava-introduction-context>
* <http://www.vogella.com/tutorials/RxJava/article.html>
* <https://www.virag.si/2015/01/publishing-gradle-android-library-to-jcenter/>
* <http://tutos-android-france.com/deployer-librairie-jcenter/>

---

# Logo credits

Social Media graphic by <a href="http://www.flaticon.com/authors/pixel-perfect">pixel_perfect</a> from <a href="http://www.flaticon.com/">Flaticon</a> is licensed under <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0">CC BY 3.0</a>. Check out the new logo that I created on <a href="http://logomakr.com" title="Logo Maker">LogoMaker.com</a> https://logomakr.com/7eyQap7eyQap