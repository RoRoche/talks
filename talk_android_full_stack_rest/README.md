---
title: "Android: a full-stack to consume a REST API"
author:
- Romain Rochegude
fontsize: 14pt
date: 2016.04.04
output: 
  beamer_presentation:
    theme: "metropolis"
    highlight: pygments
---

# Introduction

## Introduction

- Well-known concerns:
	- Communicate with remote API
	- Parse content and deal with it
	- Do it asynchronously
	- Notify components of job termination

# REST client: [Retrofit](http://square.github.io/retrofit/)

## REST client: [Retrofit](http://square.github.io/retrofit/)

- Well-known, documented, "must-have" Android library
- Write a Java interface to declare API method
- Annotations to dscribe the HTTP request
	- HTTP method (`@GET`, `@POST`, etc.)
	- URL parameter (`@Path`)
	- query parameter (`@Query`, `@QueryMap`)
	- request body (`@Body`)
	- multipart request body (`@Multipart`, `@Part`)
	- form management (`@FormUrlEncoded`, `@Field`)
	- headers management (`@Headers`)

```java
public interface GitHubService {
    @GET("/users/{user}/repos")
    Call<List<DTORepo>> listRepos(@Path("user") final String psUser);
}
```

- Build at runtime an implementation

```java
final Retrofit loRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .build();
final GitHubService loService = loRetrofit.create(GitHubService.class);
```

- Simple calls

```java
final Call<List<DTORepo>> lloRepos = loService.listRepos("RoRoche");
```

- Converters to (de)serialize HTTP bodies 

```
final Retrofit loRetrofit = new Retrofit.Builder()
                //...
                .addConverterFactory(GsonConverterFactory.create())
                .build();
```

## Conclusion

- To add a new HTTP request:
	- declare DTO class(es) with your parsing strategy
	- declare body class (optional)
	- declare the method in Java interface with suitable annotations
- Minimum amount of code to deal with remote API

# JSON parser: [LoganSquare](https://github.com/bluelinelabs/LoganSquare)

## JSON parser: [LoganSquare](https://github.com/bluelinelabs/LoganSquare)

-	[Faster, according to BlueLine Labs benchmark](https://raw.githubusercontent.com/bluelinelabs/LoganSquare/development/docs/benchmarks.jpg)

- Clear annotations

```java
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class DTORepo {
    @JsonField(name = "id")
    public Integer id;
    @JsonField(name = "name")
    public String name;
    @JsonField(name = "description")
    public String description;
    @JsonField(name = "url")
    public String url;
}
```

- Available [retrofit converter](https://github.com/aurae/retrofit-logansquare)

```java
final Retrofit loRetrofit = new Retrofit.Builder()
                //...
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();
```

- Small library

- [Supports custom types](https://github.com/bluelinelabs/LoganSquare/blob/development/docs/TypeConverters.md)

- **Compile-time**

- Simple parsing methods

```java
// Parse from an InputStream
final InputStream loIS = ...;
final Image loImage = LoganSquare.parse(loIS , Image.class);

// Parse from a String
final String lsJson = ...;
final Image loImage= LoganSquare.parse(lsJson, Image.class); 
```

- Simple serializing methods

```java
// Serialize it to an OutputStream
final OutputStream loOs = ...;
LoganSquare.serialize(loImage, loOs );

// Serialize it to a String
final String lsJson = LoganSquare.serialize(loImage);
```

# Async management: [Android Priority Job Queue (Job Manager)](https://github.com/yigit/android-priority-jobqueue)

## Async management: [Android Priority Job Queue (Job Manager)](https://github.com/yigit/android-priority-jobqueue)

- Job queue to easily schedule background tasks

- Inspired by a [Google I/O 2010 talk on REST client applications](http://www.youtube.com/watch?v=xHXn3Kg2IQE)

- Easy to declare a new taks (extends `Job`) and configure it

```java
public class PostTweetJob extends Job {
    public static final int PRIORITY = 1;
    
    private String text;
    
    public PostTweetJob(String text) {
        super(new Params(PRIORITY).requireNetwork().persist());
    }
    
    @Override
    public void onAdded() {
    }
    
    @Override
    public void onRun() throws Throwable {
        webservice.postTweet(text);
    }
    
    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return RetryConstraint.createExponentialBackoff(runCount, 1000);
    }
    
    @Override
    protected void onCancel(@CancelReason int cancelReason, @Nullable Throwable throwable) {
    }
}
```

- [Job manager configuration](https://github.com/yigit/android-priority-jobqueue/wiki/Job-Manager-Configuration)

```java
final Configuration loConfiguration = new Configuration.Builder(poContext)
				//always keep at least one consumer alive
                .minConsumerCount(1)
                //up to 3 consumers at a time
                .maxConsumerCount(3)
                //3 jobs per consumer
                .loadFactor(3) 
                //wait 2 minute
                .consumerKeepAlive(120) 
                .build();
final JobManager loJobManager = new JobManager(poContext, loConfiguration);
```

- Simple way to create and enqueue a task

```java
final PostTweetJob loPostTweetJob = new PostTweetJob("test");
jobManager.addJobInBackground(loPostTweetJob );
```

# Result propagation: [EventBus](https://github.com/greenrobot/EventBus)

## Result propagation: [EventBus](https://github.com/greenrobot/EventBus)

- Based on the publisher/subscriber pattern (loose coupling)
- Communication between application components
- Small library
- Thread delivery
- Convenient Annotation based API

### Set-up

- Create an event class

```java
public class EventQueryGetReposDidFinish {}
```

- Register your subscriber

```java
eventBus.register(this);
```

and unregister if needed:

```java
eventBus.unregister(this);
```

- Declare subscribing method

```java
@Subscribe(threadMode = ThreadMode.MAIN)
public void onEventQueryGetRepos(@NonNull final EventQueryGetReposDidFinish poEvent) {
	//...
}
```
- Post event

```java
final EventQueryGetReposDidFinish loEvent = //...
eventBus.post(loEvent);
```

# Conclusion

## Conclusion

- Highly based on Java annotations
- Write less code
- Multiple ways to configure it
- Focused on performance and UX consistency
