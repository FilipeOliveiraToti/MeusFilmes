package com.foliveira.pacientes.meusfilmes.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDB(
    @PrimaryKey val id: Int,
    @ColumnInfo val poster_path: String,
    @ColumnInfo val title: String,
)