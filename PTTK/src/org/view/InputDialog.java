package org.view;

public interface InputDialog<T> {
    
    public static final int ADD_STATE = -1;
    
    public void editAction(int index);
    public void addAction();
    public void clear();
    public void addEditInfo(T objInfo);
    public T encapsulate();
    public default void actionsPerform(int index) {
        if (index == ADD_STATE) {
            addAction();
        } else {
            editAction(index);
        }
    }
}
