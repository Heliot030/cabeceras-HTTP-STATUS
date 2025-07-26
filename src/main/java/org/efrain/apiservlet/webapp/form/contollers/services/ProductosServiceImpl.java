package org.efrain.apiservlet.webapp.form.contollers.services;

import org.efrain.apiservlet.webapp.form.contollers.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductosServiceImpl implements ProductoService{
    @Override
    public List<Producto> Listar() {
        return Arrays.asList(   new Producto(1L, "MacBook Pro", "Laptop",  199999),  // 1999
                new Producto(2L, "iPad Air", "Tablet",  89999),       // 899
                new Producto(3L, "AirPods Pro", "Audio", 24999)      // 249
        );
    }
}
