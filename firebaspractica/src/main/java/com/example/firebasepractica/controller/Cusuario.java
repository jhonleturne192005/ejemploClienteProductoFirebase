/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.firebasepractica.controller;

import com.example.firebasepractica.Messages;
import com.example.firebasepractica.models.Productos;
import com.example.firebasepractica.models.Usuario;
import com.example.firebasepractica.encriptPassword;
import com.example.firebasepractica.service.Sproductos;
import com.example.firebasepractica.service.Susuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USUARIO
 */
@RestController()
@RequestMapping("/api/usuario")
@CrossOrigin("*")
public class Cusuario 
{
 
    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(Usuario usuario)
    {
        Map<String,Object> response=new HashMap();
        Susuario sp=new Susuario();
        try
        {
            if(usuario.getContrasenia()!="" && usuario.getUsuario()!="")
            {
                encriptPassword ep=new encriptPassword();
                usuario.setContrasenia(ep.encriptPasswordUser(usuario.getContrasenia()));
                Map<String,Object> data=new HashMap();
                data.put(Usuario.PusuarioK, usuario.getUsuario());
                data.put(Usuario.PcontraseniaK, usuario.getContrasenia());
                
                sp.insert(data);
                
                return new ResponseEntity<Map<String,Object>>(data,HttpStatus.OK);
            }else
            {
                response.put(Messages.error, Messages.error_datos_incompletos);
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE); 
            }  
        }
        catch(Exception ex)
        {
            response.put(Messages.error, ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
     
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name="usuario") String usuarioF,
                                                @RequestParam(name="contrasenia") String contraseniaF)
    {
        Map<String,Object> response=new HashMap();
        Susuario sp=new Susuario();
        try
        {
            if(usuarioF!="" && contraseniaF!="")
            {
                List<Usuario> lstusuario=sp.buscar(usuarioF);
                if(lstusuario.size()>0)
                {
//                    Map<String,Object> data=new HashMap();
//                    data.put(Usuario.PusuarioK, usuario.getUsuario());
//                    data.put(Usuario.PcontraseniaK, usuario.getContrasenia());

//                    sp.insert(data);
                    Usuario usu=lstusuario.get(0);
                    encriptPassword ep=new encriptPassword();
                    if(usuarioF.equals(usu.getUsuario()) && ep.encriptPasswordUser(contraseniaF).equals(usu.getContrasenia()))
                    {
                        response.put(Messages.sucessInsert, true);
                        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
                    }else
                    {
                        response.put(Messages.sucessInsert, false);
                        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
                    }
                }
                else
                {
                    response.put(Messages.error, Messages.usuario_inexistente);
                    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE); 
                }
            }
            else
            {
                response.put(Messages.error, Messages.error_datos_incompletos);
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE); 
            }  
        }
        catch(Exception ex)
        {
            response.put(Messages.error, ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE);
        }
    }
       
}
