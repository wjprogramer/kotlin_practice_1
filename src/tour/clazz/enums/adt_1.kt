package tour.clazz.enums

/**
 * ADT: 代數資料類型
 * 用來表示一組子類型的閉集，例如：列舉就是一種簡單的 ADT
 */
enum class StudentStatus {
    NOT_ENROLLED,
    ACTIVE,
    GRADUATED
}

/**
 * 更複雜的 ADT -> 使用 sealed class (密封類別)
 * 類似列舉，但可以更靈活地控制某個子類型
 *
 * - 可以有多個子類別
 *  （有數目限制？<書上沒特別提幾個，可能基於設計邏輯或實際上真有限制？或其他因素，記憶體等？>）
 * - 若想繼承 sealed class，子類別必須位於同一個檔案
 */
sealed class StudentStatus2 {
    /**
     * 沒有狀態的，用 object 即可
     * 但有狀態的，每個學生的 id 不盡相同，需要產生多個實例對應，需使用 class
     */
    object NotEnrolled:                 StudentStatus2()
    class Active(val courseId: String): StudentStatus2()
    object Graduated:                   StudentStatus2()
}

fun main() {

    class Student(var status: StudentStatus)
    val student = Student(StudentStatus.NOT_ENROLLED)

    class Student2(var status: StudentStatus2)
    val student2 = Student2(StudentStatus2.Active("Kotlin101"))
    studentMessage(student2.status)
}

private fun studentMessage(status2: StudentStatus2): String {
    return when(status2) {
        is StudentStatus2.NotEnrolled -> "Please choose a course!"
        is StudentStatus2.Active -> "You are enrolled in: ${status2.courseId}"
        is StudentStatus2.Graduated -> "Congratulations!"
    }
}

