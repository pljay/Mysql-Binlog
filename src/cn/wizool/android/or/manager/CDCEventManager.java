package cn.wizool.android.or.manager;
import cn.wizool.android.or.CDCEvent;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CDCEventManager {
    public static final ConcurrentLinkedDeque<CDCEvent> queue = new ConcurrentLinkedDeque<>();
}
