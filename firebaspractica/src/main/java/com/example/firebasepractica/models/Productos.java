/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.firebasepractica.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USUARIO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Productos 
{
    public static String coleccionProductos="productos";
    
    public static String PnombreK="nombre";
    public static String PempresaK="empresa";
    public static String Pprecio_unitarioK="precio_unitario";
    public static String PdescuentoK="descuento";
    public static String PproveedorK="proveedor";
    public static String PdescripcionK="descripcion";
    
    String id;
    String nombre;
    String empresa;
    Double precio_unitario;
    Double descuento;
    String proveedor;
    String descripcion;
    LocalDateTime fecha_creacion=LocalDateTime.now();
}
