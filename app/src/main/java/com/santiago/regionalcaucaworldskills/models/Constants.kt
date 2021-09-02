package com.santiago.regionalcaucaworldskills.models

class Constants {
    companion object{

        //id de tipo categoria para listar
        var ID_CATEGORIA = 0

        // id para listar producto
        var ID_PRODUCTO = 0


        //politicas

        val KEY_POLITICAS = "politicas"

        //guardar recuerdame
        val KEY_CORREO = "correo"
        val KEY_CONTRASENA = "contrasena"
        val KEY_RECORDAR = "recordar"
        val KEY_STATUS = "status"
        val KEY_NOMBRE = "nombre"
        val KEY_ID_CLIENTE = "idCliente"
        val KEY_TOKEN = "token"




        //bd


        val DB_NAME = "restaurante"
        val DB_VERSION = 11


        //historial
        val TABLE_NAME_HIS = "historial"
        val TABLE_COLUMN_1_HIS = "id"
        val TABLE_COLUMN_2_HIS = "fecha"
        val TABLE_COLUMN_3_HIS = "nombre"
        val TABLE_COLUMN_4_HIS = "total"



        val TABLE_CREATE_HIS  = " CREATE TABLE " + TABLE_NAME_HIS + " ( " +
                TABLE_COLUMN_1_HIS + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                TABLE_COLUMN_2_HIS + " TEXT NOT NULL , " +
                TABLE_COLUMN_3_HIS + " TEXT NOT NULL , " +
                TABLE_COLUMN_4_HIS + " iNTEGER NOT NULL ) "




        //pedido
        val TABLE_NAME_PED = "pedido"
        val TABLE_COLUMN_1_PED = "id"
        val TABLE_COLUMN_2_PED = "idProducto"
        val TABLE_COLUMN_3_PED = "nombre"
        val TABLE_COLUMN_4_PED = "descripcion"
        val TABLE_COLUMN_5_PED = "imagen"
        val TABLE_COLUMN_6_PED = "precioUnidad"
        val TABLE_COLUMN_7_PED = "precioTotal"
        val TABLE_COLUMN_8_PED = "cantidad"



        val TABLE_CREATE_PED  = " CREATE TABLE " + TABLE_NAME_PED + " ( " +
                TABLE_COLUMN_1_PED + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                TABLE_COLUMN_2_PED + " INTEGER NOT NULL , " +
                TABLE_COLUMN_3_PED + " TEXT NOT NULL , " +
                TABLE_COLUMN_4_PED + " TEXT NOT NULL , " +
                TABLE_COLUMN_5_PED + " TEXT NOT NULL , " +
                TABLE_COLUMN_6_PED + " INTEGER NOT NULL , " +
                TABLE_COLUMN_7_PED + " INTEGER NOT NULL , " +
                TABLE_COLUMN_8_PED + " INTEGER NOT NULL ) "


        //CATEGORIAID
        val TABLE_NAME_CAT_ID = "categoriaid"
        val TABLE_COLUMN_1_CAT_ID = "id"
        val TABLE_COLUMN_2_CAT_ID = "nombre"
        val TABLE_COLUMN_3_CAT_ID = "descripcion"
        val TABLE_COLUMN_4_CAT_ID = "precio"
        val TABLE_COLUMN_5_CAT_ID = "imagen"
        val TABLE_COLUMN_6_CAT_ID = "tipoCategoria"


        val TABLE_CREATE_CAT_ID  = " CREATE TABLE " + TABLE_NAME_CAT_ID + " ( " +
                TABLE_COLUMN_1_CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                TABLE_COLUMN_2_CAT_ID + " TEXT NOT NULL , " +
                TABLE_COLUMN_3_CAT_ID + " TEXT NOT NULL , " +
                TABLE_COLUMN_4_CAT_ID + " INTEGER NOT NULL , " +
                TABLE_COLUMN_5_CAT_ID + " BLOB NOT NULL , " +
                TABLE_COLUMN_6_CAT_ID + " INTEGER NOT NULL ) "

        //CATEGORIA
        val TABLE_NAME_CAT = "categoria"
        val TABLE_COLUMN_1_CAT = "id"
        val TABLE_COLUMN_2_CAT = "nombre"
        val TABLE_COLUMN_3_CAT = "descripcion"
        val TABLE_COLUMN_4_CAT = "imagen"


    val TABLE_CREATE_CAT = " CREATE TABLE " + TABLE_NAME_CAT + " ( " +
            TABLE_COLUMN_1_CAT + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            TABLE_COLUMN_2_CAT + " TEXT NOT NULL , " +
            TABLE_COLUMN_3_CAT + " TEXT NOT NULL , " +
            TABLE_COLUMN_4_CAT + " BLOB NOT NULL ) "



    //REGISTRAR
        val TABLE_NAME_REG = "registro"
        val TABLE_COLUMN_1_REG = "id"
        val TABLE_COLUMN_2_REG = "nombre"
        val TABLE_COLUMN_3_REG = "ciudad"
        val TABLE_COLUMN_4_REG = "correo"
        val TABLE_COLUMN_5_REG = "contrasena"

        val TABLE_CREATE_REG = " CREATE TABLE " + TABLE_NAME_REG + " ( " +
                TABLE_COLUMN_1_REG + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                TABLE_COLUMN_2_REG + " TEXT NOT NULL , " +
                TABLE_COLUMN_3_REG + " TEXT NOT NULL , " +
                TABLE_COLUMN_4_REG + " TEXT NOT NULL , " +
                TABLE_COLUMN_5_REG + " TEXT NOT NULL ) "

        val QUERY_ALL = " SELECT * FROM "
    }
}