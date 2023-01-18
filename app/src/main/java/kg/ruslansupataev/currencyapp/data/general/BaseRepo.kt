package kg.ruslansupataev.currencyapp.data.general

import kg.ruslansupataev.currencyapp.core.Resource
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class BaseRepo {
    protected fun <T> doRequest(block: suspend FlowCollector<Resource<T>>.() -> Unit) = flow {
        emit(Resource.Loading())
        try {
            block()
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    error = e,
                    message = e.localizedMessage ?: "Details has not been provided"
                )
            )
        }
    }
}