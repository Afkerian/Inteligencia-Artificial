package contenedores;

import agentes.Ag1;
import agentes.Ag2;
import agentes.Ag3;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Contenedor {
    private AgentContainer agentContainer;

    public void crearContenedor(){

        jade.core.Runtime runtime = jade.core.Runtime.instance(); // Esto es un proceso como tal para el contenedor
        Profile profile = new ProfileImpl(null, 1099, null); // null : escoge aleatorio
        runtime.createMainContainer(profile);
        agentContainer = runtime.createAgentContainer(profile);
        agregarAgentes();
        // No ICP active que el puerto ya esta siendo usado

    }

    private void agregarAgentes()  { // try catch
        /**
         * Se crea el Agente 3, luego el 2 y por ultimo el 1 con el proposito de que siempre debe exitir el que recibe
         * los mensajes antes del que los envia
         */
        try{
            agentContainer.createNewAgent("AG3", Ag3.class.getName(), null).start();
            agentContainer.createNewAgent("AG2", Ag2.class.getName(), null).start();
            agentContainer.createNewAgent("AG1", Ag1.class.getName(), new Object[]{this,1}).start();
            //agentContainer
            // El setup nunca lo llamo explicitamente, con startp lo invoco
        }catch (StaleProxyException e){
            System.out.println(e);
        }

    }

    public void crearHijos(String nickname, Object[] conocimiento){
        try {
            agentContainer.createNewAgent(nickname, Ag1.class.getName(), conocimiento).start();
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
    }

}