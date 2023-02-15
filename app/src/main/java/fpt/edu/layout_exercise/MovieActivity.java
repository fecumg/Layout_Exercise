package fpt.edu.layout_exercise;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieActivity extends AppCompatActivity {

    static final int COLLAPSED_PLOT_LENGTH = 235;
    static final String COLLAPSED_PLOT_END = " More";
    static final String COLLAPSED_PLOT_END_COLOR = "#a33747";

    TextView tvPlot;
    Button btnPlay;
    ViewGroup llMovieInfo;
    TextView tvHide;
    ViewGroup rlHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        tvHide = findViewById(R.id.tvHide);
        rlHide = findViewById(R.id.rlHide);
        llMovieInfo = findViewById(R.id.llMovieInfo);

        tvPlot = findViewById(R.id.tvPlot);
        String plot = tvPlot.getText().toString();

//        collapse plot summary on creating
        this.collapsePlotWithoutAnimation(tvPlot, plot, rlHide);

//        collapse and expand plot summary on command
        tvHide.setOnClickListener(view -> this.collapsePlot(tvPlot, plot, llMovieInfo, rlHide));
        tvPlot.setOnClickListener(view -> this.expandPlot(tvPlot, plot, llMovieInfo, rlHide));

//        open trailer modal dialog on command
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(view -> openTrailerDialog());
    }

    private void collapsePlotWithoutAnimation(TextView textView, String str, ViewGroup hideTriggerViewGroup) {
        CharSequence collapsedPlotCharSequence = str.subSequence(0, COLLAPSED_PLOT_LENGTH);

//        end word with different color
        String suffix = "<font color=" + COLLAPSED_PLOT_END_COLOR + ">" + COLLAPSED_PLOT_END + "</font>";
        String text = collapsedPlotCharSequence + "..." + suffix;

        textView.setText(Html.fromHtml(text));

        hideTriggerViewGroup.setVisibility(View.GONE);
    }

    private void collapsePlot(TextView textView, String str, ViewGroup TransitionViewGroup, ViewGroup hideTriggerViewGroup) {
        if (! textView.getText().toString().endsWith(COLLAPSED_PLOT_END)) {
            this.beginTransition(TransitionViewGroup);
            this.collapsePlotWithoutAnimation(textView, str, hideTriggerViewGroup);
        }
    }

    private void expandPlot(TextView textView, String str, ViewGroup TransitionViewGroup, ViewGroup hideTriggerViewGroup) {
        if (textView.getText().toString().endsWith(COLLAPSED_PLOT_END)) {
            this.beginTransition(TransitionViewGroup);
            textView.setText(str);
            hideTriggerViewGroup.setVisibility(View.VISIBLE);
        }
    }

    private void beginTransition(ViewGroup TransitionViewGroup) {
        Transition transition = new AutoTransition();
        transition.setDuration(300);

//        begin transition
        TransitionManager.beginDelayedTransition(TransitionViewGroup, transition);
    }

    private void openTrailerDialog() {
        Dialog dialog = new Dialog(MovieActivity.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_trailer);
        dialog.show();

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(layoutParams);
        VideoView videoview = dialog.findViewById(R.id.vvTrailer);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.angel_has_fallen_trailer_2019);
        videoview.setVideoURI(uri);
        videoview.start();
    }
}
