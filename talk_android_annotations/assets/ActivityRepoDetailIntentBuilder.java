public final class ActivityRepoDetailIntentBuilder {
  private final Long mItemId;

  public ActivityRepoDetailIntentBuilder(Long mItemId) {
    this.mItemId = mItemId;
  }

  public Intent build(Context context) {
    Intent intent = new Intent(context, ActivityRepoDetail.class);
    intent.putExtra("mItemId", mItemId);
    return intent;
  }

  public static void inject(Intent intent, ActivityRepoDetail activity) {
    Bundle extras = intent.getExtras();
    if (extras.containsKey("mItemId")) {
      activity.mItemId = (Long) extras.get("mItemId");
    } else {
      activity.mItemId = null;
    }
  }

  public static Long getMItemId(Intent intent) {
    Bundle extras = intent.getExtras();
    if (extras.containsKey("mItemId")) {
      return (Long) extras.get("mItemId");
    } else {
      return null;
    }
  }
}
