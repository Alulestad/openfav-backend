-- Datos iniciales para el proyecto OpenFav
-- Ejecutar este script en la base de datos PostgreSQL configurada en la aplicación (por defecto prismadb)

-- Organizaciones
INSERT INTO ONG (ID_ONG, NOMBRE_ONG, REPRESENTANTE_LEGAL_ONG, EMAIL_ONG, CELULAR_ONG, DIRECCION_ONG)
VALUES
  (1, 'Fundación Alianza Solidaria', 'María Fernanda Torres', 'impacto@alianzasolidaria.ec', '+593994567821', 'Av. Amazonas N34-125 y Naciones Unidas, Quito'),
  (2, 'Colectivo Salud Andina', 'Jorge Oña', 'contacto@saludandina.ec', '+593983214567', 'Av. 6 de Diciembre 1924 y Pasaje J, Quito')
ON CONFLICT (ID_ONG) DO NOTHING;

-- Proyectos
INSERT INTO PROYECTO (ID_PROY, TITULO_PROY, OBJETIVO_GENERAL_PROY, ALCANCE_PROY, MONTO_TOTAL_PROY, PLAZO_TOTAL_PROY,
                      FECHA_INICIO_PROY, FECHA_FIN_PROY, EJES_PROY, RESULTADO_PROY, ID_ONG)
VALUES
  (1, 'Aulas Digitales para Comunidades Andinas',
   'Garantizar el acceso a herramientas digitales que potencien habilidades educativas y de emprendimiento en comunidades rurales.',
   1200, '85000', 12, DATE '2025-04-01', DATE '2026-03-31', 'Educación, Innovación, Inclusión',
   '80% de los participantes incrementan sus competencias digitales.', 1),
  (2, 'Red Comunitaria de Salud Preventiva',
   'Fortalecer las capacidades comunitarias en salud preventiva y detección temprana en zonas rurales de Chimborazo.',
   800, '56000', 10, DATE '2025-05-01', DATE '2026-02-28', 'Salud, Participación comunitaria',
   '70% de las familias cuentan con un plan de prevención y atención temprana.', 2)
ON CONFLICT (ID_PROY) DO NOTHING;

-- Objetivos específicos
INSERT INTO OBJETIVOS_ESPECIFICOS (ID_OBJESP, OBJETIVO_OBJESP, EJES_OBJESP, ID_PROY)
VALUES
  (1, 'Fortalecer competencias digitales docentes', 'Educación, Capacitación', 1),
  (2, 'Implementar laboratorios móviles comunitarios', 'Innovación, Inclusión', 1),
  (3, 'Crear brigadas comunitarias de salud', 'Salud, Comunidad', 2)
ON CONFLICT (ID_OBJESP) DO NOTHING;

-- Actividades
INSERT INTO ACTIVIDAD (ID_ACTI, NOMBRE_ACTI, DESCRIPCION_ACTI, RESULTADO_ESPERADO_ACTI, RESULTADO_OBTENIDO, CATEGORIA, ID_OBJESP)
VALUES
  (1, 'Bootcamp pedagógico digital', 'Actualización docente en herramientas colaborativas y diseño de proyectos STEAM.', 120, 0, 'Capacitación', 1),
  (2, 'Giras tecnológicas comunitarias', 'Instalación itinerante de laboratorios con acompañamiento técnico.', 15, 0, 'Implementación', 2),
  (3, 'Ferias de salud preventiva', 'Jornadas comunitarias para detección temprana de riesgos y educación en nutrición.', 12, 0, 'Salud preventiva', 3)
ON CONFLICT (ID_ACTI) DO NOTHING;

-- Presupuestos
INSERT INTO PRESUPUESTO (ID_PRES, CATEGORIA_PRES, CANTIDAD_PRES, UNIDADES_PRES, VALOR_PRES, ID_PROY)
VALUES
  (1, 'Equipamiento tecnológico', 5, 1, 9000, 1),
  (2, 'Logística y movilidad', 12, 1, 1500, 1),
  (3, 'Kits de salud comunitaria', 20, 1, 800, 2)
ON CONFLICT (ID_PRES) DO NOTHING;

-- Solicitudes de desembolso
INSERT INTO SOLICUTUD_DESEMBOLSO (ID_DESCM, DOCUMENTO_DESCM, ESTADO_DESCM, ID_PRES)
VALUES
  (1, 'solicitud_equipamiento.pdf', 'Aprobado', 1),
  (2, 'solicitud_logistica.pdf', 'Revisando', 2),
  (3, 'solicitud_salud.pdf', 'Enviado', 3)
ON CONFLICT (ID_DESCM) DO NOTHING;

-- Alinear los valores de las secuencias con los datos insertados
SELECT setval('seq_ong_id',        COALESCE((SELECT MAX(ID_ONG) FROM ONG), 0), true);
SELECT setval('seq_proy_id',       COALESCE((SELECT MAX(ID_PROY) FROM PROYECTO), 0), true);
SELECT setval('seq_objesp_id',     COALESCE((SELECT MAX(ID_OBJESP) FROM OBJETIVOS_ESPECIFICOS), 0), true);
SELECT setval('seq_actividad_id',  COALESCE((SELECT MAX(ID_ACTI) FROM ACTIVIDAD), 0), true);
SELECT setval('seq_pres_id',       COALESCE((SELECT MAX(ID_PRES) FROM PRESUPUESTO), 0), true);
SELECT setval('seq_descm_id',      COALESCE((SELECT MAX(ID_DESCM) FROM SOLICUTUD_DESEMBOLSO), 0), true);
