# erp-lite

Estructura multi-módulo Gradle para el proyecto ERP.

## Módulos

- `erp-common`: utilidades y contratos comunes.
- `erp-domain`: entidades y reglas de negocio.
- `erp-application`: casos de uso y orquestación.
- `erp-infrastructure`: adaptadores externos, persistencia e integraciones.
- `erp-api`: capa de entrada Spring Web.

## Ejecución

```bash
./gradlew :erp-api:bootRun
```

