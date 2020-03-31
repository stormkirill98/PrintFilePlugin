import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory


const val PRINT_FILE_TASK = "printFile"

// TODO disable button if not found task

class Action : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val descriptor = FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor()
        val files = FileChooser(descriptor, e.project).choose(e.project)

        Notifier.info("Open ${files.size} files", e.project)

        if (files.isNullOrEmpty()) {
            Notifier.error("Cannot open file", e.project)
            return
        }

        val gradleConnection = getGradleConnection(e.project)

        if (gradleConnection == null) {
            Notifier.error("Cannot connect to gradle", e.project)
            return
        }

        val filePath = files.first().path
        gradleConnection.use {
            gradleConnection.newBuild()
                .forTasks(PRINT_FILE_TASK)
                .withArguments("-PfilePath=$filePath")
                .run()
        }
    }
}
