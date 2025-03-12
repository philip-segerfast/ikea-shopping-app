package dev.psegerfast.ikeashoppingapp.cart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}