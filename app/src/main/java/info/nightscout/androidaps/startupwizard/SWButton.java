package info.nightscout.androidaps.startupwizard;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SWButton extends SWItem {
    private static Logger log = LoggerFactory.getLogger(SWButton.class);

    private Runnable buttonRunnable;
    private int buttonText;
    private SWValidator buttonValidator;

    private Button button;

    SWButton() {
        super(Type.BUTTON);
    }

    public SWButton text(int buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public SWButton action(Runnable buttonRunnable) {
        this.buttonRunnable = buttonRunnable;
        return this;
    }

    public SWButton visibility(SWValidator buttonValidator) {
        this.buttonValidator = buttonValidator;
        return this;
    }

    @Override
    public void generateDialog(View view, LinearLayout layout) {
        Context context = view.getContext();

        button = new Button(context);
        button.setText(buttonText);
        button.setOnClickListener((v) -> {
            if (buttonRunnable != null)
                buttonRunnable.run();
        });
        processVisibility();
        layout.addView(button);
        super.generateDialog(view, layout);
    }

    @Override
    public void processVisibility() {
        if (buttonValidator != null && !buttonValidator.isValid())
            button.setVisibility(View.GONE);
        else
            button.setVisibility(View.VISIBLE);
    }
}
