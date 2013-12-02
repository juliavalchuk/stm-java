package concurrency.stm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mishadoff
 */
public final class GlobalContext extends Context {
    private HashMap<Ref, Object> inTxMap = new HashMap<Ref, Object>();

    private static GlobalContext instance = new GlobalContext();

    private GlobalContext() { }

    public static GlobalContext get() {
        return instance;
    }

    @Override
    <T> T get(Ref<T> ref) {
        if (!inTxMap.containsKey(ref)) {
            RefTuple<T, Long> tuple = ref.content;
            inTxMap.put(ref, tuple.value);
        }
        return (T)inTxMap.get(ref);
    }
}
