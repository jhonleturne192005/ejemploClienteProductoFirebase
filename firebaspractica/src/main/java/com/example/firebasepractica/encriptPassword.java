/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.firebasepractica;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author USUARIO
 */
public class encriptPassword 
{
    public String encriptPasswordUser(String pwd) 
    {
        return DigestUtils.sha256Hex(pwd);
    }
}
