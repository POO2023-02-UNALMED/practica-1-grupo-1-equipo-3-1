package administracion;

import java.util.Random;

public class EventosAleatorios {

    enum EventoPaquete {
        DERRUMBE(50),
        DILUVIO(50),
        TRAFICO(50),
        GUERRILLA(50),
        LLORONA(50),
        ATENTADO(50),
        ATRACO(50),
        DAÑO_EN_VEHICULO(50);

        private int probabilidad;

        private EventoPaquete(int probabilidad) {
            this.probabilidad = probabilidad;
        }

        public int getProbabilidad() {
            return probabilidad;
        }
    }

    enum EventoAnimal {
        ESCAPE(35),
        MUERTO(20);

        private int probabilidad;

        private EventoAnimal(int probabilidad) {
            this.probabilidad = probabilidad;
        }

        public int getProbabilidad() {
            return probabilidad;
        }
    }

    // Atributos
    private int probabilidad;
    private EventoPaquete eventoPaquete;
    private EventoAnimal eventoAnimal;

    // Constructor con valor predeterminado para la probabilidad y eventos aleatorios
    public EventosAleatorios() {
        probabilidad = 100;
        eventoPaquete = EventoPaquete.values()[new Random().nextInt(EventoPaquete.values().length)];
        eventoAnimal = EventoAnimal.values()[new Random().nextInt(EventoAnimal.values().length)];
    }

    // Métodos para obtener y establecer la probabilidad
    public int getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(int probabilidad) {
        this.probabilidad = probabilidad;
    }

    // Métodos para obtener y establecer eventoPaquete
    public EventoPaquete getEventoPaquete() {
        return eventoPaquete;
    }

    public void setEventoPaquete(EventoPaquete eventoPaquete) {
        this.eventoPaquete = eventoPaquete;
    }

    // Métodos para obtener y establecer eventoAnimal
    public EventoAnimal getEventoAnimal() {
        return eventoAnimal;
    }

    public void setEventoAnimal(EventoAnimal eventoAnimal) {
        this.eventoAnimal = eventoAnimal;
    }

    // Método para generar un evento aleatorio
    public void generarEventoAleatorio() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100); // Rango entre 0 y 99

        int probabilidadTotal = eventoPaquete.getProbabilidad() + eventoAnimal.getProbabilidad();

        if (numeroAleatorio < eventoPaquete.getProbabilidad()) {
            System.out.println("¡Ha ocurrido un evento de paquete: " + eventoPaquete + "!"); //Se cambiara el texto antes del evento si no les gusta (VIERNES)
        } else if (numeroAleatorio < probabilidadTotal) {
            System.out.println("¡Ha ocurrido un evento de animal: " + eventoAnimal + "!");
        } else {
            System.out.println("No ha ocurrido ningún evento.");
        }
    }

    public static void main(String[] args) {
        EventosAleatorios objeto = new EventosAleatorios();
        // Llamar al método para generar eventos aleatorios
        objeto.generarEventoAleatorio();
    }
}