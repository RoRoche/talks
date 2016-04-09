public final class FragmentRepoDetailBuilder {

  private final Bundle mArguments = new Bundle();

  public FragmentRepoDetailBuilder(@NonNull Long itemId) {

    mArguments.putLong("itemId", itemId);
  }

  @NonNull
  public static FragmentRepoDetail newFragmentRepoDetail(@NonNull Long itemId) {
    return new FragmentRepoDetailBuilder(itemId).build();
  }

  public static final void injectArguments(@NonNull FragmentRepoDetail fragment) {
    Bundle args = fragment.getArguments();
    if (args == null) {
      throw new IllegalStateException("No arguments set. Have you setup this Fragment with the corresponding FragmentArgs Builder? ");
    }

    if (!args.containsKey("itemId")) {
      throw new IllegalStateException("required argument itemId is not set");
    }
    fragment.mItemId = args.getLong("itemId");
  }

  @NonNull
  public FragmentRepoDetail build() {
    FragmentRepoDetail fragment = new FragmentRepoDetail();
    fragment.setArguments(mArguments);
    return fragment;
  }

  @NonNull
  public <F extends FragmentRepoDetail> F build(@NonNull F fragment) {
    fragment.setArguments(mArguments);
    return fragment;
  }
}
