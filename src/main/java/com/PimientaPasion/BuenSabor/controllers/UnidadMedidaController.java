package com.PimientaPasion.BuenSabor.controllers;

import com.PimientaPasion.BuenSabor.entities.RubroProducto;
import com.PimientaPasion.BuenSabor.entities.UnidadMedida;
import com.PimientaPasion.BuenSabor.services.RubroProductoServiceImpl;
import com.PimientaPasion.BuenSabor.services.UnidadMedidaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/UnidadMedida")
public class UnidadMedidaController extends  BaseControllerImpl<UnidadMedida, UnidadMedidaServiceImpl>{
}
