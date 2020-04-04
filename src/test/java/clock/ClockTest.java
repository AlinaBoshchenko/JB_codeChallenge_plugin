package clock;

import com.intellij.mock.MockProjectEx;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.TestWindowManager;
import com.intellij.testFramework.PlatformLiteFixture;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.test.AssertionsKt;
import kotlin.text.Regex;

public final class ClockTest extends PlatformLiteFixture {
    private ClockWidget widget;

    public void setUp() throws Exception {
        super.setUp();
        this.initApplication();
        this.myProject = new MockProjectEx(this.getTestRootDisposable());
        WindowManager windowManager = new TestWindowManager();
        PlatformLiteFixture.getApplication().registerService(WindowManager.class, windowManager);
        SetUp registration = new SetUp(this.myProject);
        registration.projectOpened();
        StatusBar statusBar = windowManager.getStatusBar(this.myProject);
        StatusBarWidget statusBarWidget = statusBar.getWidget("IntellijClock");
        if (statusBarWidget == null) {
            throw new TypeCastException("null cannot be cast to non-null type clock.ClockWidget");
        } else {
            this.widget = (ClockWidget)statusBarWidget;
            ClockWidget widget = this.widget;
            widget.install(statusBar);
        }
    }

    public final void testTimeChanges() throws InterruptedException {
        ClockWidget widget = this.widget;
        if (widget == null) {
            Intrinsics.throwNpe();
        }
        String oldTime = widget.time();
        TimeUnit.MINUTES.sleep(1L);
        String currentTime = widget.time();
        AssertionsKt.assertNotEquals(oldTime, currentTime, "Time is constant");
    }

}
