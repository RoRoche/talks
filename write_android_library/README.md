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

# ==1.== Design it

---

## ==1.1.== Global approach

* Modeling with [PlantUML](http://plantuml.com/)

* Write immutable objects (because [Objects Should Be Immutable](http://www.yegor256.com/2014/06/09/objects-should-be-immutable.html))
	* <https://github.com/google/auto/tree/master/value>

* Failure strategy: fail safe vs. fail fast
	* Fail safe with resilience (recover, retry)
	* Fail fast with preconditions

* Lazy evaluation (native in Kotlin)

---

## ==1.2.== Reactive programming

* [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)
	> RxJava - Reactive Extensions for the JVM - a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
	
	> RxAndroid - RxJava bindings for Android

* Observables, subscribers
* Asynchronous programming (schedulers)
* Functional operators

---

### Benefits

&nbsp;

* Responsive

&nbsp;

* Resilient

&nbsp;

* Message-driven

---

## ==1.3.== Annotations and compile-time processing

&nbsp;

* Java module containing annotation(s)

&nbsp;

* Java module containing processor

&nbsp;

* Android application module to demonstrate it

---

### Useful libraries

* [JavaPoet](https://github.com/square/javapoet) (and now [KotlinPoet](https://github.com/square/kotlinpoet))
	> A Java API for generating .java source files.
* [AutoService](https://github.com/google/auto/tree/master/service)
	> A configuration/metadata generator for java.util.ServiceLoader-style service providers
* [Compile Testing](https://github.com/google/compile-testing)
	> Testing tools for javac and annotation processors

---

# ==2.== Check it

---

## ==2.1.== Testing strategy

&nbsp;

* Fluent assertions (ex.: [AssertJ](http://joel-costigliola.github.io/assertj/), [truth](https://github.com/google/truth))

&nbsp;

* BDD frameworks (ex.: [JGiven](http://jgiven.org/), [Cucumber](https://cucumber.io/))

&nbsp;

* Code coverage and mutation testing (ex.: [Zester](https://github.com/zalando/zester))

---

## ==2.2.== Static analysis

* [Sonar](https://plugins.jetbrains.com/plugin/7238-sonarqube-community-plugin)
* [Lint](http://www.sonarlint.org/intellij/)
* [FindBugs](https://plugins.jetbrains.com/plugin/3847-findbugs-idea)
* [PMD/CPD](http://pmd.sourceforge.net/pmd-4.3.0/cpd.html)
* [Error Prone](http://errorprone.info/index)
* [Android support annotations](https://developer.android.com/studio/write/annotations.html)

---

# ==3.== Ship it

---

## ==3.1.== Extra information

&nbsp;

* Documenting using Markdown (`README.md`)

&nbsp;

* Generate [Javadoc](https://github.com/vanniktech/gradle-android-javadoc-plugin)

&nbsp;

* Provide a demo application

---

## ==3.2.== Publication/distribution

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

* Enjoy the ecosystem (RxJava, APT, libraries, etc.)
&nbsp;

* Provide a robust set of tests...
&nbsp;

* ...and a clear API/Javadoc and/or manual and/or demo application
&nbsp;

* Automate whatever is possible with [Gradle](https://gradle.org/)

---

## Addendum: some helpful libraries

* <https://github.com/android10/arrow> _(Optional, Preconditions, etc.)_
* <http://www.pojomatic.org/>
* <http://www.vavr.io/> _(Lazy, Option, Try, etc.)_
* <https://github.com/jhalterman/failsafe>
* and so on: 
	* <https://github.com/cxxr/better-java>
	* <https://github.com/KotlinBy/awesome-kotlin>

---

# Bibliography (1/2)

* <http://www.yegor256.com/elegant-objects.html>
* <http://www.yegor256.com/2015/08/25/fail-fast.html>
* <http://liviutudor.com/2012/06/06/simplify-your-singletons/>
* <http://www.vogella.com/tutorials/RxJava/article.html>
* <http://www.slideshare.net/allegrotech/rxjava-introduction-context>

---

# Bibliography (2/2)

* <http://hannesdorfmann.com/annotation-processing/annotationprocessing101>
* <https://jobs.zalando.com/tech/blog/zester-mutation-testing/>
* <https://www.virag.si/2015/01/publishing-gradle-android-library-to-jcenter/>
* <http://tutos-android-france.com/deployer-librairie-jcenter/>
* <http://slides.com/pivovarit/javaslang-functional-java-done-right#/>

---

# Logo credits

Social Media graphic by <a href="http://www.flaticon.com/authors/pixel-perfect">pixel_perfect</a> from <a href="http://www.flaticon.com/">Flaticon</a> is licensed under <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0">CC BY 3.0</a>. Check out the new logo that I created on <a href="http://logomakr.com" title="Logo Maker">LogoMaker.com</a> https://logomakr.com/7eyQap7eyQap