package com.PimientaPasion.BuenSabor.controllers;

import com.PimientaPasion.BuenSabor.entities.Domicilio;
import com.PimientaPasion.BuenSabor.services.DomicilioServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/domicilios")

public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioServiceImpl>{


}
