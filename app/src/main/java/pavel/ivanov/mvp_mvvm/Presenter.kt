package pavel.ivanov.mvp_mvvm


class Presenter(private val view: View) {

    private val model = CounterModel()

    fun counterClick(id: ButtonType) {
        when (id) {
            ButtonType.BUTTON1 -> {
                val nextValue = model.next(0)
                view.setButtonText(0, nextValue.toString())
            }
            ButtonType.BUTTON2 -> {
                val nextValue = model.next(1)
                view.setButtonText(1, nextValue.toString())
            }
            ButtonType.BUTTON3 -> {
                val nextValue = model.next(2)
                view.setButtonText(2, nextValue.toString())
            }
        }

    }
}

enum class ButtonType {
    BUTTON1,
    BUTTON2,
    BUTTON3,
}