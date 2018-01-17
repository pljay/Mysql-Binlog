package or.manager;
import java.util.concurrent.ConcurrentLinkedDeque;
import or.CDCEvent;
public class CDCEventManager {
    public static final ConcurrentLinkedDeque<CDCEvent> queue = new ConcurrentLinkedDeque<>();
}
