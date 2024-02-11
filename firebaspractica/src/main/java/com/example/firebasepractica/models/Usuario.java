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
public class Usuario 
{
    public static String coleccionUsuario="usuario";
    
    public static String PusuarioK="usuario";
    public static String PcontraseniaK="contrasenia";
    
    String id;
    String usuario;
    String contrasenia;
    LocalDateTime fecha_creacion=LocalDateTime.now();
}
