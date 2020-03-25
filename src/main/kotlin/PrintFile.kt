import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor

class PrintFile : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val descriptor = getFileChooserDescriptor()
        val files = FileChooser.chooseFiles(descriptor, e.project, null)

        Notifier.info("Open ${files.size} files", e.project)

        if (files.isNullOrEmpty()) {
            Notifier.error("Cannot open file", e.project)
            return
        }
    }

    private fun getFileChooserDescriptor() =
        FileChooserDescriptor(
            true,
            false,
            false,
            false,
            false,
            false
        ).withDescription("Choose file which be printed in console")
}