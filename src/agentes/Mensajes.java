package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;


import java.io.IOException;
import java.io.Serializable;

public class Mensajes {
    public static void enviarMSJ(String receptor, Agent emisor, String contenido, Serializable contenidoObject ,int tipo, String idConversacion, boolean tipoContenidoObject){
        ACLMessage acl = new ACLMessage(tipo);
        if(tipoContenidoObject){
            try {
                acl.setContentObject(contenidoObject);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            acl.setContent(contenido);
        }
        acl.setConversationId(idConversacion);
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        acl.setSender(emisor.getAID());
        AID aid = new AID();
        aid.setLocalName(receptor);

        acl.addReceiver(aid);


        emisor.send(acl);

    }
}
