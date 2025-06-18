CREATE DATABASE SGDF;
USE SGDF;
-- 1. Cliente
CREATE TABLE Cliente (
    ID_CLIENTE INT PRIMARY KEY,
    NOMBRE_RAZON_SOCIAL VARCHAR(50),
    TIPO_CLIENTE VARCHAR(50),
    NIT INT,
    DIRECCION VARCHAR(100),
    TELEFONO VARCHAR(50),
    CORREO_ELECTRONICO VARCHAR(50),
    CONDICIONES_PAGO VARCHAR(20),
    ESTADO BOOLEAN
);

INSERT INTO Cliente (ID_CLIENTE, NOMBRE_RAZON_SOCIAL, TIPO_CLIENTE, NIT, DIRECCION, TELEFONO, CORREO_ELECTRONICO, CONDICIONES_PAGO, ESTADO) VALUES
(1, 'Comercial López S.A.', 'Empresa', 123456789, 'Av. Reforma 123, CDMX', '5551234567', 'contacto@lopezsa.com', 'Contado', TRUE),
(2, 'Juan Pérez', 'Persona Física', 987654321, 'Calle Morelos 45, Puebla', '2227654321', 'juan.perez@gmail.com', 'Crédito', TRUE),
(3, 'Distribuidora Martínez', 'Empresa', 112233445, 'Blvd. Benito Juárez 78, Monterrey', '8181122334', 'ventas@martinez.mx', 'Contado', TRUE),
(4, 'Lucía Gómez', 'Persona Física', 998877665, 'Calle Hidalgo 33, Guadalajara', '3339876543', 'lucia.gomez@hotmail.com', 'Crédito', FALSE),
(5, 'Servicios Integrales S.A. de C.V.', 'Empresa', 556677889, 'Av. Insurgentes 200, CDMX', '5559988776', 'info@integrales.com', 'Contado', TRUE),
(6, 'Carlos Ramírez', 'Persona Física', 123123123, 'Calle Juárez 88, Toluca', '7223334455', 'c.ramirez@gmail.com', 'Crédito', TRUE),
(7, 'Alimentos del Norte', 'Empresa', 334455667, 'Carr. Nacional Km 15, Monterrey', '8181239876', 'contacto@alnorte.com', 'Contado', FALSE),
(8, 'María Hernández', 'Persona Física', 444555666, 'Av. Universidad 67, León', '4775566778', 'm.hernandez@yahoo.com', 'Crédito', TRUE),
(9, 'ElectroMex S.A.', 'Empresa', 776655443, 'Av. Tecnológico 99, Querétaro', '4429988776', 'ventas@electromex.com.mx', 'Crédito', TRUE),
(10, 'Luis Ortega', 'Persona Física', 321654987, 'Calle Reforma 150, Mérida', '9991234567', 'l.ortega@gmail.com', 'Contado', FALSE);

-- 2. Producto o Servicio
CREATE TABLE ProductoServicio (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_descripcion VARCHAR(255) NOT NULL,
    tipo ENUM('producto', 'servicio') NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    unidad_medida VARCHAR(50),
    codigo_barras_sku VARCHAR(100) UNIQUE,
    stock INT,
    id_impuesto INT,
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    FOREIGN KEY (id_impuesto) REFERENCES Impuesto(id_impuesto)
);

INSERT INTO ProductoServicio (nombre_descripcion, tipo, precio_unitario, unidad_medida, codigo_barras_sku, stock, id_impuesto) VALUES
('Laptop HP 15"', 'producto', 12500.00, 'pieza', 'HP12345', 20, 1),
('Servicio de instalación', 'servicio', 1500.00, 'servicio', 'SERV001', NULL, 2),
('Monitor Samsung 27"', 'producto', 4800.00, 'pieza', 'MON2345', 15, 1),
('Teclado Mecánico', 'producto', 1200.00, 'pieza', 'KEY9876', 30, 3),
('Mouse Inalámbrico', 'producto', 750.00, 'pieza', 'MOU6543', 50, 3),
('Consultoría Técnica', 'servicio', 3000.00, 'servicio', 'CONS999', NULL, 4),
('Cable HDMI', 'producto', 150.00, 'pieza', 'CBL1111', 80, 1),
('Audífonos Bluetooth', 'producto', 2100.00, 'pieza', 'AUD5555', 25, 5),
('Silla Ergonómica', 'producto', 6200.00, 'pieza', 'SLL0007', 10, 6),
('Licencia Software', 'servicio', 4500.00, 'licencia', 'LIC2025', NULL, 2);


