package open.api.example

object UserService {
    private val users = hashMapOf(
            0 to User(id = 0, name = "Alice", email = "alice@alice.kt"),
            1 to User(id = 1, name = "Bob", email = "bob@bob.kt"),
            2 to User(id = 2, name = "Carol", email = "carol@carol.kt"),
            3 to User(id = 3, name = "Dave", email = "dave@dave.kt")
    )

    fun getAll() = users.values

    fun findById(id: Int): User? = users[id]
}
