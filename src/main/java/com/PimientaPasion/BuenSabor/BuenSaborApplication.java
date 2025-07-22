package com.PimientaPasion.BuenSabor;

import com.PimientaPasion.BuenSabor.entities.*;
import com.PimientaPasion.BuenSabor.enums.EstadoPedido;
import com.PimientaPasion.BuenSabor.enums.FormaPago;
import com.PimientaPasion.BuenSabor.enums.Rol;
import com.PimientaPasion.BuenSabor.enums.TipoEnvio;
import com.PimientaPasion.BuenSabor.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class BuenSaborApplication {

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;
    @Autowired
    RubroProductoRepository rubroProductoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    RubroIngredienteRepository rubroIngrdienteRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    ConfiguracionRepository configuracionRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BuenSaborApplication.class, args);
        System.out.println("Estoy funcionando");
    }

    @Bean //HACER modificaciones cuando hagan los cambios en el modelo de clases
    public CommandLineRunner init(UnidadMedidaRepository unidadMedidaRepo, RubroProductoRepository rubroProductoRepo, ClienteRepository clienteRepo, PedidoRepository pedidoRepo
            , RubroIngredienteRepository rubroIngrdienteRepo, EmpleadoRepository empleadoRepo
            , ConfiguracionRepository configuracionRepo) {

        System.out.println("-----------------ESTOY FUNCIONANDO---------");
        return args -> {

            //-----------------------------------------------
            //				Rehacerlo mas bonito
            //-----------------------------------------------

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha1 = "2023-09-10";
            String fecha2 = "2023-08-15";
            String fecha3 = "2023-07-03";

            // Parseo String en un objeto Date
            Date fechaUno = formatoFecha.parse(fecha1);
            Date fechaDos = formatoFecha.parse(fecha2);
            Date fechaTres = formatoFecha.parse(fecha3);

            // Formato Date para horaEstimadaFinalizacion
            SimpleDateFormat formatoHorario = new SimpleDateFormat("HH:mm:ss");
            String horario1 = "21:42:05";
            String horario2 = "23:00:58";
            String horario3 = "23:28:32";

            // Parseo String en un objeto Date
            Date horarioUno = formatoHorario.parse(horario1);
            Date horarioDos = formatoHorario.parse(horario2);
            Date horarioTres = formatoHorario.parse(horario3);

            // Crear Usuario todos los usuario tienen contraseña 1234
            Usuario usuario1 = Usuario.builder()
                    //.auth0Id("1")
                    .username("user1")
                    .password(passwordEncoder.encode("1234"))
                    .rol(Rol.CLIENTE)
                    .build();


            Usuario usuario2 = Usuario.builder()
                    //.auth0Id("2")
                    .username("user2")
                    .password(passwordEncoder.encode("1234"))
                    .rol(Rol.CLIENTE)
                    .build();

            Usuario usuario3 = Usuario.builder()
                    //.auth0Id("3")
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .rol(Rol.ADMINISTRADOR)
                    .build();

            Usuario usuario4 = Usuario.builder()
                    //.auth0Id("4")
                    .username("cajero")
                    .password(passwordEncoder.encode("1234"))
                    .rol(Rol.CAJERO)
                    .build();

            Usuario usuario5 = Usuario.builder()
                    //.auth0Id("5")
                    .username("cocinero1")
                    .password(passwordEncoder.encode("1234"))
                    .rol(Rol.COCINERO)
                    .build();

            Usuario usuario6 = Usuario.builder()
                    //.auth0Id("6")
                    .username("delivery")
                    .password(passwordEncoder.encode("1234"))
                    .rol(Rol.DELIVERY)
                    .build();

            Usuario usuario7 = Usuario.builder()
                    //.auth0Id("7")
                    .username("cocinero2")
                    .password(passwordEncoder.encode("1234"))
                    .rol(Rol.COCINERO)
                    .build();

            // Crear Clientes
            Cliente cliente1 = Cliente.builder()
                    .nombre("Rodrigo")
                    .apellido("Viluron")
                    .telefono("2617869567")
                    .email("rodri@gmail.com")
                    .usuario(usuario1)
                    .build();

            Cliente cliente2 = Cliente.builder()
                    .nombre("Roberto")
                    .apellido("Marin")
                    .telefono("764467375")
                    .email("roberto@gmail.com")
                    .usuario(usuario2)
                    .build();


            // Crear Domicilios
            Domicilio domicilio1 = Domicilio.builder()
                    .calle("Calle falsa")
                    .numeroDomicilio(123)
                    .codigoPostal(5507)
                    .localidad("Luján de Cuyo")
                    .numeroDpto(1)
                    .pisoDpto(1)
                    .build();

            Domicilio domicilio2 = Domicilio.builder()
                    .calle("Callejón falso")
                    .numeroDomicilio(456)
                    .codigoPostal(5501)
                    .localidad("Godoy Cruz")
                    .build();

            Domicilio domicilio3 = Domicilio.builder()
                    .calle("Callejón Rodriguez")
                    .numeroDomicilio(1864)
                    .codigoPostal(5525)
                    .localidad("Las Heras")
                    .build();

            Domicilio domicilio4 = Domicilio.builder()
                    .calle("Calle España ")
                    .numeroDomicilio(1123)
                    .codigoPostal(5312)
                    .localidad("Las Heras")
                    .build();

            // Asignar Domicilio a ClientePedidosDeliveyDTO
            cliente1.agregarDomicilio(domicilio1);
            cliente1.agregarDomicilio(domicilio3);
            cliente1.agregarDomicilio(domicilio4);
            cliente2.agregarDomicilio(domicilio2);


            //Guardar a el cliente on sus domicilio y usuario
            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);

            //Crear empleados
            Empleado empleado1 = Empleado.builder()
                    .nombre("Joaquin")
                    .apellido("Prato")
                    .dni(48625365)
                    .email("joaquinprato@gmail.com")
                    .telefono("2615693641")
                    .codigoEmpleado(125)
                    .usuario(usuario3)
                    .build();

            Empleado empleado2 = Empleado.builder()
                    .nombre("Tomas")
                    .apellido("Colombo")
                    .dni(43741586)
                    .email("tomascolombo@gmail.com")
                    .telefono("2618639874")
                    .codigoEmpleado(123)
                    .usuario(usuario4)
                    .build();

            Empleado empleado3 = Empleado.builder()
                    .nombre("German")
                    .apellido("Lopez")
                    .dni(41789321)
                    .email("germanlopez@gmail.com")
                    .telefono("2615693265")
                    .codigoEmpleado(98)
                    .usuario(usuario5)
                    .build();

            Empleado empleado4 = Empleado.builder()
                    .nombre("Lautaro")
                    .apellido("Gomez")
                    .dni(38654125)
                    .email("lautaroGomez@gmail.com")
                    .telefono("2614523698")
                    .codigoEmpleado(130)
                    .usuario(usuario6)
                    .build();

            Empleado empleado5 = Empleado.builder()
                    .nombre("Laeando")
                    .apellido("Paz")
                    .dni(436987456)
                    .email("leandropaz@gmail.com")
                    .telefono("2614785258")
                    .codigoEmpleado(131)
                    .usuario(usuario7)
                    .build();

            //Guardar empleado con su usuario
            empleadoRepository.save(empleado1);
            empleadoRepository.save(empleado2);
            empleadoRepository.save(empleado3);
            empleadoRepository.save(empleado4);
            empleadoRepository.save(empleado5);


            // Crear Rubro
            RubroIngrediente rubro1 = RubroIngrediente.builder()
                    .denominacion("vegetales")
                    .build();

            RubroIngrediente rubro2 = RubroIngrediente.builder()
                    .denominacion("Carnes")
                    .build();

            RubroIngrediente rubro3 = RubroIngrediente.builder()
                    .denominacion("Panes")
                    .build();

            RubroIngrediente rubro4 = RubroIngrediente.builder()
                    .denominacion("Fiambres")
                    .build();

            RubroIngrediente rubro5=RubroIngrediente.builder()
                    .denominacion("Bebidas")
                    .build();

            // Crear Ingredientes
            Ingrediente ingrediente1 = Ingrediente.builder()
                    .denominacion("tomate")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente2 = Ingrediente.builder()
                    .denominacion("lechuga")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente3 = Ingrediente.builder()
                    .denominacion("cebolla")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente4 = Ingrediente.builder()
                    .denominacion("carne de hamburgesa")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente5 = Ingrediente.builder()
                    .denominacion("carne de lomo")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente6 = Ingrediente.builder()
                    .denominacion("pan de hamburgesa clasico")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente7 = Ingrediente.builder()
                    .denominacion("queso")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente8 = Ingrediente.builder()
                    .denominacion("jamon")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente9 = Ingrediente.builder()
                    .denominacion("pan de lomo clasico")
                    .stockActual(50.20)
                    .stockMinimo(10.0)
                    .precioCompra(5)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente10 = Ingrediente.builder()
                    .denominacion("panceta")
                    .stockActual(50.20)
                    .stockMinimo(15.0)
                    .precioCompra(100)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            Ingrediente ingrediente11 = Ingrediente.builder()
                    .denominacion("pan negro")
                    .stockActual(50.20)
                    .stockMinimo(15.0)
                    .precioCompra(100)
                    .urlImagen("images/verduras.jpg")
                    .eliminado(false)
                    .build();

            // Crear UnidadMedida
            UnidadMedida unidadMedida1 = UnidadMedida.builder()
                    .denominacion("gramos")
                    .abreviatura("gr")
                    .build();

            UnidadMedida unidadMedida2 = UnidadMedida.builder()
                    .denominacion("litros")
                    .abreviatura("l")
                    .build();

            unidadMedidaRepository.save(unidadMedida1);
            unidadMedidaRepository.save(unidadMedida2);

            // Asignar UnidadMedida a Ingrediente
            ingrediente1.setUnidadMedida(unidadMedida1);
            ingrediente2.setUnidadMedida(unidadMedida1);
            ingrediente3.setUnidadMedida(unidadMedida1);
            ingrediente4.setUnidadMedida(unidadMedida1);
            ingrediente5.setUnidadMedida(unidadMedida1);
            ingrediente6.setUnidadMedida(unidadMedida1);
            ingrediente7.setUnidadMedida(unidadMedida1);
            ingrediente8.setUnidadMedida(unidadMedida1);
            ingrediente9.setUnidadMedida(unidadMedida1);
            ingrediente10.setUnidadMedida(unidadMedida2);
            ingrediente11.setUnidadMedida(unidadMedida2);

            // Asignar Ingrediente a Rubro
            rubro1.agregarIngrediente(ingrediente1);
            rubro1.agregarIngrediente(ingrediente2);
            rubro1.agregarIngrediente(ingrediente3);
            rubro2.agregarIngrediente(ingrediente4);
            rubro2.agregarIngrediente(ingrediente5);
            rubro3.agregarIngrediente(ingrediente6);
            rubro4.agregarIngrediente(ingrediente7);
            rubro4.agregarIngrediente(ingrediente8);
            rubro3.agregarIngrediente(ingrediente9);
            rubro5.agregarIngrediente(ingrediente10);
            rubro5.agregarIngrediente(ingrediente11);

            //Guardar Rubros con ingredientes y unidad de medida
            rubroIngrdienteRepository.save(rubro1);
            rubroIngrdienteRepository.save(rubro2);
            rubroIngrdienteRepository.save(rubro3);
            rubroIngrdienteRepository.save(rubro4);
            rubroIngrdienteRepository.save(rubro5);

            // Crear Rubros de Productos
            RubroProducto rubroProducto1 = RubroProducto.builder()
                    .denominacion("Hamburguesas")
                    .build();

            RubroProducto rubroProducto2 = RubroProducto.builder()
                    .denominacion("Lomos")
                    .build();

            RubroProducto rubroProducto3 = RubroProducto.builder()
                    .denominacion("Bebidas")
                    .build();

            RubroProducto rubroProducto4 = RubroProducto.builder()
                    .denominacion("Bebida con gas")
                    .rubroPadre(rubroProducto3)
                    .build();



            // Crear Productos

            //Hamburguesas
            Producto producto1 = Producto.builder()
                    .denominacion("Hamburguesa Clásica")
                    .descripcion("Carne, pan, lechuga, tomate, queso.")
                    .tiempoEstimadoCocina(20)
                    .precioVenta(4000.00)
                    .precioCosto(1500.00)
                    .urlImagen("images/hamburguesa1.jpg")
                    .receta("Cocinar carne 10min, agregar 2 hojas de lechuga, agregar 3 fetas de tomate, 1 feta de queso")
                    .eliminado(false)
                    .build();

            Producto producto2 = Producto.builder()
                    .denominacion("hamburguesa Bacon")
                    .descripcion("Carne, pan, cheddar, bacon, cebolla.")
                    .tiempoEstimadoCocina(20)
                    .precioVenta(4500.00)
                    .precioCosto(1800.00)
                    .urlImagen("images/hamburguesa2.jpg")
                    .receta("Mezcla carne molida con sal y pimienta. Forma la carne en hamburguesas y ásalas. Tuesta el pan y añade cheddar, bacon y cebolla asada. Arma la hamburguesa y ¡disfruta!")
                    .eliminado(false)
                    .build();

            Producto producto3 = Producto.builder()
                    .denominacion("Hamburguesa Guacamole")
                    .descripcion("Carne, pan, bacon, lechuga, queso, cebolla, guacamole.")
                    .tiempoEstimadoCocina(25)
                    .precioVenta(4700.00)
                    .precioCosto(2000.00)
                    .urlImagen("images/hamburguesa3.jpg")
                    .receta("Condimenta la carne, cocina, agrega bacon y queso. Arma con lechuga, cebolla, y guacamole. ¡Listo para disfrutar!")
                    .eliminado(false)
                    .build();

            //Lomos
            Producto producto4 = Producto.builder()
                    .denominacion("Lomo Clásico")
                    .descripcion("Cocinado a la parrilla y sazonado al gusto, con lechuga, tomate y cebolla.")
                    .tiempoEstimadoCocina(25)
                    .precioVenta(4500.00)
                    .precioCosto(2000.00)
                    .urlImagen("images/lomoClasico.jpg")
                    .receta("Cocinar carne 10min, agregar 2 hojas de lechuga, 3 fetas de tomate, 1 feta de queso, 2 cebollas")
                    .eliminado(false)
                    .build();

            Producto producto5 = Producto.builder()
                    .denominacion("Lomo de Pollo")
                    .descripcion("Cocinado a la parrilla y sazonado al gusto, con lechuga, tomate y queso.")
                    .tiempoEstimadoCocina(25)
                    .precioVenta(4200.00)
                    .precioCosto(1700.00)
                    .urlImagen("images/lomo2.jpg")
                    .receta("Asa el pollo sazonado a la parrilla. Añade queso, arma con lechuga y tomate. ¡Delicioso y listo para servir!")
                    .eliminado(false)
                    .build();

            Producto producto6 = Producto.builder()
                    .denominacion("Lomo Desmenuzado")
                    .descripcion("Cocinado a la parrilla y sazonado al gusto, con lechuga, tomate,cebolla, queso y pepino.")
                    .tiempoEstimadoCocina(30)
                    .precioVenta(5000.00)
                    .precioCosto(2300.00)
                    .urlImagen("images/lomo3.jpg")
                    .receta("Asa el lomo sazonado, luego desmenuzalo. Agrega queso derretido. Prepara con lechuga, tomate, cebolla y pepino. ¡Listo para disfrutar en tu plato!")
                    .eliminado(false)
                    .build();

            //Bebidas

            Producto producto7 = Producto.builder()
                    .denominacion("CocaCola 1.5L")
                    .descripcion("coca cola sabor original de 1.5 litros, descartable")
                    .tiempoEstimadoCocina(0)
                    .precioVenta(1600.00)
                    .precioCosto(800.00)
                    .urlImagen("images/cocaCola1.25.jpg")
                    .eliminado(false)
                    .build();

            Producto producto8 = Producto.builder()
                    .denominacion("Agua Mineral 0.6L")
                    .descripcion("agua mineral descartable de 600 ml")
                    .tiempoEstimadoCocina(0)
                    .precioVenta(900.00)
                    .precioCosto(400.00)
                    .urlImagen("images/aguamineral.jpg")
                    .eliminado(false)
                    .build();

            Producto producto9 = Producto.builder()
                    .denominacion("Agua Saborizada Manzana 0.6L")
                    .descripcion("agua saborizada manzana descartable de 600 ml")
                    .tiempoEstimadoCocina(0)
                    .precioVenta(1000.00)
                    .precioCosto(500.00)
                    .urlImagen("images/aguasaborizada.jpg")
                    .eliminado(false)
                    .build();

            // Asignar Producto a rubro de Productos
            rubroProducto1.agregarProducto(producto1);
            rubroProducto1.agregarProducto(producto2);
            rubroProducto1.agregarProducto(producto3);

            rubroProducto2.agregarProducto(producto4);
            rubroProducto2.agregarProducto(producto5);
            rubroProducto2.agregarProducto(producto6);

            rubroProducto3.agregarProducto(producto7);
            rubroProducto3.agregarProducto(producto8);
            rubroProducto3.agregarProducto(producto9);

            // Crear DetalleProducto
            //Producto 1
            DetalleProducto detalleProducto11 = DetalleProducto.builder()
                    .cantidad(30)
                    .ingrediente(ingrediente1)
                    .build();

            DetalleProducto detalleProducto12 = DetalleProducto.builder()
                    .cantidad(30)
                    .ingrediente(ingrediente2)
                    .build();

            DetalleProducto detalleProducto13 = DetalleProducto.builder()
                    .cantidad(50)
                    .ingrediente(ingrediente7)
                    .build();

            DetalleProducto detalleProducto14 = DetalleProducto.builder()
                    .cantidad(200)
                    .ingrediente(ingrediente4)
                    .build();

            DetalleProducto detalleProducto15 = DetalleProducto.builder()
                    .cantidad(100)
                    .ingrediente(ingrediente6)
                    .build();

            //Producto 4
            DetalleProducto detalleProducto21 = DetalleProducto.builder()
                    .cantidad(30)
                    .ingrediente(ingrediente1)
                    .build();

            DetalleProducto detalleProducto22 = DetalleProducto.builder()
                    .cantidad(30)
                    .ingrediente(ingrediente2)
                    .build();

            DetalleProducto detalleProducto23 = DetalleProducto.builder()
                    .cantidad(30)
                    .ingrediente(ingrediente3)
                    .build();

            DetalleProducto detalleProducto24 = DetalleProducto.builder()
                    .cantidad(300)
                    .ingrediente(ingrediente5)
                    .build();

            DetalleProducto detalleProducto25 = DetalleProducto.builder()
                    .cantidad(200)
                    .ingrediente(ingrediente9)
                    .build();

            //Producto 7
            DetalleProducto detalleProducto31 = DetalleProducto.builder()
                    .cantidad(1)
                    .ingrediente(ingrediente10)
                    .build();

            //Producto 8
            DetalleProducto detalleProducto41 = DetalleProducto.builder()
                    .cantidad(1)
                    .ingrediente(ingrediente11)
                    .build();



            // Asignar DetalleProducto a Producto
            producto1.agregarDetalleProducto(detalleProducto11);
            producto1.agregarDetalleProducto(detalleProducto12);
            producto1.agregarDetalleProducto(detalleProducto13);
            producto1.agregarDetalleProducto(detalleProducto14);
            producto1.agregarDetalleProducto(detalleProducto15);

            producto4.agregarDetalleProducto(detalleProducto21);
            producto4.agregarDetalleProducto(detalleProducto22);
            producto4.agregarDetalleProducto(detalleProducto23);
            producto4.agregarDetalleProducto(detalleProducto24);
            producto4.agregarDetalleProducto(detalleProducto25);

            producto7.agregarDetalleProducto((detalleProducto31));

            producto8.agregarDetalleProducto((detalleProducto41));

            // Guardar rubro producto con los productos y detalles producto
            rubroProductoRepository.save(rubroProducto1);
            rubroProductoRepository.save(rubroProducto2);
            rubroProductoRepository.save(rubroProducto3);
            rubroProductoRepository.save(rubroProducto4);

            // Crear Pedido
            Pedido pedido1 = Pedido.builder()
                    .fechaPedido(fechaUno)
                    .horaEstimadaFinalizacion(horarioUno)
                    .totalPedido(8500)
                    .estadoPedido(EstadoPedido.EN_COCINA)
                    .tipoEnvio(TipoEnvio.TAKE_AWAY)
                    .formaPago(FormaPago.EFECTIVO)
                    .cliente(cliente1)
                    .domicilioEntrega(domicilio1)
                    .build();

            Pedido pedido2 = Pedido.builder()
                    .fechaPedido(fechaDos)
                    .horaEstimadaFinalizacion(horarioDos)
                    .totalPedido(8500)
                    .estadoPedido(EstadoPedido.ENTREGADO)
                    .tipoEnvio(TipoEnvio.DELIVERY)
                    .formaPago(FormaPago.MERCADO_PAGO)
                    .cliente(cliente2)
                    .domicilioEntrega(domicilio2)
                    .build();

            Pedido pedido3 = Pedido.builder()
                    .fechaPedido(fechaDos)
                    .horaEstimadaFinalizacion(horarioDos)
                    .totalPedido(8500)
                    .estadoPedido(EstadoPedido.A_CONFIRMAR)
                    .tipoEnvio(TipoEnvio.TAKE_AWAY)
                    .formaPago(FormaPago.EFECTIVO)
                    .cliente(cliente1)
                    .domicilioEntrega(domicilio3)
                    .build();

            //crear Detalle Pedido

            DetallePedido detallePedido11 = DetallePedido.builder()
                    .cantidad(1)
                    .subtotal(4000)
                    .producto(producto1)
                    .build();

            DetallePedido detallePedido12 = DetallePedido.builder()
                    .cantidad(2)
                    .subtotal(2250)
                    .producto(producto2)
                    .build();

            DetallePedido detallePedido21 = DetallePedido.builder()
                    .cantidad(3)
                    .subtotal(8600)
                    .producto(producto1)
                    .build();

            DetallePedido detallePedido22 = DetallePedido.builder()
                    .cantidad(2)
                    .subtotal(4500)
                    .producto(producto2)
                    .build();

            DetallePedido detallePedido31 = DetallePedido.builder()
                    .cantidad(3)
                    .subtotal(2100)
                    .producto(producto2)
                    .build();

            DetallePedido detallePedido32 = DetallePedido.builder()
                    .cantidad(1)
                    .subtotal(4000)
                    .producto(producto2)
                    .build();

            //agregamos los detalles al pedido
            pedido1.agregarDetallePedido(detallePedido11);
            pedido1.agregarDetallePedido(detallePedido12);
            pedido2.agregarDetallePedido(detallePedido21);
            pedido2.agregarDetallePedido(detallePedido22);
            pedido3.agregarDetallePedido(detallePedido31);
            pedido3.agregarDetallePedido(detallePedido32);

            // Crear Factura
            Factura factura1 = Factura.builder()
                    .fechaFacturacion(fechaUno)
                    .numeroFactura(1)
                    .porcentajeDescuento(10.00)
                    .formaPago(FormaPago.EFECTIVO)
                    .totalCosto(3000.00)
                    .totalVenta(8500.00)
                    .build();

            Factura factura2 = Factura.builder()
                    .fechaFacturacion(fechaUno)
                    .numeroFactura(2)
                    .porcentajeDescuento(0.0)
                    .formaPago(FormaPago.MERCADO_PAGO)
                    .totalCosto(4000.00)
                    .totalVenta(9500.00)
                    .build();

            Factura factura3 = Factura.builder()
                    .fechaFacturacion(fechaUno)
                    .numeroFactura(3)
                    .porcentajeDescuento(10.00)
                    .formaPago(FormaPago.EFECTIVO)
                    .totalCosto(4000.00)
                    .totalVenta(6100.00)
                    .build();

            //crear detalleFactura

            DetalleFactura detalleFactura11 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(4000)
                    .producto(producto1)
                    .build();

            DetalleFactura detalleFactura12 = DetalleFactura.builder()
                    .cantidad(2)
                    .subtotal(2250)
                    .producto(producto2)
                    .build();

            DetalleFactura detalleFactura21 = DetalleFactura.builder()
                    .cantidad(3)
                    .subtotal(8600)
                    .producto(producto1)
                    .build();

            DetalleFactura detalleFactura22 = DetalleFactura.builder()
                    .cantidad(2)
                    .subtotal(4500)
                    .producto(producto2)
                    .build();

            DetalleFactura detalleFactura31 = DetalleFactura.builder()
                    .cantidad(3)
                    .subtotal(2100)
                    .producto(producto2)
                    .build();

            DetalleFactura detalleFactura32 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(4000)
                    .producto(producto2)
                    .build();

            //Agreagamos los detalle factura a la factura

            factura1.agregarDetalleFactura(detalleFactura11);
            factura1.agregarDetalleFactura(detalleFactura12);
            factura2.agregarDetalleFactura(detalleFactura21);
            factura2.agregarDetalleFactura(detalleFactura22);
            factura3.agregarDetalleFactura(detalleFactura31);
            factura3.agregarDetalleFactura(detalleFactura32);

            // Asignar Factura a Pedido
            pedido1.setFactura(factura1);
            pedido2.setFactura(factura2);
            pedido3.setFactura(factura3);

            //Guardamos el pedido junto con el datalle del pedido y la factura con sus detalles
            pedidoRepository.save(pedido1);
            pedidoRepository.save(pedido2);
            pedidoRepository.save(pedido3);

            //crear Configuracion
            Configuracion configuracion = Configuracion.builder()
                    .emailEmpresa("elbuensabor@gamil.com")
                    .cantidadCocineros(2)
                    .tokenMercadoPgo("elbuensabor.mp")
                    .build();

            //guardar Configuracion
            configuracionRepository.save(configuracion);
        };
    }
}
