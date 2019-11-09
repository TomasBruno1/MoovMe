# MoovMe
Tp de Prog 2

Para poder registrarse a la aplicación se le pide al usuario que ingrese un nombre, un teléfono y una contraseña, los cuales se le pasan al operador de usuarios. Este verifica que no exista un usuario con el mismo nombre y de ser así lo crea y lo agrega a una lista de usuarios que comienza por default con único administrador. Además al crearse,  el operador de zonas le pasa todas las zonas existentes para poder crear dos HashMaps  iguales (puntosPorZona y puntosPorZonaFijos) los cuales tienen como código las zonas y como valor la cantidad de puntos que va a ganar el cliente al usar activos.

Cada usuario tiene un boolean (isAdmin) que inicia como false indicando si este es administrador o no. Al iniciar sesión como administrador el operador de usuarios verifica que isAdmin sea true y además que exista el usuario y la contraseña ingresada sea correcta. Al iniciar como cliente realiza el mismo proceso pero verifica que isAdmin sea false.

Únicamente un administrador tiene la posibilidad de crear nuevos administradores, para esto se le pasa un nombre y una contraseña. Al igual que al crear cualquier usuario se le pasan los datos al operador el cual verifica que no exista nadie con ese nombre y de ser así crea al administrador (isAdmin true) y lo agrega a la lista de usuarios. El administrador también puede eliminar a otros administradores o clientes. En el caso de los administradores se la pasa un nombre y verifica que este usuario sea un administrador, si esto se cumple verifica que este no sea él mismo y que exista por lo menos otro administrador en la lista de usuarios. Para los clientes simplemente se fija que no sea un administrador y lo elimina.
	
Para dar de alta distintos tipos de activos se le pide al administrador que ingrese el nombre de uno. El operador de zonas tiene una lista de los tipos de activos existentes, si el nombre que se le pasa todavía no existe lo agrega a la lista, de lo contrario le indica al administrador que el tipo de activo ingresado ya existe. 
Cada zona va a tener lotes de distintos tipos de activos, el administrador es el encargado de comprar estos lotes. Para esto se le pide que ingrese el tipo de activo, la cantidad de activos que desea, la zona y terminal en la que los quiere poner inicialmente, una tarifa de uso por minuto como también los puntos que se le otorgan al cliente al utilizar dicho activo. Primero el operador de zonas confirma que la zona, terminal y tipo de activo ingresados existen, después crea una lista de activos teniendo en cuenta los datos anteriores otorgándole a cada uno de estos un código numérico único. Por último crea un lote con esta lista y también se le establece un código numérico a este lote para finalmente poder ubicarlo en la terminal indicada.

Cuando un cliente inicia sesión tiene la posibilidad de alquilar un activo, solo se podrá alquilar un activo a la vez y el nombre y código del mismo se mostraran en pantalla. De no estar utilizando ninguno se le va a pedir al usuario que ingrese la zona en la que se encuentra. A continuación se le van a mostrar todas las terminales en dicha zona para que pueda elegir una y ver todos los tipos de activos disponibles en la misma. Una vez que elige el tipo de activo deberá ingresar una hora estimada de devolución y finalmente el activo estará alquilado. 
A la hora de devolver el activo se le va a mostrar al cliente el costo total del alquiler el cual va a depender de la cantidad de minutos que fue utilizado mas una tarifa fija. Después se le va a pedir que ingrese la zona y terminal en la que se encuentra actualmente y el operador de usuarios el cual tiene una lista de descuentos según zona, tipo de activo, puntos mínimos para dicho canje y descuento a realizar, va a ver cuales son los descuentos que podría llegar a aplicar el cliente. Si llegan a existir descuentos aplicables se le va a preguntar si quiere utilizar alguno y en caso de que la respuesta sea positiva se procederá a mostrarle todos los posibles. Una vez que el cliente elige un descuento se muestra en pantalla cual seria el precio total y se le restan los puntos necesarios para realizar el canje.
Cuando el activo es devuelto se verifica que este se encuentre dentro de la misma zona que la terminal de la que se retiro, si estas no coinciden se bloquea al cliente y el activo vuelve al la terminal de origen. De no ser así se establece que la terminal del activo es la ingresada. Además, cuando el cliente devuelve el activo si la hora actual es igual o menor a la hora de devolución estimada se le van a sumar 20% más de puntos establecidos por el activo al cliente, de no ser así se otorgan los puntos sin ningún beneficio.

Cuando el cliente utiliza un descuento, los puntos con los que se trabajan son con los puntosPorZona. Mientras que a estos se les pueden sumar puntos al utilizar activos y restar puntos por el uso de descuentos a los puntosPorZonaFijos solo se le van a sumar los puntos por uso dado que estos van a ser los que mas adelante se van a utilizar para realizar el ranking mensual de clientes por zona.
	
Cuando se bloquea a un cliente por salir de su zona se genera una multa cuyo precio va a ser la mitad del precio fijo del activo. Cuando un cliente se encuentra bloqueado, en el momento que vuelva a iniciar sesión no le va a permitir ingresar hasta que la multa este pagada.  
