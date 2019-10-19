package ru.alekseyk.testskblab.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity


private const val TABLE_NAME = "UserDbEntity"

@Dao
interface UserDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Observable<List<UserDbEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id LIKE :id")
    fun getById(id: Int): Single<List<UserDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: UserDbEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<UserDbEntity>): Completable

    @Delete
    fun delete(item: UserDbEntity): Completable

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll(): Completable

    @Query("SELECT * FROM $TABLE_NAME WHERE isSignIn LIKE '%' || :status || '%'")
    fun getCurrentAccount(status: Boolean = true): Single<List<UserDbEntity>>

}