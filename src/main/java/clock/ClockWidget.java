package clock;

import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.Timer;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ClockWidget implements CustomStatusBarWidget {

    private final JLabel label = new JLabel(this.time());

    private final Timer timer = new Timer(1000, it -> ClockWidget.this.getLabel().setText(ClockWidget.this.time()));

    public final JLabel getLabel() {
        return this.label;
    }

    public final String time() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeFormat = "   %02d:%02d   ";
        Object[] hourMin = new Object[]{localDateTime.getHour(), localDateTime.getMinute()};
        String hourMinFormat = String.format(timeFormat, Arrays.copyOf(hourMin, hourMin.length));
        Intrinsics.checkExpressionValueIsNotNull(hourMinFormat, "java.lang.String.format(this, *args)");
        return hourMinFormat;
    }

    public void install(@NotNull StatusBar statusBar) {
        Intrinsics.checkParameterIsNotNull(statusBar, "statusBar");
        this.timer.start();
    }

    public void dispose() {
        this.timer.stop();
    }

    @Override
    public JLabel getComponent() {
        return this.label;
    }

    @Nullable
    @Override
    public WidgetPresentation getPresentation(@NotNull PlatformType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return null;
    }

    public String ID() {
        return "IntellijClock";
    }

}
