package domain.initData

import domain.entity.WiseSaying
import domain.entity.quotes

class InitData {
    init {
        initData(quotes)
        println("초기 데이터 생성")
    }

    fun initData(qutes: MutableList<WiseSaying>) {
        quotes.add(WiseSaying(1, "실패는 새로운 시작이다.", "헨리 포드"))
        quotes.add(WiseSaying(2, "계획 없는 목표는 그냥 바램에 불과하다", "릭 워렌"))
        quotes.add(WiseSaying(3, "가장 어려운 일은 스스로를 깨끗이 닦는 일이다.", "미로"))
        quotes.add(WiseSaying(4, "네 자신을 믿어라. 너 자신이 가장 큰 기적이다.", "브레이크"))
        quotes.add(WiseSaying(5, "성공은 준비된 사람을 만나게 된다.", "에드 윈 랜델"))
        quotes.add(WiseSaying(6, "성공의 비결은 실패를 견뎌내는 데 있다.", "윈스턴 처칠"))
        quotes.add(WiseSaying(7, "시작이 반이다.", "아리스토텔레스"))
    }
}