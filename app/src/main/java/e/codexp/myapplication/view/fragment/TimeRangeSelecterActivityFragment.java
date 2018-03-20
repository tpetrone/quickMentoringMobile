package e.codexp.myapplication.view.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import e.codexp.myapplication.R;
import me.tittojose.www.timerangepicker_library.TimeRangePickerDialog;


/**
 * A placeholder fragment containing a simple view.
 */
public class TimeRangeSelecterActivityFragment extends Fragment implements TimeRangePickerDialog.OnTimeRangeSelectedListener {
    Button selectTimeRangeButton;
    TextView timeRangeSelectedTextView;
    public static final String TIMERANGEPICKER_TAG = "timerangepicker";

    public TimeRangeSelecterActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        selectTimeRangeButton = (Button) getActivity().findViewById(R.id.btSelectTimeRangeFragment);
        timeRangeSelectedTextView = (TextView) getActivity().findViewById(R.id.SelectedTimeRangeFragment);
        if (savedInstanceState != null) {
            TimeRangePickerDialog tpd = (TimeRangePickerDialog) getActivity().getSupportFragmentManager()
                    .findFragmentByTag(TIMERANGEPICKER_TAG);
            if (tpd != null) {
                tpd.setOnTimeRangeSetListener(this);
            }
        }
        selectTimeRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TimeRangePickerDialog timePickerDialog = TimeRangePickerDialog.newInstance(
                        TimeRangeSelecterActivityFragment.this, false);
                timePickerDialog.show(getActivity().getSupportFragmentManager(), TIMERANGEPICKER_TAG);
            }
        });
    }

    @Override
    public void onTimeRangeSelected(int startHour, int startMin, int endHour, int endMin) {
        String startTime = startHour + " : " + startMin;
        String endTime = endHour + " : " + endMin;
        timeRangeSelectedTextView.setText(startTime + "\n" + endTime);
    }
}
