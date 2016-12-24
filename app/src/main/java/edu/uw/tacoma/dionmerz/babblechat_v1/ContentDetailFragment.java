package edu.uw.tacoma.dionmerz.babblechat_v1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import edu.uw.tacoma.dionmerz.babblechat_v1.contact.ContactItem;

public class ContentDetailFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String DETAIL_ARG = "detail_arg";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ContactItem> myContacts;

    private OnFragmentInteractionListener mListener;

    public ContentDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContentDetailFragment.
     */
    public static ContentDetailFragment newInstance(String param1, String param2) {
        ContentDetailFragment fragment = new ContentDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = getArguments();

        myContacts = (ArrayList<ContactItem>) bundle.getSerializable("contactsList");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content_detail, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the course text.
        Bundle args = getArguments();
        if (args != null) {
            // Set course based on argument passed in
            updateCourseItemView((ContactItem) args.getSerializable(DETAIL_ARG));
        } else {
            // Set article based on saved instance state defined during onCreateView
            updateCourseItemView(myContacts.get(0));
        }
    }

    public void updateCourseItemView(ContactItem item) {
        TextView courseTitleTextView = (TextView) getActivity().findViewById(R.id.contact_name);
        courseTitleTextView.setText(item.name);
        TextView courseShortDescTextView = (TextView) getActivity().findViewById(R.id.contact_number);
        courseShortDescTextView.setText(item.number);
    }

}
