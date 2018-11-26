-- Creamos la base de datos
CREATE DATABASE PruebaTodo1;

-- Creamos la tabla USUARIOS_TODO1
-- Eliminamos la tabla antes de crearla, esto por si al momento de correr el script ya està la tabla creada no saque error
DROP TABLE USUARIOS_TODO1;
-- Creamos la tabla USUARIOS_TODO1
CREATE TABLE USUARIOS_TODO1(
	ID_USUARIO MEDIUMINT NOT NULL AUTO_INCREMENT COMMENT 'prueba tecnica de todo1 - almacena el id del usuario',
	TIPO_DOCUMENTO VARCHAR(15) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el tipo de documento del usuario',
	DOCUMENTO VARCHAR(15) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el número de documento del usuario',
	NOMBRES VARCHAR(120) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena los nombres del usuario',
	APELLIDOS VARCHAR(120) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena los apellidos del usuario',
	CARGO VARCHAR(120) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el cargo del usuario',
	PASSWORD VARCHAR(15) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena la clave del usuario',
	FECHA_CREACION DATE NOT NULL COMMENT 'Fecha de creaciòn del registro',
	PRIMARY KEY(ID_USUARIO)	
);

-- INSERTAMOS EL REGISTRO DEL USUARIO ADMINISTRADOR DE LA TIENDA TODO1
INSERT INTO USUARIOS_TODO1(TIPO_DOCUMENTO,DOCUMENTO,NOMBRES,APELLIDOS,CARGO,PASSWORD,FECHA_CREACION)
VALUES('CC','1111111111','Carlos Andres','Mercado Dominguez','Administrador','123456',SYSDATE());

-- Creamos la tabla TIPOS_PRODUCTOS_TODO1
-- Eliminamos la tabla antes de crearla, esto por si al momento de correr el script ya està la tabla creada no saque error
DROP TABLE TIPOS_PRODUCTOS_TODO1;
-- Creamos la tabla TIPOS_PRODUCTOS_TODO1
CREATE TABLE TIPOS_PRODUCTOS_TODO1(
	ID_TIPOS_PRODUCTOS MEDIUMINT NOT NULL AUTO_INCREMENT COMMENT 'prueba tecnica de todo1 - almacena el id del Tipo Producto',
	CODIGO VARCHAR(10) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el código del tipo producto',
	TIPO VARCHAR(60) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el tipo de producto',
	FECHA_CREACION DATE NOT NULL COMMENT 'Fecha de creaciòn del registro',
	PRIMARY KEY(ID_TIPOS_PRODUCTOS)	
);
-- INSERTAMOS EL REGISTROS DE LOS DIFERENTES TIPOS DE PRODUCTOS QUE MANEJARÀ LA TIENDA TODO1
INSERT INTO TIPOS_PRODUCTOS_TODO1(CODIGO,TIPO,FECHA_CREACION)
VALUES('01','CAMISETA',SYSDATE());
INSERT INTO TIPOS_PRODUCTOS_TODO1(CODIGO,TIPO,FECHA_CREACION)
VALUES('02','PANTALON',SYSDATE());
INSERT INTO TIPOS_PRODUCTOS_TODO1(CODIGO,TIPO,FECHA_CREACION)
VALUES('03','VASOS',SYSDATE());
INSERT INTO TIPOS_PRODUCTOS_TODO1(CODIGO,TIPO,FECHA_CREACION)
VALUES('04','JUGUETES',SYSDATE());
INSERT INTO TIPOS_PRODUCTOS_TODO1(CODIGO,TIPO,FECHA_CREACION)
VALUES('05','ACCESORIOS MARVEL',SYSDATE());

-- Creamos la tabla PRODUCTOS_TODO1
-- Eliminamos la tabla antes de crearla, esto por si al momento de correr el script ya està la tabla creada no saque error
DROP TABLE PRODUCTOS_TODO1;
-- Creamos la tabla USUARIOS_TODO1
CREATE TABLE PRODUCTOS_TODO1(
	ID_PRODUCTO MEDIUMINT NOT NULL AUTO_INCREMENT COMMENT 'prueba tecnica de todo1 - almacena el id del Producto',
	CODIGO VARCHAR(10) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el código del producto',
	TIPO VARCHAR(60) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el tipo de producto',
	NOMBRE VARCHAR(120) NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el nombre del producto',
	FECHA_CREACION DATE NOT NULL COMMENT 'Fecha de creaciòn del registro',
	PRIMARY KEY(ID_PRODUCTO)	
);

-- Creamos la tabla INVENTARIO_PRODUCTO_TODO1
-- Eliminamos la tabla antes de crearla, esto por si al momento de correr el script ya està la tabla creada no saque error
DROP TABLE INVENTARIO_PRODUCTO_TODO1;
-- Creamos la tabla INVENTARIO_PRODUCTO_TODO1
CREATE TABLE INVENTARIO_PRODUCTO_TODO1(
	ID_INVENTARIO_PRODUCTO MEDIUMINT NOT NULL AUTO_INCREMENT COMMENT 'prueba tecnica de todo1 - almacena el id del inventario por producto',
	ID_PRODUCTO NUMERIC NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el id del producto',
	CANTIDAD NUMERIC NOT NULL COMMENT 'prueba tecnica de todo1 - almacena la cantidad de productos',
	VALOR_UNIDAD NUMERIC NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el valor por unidad del producto',
	FECHA_CREACION DATE NOT NULL COMMENT 'Fecha de creaciòn del registro',
	PRIMARY KEY(ID_INVENTARIO_PRODUCTO)
);

-- Creamos la tabla VENTAS_TODO1
-- Eliminamos la tabla antes de crearla, esto por si al momento de correr el script ya està la tabla creada no saque error
DROP TABLE VENTAS_TODO1;
-- Creamos la tabla VENTAS_TODO1
CREATE TABLE VENTAS_TODO1(
	ID_VENTAS MEDIUMINT NOT NULL AUTO_INCREMENT COMMENT 'prueba tecnica de todo1 - almacena el id de la venta de productos',
	ID_PRODUCTO NUMERIC NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el id del producto',
	ID_USUARIO NUMERIC NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el id del usuario que realiza la venta',
	CANTIDAD NUMERIC NOT NULL COMMENT 'prueba tecnica de todo1 - almacena la cantidad de productos',
	VALOR_VENTA NUMERIC NOT NULL COMMENT 'prueba tecnica de todo1 - almacena el valor de la venta por producto',
	FECHA_CREACION DATE NOT NULL COMMENT 'Fecha de creaciòn del registro',
	PRIMARY KEY(ID_VENTAS)
);
