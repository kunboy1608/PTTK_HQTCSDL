package org.view.panel;

import java.awt.TrayIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import org.resources.MetroUI;

public interface Panel {
    public TableModel getTableModel();
    public void setTableModel(TableModel tableModel);
    public void reloadTable();
    
    public void actionAdd();
    public void actionEdit();
    public void actionSearch();
    public void actionDelete();
    public void actionPrint();
    public void actionAdvancedSearch();
    
    public default void requireSelection() {
        MetroUI.notificate(
            "Lỗi: Thiếu đối tượng", 
            "Vui lòng chọn đối tượng trong bảng để thao tác", 
            TrayIcon.MessageType.ERROR
        );
    }
    public default void requireFulfill() {
        MetroUI.notificate(
            "Trường thông tin trống", 
            "Hãy nhập vào mã để tìm kiếm dữ liệu", 
            TrayIcon.MessageType.WARNING
        );
    }
    public default void notificateUnavailable() {
        MetroUI.notificate(
            "Dữ liệu không tồn tại", 
            "Không tìm thấy dữ liệu có mã yêu cầu", 
            TrayIcon.MessageType.WARNING
        );
    }
    public default int requireConfirm() {
        return JOptionPane.showConfirmDialog(
                null, 
                "Bạn chắc chắn muốn xóa những dữ liệu đã chọn?", 
                "Xác nhận xóa", 
                JOptionPane.QUESTION_MESSAGE, 
                JOptionPane.YES_NO_OPTION
        );
    }
    
}
