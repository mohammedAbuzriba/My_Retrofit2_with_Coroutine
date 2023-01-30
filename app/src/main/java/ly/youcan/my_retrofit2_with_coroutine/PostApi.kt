package ly.youcan.my_retrofit2_with_coroutine

import ly.youcan.my_retrofit2_with_coroutine.Constans.END_POINT_GET
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {
    @GET(END_POINT_GET)
    suspend fun getAllPosts():Response<List<PostItem>>
}