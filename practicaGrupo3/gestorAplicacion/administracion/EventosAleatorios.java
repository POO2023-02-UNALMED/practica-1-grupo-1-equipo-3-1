package administracion;

import java.util.Random;

public class EventosAleatorios {

    enum EventoPaquete {
        DERRUMBE(50, new String[]{"¡Ha ocurrido un DERRUMBE, tu paquete se ha roto!", "¡Ha ocurrido un DERRUMBE pero tu paquete se encuentra en buen estado!"}),
        DILUVIO(50, new String[]{"¡Ha ocurrido un DILUVIO, tu paquete se ha perdido!", "¡Ha ocurrido un DILUVIO pero tu paquete se encuentra en buen estado!"}),
        TRAFICO(50, new String[]{"¡El conductor se ha quedado en un TACO, tu paquete podría tardar un poco!", "¡El conductor se ha quedado en un TACO, tu paquete podría demorarse un poco!"}),
        GUERRILLA(50, new String[]{"¡La GUERRILLA ha aparecido y ha robado tu paquete!", "¡La GUERRILLA ha aparecido y ha destrozado tu paquete!"}),
        ATENTADO(50, new String[]{"¡Ha ocurrido un ATENTADO en nuestra sede, tu paquete ha sido explotado!", "¡Ha ocurrido un ATENTADO pero tu paquete sorprendentemente esta intacto!"}),
        ATRACO(50, new String[]{"¡Ha ocurrido un ATRACO en nuestra sede, tu paquete ha sido robado!", "¡Ha ocurrido un ATRACO en nuestra sede, tu paquete ha pasado desapercibido!"}),
        DAÑO_EN_VEHICULO(50, new String[]{"¡Nuestro medio de transporte se ha CHOCADO, tu paquete se ha roto!", "¡Nuestro medio de transporte se ha quedado sin GASOLINA ,tu paquete podría tardar un poco!", "¡Nuestro medio de transporte ha sufrido un daño pero tu paquete se encuentra en buen estado!"});

        private int probabilidad;
        private String[] mensajes;

        private EventoPaquete(int probabilidad, String[] mensajes) {
            this.probabilidad = probabilidad;
            this.mensajes = mensajes;
        }

        public int getProbabilidad() {
            return probabilidad;
        }

        public String obtenerMensaje() {
            Random random = new Random();
            return mensajes[random.nextInt(mensajes.length)];
        }
    }

    enum EventoAnimal {
        ESCAPE(35, new String[]{"Tu animal ha escapado", "Tu animal ha escapado pero pudimos recuperarlo"}),
        MUERTO(20, new String[]{"Tu animal ha muerto", "Tu animal ha muerto."});

        private int probabilidad;
        private String[] mensajes;

        private EventoAnimal(int probabilidad, String[] mensajes) {
            this.probabilidad = probabilidad;
            this.mensajes = mensajes;
        }

        public int getProbabilidad() {
            return probabilidad;
        }

        public String obtenerMensaje() {
            Random random = new Random();
            return mensajes[random.nextInt(mensajes.length)];
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
    public String generarEventoAleatorio() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100); // Rango entre 0 y 99

        int probabilidadTotal = eventoPaquete.getProbabilidad() + eventoAnimal.getProbabilidad();

        if (numeroAleatorio < eventoPaquete.getProbabilidad()) {
            return(eventoPaquete.obtenerMensaje()); 
        } else if (numeroAleatorio < probabilidadTotal) {
            return(eventoAnimal.obtenerMensaje());
        } else {
            return("No ha ocurrido ningún evento, tu paquete sigue en perfecto estado.");
        }
    }
}