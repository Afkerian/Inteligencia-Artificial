package agentes;

import contenedores.Cliente;
import contenedores.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/*
Estructura de una agente, set up, comportamiento (varios tipos de comportamientos),
el done() hace que un comportamiento secuencial sea ciclico.
 */

public class Ag1 extends Agent { // Para que esta clase sea una agente extiendo, el sniffer es un policia


    /*
    Este metodo es la configuracion inicial del agente. Aqui programo al agente
     */
    @Override
    protected void setup() {
        //System.out.println("Agent name: " + getName());
        //super.setup(); super constructor
        addBehaviour(new CompotamientoAgente());
    }


    /*
    Es el ultimo suspiro del agente
     */
    @Override
    protected void takeDown() {
        // super.takeDown();

        Contenedor contenedor = (Contenedor) getArguments()[0];
        int i = Integer.parseInt(getArguments()[1].toString());
        i++;
        contenedor.crearHijos("Agente Hijo "+i, new Object[]{contenedor,i});
        System.out.println("El Agente "+(i-1)+" murio");

    }

    // Sub clase, del comportamiento del agente y esto lo agrego al set up.
    class CompotamientoAgente extends Behaviour {

        @Override
        public void action() {
            System.out.println(getName());
            Mensajes.enviarMSJ("Ag2",getAgent(),"Hola Agente 2",new Cliente("Alejandro","Moya", 25), ACLMessage.INFORM,
                    "CODAG1-AG2",true);

            doDelete();//Elimina los recursos del conytenedor

        }

        @Override
        public boolean done() {
            return false;
            // false es un comportamiento ciclico, de esta forma lo puedo controlar
            // pero si extiendo la clase a CyclicBehaviour y borro el done() ahi es ciclico
            // siempre pero no lo puedo controlar.
        }
    }
}