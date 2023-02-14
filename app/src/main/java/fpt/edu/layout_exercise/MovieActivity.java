package fpt.edu.layout_exercise;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieActivity extends AppCompatActivity {

    static final int COLLAPSED_PLOT_LENGTH = 235;
    static final String COLLAPSED_PLOT_SUFFIX = " More";
    static final String COLLAPSED_PLOT_SUFFIX_COLOR = "#a33747";

    TextView tvPlot;
    Button btnPlay;
    ViewGroup llMovieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

//        collapse and expand plot summary
        tvPlot = findViewById(R.id.tvPlot);
        String plot = getString(R.string.plot_summary);

        this.collapsePlot(tvPlot, plot);

        tvPlot.setOnClickListener(view -> this.expandAndCollapsePlot(tvPlot, plot));

//        open trailer modal dialog
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(view -> openDialog());
    }

    private void collapsePlot(TextView textView, String str) {
        CharSequence collapsedPlotCharSequence = str.subSequence(0, COLLAPSED_PLOT_LENGTH);

//        suffix with different color
        String suffix = "<font color=" + COLLAPSED_PLOT_SUFFIX_COLOR + ">" + COLLAPSED_PLOT_SUFFIX + "</font>";
        String text = collapsedPlotCharSequence + "..." + suffix;

        textView.setText(Html.fromHtml(text));
    }

    private void expandPlot(TextView textView, String str) {
        textView.setText(str);
    }

    private void expandAndCollapsePlot(TextView textView, String str) {
        Transition transition = new AutoTransition();
        transition.setDuration(300);

//        transition
        llMovieInfo = findViewById(R.id.llMovieInfo);
        TransitionManager.beginDelayedTransition(llMovieInfo, transition);

        if (textView.getText().toString().endsWith(COLLAPSED_PLOT_SUFFIX)) {
            this.expandPlot(textView, str);
        } else {
            this.collapsePlot(textView, str);
        }
    }

    private void openDialog() {
        Dialog dialog = new Dialog(MovieActivity.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_trailer);
        dialog.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
        final VideoView videoview = (VideoView) dialog.findViewById(R.id.vvTrailer);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.angel_has_fallen_trailer_2019);
        videoview.setVideoURI(uri);
        videoview.start();
    }
}
