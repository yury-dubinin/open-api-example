package open.api.example

import cc.vileda.openapi.dsl.info
import cc.vileda.openapi.dsl.openapiDsl
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.plugin.openapi.OpenApiOptions
import io.javalin.plugin.openapi.OpenApiPlugin
import io.javalin.plugin.openapi.ui.SwaggerOptions

fun main() {

    Javalin.create {
        it.registerPlugin(getConfiguredOpenApiPlugin())
        it.defaultContentType = "application/json"
    }.routes {
        path("users") {
            get(UserController::getAll)
            path(":userId") {
                get(UserController::getOne)
            }
        }
    }.start(7001)

    println("Check out ReDoc docs at http://localhost:7001/swagger-docs")
    println("Check out Swagger UI docs at http://localhost:7001/swagger-ui")

}

fun getConfiguredOpenApiPlugin() = OpenApiPlugin(
        OpenApiOptions {
            openapiDsl {
                info {
                    title = "User API"
                    description = "Demo API with 5 operations"
                    version = "1.0.0"
                }
            }
        }.apply {
            path("/swagger-docs") // endpoint for OpenAPI json
            swagger(SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
            defaultDocumentation { doc ->
                doc.json("500", ErrorResponse::class.java)
                doc.json("503", ErrorResponse::class.java)
            }
        }
)
