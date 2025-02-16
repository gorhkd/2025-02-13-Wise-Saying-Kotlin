fun main() {
    val qutes = mutableListOf<WiseSaying>()
    initData(qutes)

    println("== 명언 앱 ==")
    println("등록, 목록, 삭제 중 하나를 입력해주세요.")

    while (true) {
        print("명령) ")
        val cmd = readln()

        when (cmd) {
            "등록" -> create(qutes)
            "목록" -> show(qutes)
            "삭제" -> delete(qutes)
            "수정" -> modify(qutes)
            "종료" -> {
                print("프로그램을 종료합니다.")
                break
            }
            else -> println("잘못된 요청입니다. 다시 요청 부탁드립니다.")
        }
    }
}

    data class WiseSaying(val number: Int, var author: String = "익명", var content: String)
    fun initData(qutes: MutableList<WiseSaying>) {
        qutes.add(WiseSaying(1, "헨리 포드", "실패는 새로운 시작이다."))
        qutes.add(WiseSaying(2, "릭 워렌", "계획 없는 목표는 그냥 바램에 불과하다"))
        qutes.add(WiseSaying(3, "미로", "가장 어려운 일은 스스로를 깨끗이 닦는 일이다."))
        qutes.add(WiseSaying(4, "브레이크", "네 자신을 믿어라. 너 자신이 가장 큰 기적이다."))
        qutes.add(WiseSaying(5, "에드 윈 랜델", "성공은 준비된 사람을 만나게 된다."))
        qutes.add(WiseSaying(6, "윈스턴 처칠", "성공의 비결은 실패를 견뎌내는 데 있다."))
        qutes.add(WiseSaying(7, "아리스토텔레스", "시작이 반이다."))
    }

    fun create(qutes: MutableList<WiseSaying>) {
        print("명언: ")
        val content = readlnOrNull()?.takeIf { it.isNotBlank() }
            ?: throw WiseSayingException(WiseSayingErrorCode.INVALID_INPUT)

        print("작가: ")
        val author = readlnOrNull()?.takeIf { it.isNotBlank() }
            ?: throw WiseSayingException(WiseSayingErrorCode.INVALID_INPUT)

        var num = qutes.maxOf { it.number } + 1
        qutes.add(WiseSaying(num, author, content))

        println("${num}번 명언이 생성되었습니다.")
    }

    fun show(qutes: MutableList<WiseSaying>) {
        if(qutes.isEmpty()) {
            println("등록된 명언이 없습니다.")
            return
        }

        for(q in qutes.asReversed()) {
           println("${q.number}번 명언  //  작가: ${q.author}  //  명언: ${q.content}")
        }
    }

    fun delete(qutes: MutableList<WiseSaying>) {
        print("삭제 번호: ")
        val num = readlnOrNull()?.toIntOrNull()
            ?: throw WiseSayingException(WiseSayingErrorCode.INVALID_INPUT)

        val wiseSaying = qutes.find { it.number == num }
            ?: throw WiseSayingException(WiseSayingErrorCode.NOT_FOUND_WISE_SAYING)

        qutes.remove(wiseSaying)
        println("${num}번 명언이 삭제되었습니다.")
    }

    fun modify(qutes: MutableList<WiseSaying>) {
        print("수정 번호: ")
        val num = readlnOrNull()?.toIntOrNull()
            ?: throw WiseSayingException(WiseSayingErrorCode.INVALID_INPUT)

        var wiseSaying = qutes.find { it.number == num }
            ?: throw WiseSayingException(WiseSayingErrorCode.NOT_FOUND_WISE_SAYING)

        println("기존 명언: ${wiseSaying.content}")
        print("명언: ")
        val newContent = readlnOrNull()?.takeIf { it.isNotBlank() }
            ?: throw WiseSayingException(WiseSayingErrorCode.INVALID_INPUT)

        println("기존 작가: ${wiseSaying.author}")
        print("작가: ")
        val newAuthor = readlnOrNull()?.takeIf { it.isNotBlank() }
            ?: throw WiseSayingException(WiseSayingErrorCode.INVALID_INPUT)

        wiseSaying.content = newContent
        wiseSaying.author = newAuthor

        println("${num}번 명언이 수정되었습니다.")
    }


// ========================= 예외 처리 ==============================================
    object MyHttpStatus {
        const val OK = 200
        const val NOT_FOUND = 404
        const val BAD_REQUEST = 400
    }

    enum class WiseSayingErrorCode(val status: Int, val code: String, val message: String) {
        NOT_FOUND_WISE_SAYING(MyHttpStatus.NOT_FOUND, "404", "존재하지 않는 명언입니다."),
        INVALID_INPUT(MyHttpStatus.BAD_REQUEST, "400", "유효하지 않은 입력입니다.")
    }

    class WiseSayingException(val errorCode: WiseSayingErrorCode) : RuntimeException(errorCode.message) {
        fun getStatus(): Int = errorCode.status
        fun getCode(): String = errorCode.code
    }
