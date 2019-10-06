# MoovMe
Tp de Prog 2


Cálculo de precio total de uso de activo:

Cada activo va a tener un precio fijo por ser utilizado independientemente de la zona, además este va a tener una tarifa por minuto que va a depender del tipo de activo y de la zona en la que está siendo utilizado.
Cada una de las zonas (norte, sur, este, oeste) va a tener un valor diferencial considerando la variación de costos entre las mismas. Para saber la tarifa por minuto se multiplica este valor por una ponderación asignada a cada tipo de activo.
El precio total va a ser la suma del precio fijo con el producto de la tarifa por minuto por los minutos que fue utilizado.


Multas y bloqueo de clientes por estar fuera de zona:

Cada activo va a tener una zona de origen, en caso de que esta zona de origen no sea la misma que la actual ( estaEnZona sea false) el administrador puede generar una multa a dicho cliente cuyo precio va a variar según el tipo de activo. La multa también tiene un estado de pago.
Además las cuentas de los clientes tienen dos estados: bloqueada (isBlocked = true) o no bloqueada (isBlocked = false). El administrador es el único que puede bloquear o desbloquear las cuentas. 

Suma de puntos por uso de activo:

Cada usuario cuenta con un puntaje, a este se la va a sumar un punto por cada activo utilizado.
