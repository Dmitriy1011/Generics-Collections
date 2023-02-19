import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService

        val note = service.add(
            Note("1", "note 1", "string 1")
        )
    }

    @Test
    fun create() {
        val service = WallService

        service.add(
            Note("2", "note 2", "string 2")
        )

        service.add(
            Note("3", "note 3", "string 3")
        )

        service.add(
            Note("4", "note 3", "string 3")
        )

        val res = service.add(
            Note("5", "note 3", "string 3")
        )

        println(res)

        service.createComment("2", "some comment 2")
        service.createComment("3", "some comment 3")
        service.createComment("4", "some comment 4")
        service.createComment("5", "some comment 5")
        service.createComment("5", "some comment 5")
    }

    @Test
    fun deleteNote() {
        val service = WallService
        service.delete("1")
    }


    @Test
    fun deleteCom() {
        val service = WallService
        println(service.deleteComment(4))
    }

    @Test
    fun editNote() {
        val service = WallService
        service.edit("3", "IMHO", "This param was changed")
    }

    @Test
    fun editCommentTest() {
        val service = WallService
        service.editComment(0, "This comment was changed :)")
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
        service.restoreComment("4")
    }

    @Test(expected = WallService.NoteNotFoundException::class)
    fun shouldThrow1() {
        val service = WallService
        service.delete("10")
    }

    @Test(expected = WallService.AccessToNoteDenied::class)
    fun shouldThrow2() {
        val service = WallService
        service.getComments("10")
    }

    @Test(expected = WallService.AccessToCommentDenied::class)
    fun shouldThrow3() {
        val service = WallService
        service.restoreComment("10")
    }

}