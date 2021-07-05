package org.view.dialog;

public interface InputDialog<T> {
    
    public static final int ADD_STATE = -1;
    
    public void setEditingIndex(int index);
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
    public default void resetToAdd() {
        clear();
        setEditingIndex(ADD_STATE);
    }
}
