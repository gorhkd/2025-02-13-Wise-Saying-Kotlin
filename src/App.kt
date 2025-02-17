import domain.controller.WiseSayingController
import domain.exception.WiseSayingException
import domain.initData.InitData
import java.util.*

class App {
    private val scanner = Scanner(System.`in`)
    private val wiseSayingController = WiseSayingController()

    fun run() {
        InitData()
        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")
            val cmd = scanner.nextLine().trim()
try {
    when (cmd) {
        "등록" -> wiseSayingController.create()
        "목록" -> wiseSayingController.show()
        "삭제" -> wiseSayingController.delete()
        "수정" -> wiseSayingController.modify()
        "종료" -> {
            println("시스템을 종료합니다.")
            return
        }

        else -> println("잘못된 명령입니다. 다시 입력해주세요.")
    }
} catch (e : WiseSayingException) {
    println(e.errorCode.message)
}
        }
    }
}