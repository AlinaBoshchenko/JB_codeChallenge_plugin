package clock;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class SetUp implements ProjectComponent {
    private final Project project;

    @Override
    public void projectOpened() {
        StatusBar bar = WindowManager.getInstance().getStatusBar(project);
        if (bar != null) {
            bar.addWidget(new ClockWidget());
        }
    }

    public SetUp(@NotNull Project project) {
        Intrinsics.checkParameterIsNotNull(project, "project");
        this.project = project;
    }

    @Override
    public String getComponentName() {
        return "IntellijClock";
    }

}
