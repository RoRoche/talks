---
title: "Android: data persistence"
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

- POO basics: modeling the **domain**
- Modeling domain objects and their interactions
- Data bound with a remote API
- Need of a local database

# The native way

## [The "raw" way](https://developer.android.com/training/basics/data-storage/databases.html)

- Subclass `SQLiteOpenHelper`

```java
private static final String SQL_CREATE_ENTRIES =
    "CREATE TABLE REPO (" +
    "_ID INTEGER PRIMARY KEY," +
    "NAME TEXT)";
private static final String SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS REPO ";
```

```java
public class ReposDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "repos.db";

    public ReposDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
```

- Get an instance of `SQLiteOpenHelper`
```java
final ReposDbHelper loDbHelper = new ReposDbHelper(getContext());
```

- Put Information into a Database

```java
final SQLiteDatabase loDB = loDbHelper.getWritableDatabase();

final ContentValues loValues = new ContentValues();
loValues .put("name", "a sample name");

final long llNewRowId = db.insert("REPO", null, loValues);
```

- Read Information from a Database

```java
final SQLiteDatabase loDB = loDbHelper.getReadableDatabase();

final String[] lasProjection = { "_id", "name" };

final String lsSelection = "NAME = ?";
final String[] lasSelectionArgs = { "a sample name" };

final String lsSortOrder = "NAME DESC";

final Cursor loCursor = loDB.query(
    "REPO",
    lasProjection,
    lsSelection,
    lasSelectionArgs,
    null,
    null,
    lsSortOrder
    );
loCursor.moveToFirst();

final long llItemId = loCursor.getLong(
    loCursor.getColumnIndexOrThrow("_ID")
);
```

