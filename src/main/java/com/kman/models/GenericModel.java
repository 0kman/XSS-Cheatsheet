package com.kman.models;

import com.kman.data.Data;
import com.kman.objects.GenericRow;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

public class GenericModel extends AbstractTableModel {
    Data data;
    private String[] COLUMN_NAMES = {"Compatibility", "Description", "Code"};
    private List<GenericRow> rows = new ArrayList<>();

    public GenericModel(Data data, ArrayList<String> COLUMN_NAMES, List<GenericRow> rows) {
        this.data = data;
        this.rows = rows;
        if (!isNull(COLUMN_NAMES)){
            this.COLUMN_NAMES = Arrays.copyOf(COLUMN_NAMES.toArray(), COLUMN_NAMES.size(), String[].class);
        }
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        GenericRow row = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = row.getIcon();
                break;
            case 1:
                value = row.getDescription();
                break;
            case 2:
                value = row.getCode();
        }
        return value;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
}
