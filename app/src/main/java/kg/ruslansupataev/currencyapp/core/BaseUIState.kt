package kg.ruslansupataev.currencyapp.core

abstract class BaseUIState {
    /** Some progress, refreshing **/
    open var isLoading: Boolean = false

    /** To show some error to user **/
    open var showError: String? = null
}