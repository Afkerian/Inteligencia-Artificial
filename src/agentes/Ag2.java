package agentes;

import contenedores.Cliente;
import jade.core.behaviours.Behaviour;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Ag2 extends Agent {
    /*
    Este metodo es la configuracion inicial del agente. Aqui programo al agente
     */
    @Override
    protected void setup() {
        //System.out.println("Agent name: " + getName());
        //super.setup(); super constructor
        addBehaviour(new Ag2.CompotamientoAgente());
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
            //System.out.println(getName());
            ACLMessage acl = blockingReceive();
            try {
                Cliente cliente = (Cliente) acl.getContentObject();
                System.out.println(cliente);
            } catch (UnreadableException e) {
                throw new RuntimeException(e);
            }

            finalizar = true;
            //doDelete();//Elimina los recursos del conytenedor

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