-- 6. Impuesto
CREATE TABLE Impuesto (
    id_impuesto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL,
    tipo ENUM('por producto', 'por factura') NOT NULL,
    aplicacion ENUM('exento', 'no exento') NOT NULL
);

INSERT INTO Impuesto (nombre, porcentaje, tipo, aplicacion) VALUES
('IVA General', 16.00, 'por producto', 'no exento'),
('IVA Reducido', 8.00, 'por producto', 'no exento'),
('Exento de IVA', 0.00, 'por producto', 'exento'),
('ISR', 10.00, 'por factura', 'no exento'),
('IEPS', 5.00, 'por producto', 'no exento'),
('IVA Zona Frontera', 11.00, 'por producto', 'no exento'),
('Tasa Cero', 0.00, 'por producto', 'exento'),
('Servicio Especial', 12.00, 'por factura', 'no exento'),
('Gravamen Extra', 7.50, 'por producto', 'no exento'),
('Retención ISR', 1.00, 'por factura', 'no exento');

-- 3. Factura
CREATE TABLE Factura (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    fecha_emision DATE NOT NULL,
    id_cliente INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    impuestos_aplicados DECIMAL(10, 2),
    total_final DECIMAL(10, 2) NOT NULL,
    forma_pago ENUM('efectivo', 'tarjeta', 'transferencia', 'otro') NOT NULL,
    estado ENUM('pagada', 'parcial', 'no pagada', 'anulada') DEFAULT 'no pagada',
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

INSERT INTO Factura (fecha_emision, id_cliente, subtotal, impuestos_aplicados, total_final, forma_pago, estado)
VALUES
('2024-01-01', 1, 10000.00, 1600.00, 11600.00, 'efectivo', 'pagada'),
('2024-01-16', 2, 1500.00, 0.00, 1500.00, 'tarjeta', 'pagada'),
('2024-01-31', 3, 12000.00, 1920.00, 13920.00, 'transferencia', 'pagada'),
('2024-02-15', 4, 750.00, 60.00, 810.00, 'efectivo', 'pagada'),
('2024-03-01', 5, 3000.00, 300.00, 3300.00, 'efectivo', 'parcial'),
('2024-03-16', 6, 150.00, 24.00, 174.00, 'transferencia', 'pagada'),
('2024-03-31', 7, 2100.00, 336.00, 2436.00, 'tarjeta', 'no pagada'),
('2024-04-15', 8, 6200.00, 0.00, 6200.00, 'tarjeta', 'anulada'),
('2024-04-30', 9, 4500.00, 0.00, 4500.00, 'efectivo', 'pagada'),
('2024-05-15', 10, 2500.00, 400.00, 2900.00, 'otro', 'parcial');


-- 4. Detalle de Factura
CREATE TABLE DetalleFactura (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    descuento DECIMAL(10, 2) DEFAULT 0,
    impuestos_aplicados DECIMAL(10, 2),
    total_item DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_factura) REFERENCES Factura(id_factura),
    FOREIGN KEY (id_producto) REFERENCES ProductoServicio(id_producto)
);

INSERT INTO DetalleFactura (id_factura, id_producto, cantidad, precio_unitario, descuento, impuestos_aplicados, total_item)
VALUES
(1, 1, 1, 10000.00, 0, 1600.00, 11600.00),
(2, 2, 1, 1500.00, 0, 0.00, 1500.00),
(3, 3, 2, 6000.00, 0, 1920.00, 13920.00),
(4, 5, 1, 750.00, 0, 60.00, 810.00),
(5, 6, 1, 3000.00, 0, 300.00, 3300.00),
(6, 7, 1, 150.00, 0, 24.00, 174.00),
(7, 8, 1, 2100.00, 0, 336.00, 2436.00),
(8, 9, 1, 6200.00, 0, 0.00, 6200.00),
(9, 10, 1, 4500.00, 0, 0.00, 4500.00),
(10, 4, 2, 1250.00, 0, 400.00, 2900.00);

