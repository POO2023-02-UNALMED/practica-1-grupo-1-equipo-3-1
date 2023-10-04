package productos;

public class EventosAleatorios{
 
/*Los Enum clasificados en 2*/

enum EventoPaquete {
	derrumbe,
	diluvio,
	tráfico,
	guerrilla,
	llorona,
	atentado,
	atraco,
	tu vehiculo ha sufrido un daño
}

enum EventoAnimal{
	escape,
	muerto
}	

	// Atributos de la clase

	private int probabilidad;
	private EventoPaquete evento1;
	private EventoAnimal evento2;

}
