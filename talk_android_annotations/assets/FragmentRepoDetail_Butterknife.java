@FragmentWithArgs
public class FragmentRepoDetail extends Fragment {

    @Bind(R.id.FragmentRepoDetail_TextView_Description)
    TextView mTextViewDescription;
    @Bind(R.id.FragmentRepoDetail_TextView_Url)
    TextView mTextViewUrl;
    @Bind(R.id.FragmentRepoDetail_TextView_Empty)
    TextView mTextViewEmpty;
    @Bind(R.id.FragmentRepoDetail_TextView_Error)
    TextView mTextViewError;
    @Bind(R.id.FragmentRepoDetail_ProgressBar_Loading)
    ProgressBar mProgressBarLoading;
    @Bind(R.id.FragmentRepoDetail_ContentView)
    LinearLayout mContentView;

    @Override
    public void onViewCreated(final View poView, final Bundle poSavedInstanceState) {
        super.onViewCreated(poView, poSavedInstanceState);

        ButterKnife.bind(this, poView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
