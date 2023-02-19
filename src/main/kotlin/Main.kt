import java.awt.List
open class Note(
    val id: String,
    var title: String,
    var text: String
)

data class Comment(
    val id: Int,
    var message: String
)
class Access (
    id: String,
    title: String,
    text: String,
    comment: Comment,
    val privacy: Int,
    val commentPrivacy: Int,
    val canComment: Int
): Note(id, title, text)

object WallService {

    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()
    private val deletedComments = mutableListOf<Comment>()
    private var privateId = 0;
    private var notePrivateId = 0;

    class NoteNotFoundException(message: String) : RuntimeException(message)
    class AccessToNoteDenied(message: String) : RuntimeException(message)
    class AccessToCommentDenied(message: String) : RuntimeException(message)

    fun add(note: Note): String {
        notes.add(note)
        return note.id
    }

    fun createComment(noteId: String, message: String): Int {
        for (note in notes) {
            if (note.id == noteId) {
                comments.add(Comment(id = privateId++, message))
                return comments.last().id
            }
        }
        throw AccessToCommentDenied("Access to comment denied")
    }

    fun delete(noteId: String): Int {
        for (note in notes) {
            if (noteId == note.id) {
                notes.remove(note)
                return 1
            }
        }
        throw NoteNotFoundException("Note not found")
    }

    fun deleteComment(commentId: Int): Int {
        for (comment in comments) {
            if (commentId == comment.id) {
                    deletedComments.add(comment)
                    comments.remove(comment)
                    return 1
            }
        }
        throw AccessToCommentDenied("Access to comment denied")
    }

    fun edit(noteId: String, title: String, text: String): Int {
        for (note in notes) {
            if (note.id == noteId) {
                note.title = title
                note.text = text
                return 1
            }
        }
        throw NoteNotFoundException("Note not found")
    }

    fun editComment(commentId: Int, message: String): Int {
        for (comment in comments) {
            if (comment.id == commentId) {
                comment.message = message
                return 1
            }
        }
        throw AccessToCommentDenied("Access to comment denied")
    }

    fun get(noteIDS: String): MutableCollection<Note> {
        val ids = noteIDS.split(",")
        var notesList = mutableListOf<Note>()

        for (note in notes) {
            for (item in ids) {
                if (note.id == item) {
                    notesList.add(note)
                }
            }
        }

        return notesList
    }

    fun getById(noteId: String): Note {

        for (note in notes) {
            if (noteId == note.id) {
                val access = Access("1", "accessTitle", "accessText", Comment(5, "accessComment"), 1, 2, 3)
                println(access)
                return access
            }
        }
        throw NoteNotFoundException("Note not found")
    }

    fun getComments(noteId: String): Array<Comment> {
        var commentsArray = emptyArray<Comment>()

        for (note in notes) {
            if (noteId == note.id) {
                val noteID = note.id.toInt()
                commentsArray += comments[noteID]
                println(commentsArray)
                return commentsArray
            }
        }
        throw AccessToNoteDenied("Access to note denied")
    }

    fun restoreComment(commentId: String): Int {
        println(deletedComments)
        println(comments)
        for (item in deletedComments) {
            if (item.id == commentId.toInt()) {
                comments.add(item)
                deletedComments.remove(item)
                println(deletedComments)
                println(comments)
                return 1
            }
        }
        throw AccessToCommentDenied("Access to comment denied")
    }
}

fun main() {

}
