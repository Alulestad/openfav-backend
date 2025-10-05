package com.senadi.pasantes.intranet.config;

import com.senadi.pasantes.intranet.model.Actividad;
import com.senadi.pasantes.intranet.model.ActividadFecha;
import com.senadi.pasantes.intranet.model.Kpi;
import com.senadi.pasantes.intranet.model.ObjetivoEspecifico;
import com.senadi.pasantes.intranet.model.Ong;
import com.senadi.pasantes.intranet.model.PresupuestoItem;
import com.senadi.pasantes.intranet.model.Proyecto;
import com.senadi.pasantes.intranet.model.SolicitudDesembolso;
import com.senadi.pasantes.intranet.model.Usuario;
import com.senadi.pasantes.intranet.repository.OngRepository;
import com.senadi.pasantes.intranet.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final OngRepository ongRepository;

    public DataInitializer(UsuarioRepository usuarioRepository, OngRepository ongRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(new Usuario("admin", "admin", "ADMIN"));
            usuarioRepository.save(new Usuario("ong", "ong", "ONG"));
        }

        if (ongRepository.count() == 0) {
            Ong ong = new Ong();
            ong.setNombre("Fundación Aprender");
            ong.setRepresentanteLegal("María Pérez");
            ong.setEmail("contacto@fundacionaprender.org");
            ong.setCelular("0991234567");
            ong.setDireccion("Av. Siempre Viva 123, Quito");

            Proyecto proyecto = new Proyecto();
            proyecto.setTitulo("Programa de Inclusión Digital");
            proyecto.setObjetivoGeneral("Reducir la brecha digital en comunidades rurales");
            proyecto.setAlcance(250);
            proyecto.setMontoTotal(new BigDecimal("15000"));
            proyecto.setPlazoTotal(12);
            proyecto.setFechaInicio(LocalDate.now().minusMonths(2));
            proyecto.setFechaFin(LocalDate.now().plusMonths(10));
            proyecto.setEjes("Educación; Tecnología; Inclusión Social");
            proyecto.setResultado("200 estudiantes capacitados en herramientas digitales");

            ObjetivoEspecifico objetivo = new ObjetivoEspecifico();
            objetivo.setObjetivo("Fortalecer las competencias digitales de jóvenes");
            objetivo.setEjes("Educación Digital, Desarrollo Comunitario");

            Actividad actividad = new Actividad();
            actividad.setNombre("Capacitación en ofimática");
            actividad.setDescripcion("Sesiones semanales sobre herramientas ofimáticas");
            actividad.setCategoria("Formación");
            actividad.setResultadoEsperado(new BigDecimal("85"));
            actividad.setResultadoObtenido(new BigDecimal("70"));

            ActividadFecha fechaUno = new ActividadFecha();
            fechaUno.setFechaInicio(LocalDate.now().minusWeeks(6));
            fechaUno.setFechaFin(LocalDate.now().minusWeeks(5));

            ActividadFecha fechaDos = new ActividadFecha();
            fechaDos.setFechaInicio(LocalDate.now().minusWeeks(4));
            fechaDos.setFechaFin(LocalDate.now().minusWeeks(3));

            actividad.addFecha(fechaUno);
            actividad.addFecha(fechaDos);

            Kpi kpi = new Kpi();
            kpi.setValor("80% asistencia" );
            kpi.setDescripcion("Porcentaje de asistencia promedio por cohorte");
            actividad.addKpi(kpi);

            objetivo.addActividad(actividad);
            proyecto.addObjetivo(objetivo);

            PresupuestoItem computadoras = new PresupuestoItem();
            computadoras.setCategoria("Equipamiento");
            computadoras.setCantidad(10);
            computadoras.setUnidades("Computadoras portátiles");
            computadoras.setValor(new BigDecimal("8000"));
            proyecto.addPresupuesto(computadoras);

            PresupuestoItem utiles = new PresupuestoItem();
            utiles.setCategoria("Materiales");
            utiles.setCantidad(200);
            utiles.setUnidades("Kits de papelería");
            utiles.setValor(new BigDecimal("2000"));
            proyecto.addPresupuesto(utiles);

            SolicitudDesembolso solicitud = new SolicitudDesembolso();
            solicitud.setDocumento("presupuesto_inclusion_digital.pdf");
            solicitud.setEstado("APROBADO");
            proyecto.addSolicitud(solicitud);

            ong.addProyecto(proyecto);
            ongRepository.save(ong);
        }
    }
}
