package pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireRoom

@Dao
interface FireDao {

    @Insert
    suspend fun insert(fire: FireRoom)

    @Insert
    suspend fun insertAll(fires: List<FireRoom>)

    @Query("SELECT * FROM fire ORDER BY timestamp ASC")
    suspend fun getAll(): List<FireRoom>

    @Query("SELECT * FROM fire WHERE id = :id")
    suspend fun getById(id:String): FireRoom

    @Query("DELETE FROM fire")
    suspend fun deleteAll() : Int
}