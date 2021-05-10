package egroup.gameserver;

import java.util.List;

/**
 * Created by prog on 13.03.15.
 */
public interface ReceiveListener {
    public void dataReceive(Client client, String data);
    
    public void dataReceve(Client client, List<Client> clients);
}
