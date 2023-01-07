package com.cookandroid.umc_android_8_1s
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version=1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun MemoDao(): MemoDao

    companion object{
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase?{
            if(appDatabase == null){
                synchronized(AppDatabase::class.java){
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return appDatabase
        }
    }
}
