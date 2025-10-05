import { AfterViewInit, Component, Inject, PLATFORM_ID, signal } from '@angular/core';
import { isPlatformBrowser, CommonModule } from '@angular/common';

declare const iniciarDatos: (data: any) => void;

@Component({
  selector: 'app-payments',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './payments.component.html'
})
export class PaymentsComponent implements AfterViewInit {
  loading = signal(false);
  successMsg = signal('');
  errorMsg = signal('');
  lastResponse = signal<any | null>(null);

  private payboxData: any = {
    // ➜ correo del comercio en SANDBOX
    PayboxRemail: 'correoplux@gmail.com',

    // ➜ cómo se muestra tu comercio (puedes incluir el id de negocio y/o RUC como referencia)
    PayboxRename: 'OpenFav • Comercio 921 (RUC 1792236894001)',

    // ➜ datos del cliente (de prueba)
    PayboxSendname: 'Cliente de Prueba',
    PayboxSendmail: 'cliente.prueba@example.com',
    PayBoxClientPhone: '0999999999',
    PayboxDirection: 'Dirección de prueba',

    // ➜ monto de prueba (ajústalo a lo que necesites)
    PayboxBase0: '0.00',      // parte con 0% IVA
    PayboxBase12: '10.00',    // parte con IVA (si tu comercio maneja 12%/15% según su config)
    PayboxDescription: 'Orden #TEST-921',

    // ➜ ambiente sandbox (solo frontend)
    PayboxProduction: false,
    PayboxEnvironment: 'sandbox',
    PayboxLanguage: 'es',
    PayboxPagoPlux: true,

    // ➜ recurrente (déjalo así si no usas planes)
    PayboxRecurrent: false,
    PayboxIdPlan: 'Plan Mensual',
    PayboxPermitirCalendarizar: true,
    PayboxPagoInmediato: true,
    PayboxCobroPrueba: false,

    // ➜ comentario opcional
    consumptionCode: 'Compra de prueba sandbox — comercio 921',

    // ➜ callback solo para ver la respuesta (sin backend por ahora)
    onAuthorize: (response: any) => {
      console.log('onAuthorize:', response);
      // si ya tienes signals o mensajes en pantalla, puedes setearlos aquí
    }
  };

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.initPayboxSafely(); // espera Data/$/DOM y corre Data.init(...)
    }
  }

  // Opcional: logs al click para depuración
  onPayClick() {
    const w: any = window as any;
    console.log('Click Pagar ahora. window.Data =', w.Data);
    if (!w.Data) {
      this.errorMsg.set('El script de PayPlux no está disponible. Revisa index.html.');
    } else {
      this.errorMsg.set('');
      this.loading.set(true); // mostrará "Procesando..." mientras interactúas con el modal
      // Nota: no llamamos a nada manual aquí; el click en #pay abre el modal si Data.init ya corrió.
    }
  }

  /** Inicializa PayPlux solo cuando todo está listo para evitar que el click no haga nada */
  private initPayboxSafely() {
    const ready = () =>
      !!((window as any).Data && (window as any).$ &&
        document.getElementById('pay') && document.getElementById('modalPaybox'));

    const boot = () => {
      console.log('[Paybox] Inicializando con Data.init(...) (solo frontend/sandbox)');
      iniciarDatos(this.payboxData);
    };

    if (ready()) {
      boot();
    } else {
      console.log('[Paybox] Esperando que cargue script y DOM...');
      const interval = setInterval(() => {
        if (ready()) { clearInterval(interval); clearTimeout(timeout); boot(); }
      }, 200);
      const timeout = setTimeout(() => {
        clearInterval(interval);
        this.errorMsg.set('No se pudo inicializar PayPlux: verifica scripts/IDs.');
        console.error('[Paybox] Timeout esperando window.Data/jQuery/DOM. Revisa index.html y IDs.');
      }, 10000);
    }
  }
}