- Recommandation to setup a ["contract" class](https://developer.android.com/training/basics/data-storage/databases.html#DefineContract)

## [The ContentProvider way](https://developer.android.com/guide/topics/providers/content-provider-creating.html)

- Provide a `ContentProvider` subclass dedicated to the application

```java
public class RepoProvider extends ContentProvider {
}
```

- Define a specific `UriMatcher` and configure available URI

```java
public class RepoProvider extends ContentProvider {
	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI("com.example.app.provider", "repo", 1);
        sUriMatcher.addURI("com.example.app.provider", "repo/#", 2);
    }
}
```

- Override various CRUD methods

```java
public class RepoProvider extends ContentProvider {
	public Cursor query(
        Uri uri,
        String[] projection,
        String selection,
        String[] selectionArgs,
        String sortOrder) {
		//...
        switch (sUriMatcher.match(uri)) {
            case 1:
                if (TextUtils.isEmpty(sortOrder)) {
	                sortOrder = "_ID ASC";
	            }
                break;
            case 2:
                selection = selection + "_ID = " + uri.getLastPathSegment();
                break;
            default:
	            //...
        }
}
```

- Use it through a `ContentResolver` instance

```java
mCursor = getContentResolver().query(
    "com.example.app.provider/repo/2",
    mProjection,
    mSelectionClause,
    mSelectionArgs,
    mSortOrder); 
```

- Refer to the [open-sourced Google's iosched application](https://github.com/google/iosched/tree/master/android/src/main/java/com/google/samples/apps/iosched/provider)

## Async management: [Loader](https://developer.android.com/training/load-data-background/setup-loader.html) and [AsyncQueryHandler](http://developer.android.com/reference/android/content/AsyncQueryHandler.html)

- Perform CRUD operations outside of the main thread

- For example, to query the `ContentProvider` in an `Activity`, make it implementing `LoaderManager.LoaderCallbacks<Cursor>`

```java
public class ReposListActivity extends FragmentActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
}
```

- Start loading data with a loader identifier

```java
getLoaderManager().initLoader(LOADER_REPOS, null, this);
```

- Implement `LoaderCallbacks`...

- ...to create the `CursorLoader`

```java
@Override
public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle)
{
    switch (loaderID) {
        case LOADER_REPOS:
            return new CursorLoader(
                        this,
                        "com.example.app.provider/repo/",
                        mProjection,
                        null,
                        null,
                        null
        );
        default:
            return null;
    }
}
```
- ...and deal with result
```java
@Override
public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
    if(loader.getId() == LOADER_REPOS){
	    // ...
    }
}
```

# The ORM way

## The well-known: ORMLite

- Declare your model using ORMLite annotations

```java
@DatabaseTable(tableName = "REPO", daoClass = DAORepo.class)
public class RepoEntity {
    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
    public long _id;

    @DatabaseField
    public String name;
}
```

- declare the corresponding DAO

```java
public class DAORepo extends extends BaseDaoImpl<RepoEntity, Long> {
    public DAORepo(final ConnectionSource poConnectionSource) throws SQLException {
        this(poConnectionSource, RepoEntity.class);
    }

    public DAORepo(final ConnectionSource poConnectionSource, final Class<RepoEntity> poDataClass) throws SQLException {
        super(poConnectionSource, poDataClass);
    }

    public DAORepo(final ConnectionSource poConnectionSource, final DatabaseTableConfig<RepoEntity> poTableConfig) throws SQLException {
        super(poConnectionSource, poTableConfig);
    }
}
```

- subclass `OrmLiteSqliteOpenHelper`

```java
public class DatabaseHelperAndroidStarter extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "android_starter.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelperAndroidStarter(
	    @NonNull final Context poContext) {
        super(poContext, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(@NonNull final SQLiteDatabase poDatabase, @NonNull final ConnectionSource poConnectionSource) {
        try {
            TableUtils.createTable(poConnectionSource, RepoEntity.class);
        } catch (final SQLException loException) {
            //...
        }
    }

    @Override
    public void onUpgrade(@NonNull final SQLiteDatabase poDatabase, @NonNull final ConnectionSource poConnectionSource, final int piOldVersion, final int piNewVersion) {
        //...
    }
}
```

- get the requested DAO

```java
public DAORepo provideDAORepo(@NonNull final DatabaseHelperAndroidStarter poDatabaseHelperAndroidStarter) {
        try {
            final ConnectionSource loConnectionSource = poDatabaseHelperAndroidStarter.getConnectionSource();
            final DatabaseTableConfig<RepoEntity> loTableConfig = DatabaseTableConfigUtil.fromClass(loConnectionSource, RepoEntity.class);
            return new DAORepo(loConnectionSource);
        } catch (final SQLException loException) {
            //...
        }
        return null;
    }
```

- perform CRUD operations

```java
// create
final RepoEntity loRepoEntity = new RepoEntity("a sample name");
loDaoRepo.create(loRepoEntity );

// read
final QueryBuilder<RepoEntity, Long> loQueryBuilder =
  loDaoRepo.queryBuilder();
loQueryBuilder.where().eq("name", "a sample name");
final PreparedQuery<RepoEntity> loPreparedQuery = loQueryBuilder.prepare();
final List<RepoEntity> lloRepo = loDaoRepo.query(loPreparedQuery);
```

- Performance: [orm-gap gradle plugin](https://github.com/stephanenicolas/ormlite-android-gradle-plugin)

```groovy
buildscript {
  repositories {
    mavenCentral()
  }

  dependencies {
    classpath 'com.github.stephanenicolas.ormgap:ormgap-plugin:1.0.0-SNAPSHOT'
  }
}

apply plugin: 'ormgap'
```

- generate an ORMLite configuration file that boosts DAOs creations
- to use this file

```java
public RepoDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
}
```


## The attractive way: [requery](https://github.com/requery/requery/)

## Async management: RxJava

# Conclusion

## Conclusion

- Personal assessment of each way

|| Raw/ContentProvider | ORMLite | requery |
--- | --- | --- | ---
|setup		 | - |+|+|
|performance     |+|-|+|
|readability     |-|+|+|
|maintainability |-|+|+|
