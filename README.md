# 2020-1-01
Repositorio 2020-1 Equipo Garage del curso Métodos formales
# Garage V2.5

## Correcciones

Se corrigieron errores relacionados con métodos que causaban problemas de ejecución, es decir, generaban excepciones; por otro lado, se 
corrigieron problemas asociados con los JSP, pues algunas etiquetas no cerraban causando problemas en el envió de datos a los Servlet. 
Por otro lado, se realizó limpieza general al código en donde se eliminó aquello comentado e innecesario.

## Actualizaciones

### Actualización de Reportes
Se actualizaron los queries y JSP relacionados con los reportes del ministerio de transporte, teniendo en cuenta que los datos relevantes 
para estos son el nombre del parqueadero, el tipo de plaza y la cantidad de reservas asociadas.

### Actualización de Documentación
Se añadieron nuevos datos de prueba en el ***Script - INSERCION y ELIMINAR*** con el objetivo de tener un mejor panorama de Bogotá,
es por esto que se tomaron direcciones, latitudes y longitudes reales respecto a cada localidad; Así mismo se añadieron plazas
de los diferentes tipos de vehículos a cada parqueadero para tener mayor opción de selección.

## Incorporación

### Incorporación de Graficas
Se incorporaron graficas en la sección de reportes para una mayor facilidad y entendimiento de los datos generados por los query 
solicitados por el encargado del Ministerio de Transporte. Por lo cual, se añadió una clase del lenguaje de programación JavaScript
llamada ***Graficas*** en donde se encuentran los métodos necesarios para el tratado de datos y creación de graficas.

### Incorporación de Operaciones
Dado que la mayoría de JSP utilizaban procesos y datos en común, se realizó una clase especifica llamada ***Operaciones***, en donde se
generalizaron los procesos para posteriormente ser importada por los diferentes JSP que lo requieran.

## Incorporación de Alza de Precios
Dado que el ministerio de transporte es el encargado de estipular el alza de precios según las diferentes categorías de parqueaderos,
se añadió un JSP llamado ***actualizarPrecios***, el cual solo puede ser accedido por medio de un usuario único, que se le dará al
ministerio de transporte; por lo cual, para tener acceso se debe agregar lo siguiente en la base de datos.

```
INSERT INTO USUARIO (USUARIO, CONTRASENA, TIPODEUSUARIO) 
VALUES ('minTransporte', '123', 'mintransporte');
```

## Importante
Después de completar la conexión con la base de datos y total funcionamiento se debe ejecutar el siguiente query en la base de datos

```
INSERT INTO PRECIO (TIPOPARQUEADERO, PRECIOMINUTO, PRECIOHORA, PRECIORESERVA, PRECIOINTERRUPCION)
VALUES ('Alturas o Subterraneos', 107.5, 6450, 0 ,3210);

INSERT INTO PRECIO (TIPOPARQUEADERO, PRECIOMINUTO, PRECIOHORA, PRECIORESERVA, PRECIOINTERRUPCION)
VALUES ('Concreto, Asfalto o Gravilla', 75.5, 4550, 0 , 2265);

INSERT INTO PRECIO (TIPOPARQUEADERO, PRECIOMINUTO, PRECIOHORA, PRECIORESERVA, PRECIOINTERRUPCION)
VALUES ('Piso en Afirmado o Cesped', 54, 3250, 0 , 1620);

```
