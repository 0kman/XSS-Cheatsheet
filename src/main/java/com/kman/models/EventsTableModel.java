package com.kman.models;

import com.kman.objects.Event;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class EventsTableModel extends AbstractTableModel {
    private final String[] COLUMN_NAMES = {"Compatibility", "Event", "Description", "Tag", "Code", "Interaction"};
    private List<Event> events;
    ReentrantLock mutex = new ReentrantLock();

    public EventsTableModel(List<Event> events) {
        this.events = events;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        try{
            mutex.lock();
            Event event = events.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    value = event.getSelectedTag().getIcon();

                    break;
                case 1:
                    value = event.getName();

                    break;
                case 2:
                    value = event.getDescription();

                    break;
                case 3:
                    value = event.getSelectedTag().getName();

                    break;
                case 4:
                    value = event.getSelectedTag().getCode();

                    break;
                case 5:
                    if (event.getSelectedTag().getInteraction()){
                        value = "Yes";
                    }
                    else{
                        value = "No";
                    }
            }
        }
        finally{
            mutex.unlock();
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 3){
            Event event = events.get(rowIndex);
            event.setSelectedTag(aValue.toString());
        }
        if (columnIndex == 5){
            Event event = events.get(rowIndex);
            if (aValue.equals("Yes")){
                event.getSelectedTag().setInteraction(true);
            }
            else{
                event.getSelectedTag().setInteraction(false);
            }
        }
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public int getRowCount() {
        return events.size();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3){
            return true;
        }
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ImageIcon.class;
        }
        return String.class;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    public Event getEvent(int row){
        return events.get(row);
    }

    public void setEvents(List<Event> events){
        this.events = events;
        fireTableDataChanged();
    }
}
