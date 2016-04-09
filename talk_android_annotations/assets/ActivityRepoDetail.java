@IntentBuilder
public class ActivityRepoDetail extends Activity {

    @Extra
    Long mItemId;
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        
        ActivityRepoDetailIntentBuilder.inject(getIntent(), this);
        // ...
    }
}