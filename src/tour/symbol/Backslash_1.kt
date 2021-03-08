package tour.symbol

private fun backslash1() {
    `is`()
}

private fun `is`() {
    println("在 java, is 不是保留字; 在 kotlin 則是保留字; \n" +
            "透過反斜線使得 kotlin 可以呼叫 java 的名稱是 kotlin 保留字的情況")
}

private fun backslash2() {

    println("可以用在測試上，可以更清楚目的")
    `user should be signed out when they click logout`()

    println("傳統寫法，這種可讀性較差")
    userShouldBeSignedOutWhenTheyClickLogout()

}

private fun `user should be signed out when they click logout`() {
    // Do test
}

private fun userShouldBeSignedOutWhenTheyClickLogout() {
    // Do test
}
