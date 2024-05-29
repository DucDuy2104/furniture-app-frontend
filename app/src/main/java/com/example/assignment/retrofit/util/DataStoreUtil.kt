import android.content.Context
import android.content.SharedPreferences
import com.example.assignment.model.User
import com.google.gson.Gson

class DataStoreUtil(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveUser(user: User) {
        sharedPreferences.edit().apply {
            putString("user", gson.toJson(user))
            apply()
        }
    }

    fun getUser(): User? {
        return sharedPreferences.getString("user", null)?.let {
            gson.fromJson(it, User::class.java)
        }
    }

    fun deleteUser() {
        sharedPreferences.edit().apply {
            remove("user")
            apply()
        }
    }
}