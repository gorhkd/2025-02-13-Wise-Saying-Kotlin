package domain.controller

import domain.service.WiseSayingService

class WiseSayingController {

    val wiseSayingService = WiseSayingService()

    fun create() {
        val newContent = wiseSayingService.readNonBlankInput("명언) ")
        val newAuthor = wiseSayingService.readNonBlankInput("작가) ")

        val wiseSaying = wiseSayingService.save(newContent, newAuthor)
        println("${wiseSaying.number}번 명언이 생성되었습니다.")
    }

    fun show() {
        val wiseSayingList = wiseSayingService.getAll()
        wiseSayingList.asReversed().forEach { println("${it.number}번 // ${it.author} // ${it.content}") }
    }

    fun delete() {
        val num = wiseSayingService.readOnlyInt("삭제할 번호) ")
        wiseSayingService.delete(num)
        println("${num}번 명언이 삭제가 완료되었습니다")
    }

    fun modify() {
        val num = wiseSayingService.readOnlyInt("수정할 번호) ")
        var wiseSaying = wiseSayingService.getWiseSaying(num)

        println("기존 명언) ${wiseSaying.content}")
        val content = wiseSayingService.readNonBlankInput("명언 변경) ")

        println("기존 작가) ${wiseSaying.author}")
        val author = wiseSayingService.readNonBlankInput("작가 변경) ")

        wiseSayingService.modify(wiseSaying, content, author)
        println("${num}번 명언이 수정되었습니다.")
    }
}