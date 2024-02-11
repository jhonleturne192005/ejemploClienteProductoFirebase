/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.firebasepractica.controller;

import com.example.firebasepractica.Messages;
import com.example.firebasepractica.models.Productos;
import com.example.firebasepractica.service.Sproductos;
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
@RequestMapping("/api/producto")
@CrossOrigin("*")
public class Cproducto 
{
    
    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(Productos productos)
    {
        Map<String,Object> response=new HashMap();
        Sproductos sp=new Sproductos();
        try
        {
            if(productos.getNombre()!="" && productos.getPrecio_unitario()!=null && productos.getDescuento()!=null 
                    && productos.getEmpresa()!="" && productos.getDescripcion()!=null && productos.getProveedor()!=null)
            {
                Map<String,Object> data=new HashMap();
                data.put(Productos.PnombreK, productos.getNombre());
                data.put(Productos.PempresaK, productos.getEmpresa());
                data.put(Productos.Pprecio_unitarioK, productos.getPrecio_unitario());
                data.put(Productos.PdescuentoK, productos.getDescuento());
                data.put(Productos.PproveedorK, productos.getProveedor());
                data.put(Productos.PdescripcionK, productos.getDescripcion());
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
    
    
    
    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizar(Productos productos,@RequestParam(name="id") String idocumento)
    {
        Map<String,Object> response=new HashMap();
        Sproductos sp=new Sproductos();
        try
        {
            if(productos.getNombre()!="" && productos.getPrecio_unitario()!=null && productos.getDescuento()!=null 
                    && productos.getEmpresa()!="" && productos.getDescripcion()!=null && productos.getProveedor()!=null)
            {
                Map<String,Object> data=new HashMap();
                data.put(Productos.PnombreK, productos.getNombre());
                data.put(Productos.PempresaK, productos.getEmpresa());
                data.put(Productos.Pprecio_unitarioK, productos.getPrecio_unitario());
                data.put(Productos.PdescuentoK, productos.getDescuento());
                data.put(Productos.PproveedorK, productos.getProveedor());
                data.put(Productos.PdescripcionK, productos.getDescripcion());
                sp.update(data,idocumento);
                
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
    
    
    
    
    
    
    @PostMapping("/leer")
    public ResponseEntity<?> leer()
    {
        Map<String,Object> response=new HashMap();
        Sproductos sp=new Sproductos();
        try
        {
            
            List<Productos> lstproductos=sp.read();
            return new ResponseEntity<List<Productos>>(lstproductos,HttpStatus.OK);
            
        }
        catch(Exception ex)
        {
            response.put(Messages.error, ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    
    @PostMapping("/buscar")
    public ResponseEntity<?> buscar(@RequestParam(name="valor") String valor)
    {
        Map<String,Object> response=new HashMap();
        Sproductos sp=new Sproductos();
        try
        {
            System.out.println("dsfsdfsdf");
            List<Productos> lstproduc=sp.buscar("nombre", valor);
            return new ResponseEntity<List<Productos>>(lstproduc,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put(Messages.error, ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @PostMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestParam(name="id") String valor)
    {
        Map<String,Object> response=new HashMap();
        Sproductos sp=new Sproductos();
        try
        {
            System.out.println("dsfsdfsdf");
            Integer status=sp.eliminar(valor);
            return new ResponseEntity<Integer>(status,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put(Messages.error, ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    
}
