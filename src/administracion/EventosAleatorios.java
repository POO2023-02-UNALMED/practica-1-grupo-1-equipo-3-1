package administracion;

import java.util.Random;

public class EventosAleatorios {
	
}
    // Enum para EventoPaquete
    enum EventoPaquete {
    	
        DERRUMBE(?),              // Probabilidad del ? (Puede sumar 100% pero deberiamos dejar un porcentaje en el que no pase nada)
        DILUVIO(?),               // Probabilidad del 
        TRAFICO(?),               // Probabilidad del 
        GUERRILLA(?),              // Probabilidad del 
        LLORONA(?),               // Probabilidad del 
        ATENTADO(?),               // Probabilidad del 
        ATRACO(?),                // Probabilidad del 
        DAÑO_EN_VEHICULO(?);     // Probabilidad del 

        private int probabilidad;

        // Constructor personalizado para asignar la probabilidad
        private EventoPaquete(int probabilidad) {
            this.probabilidad = probabilidad;
        }

        // Método para obtener la probabilidad
        public int getProbabilidad() {
            return probabilidad;
        }
    }

    // Enum para EventoAnimal
    enum EventoAnimal {
    	
        ESCAPE(?),                // Probabilidad del ? (La probabilidad acá no debe sumar 100% no todos los casos escapa o muere) 
        MUERTO(?);               // Probabilidad del 

        private int probabilidad;

        // Constructor personalizado para asignar la probabilidad
        private EventoAnimal(int probabilidad) {
            this.probabilidad = probabilidad;
        }

        // Método para obtener la probabilidad
        public int getProbabilidad() {
            return probabilidad;
        }
    }

    // Atributos de la clase
    private int probabilidad;
    private EventoPaquete eventoPaquete;
    private EventoAnimal eventoAnimal;

    // Constructor con valor predeterminado para la probabilidad  (VIERNES HABLAMOS DE ESTO)
    public EventosAleatorios() {
        probabilidad = 0;  // Valor predeterminado de probabilidad
        eventoPaquete = EventoPaquete.LLORONA; // Valor personalizado para eventoPaquete
        eventoAnimal = EventoAnimal.MUERTO;    // Valor personalizado para eventoAnimal
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

        if (numeroAleatorio < eventoPaquete.getProbabilidad()) {
            System.out.println("¡Ha ocurrido un evento de paquete: " + eventoPaquete + "!"); // El viernes asignaremos un print mas enfocado 
        } else if (numeroAleatorio < (eventoPaquete.getProbabilidad() + eventoAnimal.getProbabilidad())) {
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