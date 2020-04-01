import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.io.File
import java.nio.charset.Charset

const val PRINT_FILE_TASK = "printFile"

class Action : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val descriptor = FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor()
        val files = FileChooser(descriptor, e.project).choose(e.project)

        if (files.isNullOrEmpty()) {
            Notifier.error("Cannot open file", e.project)
            return
        }

        try {
            printFile(files.first(), e.project!!)
            Notifier.info("Success print file")
        } catch (exception: Exception) {
            Notifier.error("Failure print file", exception, e.project)
        }
    }

    private fun printFile(file: VirtualFile, project: Project) {
        ConsoleOutput.consoleView?.clear()

        val command = createCommand(project.basePath!!, file.path)
        val process = Runtime.getRuntime().exec(command, null, File(project.basePath!!))

        val processHandler: ProcessHandler = OSProcessHandler(process, command, Charset.defaultCharset())
        processHandler.startNotify()
        ConsoleOutput.consoleView?.attachToProcess(processHandler)
    }

    private fun createCommand(projectPath: String, filePath: String): String {
        return "$projectPath/gradlew.bat $PRINT_FILE_TASK -PfilePath=\"$filePath\""
    }
}
