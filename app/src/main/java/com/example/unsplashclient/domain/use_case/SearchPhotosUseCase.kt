package com.example.unsplashclient.domain.use_case

import com.example.unsplashclient.common.NetworkResponse
import com.example.unsplashclient.data.remote.toPhotos
import com.example.unsplashclient.domain.model.Photo
import com.example.unsplashclient.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository,
) {
    // query(String) -> 検索実行 -> List<Photo>

    // val useCase = SearchPhotoUseCase(repository)
    // useCase("example_query") ← invoke関数が呼び出される

    // Flowは非同期データストリームを表すKotlin Coroutines
    // データが順次、非同期的に発生する状況に対応するために使用される

    // flowはFlowインタフェースを簡単に構築するための関数
    // emitはflow中で使用され、データをFlowストリームに送信する役割を果たす
    operator fun invoke(query: String): Flow<NetworkResponse<List<Photo>>> = flow {
        try {
            emit(NetworkResponse.Loading<List<Photo>>())
            val photos = repository.searchPhotos(query = query).toPhotos()
            emit(NetworkResponse.Success<List<Photo>>(photos))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure<List<Photo>>(e.message.toString()))
        }
    }
}