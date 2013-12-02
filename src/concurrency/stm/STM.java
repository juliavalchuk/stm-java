package concurrency.stm;

/**
 * @author mishadoff
 */
public final class STM {
    private STM() {}


    public static final Object commitLock = new Object();

    public static <T> T transaction(TransactionBlock block, Ref<T> ref) {
        boolean committed = false;
        T value = null;
        while (!committed) {
            Transaction tx = new Transaction();
            block.setTx(tx);
            block.run();
            committed = tx.commit();
            value = block.getTx().get(ref);
        }

        return value;

    }

}
