public class AsyncFragment extends Fragment {

    static final int INTERNAL_PROGRESS_CONTAINER_ID = 0x00ff0001;
    static final int INTERNAL_CONTENT_CONTAINER_ID = 0x00ff0002;

    View mProgressContainer;
    View mContentContainer;
    boolean mContentShown = true;

    public AsyncFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = getActivity();

        FrameLayout root = new FrameLayout(context);

        // ------------------------------------------------------------------

        LinearLayout pframe = new LinearLayout(context);
        pframe.setId(INTERNAL_PROGRESS_CONTAINER_ID);
        pframe.setOrientation(LinearLayout.VERTICAL);
        pframe.setVisibility(View.GONE);
        pframe.setGravity(Gravity.CENTER);

        ProgressBar progress = new ProgressBar(context, null,
                android.R.attr.progressBarStyleLarge);
        pframe.addView(progress, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        root.addView(pframe, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        // ------------------------------------------------------------------

        FrameLayout lframe = new FrameLayout(context);
        lframe.setId(INTERNAL_CONTENT_CONTAINER_ID);

        root.addView(lframe, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        // ------------------------------------------------------------------

        root.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        return root;
    }

    public void setView(View v) {

        FrameLayout lframe = (FrameLayout) getView().findViewById(INTERNAL_CONTENT_CONTAINER_ID);
        lframe.addView(v, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressContainer = getView().findViewById(INTERNAL_PROGRESS_CONTAINER_ID);
        mContentContainer = getView().findViewById(INTERNAL_CONTENT_CONTAINER_ID);
        setContentShown(false, false);
    }

    @Override
    public void onDestroyView() {
        mContentShown = false;
        mProgressContainer = mContentContainer = null;
        super.onDestroyView();
    }


    public void setContentShown(boolean shown) {
        setContentShown(shown, true);
    }

    public void setContentShownNoAnimation(boolean shown) {
        setContentShown(shown, false);
    }

    private void setContentShown(boolean shown, boolean animate) {

        mProgressContainer = getView().findViewById(INTERNAL_PROGRESS_CONTAINER_ID);
        mContentContainer = getView().findViewById(INTERNAL_CONTENT_CONTAINER_ID);

        if (mContentShown == shown) {
            return;
        }
        mContentShown = shown;
        if (shown) {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        getActivity(), android.R.anim.fade_out));
                mContentContainer.startAnimation(AnimationUtils.loadAnimation(
                        getActivity(), android.R.anim.fade_in));
            } else {
                mProgressContainer.clearAnimation();
                mContentContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(View.GONE);
            mContentContainer.setVisibility(View.VISIBLE);
        } else {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        getActivity(), android.R.anim.fade_in));
                mContentContainer.startAnimation(AnimationUtils.loadAnimation(
                        getActivity(), android.R.anim.fade_out));
            } else {
                mProgressContainer.clearAnimation();
                mContentContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(View.VISIBLE);
            mContentContainer.setVisibility(View.GONE);
        }
    }
}
