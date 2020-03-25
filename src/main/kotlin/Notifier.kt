import com.intellij.notification.Notification
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project


object Notifier {
    private val notificationGroup =
        NotificationGroup("Print File To Console Errors", NotificationDisplayType.BALLOON, true)

    fun error(content: String, project: Project? = null) = notify(content, project, NotificationType.ERROR)

    fun info(content: String, project: Project? = null) = notify(content, project, NotificationType.INFORMATION)

    private fun notify(content: String, project: Project?, type: NotificationType): Notification {
        val notification: Notification = notificationGroup.createNotification(content, type)
        notification.notify(project)
        return notification
    }
}
