import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService

        val note = service.add(
            Note("1", "note 1", "string 1",Comment(1, "just comment"))
        )
    }

    @Test
    fun create() {
        val service = WallService

        service.add(
            Note("2", "note 2", "string 2",Comment(2, "just comment"))
        )

        service.add(
            Note("3", "note 3", "string 3",Comment(3, "just comment"))
        )

        service.add(
            Note("4", "note 3", "string 3",Comment(4, "just comment"))
        )

        service.add(
            Note("5", "note 3", "string 3",Comment(5, "just comment"))
        )

        service.createComment("1", "some comment 1")
        service.createComment("2", "some comment 2")
        service.createComment("3", "some comment 3")
    }

    @Test
    fun deleteNote() {
        val service = WallService
        service.delete("1")
    }


    @Test
    fun deleteCom() {
        val service = WallService
        println(service.deleteComment(2))
    }

    @Test
    fun editNote() {
        val service = WallService
        service.edit("3", "IMHO", "This param was changed")
    }

    @Test
    fun editCommentTest() {
        val service = WallService
        service.editComment(2, "This comment was changed :)")
    }

    @Test
    fun getNotesList() {
        val service = WallService
        service.get("3,4")
    }

    @Test
    fun getNoteById() {
        val service = WallService
        service.getById("3")
    }

    @Test
    fun getCommentById() {
        val service = WallService
        service.getComments("1")
    }

    @Test
    fun restoreCommentById() {
        val service = WallService
        service.restoreComment("2")
    }

}