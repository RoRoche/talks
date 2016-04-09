public class FragmentRepoDetail$$ViewBinder<T extends fr.guddy.androidstarteralt.mvp.repoDetail.FragmentRepoDetail> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558574, "field 'mTextViewDescription'");
    target.mTextViewDescription = finder.castView(view, 2131558574, "field 'mTextViewDescription'");
    view = finder.findRequiredView(source, 2131558575, "field 'mTextViewUrl'");
    target.mTextViewUrl = finder.castView(view, 2131558575, "field 'mTextViewUrl'");
    view = finder.findRequiredView(source, 2131558570, "field 'mTextViewEmpty'");
    target.mTextViewEmpty = finder.castView(view, 2131558570, "field 'mTextViewEmpty'");
    view = finder.findRequiredView(source, 2131558571, "field 'mTextViewError'");
    target.mTextViewError = finder.castView(view, 2131558571, "field 'mTextViewError'");
    view = finder.findRequiredView(source, 2131558572, "field 'mProgressBarLoading'");
    target.mProgressBarLoading = finder.castView(view, 2131558572, "field 'mProgressBarLoading'");
    view = finder.findRequiredView(source, 2131558573, "field 'mContentView'");
    target.mContentView = finder.castView(view, 2131558573, "field 'mContentView'");
  }

  @Override public void unbind(T target) {
    target.mTextViewDescription = null;
    target.mTextViewUrl = null;
    target.mTextViewEmpty = null;
    target.mTextViewError = null;
    target.mProgressBarLoading = null;
    target.mContentView = null;
  }
}