// TaskRepository.kt
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.stmpapp.data.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.edit

val Context.taskDataStore: DataStore<Preferences> by preferencesDataStore(name = "tasks")

class TaskRepository(private val context: Context) {
    private val TASKS_KEY = stringPreferencesKey("tasks")
    private val gson = Gson()

    suspend fun saveTasks(tasks: List<Task>) {
        val json = gson.toJson(tasks)
        context.taskDataStore.edit { preferences ->
            preferences[TASKS_KEY] = json
        }
    }

    suspend fun getTasks(): List<Task> {
        val json = context.taskDataStore.data.map { preferences ->
            preferences[TASKS_KEY] ?: "[]"
        }.first()
        val type = object : TypeToken<List<Task>>() {}.type
        return gson.fromJson(json, type)
    }
}