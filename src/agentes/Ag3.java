package agentes;

import contenedores.Cliente;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Ag3 extends Agent {
    /*
    Este metodo es la configuracion inicial del agente. Aqui programo al agente
     */
    @Override
    protected void setup() {
        //System.out.println("Agent name: " + getName());
        //super.setup(); super constructor
        addBehaviour(new Ag3.CompotamientoAgente());
    }


    /*
    Es el ultimo suspiro del agente
     */
    @Override
    protected void takeDown() {
        // super.takeDown();
        System.out.println("Muerto");


    }

    // Sub clase, del comportamiento del agente y esto lo agrego al set up.
    class CompotamientoAgente extends Behaviour {
        private boolean finalizar = false;
        @Override
        public void action() {
            System.out.println(getName());
            Mensajes.enviarMSJ("Ag2",getAgent(),"Hola Agente 2 soy el Agente 3",new Cliente("El ultimo mundial del","Bicho", 2022), ACLMessage.INFORM,
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
