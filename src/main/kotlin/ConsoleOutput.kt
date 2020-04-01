import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.ui.ConsoleView
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

class ConsoleOutput : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.title = "Print File"
        val contentManager = toolWindow.contentManager

        consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).console
        val content = contentManager
            .factory
            .createContent(consoleView?.component, "Output", false)
        contentManager.addContent(content)
    }

    companion object {
        var consoleView: ConsoleView? = null
    }
}