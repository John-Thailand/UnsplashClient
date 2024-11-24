package com.example.unsplashclient.common

// sealedクラスは同じファイル内でのみサブクラスを定義できる
// そのサブクラスは、Success Failure Loadingクラスである
// NetworkResponseの状態がSuccess Failure Loadingのいずれかに限定されるため、予期しない状態を防ぐことがメリット
sealed class NetworkResponse<T> (
    val data: T? = null,
    val error: String? = null,
) {
    class Success<T>(data: T) : NetworkResponse<T>(data = data)
    class Failure<T>(error: String) : NetworkResponse<T>(error = error)
    class Loading<T> : NetworkResponse<T>()
}