package administracion;

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
