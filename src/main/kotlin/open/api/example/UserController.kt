package open.api.example

import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import io.javalin.plugin.openapi.annotations.OpenApi
import io.javalin.plugin.openapi.annotations.OpenApiContent
import io.javalin.plugin.openapi.annotations.OpenApiParam
import io.javalin.plugin.openapi.annotations.OpenApiResponse

object UserController {
    @OpenApi(
            summary = "Get all users",
            operationId = "getAllUsers",
            tags = ["User"],
            responses = [OpenApiResponse("200", [OpenApiContent(Array<User>::class)])]
    )
    fun getAll(ctx: Context) {
        ctx.json(UserService.getAll())
    }

    @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            tags = ["User"],
            pathParams = [OpenApiParam("userId", Int::class, "The user ID")],
            responses = [
                OpenApiResponse("200", [OpenApiContent(User::class)]),
                OpenApiResponse("400", [OpenApiContent(ErrorResponse::class)]),
                OpenApiResponse("404", [OpenApiContent(ErrorResponse::class)])
            ]
    )
    fun getOne(ctx: Context) {
        ctx.json(UserService.findById(ctx.validPathParamUserId()) ?: throw NotFoundResponse("User not found"))
    }
}

// Prevent duplicate validation of userId
private fun Context.validPathParamUserId() = this.pathParam<Int>("userId").check({ it >= 0 }).get()
