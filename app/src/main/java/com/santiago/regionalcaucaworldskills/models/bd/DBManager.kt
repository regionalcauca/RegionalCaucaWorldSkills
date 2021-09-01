package com.santiago.regionalcaucaworldskills.models.bd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.santiago.regionalcaucaworldskills.models.*

class DBManager(context: Context){

    val dbHelper = DBHelper(context)

    var db:SQLiteDatabase ?= null

    fun openDbWr(){
        db = dbHelper.writableDatabase
    }
    fun openDbRd(){
        db = dbHelper.readableDatabase
    }
    fun closeDb(){
        if (db!=null){
            db?.close()
        }
    }

    fun insertRegistro(dbRegistro: DBRegistro):Long{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2_REG,dbRegistro.nombre)
        values.put(Constants.TABLE_COLUMN_3_REG,dbRegistro.ciudad)
        values.put(Constants.TABLE_COLUMN_4_REG,dbRegistro.correo)
        values.put(Constants.TABLE_COLUMN_5_REG,dbRegistro.contrasena)
        val res = db?.insert(Constants.TABLE_NAME_REG,null,values)
        closeDb()
        return res!!
    }
    fun listRegistro(correo:String):MutableList<DBRegistro>{
        val lista : MutableList<DBRegistro> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(
            Constants.QUERY_ALL + Constants.TABLE_NAME_REG + " WHERE " + Constants.TABLE_COLUMN_4_REG + " =? ",
            arrayOf(correo))
        if (result!!.moveToFirst())
            do {
                val dbRegistro = DBRegistro()
                dbRegistro.id = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_1_REG))
                dbRegistro.nombre = result.getString(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_2_REG))
                dbRegistro.ciudad = result.getString((result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_3_REG)))
                dbRegistro.correo = result.getString(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_4_REG))
                dbRegistro.contrasena = result.getString(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_5_REG))
                lista.add(dbRegistro)
            }while (result.moveToNext())
        closeDb()
        return lista

   }
    fun insertCategoria(dbCategoria: DBCategoria):Long{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2_CAT,dbCategoria.nombre)
        values.put(Constants.TABLE_COLUMN_3_CAT,dbCategoria.descripcion)
        values.put(Constants.TABLE_COLUMN_4_CAT,dbCategoria.imagen)
        val res = db?.insert(Constants.TABLE_NAME_CAT,null,values)
        closeDb()
        return res!!
    }

    fun listCategorias():MutableList<DBCategoria>{
        val lista : MutableList<DBCategoria> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL + Constants.TABLE_NAME_CAT,null)
        if (result!!.moveToFirst())
            do {
                val dbCategoria = DBCategoria()
                dbCategoria.id = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_1_CAT))
                dbCategoria.nombre = result.getString(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_2_CAT))
                dbCategoria.descripcion = result.getString((result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_3_CAT)))
                dbCategoria.imagen = result.getBlob(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_4_CAT))
                lista.add(dbCategoria)
            }while (result.moveToNext())
        closeDb()
        return lista

    }
    fun insertCategoriaId(dbCategoriaId: DBCategoriaId):Long{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2_CAT_ID,dbCategoriaId.nombre)
        values.put(Constants.TABLE_COLUMN_3_CAT_ID,dbCategoriaId.descripcion)
        values.put(Constants.TABLE_COLUMN_4_CAT_ID,dbCategoriaId.precio)
        values.put(Constants.TABLE_COLUMN_5_CAT_ID,dbCategoriaId.imagen)
        values.put(Constants.TABLE_COLUMN_6_CAT_ID,dbCategoriaId.tipoCategoria)
        val res = db?.insert(Constants.TABLE_NAME_CAT_ID,null,values)
        closeDb()
        return res!!
    }
    fun listCategoriaId(tipoCategoria:Int):MutableList<DBCategoriaId>{
        val lista : MutableList<DBCategoriaId> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(
            Constants.QUERY_ALL + Constants.TABLE_NAME_CAT_ID + " WHERE " + Constants.TABLE_COLUMN_6_CAT_ID + " =? ",
            arrayOf(tipoCategoria.toString()))
        if (result!!.moveToFirst())
            do {
                val dbCategoriaId = DBCategoriaId()
                dbCategoriaId.id = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_1_CAT_ID))
                dbCategoriaId.nombre = result.getString(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_2_CAT_ID))
                dbCategoriaId.descripcion = result.getString((result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_3_CAT_ID)))
                dbCategoriaId.precio = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_4_CAT_ID))
                dbCategoriaId.imagen = result.getBlob(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_5_CAT_ID))
                dbCategoriaId.tipoCategoria = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_6_CAT_ID))
                lista.add(dbCategoriaId)
            }while (result.moveToNext())
        closeDb()
        return lista

    }
    fun listProducto(id:Int):MutableList<DBCategoriaId>{
        val lista : MutableList<DBCategoriaId> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(
            Constants.QUERY_ALL + Constants.TABLE_NAME_CAT_ID + " WHERE " + Constants.TABLE_COLUMN_1_CAT_ID + " =? ",
            arrayOf(id.toString()))
        if (result!!.moveToFirst())
            do {
                val dbCategoriaId = DBCategoriaId()
                dbCategoriaId.id = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_1_CAT_ID))
                dbCategoriaId.nombre = result.getString(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_2_CAT_ID))
                dbCategoriaId.descripcion = result.getString((result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_3_CAT_ID)))
                dbCategoriaId.precio = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_4_CAT_ID))
                dbCategoriaId.imagen = result.getBlob(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_5_CAT_ID))
                dbCategoriaId.tipoCategoria = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_6_CAT_ID))
                lista.add(dbCategoriaId)
            }while (result.moveToNext())
        closeDb()
        return lista

    }

    fun insertPedido(dbPedido: DBPedido):Long{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2_PED,dbPedido.idProducto)
        values.put(Constants.TABLE_COLUMN_3_PED,dbPedido.nombre)
        values.put(Constants.TABLE_COLUMN_4_PED,dbPedido.descripcion)
        values.put(Constants.TABLE_COLUMN_5_PED,dbPedido.imagen)
        values.put(Constants.TABLE_COLUMN_6_PED,dbPedido.precioUnidad)
        values.put(Constants.TABLE_COLUMN_7_PED,dbPedido.precioTotal)
        values.put(Constants.TABLE_COLUMN_8_PED,dbPedido.cantidad)
        val res = db?.insert(Constants.TABLE_NAME_PED,null,values)
        closeDb()
        return res!!
    }
    fun updatePedido(id: Int,precioTotal:Int,cantidad:Int):Int{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_7_PED,precioTotal)
        values.put(Constants.TABLE_COLUMN_8_PED,cantidad)
        val res = db?.update(Constants.TABLE_NAME_PED,values," id =? " , arrayOf(id.toString()))
        closeDb()
        return res!!
    }
    fun sumTotal():Int{
        var valor = 0
        openDbRd()
        val result = db?.rawQuery(" SELECT SUM ( " + Constants.TABLE_COLUMN_7_PED + " ) FROM " + Constants.TABLE_NAME_PED,null)
        if (result!!.moveToFirst())
            valor = result.getInt(0)
        closeDb()
        return valor!!
    }
    fun listPedido():MutableList<DBPedido>{
        val lista : MutableList<DBPedido> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL + Constants.TABLE_NAME_PED,null)
        if (result!!.moveToFirst())
            do {
                val dBPedido = DBPedido()
                dBPedido.id = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_1_PED))
                dBPedido.idProducto = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_2_PED))
                dBPedido.nombre = result.getString(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_3_PED))
                dBPedido.descripcion = result.getString((result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_4_PED)))
                dBPedido.imagen = result.getBlob(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_5_PED))
                dBPedido.precioUnidad = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_6_PED))
                dBPedido.precioTotal = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_7_PED))
                dBPedido.cantidad = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_8_PED))
                lista.add(dBPedido)
            }while (result.moveToNext())
        closeDb()
        return lista

    }

    fun listAcumulacion(id:Int):MutableList<DBPedido>{
        val lista : MutableList<DBPedido> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(
            Constants.QUERY_ALL + Constants.TABLE_NAME_PED + " WHERE " + Constants.TABLE_COLUMN_2_PED + "  =? ",
            arrayOf(id.toString()))
        if (result!!.moveToFirst())
            do {
                val dBPedido = DBPedido()
                dBPedido.id = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_1_PED))
                dBPedido.idProducto = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_2_PED))
                dBPedido.nombre = result.getString(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_3_PED))
                dBPedido.descripcion = result.getString((result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_4_PED)))
                dBPedido.imagen = result.getBlob(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_5_PED))
                dBPedido.precioUnidad = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_6_PED))
                dBPedido.precioTotal = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_7_PED))
                dBPedido.cantidad = result.getInt(result.getColumnIndexOrThrow(Constants.TABLE_COLUMN_8_PED))
                lista.add(dBPedido)
            }while (result.moveToNext())
        closeDb()
        return lista

    }
    fun deleteId(id:Int):Int{
        openDbWr()
            val res = db?.delete(Constants.TABLE_NAME_PED," id =? ", arrayOf(id.toString()))
        closeDb()
        return res!!
    }

    fun deleteAll():Int{
        openDbWr()
        val res = db?.delete(Constants.TABLE_NAME_PED,null,null)
        closeDb()
        return res!!
    }

    fun insertarHistorial(){

    }
}