-- 5. Pago
CREATE TABLE Pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT NOT NULL,
    fecha_pago DATE NOT NULL,
    monto_pagado DECIMAL(10, 2) NOT NULL,
    medio_pago ENUM('efectivo', 'tarjeta', 'transferencia', 'cheque', 'otro') NOT NULL,
    referencia_bancaria VARCHAR(100),
    estado ENUM('confirmado', 'pendiente') DEFAULT 'pendiente',
    FOREIGN KEY (id_factura) REFERENCES Factura(id_factura)
);

INSERT INTO Pago (id_factura, fecha_pago, monto_pagado, medio_pago, referencia_bancaria, estado)
VALUES
(1, '2024-01-01', 11600.00, 'efectivo', 'REF001', 'confirmado'),
(2, '2024-01-16', 1500.00, 'tarjeta', 'REF002', 'confirmado'),
(3, '2024-01-31', 13920.00, 'transferencia', 'REF003', 'confirmado'),
(4, '2024-02-15', 810.00, 'efectivo', 'REF004', 'confirmado'),
(5, '2024-03-02', 1500.00, 'efectivo', 'REF005', 'pendiente'),
(6, '2024-03-17', 174.00, 'transferencia', 'REF006', 'confirmado'),
(9, '2024-04-30', 4500.00, 'efectivo', 'REF007', 'confirmado'),
(10, '2024-05-16', 1500.00, 'otro', 'REF008', 'pendiente'),
(5, '2024-03-03', 1800.00, 'efectivo', 'REF009', 'confirmado'),
(10, '2024-05-20', 1400.00, 'otro', 'REF010', 'pendiente');


-- 7. Transacción
CREATE TABLE Transaccion (
    id_transaccion INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo ENUM('venta', 'pago', 'devolución', 'otro') NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    descripcion TEXT,
    referencia_factura INT,
    referencia_pago INT,
    FOREIGN KEY (referencia_factura) REFERENCES Factura(id_factura),
    FOREIGN KEY (referencia_pago) REFERENCES Pago(id_pago)
);

INSERT INTO Transaccion (fecha, tipo, monto, descripcion, referencia_factura, referencia_pago)
VALUES
('2024-01-01', 'venta', 11600.00, 'Venta laptop', 1, 1),
('2024-01-16', 'venta', 1500.00, 'Servicio instalación', 2, 2),
('2024-01-31', 'venta', 13920.00, 'Venta monitores', 3, 3),
('2024-02-15', 'venta', 810.00, 'Venta mouse', 4, 4),
('2024-03-01', 'pago', 1500.00, 'Pago parcial consultoría', 5, 5),
('2024-03-16', 'venta', 174.00, 'Venta cable HDMI', 6, 6),
('2024-04-30', 'pago', 4500.00, 'Pago completo software', 9, 7),
('2024-05-15', 'venta', 2900.00, 'Venta teclado', 10, 8),
('2024-03-03', 'pago', 1800.00, 'Pago parcial restante', 5, 9),
('2024-05-20', 'pago', 1400.00, 'Pago parcial restante', 10, 10);


-- 8. Reporte (estructura base para registros generados dinámicamente)
CREATE TABLE Reporte (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    tipo_reporte VARCHAR(100) NOT NULL,
    rango_fecha_inicio DATE NOT NULL,
    rango_fecha_fin DATE NOT NULL,
    filtros_aplicados TEXT,
    formato_salida ENUM('PDF', 'Excel', 'vista web') NOT NULL,
    fecha_generacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Reporte (tipo_reporte, rango_fecha_inicio, rango_fecha_fin, filtros_aplicados, formato_salida)
VALUES
('Ventas Mensuales', '2024-01-01', '2024-01-31', 'Todas las facturas pagadas', 'PDF'),
('Clientes Inactivos', '2024-01-01', '2024-06-01', 'Estado = FALSE', 'Excel'),
('Pagos Pendientes', '2024-03-01', '2024-05-31', 'Estado = pendiente', 'vista web'),
('Productos más vendidos', '2024-01-01', '2024-06-01', '', 'PDF'),
('Facturas anuladas', '2024-01-01', '2024-06-01', 'Estado = anulada', 'Excel'),
('Top clientes', '2024-01-01', '2024-06-01', '', 'vista web'),
('Transacciones por tipo', '2024-01-01', '2024-06-01', 'tipo=venta', 'Excel'),
('Uso de impuestos', '2024-01-01', '2024-06-01', '', 'PDF'),
('Resumen mensual', '2024-05-01', '2024-05-31', '', 'vista web'),
('Detalle por producto', '2024-01-01', '2024-06-01', '', 'Excel');
