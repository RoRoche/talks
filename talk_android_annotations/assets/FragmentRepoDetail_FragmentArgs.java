@FragmentWithArgs
public class FragmentRepoDetail extends Fragment {

    @Arg
    Long mItemId;

    public FragmentRepoDetail() {
    }
    
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }
}
