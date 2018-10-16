/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.uet;

import com.uet.model.ItemEntity;
import com.uet.model.PersonEntity;
import com.uet.model.StoreEntity;
import com.uet.service.ItemService;
import com.uet.service.PersonService;
import com.uet.service.StoreService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hungpt
 */
public class ManageStorage extends JFrame {

    /**
     * Creates new form ManageStorage
     */
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    public ManageStorage() {
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        initData();
        initComponents();
        this.changeView(this.itemMenu);
    }

    private void initData() {
        try {

            providerService = new PersonService("provider.txt");
            providerObj = providerService.generateProviderObject();
            providers = providerService.convertData();

            customerService = new PersonService("customer.txt");
            customers = customerService.convertData();
            customerObj = customerService.generateProviderObject();

            storeService = new StoreService("import.txt");
            importStorages = storeService.convertData();
            importStorageObj = storeService.generateStoreObject();

            exportService = new StoreService("export.txt");
            exportStorages = exportService.convertData();
            exportStorageObj = exportService.generateStoreObject();

            staticsObj = new Object[1][5];

            itemService = new ItemService("item.txt");
            items = itemService.convertData();
            itemObj = itemService.generateItemObject();
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    private void changeView(JPanel panel){
        this.itemMenu.setVisible(false);
        this.storageMenu.setVisible(false);
        this.exportMenu.setVisible(false);
        this.customerMenu.setVisible(false);
        this.providerMenu.setVisible(false);
        this.statisticMenu.setVisible(false);

        if(panel != null) {
            panel.setVisible(true);
            validate();
        }
    }

    private Object[][] customerObj;
    private void renderCustomerTable(){
        customerTable.setModel(new javax.swing.table.DefaultTableModel(
                customerObj,
                new String [] {
                        "STT", "Tên", "Địa chỉ", "Số điện thoại", "Ngày tạo", "Tổng tiền", "Ghi chú"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.setDefaultRenderer(Object.class, centerRenderer);
        ((DefaultTableCellRenderer)customerTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
    }

    private void renderItemTable(){
        itemTable.setModel(new DefaultTableModel(
                itemObj,
                new String [] {
                        "STT", "Tên", "Mã hàng", "Đơn vị", "Nhà cung cấp", "Số lượng", "Giá nhập", "Giá xuất", "Ghi chú"
                }
        ) {
            Class[] types = new Class [] {
                    Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        itemTableContainer.setViewportView(itemTable);
        if (itemTable.getColumnModel().getColumnCount() > 0) {
            itemTable.getColumnModel().getColumn(0).setPreferredWidth(2);
            itemTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            itemTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            itemTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            itemTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            itemTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            itemTable.getColumnModel().getColumn(6).setPreferredWidth(50);
            itemTable.getColumnModel().getColumn(7).setPreferredWidth(50);
            itemTable.getColumnModel().getColumn(8).setPreferredWidth(80);
        }
    }

    private void renderProviderTable(){
        providerTable.setModel(new DefaultTableModel(
                providerObj,
                new String [] {
                        "STT", "Tên", "Địa chỉ", "Số điện thoại", "Ngày tạo", "Tổng tiền", "Ghi chú"
                }
        ) {
            Class[] types = new Class [] {
                    Integer.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        providerTable.setDefaultRenderer(Object.class, centerRenderer);
        ((DefaultTableCellRenderer)providerTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
    }

    private void renderImportTable(){
        importTable.setModel(new DefaultTableModel(
                importStorageObj,
                new String [] {
                        "STT", "Tên", "Mã hàng", "Nhà cung cấp", "Ngày nhập", "Đơn vị", "Số lượng", "Đơn giá", "Tổng tiền"
                }
        ){
            Class[] types = new Class [] {
                    Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class,Integer.class,Integer.class
            };

            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        importTable.setDefaultRenderer(Object.class, centerRenderer);
        ((DefaultTableCellRenderer)importTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        importTable.setRowSelectionAllowed(true);
        providerList.removeAllItems();
        for(int i = 0; i < providers.size(); i++) {
            providerList.addItem(providers.get(i).name);
        }
        providerList.setSelectedIndex(0);
    }

    private void renderExportTable(){
        exportTable.setModel(new DefaultTableModel(
                exportStorageObj,
                new String [] {
                        "STT", "Tên", "Mã hàng", "Khách hàng", "Ngày xuất", "Đơn vị", "Ghi chú", "Giá xuất", "Tổng tiền"
                }
        ) {
            Class[] types = new Class [] {
                    Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        exportTable.setDefaultRenderer(Object.class, centerRenderer);
        ((DefaultTableCellRenderer)exportTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);


        customerExportCom.removeAllItems();
        for(int i = 0; i < customers.size(); i++){
            customerExportCom.addItem(customers.get(i).name);
        }
    }

    private void renderStaticsTable(){
        statisticTable.setModel(new DefaultTableModel(
                staticsObj,
                new String [] {
                        "STT", "Tên", "Mã hàng", "Số lượng", "Tổng tiền"
                }
        ) {
            Class[] types = new Class [] {
                    Integer.class, String.class, String.class, Integer.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        statisticTable.setDefaultRenderer(Object.class, centerRenderer);
        ((DefaultTableCellRenderer)statisticTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemBtn = new JButton();
        importBtn = new JButton();
        exportBtn = new JButton();
        customerBtn = new JButton();
        providerBtn = new JButton();
        statisticBtn = new JButton();
        itemMenu = new JPanel();
        nameInput = new JTextField();
        itemLabel = new JLabel();
        itemCodeInput = new JTextField();
        codeLabel = new JLabel();
        typeItem = new JComboBox<>();
        typeLabel = new JLabel();
        providerComp = new JComboBox<>();
        providerLabel = new JLabel();
        moneyImportInput = new JTextField();
        moneyImportLabel = new JLabel();
        moneyExportInput = new JTextField();
        moneyExportLabel = new JLabel();
        noteInput = new JTextField();
        noteLabel = new JLabel();
        addItemBtn = new JButton();
        editItemBtn = new JButton();
        deleteItemBtn = new JButton();
        itemTableContainer = new JScrollPane();
        itemTable = new JTable();
        storageMenu = new JPanel();
        itemListStorage = new JComboBox<>();
        itemListLabel = new JLabel();
        codeListStorage = new JComboBox<>();
        quantityInputStorage = new JTextField();
        codeListLabel = new JLabel();
        quantityLabel = new JLabel();
        providerList = new JComboBox<>();
        providerStorageLabel = new JLabel();
        storeBtn = new JButton();
        editStorageBtn = new JButton();
        deleteItemStorageBtn = new JButton();
        importContainer = new JScrollPane();
        importTable = new JTable();
        exportMenu = new JPanel();
        itemLabel1 = new JLabel();
        codeLabel1 = new JLabel();
        typeLabel1 = new JLabel();
        providerLabel1 = new JLabel();
        addExportBtn = new JButton();
        editExportBtn = new JButton();
        deleteExportBtn = new JButton();
        exportTableContainer = new JScrollPane();
        exportTable = new JTable();
        nameItemExportCom = new JComboBox<>();
        codeExportCom = new JComboBox<>();
        customerExportCom = new JComboBox<>();
        quantityExportInput = new JTextField();
        customerMenu = new JPanel();
        nameCustomerInput = new JTextField();
        itemLabel2 = new JLabel();
        addressCustomerInput = new JTextField();
        codeLabel2 = new JLabel();
        typeLabel2 = new JLabel();
        providerLabel2 = new JLabel();
        phoneCustomertInput = new JTextField();
        noteCustomerInput = new JTextField();
        addCustomerBtn = new JButton();
        editCustomerBtn = new JButton();
        deleteCustomerBtn = new JButton();
        customerTableContainer = new JScrollPane();
        customerTable = new JTable();
        providerMenu = new JPanel();
        nameProviderInput = new JTextField();
        itemLabel4 = new JLabel();
        addressProviderInput = new JTextField();
        codeLabel4 = new JLabel();
        typeLabel4 = new JLabel();
        providerLabel4 = new JLabel();
        phoneProviderInput = new JTextField();
        noteProviderInput = new JTextField();
        addProviderBtn = new JButton();
        editProviderBtn = new JButton();
        deleteProviderBtn = new JButton();
        providerTableContainer = new JScrollPane();
        providerTable = new JTable();
        statisticMenu = new JPanel();
        statisticExportBtn = new JButton();
        statisticImportBtn = new JButton();
        statisticTableContainer = new JScrollPane();
        statisticTable = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        itemBtn.setText("Quản lí vật tư");
        itemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBtnActionPerformed(evt);
            }
        });

        importBtn.setText("Nhập kho");
        importBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importBtnActionPerformed(evt);
            }
        });

        exportBtn.setText("Xuất kho");
        exportBtn.setToolTipText("");
        exportBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportBtnMouseClicked(evt);
            }
        });

        customerBtn.setText("Khách hàng");
        customerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerBtnMouseClicked(evt);
            }
        });

        providerBtn.setText("Nhà cung cấp");
        providerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                providerBtnMouseClicked(evt);
            }
        });

        statisticBtn.setText("Thống kê");
        statisticBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statisticBtnMouseClicked(evt);
                renderStaticsTable();
            }
        });

        addCustomerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PersonEntity customer = new PersonEntity();
                customer.name = nameCustomerInput.getText();
                customer.address = addressCustomerInput.getText();
                customer.phone = phoneCustomertInput.getText();
                customer.note = noteCustomerInput.getText();
                if(customer.name == null){
                    JOptionPane.showMessageDialog(null, "Bạn không được để trống tên khách hàng");
                } else if (customer.address == null){
                    JOptionPane.showMessageDialog(null, "Bạn không được để trống địa chỉ khách hàng");
                } else if (customer.phone == null){
                    JOptionPane.showMessageDialog(null, "Bạn không được để trống số điện thoại khách hàng");
                } else {
                    customer.createdAt = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
                    customer.total = 0;
                    customers.add(customer);
                    Object[] o = new Object[7];
                    o[0] = customers.size();
                    o[1] = customer.name;
                    o[2] = customer.address;
                    o[3] = customer.phone;
                    o[4] = customer.createdAt;
                    o[5] = customer.total;
                    o[6] = customer.note;
                    DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
                    model.addRow(o);
                    customerService.save(customers);
                }
            }
        });

        editCustomerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                editCustomerBtnMouseClicked(evt);
                int row = customerTable.getSelectedRow();
                if(row >= 0){
                    PersonEntity customer = new PersonEntity();
                    customer.name = nameCustomerInput.getText();
                    customer.address = addressCustomerInput.getText();
                    customer.phone = phoneCustomertInput.getText();
                    customer.note = noteCustomerInput.getText();
                    if(customer.name == null){
                        JOptionPane.showMessageDialog(null, "Bạn không được để trống tên khách hàng");
                    } else if (customer.address == null){
                        JOptionPane.showMessageDialog(null, "Bạn không được để trống địa chỉ khách hàng");
                    } else if (customer.phone == null){
                        JOptionPane.showMessageDialog(null, "Bạn không được để trống số điện thoại khách hàng");
                    } else {
                        customer.createdAt = customerTable.getValueAt(row,4).toString();
                        customer.total = Integer.parseInt(customerTable.getValueAt(row,5).toString());
                        customers.set(row, customer);
                        customerTable.setValueAt(customer.name, row, 1);
                        customerTable.setValueAt(customer.address, row, 2);
                        customerTable.setValueAt(customer.phone, row, 3);
                        customerTable.setValueAt(customer.createdAt, row, 4);
                        customerTable.setValueAt(customer.total, row, 5);
                        customerTable.setValueAt(customer.note, row, 6);
                    }
                }
                customerService.save(customers);
            }
        });

        deleteCustomerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            int row = customerTable.getSelectedRow();
            if(row >= 0){
                customers.remove(row);
                try {
                    customerService.save(customers);
                    customerObj = customerService.generateProviderObject();
                    renderCustomerTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }
        });

        itemTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                int row = itemTable.getSelectedRow();
                try{
                    nameInput.setText(itemTable.getValueAt(row, 1).toString());
                    itemCodeInput.setText(itemTable.getValueAt(row, 2).toString());
                    moneyImportInput.setText(itemTable.getValueAt(row, 6).toString());
                    moneyExportInput.setText(itemTable.getValueAt(row, 7).toString());
                    noteInput.setText(itemTable.getValueAt(row, 8).toString());
                    typeItem.setSelectedItem(itemTable.getValueAt(row, 3).toString());
                    providerComp.setSelectedItem(itemTable.getValueAt(row, 4).toString());
                } catch (Exception e){

                }
            }
        });

        addItemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItemEntity item = new ItemEntity();
                item.name = nameInput.getText();
                item.code = itemCodeInput.getText();
                item.type = typeItem.getSelectedItem().toString();
                item.provider = providerComp.getSelectedItem().toString();
                item.quantity = 0;
                item.note = noteInput.getText();
                if(item.name == null || item.name.length() == 0){
                    JOptionPane.showMessageDialog(null, "Bạn không được để trống tên vật tư");
                } else if (item.code == null || item.code.length() == 0){
                    JOptionPane.showMessageDialog(null, "Bạn không được để trống mã vật tư");
                } else if (item.type == null){
                    JOptionPane.showMessageDialog(null, "Bạn không được để trống loại vật tư");
                } else if (item.provider == null){
                    JOptionPane.showMessageDialog(null, "Bạn không được để trống nhà cung cấp");
                } else {
                    try {
                        item.priceImport = Integer.parseInt(moneyImportInput.getText());
                        item.priceExport = Integer.parseInt(moneyExportInput.getText());
                        if (item.priceImport <= 0){
                            JOptionPane.showMessageDialog(null, "Giá nhập phải lớn hơn 0");
                        } else if (item.priceExport <= 0){
                            JOptionPane.showMessageDialog(null, "Giá xuất phải lớn hơn 0");
                        } else {
                            items.add(item);
                            Object[] o = new Object[9];
                            o[0] = items.size();
                            o[1] = item.name;
                            o[2] = item.code;
                            o[3] = item.type;
                            o[4] = item.provider;
                            o[5] = item.quantity;
                            o[6] = item.priceImport;
                            o[7] = item.priceExport;
                            o[8] = item.note;
                            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
                            model.addRow(o);
//                            Lỗi trong việc ghi chú == null (tất cả các phần khác cung vậy)
                            itemService.save(items);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Giá nhập hoặc giá xuất không đúng định dạng");
                    }
                }
            }
        });

        editItemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = itemTable.getSelectedRow();
                if(row >= 0){
                    ItemEntity item = new ItemEntity();
                    item.name = nameInput.getText();
                    item.code = itemCodeInput.getText();
                    item.type = typeItem.getSelectedItem().toString();
                    item.provider = providerComp.getSelectedItem().toString();
                    item.quantity = 0;
                    item.note = noteInput.getText();
                    if(item.name == null || item.name.length() == 0){
                        JOptionPane.showMessageDialog(null, "Bạn không được để trống tên vật tư");
                    } else if (item.code == null || item.code.length() == 0){
                        JOptionPane.showMessageDialog(null, "Bạn không được để trống mã vật tư");
                    } else if (item.type == null){
                        JOptionPane.showMessageDialog(null, "Bạn không được để trống loại vật tư");
                    } else if (item.provider == null){
                        JOptionPane.showMessageDialog(null, "Bạn không được để trống nhà cung cấp");
                    } else {
                        try {
                            item.priceImport = Integer.parseInt(moneyImportInput.getText());
                            item.priceExport = Integer.parseInt(moneyExportInput.getText());
                            if (item.priceImport <= 0){
                                JOptionPane.showMessageDialog(null, "Giá nhập phải lớn hơn 0");
                            } else if (item.priceExport <= 0){
                                JOptionPane.showMessageDialog(null, "Giá xuất phải lớn hơn 0");
                            } else {
                                items.set(row, item);
                                customerTable.setValueAt(item.name, row, 1);
                                customerTable.setValueAt(item.code, row, 2);
                                customerTable.setValueAt(item.type, row, 3);
                                customerTable.setValueAt(item.provider, row, 4);
                                customerTable.setValueAt(item.quantity, row, 5);
                                customerTable.setValueAt(item.priceImport, row, 6);
                                customerTable.setValueAt(item.priceExport, row, 7);
                                customerTable.setValueAt(item.note, row, 8);
                                itemService.save(items);
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Giá nhập hoặc giá xuất không đúng định dạng");
                        }
                    }
                }
                customerService.save(customers);
            }
        });

        deleteItemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = itemTable.getSelectedRow();
                if(row >= 0){
                    items.remove(row);
                    try {
                        itemService.save(items);
                        itemObj = itemService.generateItemObject();
                        renderItemTable();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        itemLabel.setText("Tên");

        codeLabel.setText("Mã hàng");

        typeItem.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        typeLabel.setText("Đơn vị");

        providerComp.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        providerLabel.setText("Nhà cung cấp");

        moneyImportLabel.setText("Giá nhập");

        moneyExportLabel.setText("Giá xuất");

        noteLabel.setText("Ghi chú");

        addItemBtn.setText("Thêm");

        editItemBtn.setText("Sửa");

        deleteItemBtn.setText("Xoá");

        renderItemTable();

        GroupLayout itemMenuLayout = new GroupLayout(itemMenu);
        itemMenu.setLayout(itemMenuLayout);
        itemMenuLayout.setHorizontalGroup(
            itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(itemMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, itemMenuLayout.createSequentialGroup()
                        .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(nameInput, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addGroup(itemMenuLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(itemLabel)))
                        .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(itemMenuLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(codeLabel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                            .addGroup(itemMenuLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(itemCodeInput)))
                        .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(itemMenuLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(typeItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(providerComp, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                            .addGroup(itemMenuLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(typeLabel)
                                .addGap(73, 73, 73)
                                .addComponent(providerLabel)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(itemMenuLayout.createSequentialGroup()
                                .addComponent(moneyImportInput, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(moneyExportInput, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(noteInput)
                                .addContainerGap())
                            .addGroup(itemMenuLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(moneyImportLabel)
                                .addGap(104, 104, 104)
                                .addComponent(moneyExportLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(noteLabel)
                                .addGap(80, 80, 80))))
                    .addGroup(GroupLayout.Alignment.TRAILING, itemMenuLayout.createSequentialGroup()
                        .addComponent(addItemBtn)
                        .addGap(18, 18, 18)
                        .addComponent(editItemBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteItemBtn)
                        .addGap(402, 402, 402))
                    .addGroup(GroupLayout.Alignment.TRAILING, itemMenuLayout.createSequentialGroup()
                        .addComponent(itemTableContainer)
                        .addContainerGap())))
        );
        itemMenuLayout.setVerticalGroup(
            itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(itemMenuLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(providerLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(itemLabel)
                        .addComponent(codeLabel)
                        .addComponent(typeLabel)
                        .addComponent(moneyImportLabel)
                        .addComponent(moneyExportLabel)
                        .addComponent(noteLabel)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameInput)
                    .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(itemCodeInput, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(typeItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(providerComp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(moneyImportInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(moneyExportInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(noteInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(itemMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
                    .addComponent(editItemBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addItemBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteItemBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(itemTableContainer, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        itemListStorage.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        itemListLabel.setText("Tên sản phẩm");

        codeListStorage.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        quantityInputStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityInputStorageActionPerformed(evt);
            }
        });

        codeListLabel.setText("Mã hàng");

        quantityLabel.setText("Số lượng");

        providerStorageLabel.setText("Nhà cung cấp");

        storeBtn.setText("Thêm");
        storeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStorageBtnActionPerformed(-1, evt);
            }

        });

        editStorageBtn.setText("Sửa");
        editStorageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStorageBtnActionPerformed(selectedItem, evt);
            }
        });

        deleteItemStorageBtn.setText("Xoá");
        deleteItemStorageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importStorages.remove(selectedItem - 1);
                storeService.save(importStorages);
                importStorageObj = storeService.generateStoreObject();
                renderImportTable();
            }
        });

        renderImportTable();
        importTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                try{
                    selectedItem = Integer.parseInt(importTable.getValueAt(importTable.getSelectedRow(), 0).toString());
                    itemListStorage.setSelectedItem(importTable.getValueAt(importTable.getSelectedRow(), 1).toString());
                    codeListStorage.setSelectedItem(importTable.getValueAt(importTable.getSelectedRow(), 2).toString());
                    providerList.setSelectedItem(importTable.getValueAt(importTable.getSelectedRow(), 3).toString());
                    quantityInputStorage.setText(importTable.getValueAt(importTable.getSelectedRow(), 6).toString());
                } catch (Exception e){
                    selectedItem = -1;
                }
            }
        });

        importContainer.setViewportView(importTable);

        GroupLayout storageMenuLayout = new GroupLayout(storageMenu);
        storageMenu.setLayout(storageMenuLayout);
        storageMenuLayout.setHorizontalGroup(
            storageMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(storageMenuLayout.createSequentialGroup()
                .addGroup(storageMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(storageMenuLayout.createSequentialGroup()
                        .addGroup(storageMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(GroupLayout.Alignment.TRAILING, storageMenuLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(itemListLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(codeListLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)
                                .addComponent(quantityLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(GroupLayout.Alignment.TRAILING, storageMenuLayout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(itemListStorage, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(codeListStorage, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(quantityInputStorage, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGroup(storageMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(storageMenuLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(providerStorageLabel))
                            .addGroup(storageMenuLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(providerList, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                    .addGroup(storageMenuLayout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(storeBtn)
                        .addGap(18, 18, 18)
                        .addComponent(editStorageBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteItemStorageBtn)))
                .addContainerGap(130, Short.MAX_VALUE))
            .addComponent(importContainer)
        );
        storageMenuLayout.setVerticalGroup(
            storageMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, storageMenuLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(storageMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, storageMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(itemListLabel)
                        .addComponent(codeListLabel))
                    .addGroup(GroupLayout.Alignment.TRAILING, storageMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(quantityLabel)
                        .addComponent(providerStorageLabel)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(storageMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(itemListStorage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeListStorage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityInputStorage, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addComponent(providerList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(storageMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(storeBtn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addComponent(editStorageBtn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteItemStorageBtn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(importContainer, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        itemLabel1.setText("Tên");

        codeLabel1.setText("Mã hàng");

        typeLabel1.setText("Số lượng");

        providerLabel1.setText("Khách hàng");

        addExportBtn.setText("Thêm");
        addExportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExportBtnActionPerformed(-1, evt);
            }

        });

        editExportBtn.setText("Sửa");
        editExportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExportBtnActionPerformed(selectedItem, evt);
            }

        });

        deleteExportBtn.setText("Xoá");
        deleteExportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportStorages.remove(selectedItem - 1);
                exportService.save(exportStorages);
                exportStorageObj = exportService.generateStoreObject();
                renderExportTable();
            }
        });


        renderExportTable();
        exportTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                try{
                    selectedItem = Integer.parseInt(exportTable.getValueAt(exportTable.getSelectedRow(), 0).toString());
                    nameItemExportCom.setSelectedItem(exportTable.getValueAt(exportTable.getSelectedRow(), 1).toString());
                    codeExportCom.setSelectedItem(exportTable.getValueAt(exportTable.getSelectedRow(), 2).toString());
                    customerExportCom.setSelectedItem(exportTable.getValueAt(exportTable.getSelectedRow(), 3).toString());
                    quantityExportInput.setText(exportTable.getValueAt(exportTable.getSelectedRow(), 6).toString());
                } catch (Exception e){
                    selectedItem = -1;
                }
            }
        });
        exportTableContainer.setViewportView(exportTable);
        if (exportTable.getColumnModel().getColumnCount() > 0) {
            exportTable.getColumnModel().getColumn(0).setPreferredWidth(2);
            exportTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            exportTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            exportTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            exportTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            exportTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            exportTable.getColumnModel().getColumn(6).setPreferredWidth(50);
            exportTable.getColumnModel().getColumn(7).setPreferredWidth(50);
            exportTable.getColumnModel().getColumn(8).setPreferredWidth(80);
        }

        nameItemExportCom.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        codeExportCom.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        customerExportCom.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        GroupLayout exportMenuLayout = new GroupLayout(exportMenu);
        exportMenu.setLayout(exportMenuLayout);
        exportMenuLayout.setHorizontalGroup(
            exportMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(exportMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, exportMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addExportBtn)
                        .addGap(18, 18, 18)
                        .addComponent(editExportBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteExportBtn)
                        .addGap(402, 402, 402))
                    .addGroup(GroupLayout.Alignment.TRAILING, exportMenuLayout.createSequentialGroup()
                        .addComponent(exportTableContainer)
                        .addContainerGap())))
            .addGroup(exportMenuLayout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(nameItemExportCom, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemLabel1))
                .addGap(48, 48, 48)
                .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(codeLabel1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeExportCom, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(typeLabel1)
                    .addComponent(quantityExportInput, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(providerLabel1)
                    .addComponent(customerExportCom, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                .addGap(156, 156, 156))
        );
        exportMenuLayout.setVerticalGroup(
            exportMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(exportMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(exportMenuLayout.createSequentialGroup()
                        .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(codeLabel1)
                            .addComponent(itemLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(codeExportCom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameItemExportCom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(exportMenuLayout.createSequentialGroup()
                            .addComponent(providerLabel1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(customerExportCom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(exportMenuLayout.createSequentialGroup()
                            .addComponent(typeLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(quantityExportInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(exportMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
                    .addComponent(editExportBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addExportBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteExportBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(exportTableContainer, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        itemLabel2.setText("Tên");

        codeLabel2.setText("Địa chỉ");

        typeLabel2.setText("Số điện thoại");

        providerLabel2.setText("Ghi chú");

        addCustomerBtn.setText("Thêm");

        editCustomerBtn.setText("Sửa");

        deleteCustomerBtn.setText("Xoá");

        renderCustomerTable();

        customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
               try{
                   nameCustomerInput.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 1).toString());
                   addressCustomerInput.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 2).toString());
                   phoneCustomertInput.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 3).toString());
                   noteCustomerInput.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 6).toString());
               } catch (Exception e){
                   nameCustomerInput.setText("");
                   addressCustomerInput.setText("");
                   phoneCustomertInput.setText("");
                   noteCustomerInput.setText("");
               }
            }
        });

        customerTableContainer.setViewportView(customerTable);
        if (customerTable.getColumnModel().getColumnCount() > 0) {
            customerTable.getColumnModel().getColumn(0).setPreferredWidth(2);
            customerTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            customerTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            customerTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            customerTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            customerTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            customerTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        GroupLayout customerMenuLayout = new GroupLayout(customerMenu);
        customerMenu.setLayout(customerMenuLayout);
        customerMenuLayout.setHorizontalGroup(
            customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(customerMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, customerMenuLayout.createSequentialGroup()
                        .addComponent(customerTableContainer)
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, customerMenuLayout.createSequentialGroup()
                        .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(customerMenuLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(addCustomerBtn)
                                .addGap(18, 18, 18)
                                .addComponent(editCustomerBtn))
                            .addGroup(customerMenuLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(nameCustomerInput, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itemLabel2))
                                .addGap(45, 45, 45)
                                .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(codeLabel2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addressCustomerInput, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(customerMenuLayout.createSequentialGroup()
                                .addComponent(deleteCustomerBtn)
                                .addGap(402, 402, 402))
                            .addGroup(GroupLayout.Alignment.TRAILING, customerMenuLayout.createSequentialGroup()
                                .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(typeLabel2)
                                    .addComponent(phoneCustomertInput, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(noteCustomerInput, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(providerLabel2))
                                .addGap(78, 78, 78))))))
        );
        customerMenuLayout.setVerticalGroup(
            customerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(customerMenuLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel2)
                    .addComponent(typeLabel2)
                    .addComponent(itemLabel2)
                    .addComponent(providerLabel2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addressCustomerInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneCustomertInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(noteCustomerInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameCustomerInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(customerMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
                    .addComponent(editCustomerBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustomerBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteCustomerBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(customerTableContainer, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        itemLabel4.setText("Tên");

        codeLabel4.setText("Địa chỉ");

        typeLabel4.setText("Số điện thoại");

        providerLabel4.setText("Ghi chú");

        addProviderBtn.setText("Thêm");
        addProviderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProviderBtnActionPerformed(-1, evt);
            }
        });

        editProviderBtn.setText("Sửa");
        editProviderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProviderBtnActionPerformed(selectedProvider, evt);
            }
        });

        deleteProviderBtn.setText("Xoá");
        deleteProviderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                providers.remove(selectedProvider - 1);
                providerService.save(providers);
                providerObj = providerService.generateProviderObject();
                renderProviderTable();
            }
        });

        renderProviderTable();

        providerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                try {
                    selectedProvider = Integer.parseInt(providerTable.getValueAt(providerTable.getSelectedRow(), 0).toString());
                    nameProviderInput.setText(providerTable.getValueAt(providerTable.getSelectedRow(), 1).toString());
                    addressProviderInput.setText(providerTable.getValueAt(providerTable.getSelectedRow(), 2).toString());
                    phoneProviderInput.setText(providerTable.getValueAt(providerTable.getSelectedRow(), 3).toString());
                    noteProviderInput.setText(providerTable.getValueAt(providerTable.getSelectedRow(), 6).toString());
                } catch (Exception e){
                    selectedProvider = -1;
                    nameProviderInput.setText("");
                    addressProviderInput.setText("");
                    phoneProviderInput.setText("");
                    noteProviderInput.setText("");
                }

            }
        });


        providerTableContainer.setViewportView(providerTable);
        if (providerTable.getColumnModel().getColumnCount() > 0) {
            providerTable.getColumnModel().getColumn(0).setPreferredWidth(2);
            providerTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            providerTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            providerTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            providerTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            providerTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            providerTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        GroupLayout providerMenuLayout = new GroupLayout(providerMenu);
        providerMenu.setLayout(providerMenuLayout);
        providerMenuLayout.setHorizontalGroup(
            providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(providerMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, providerMenuLayout.createSequentialGroup()
                        .addComponent(providerTableContainer)
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, providerMenuLayout.createSequentialGroup()
                        .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(providerMenuLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(addProviderBtn)
                                .addGap(18, 18, 18)
                                .addComponent(editProviderBtn))
                            .addGroup(providerMenuLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(nameProviderInput, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itemLabel4))
                                .addGap(45, 45, 45)
                                .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(codeLabel4, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addressProviderInput, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(providerMenuLayout.createSequentialGroup()
                                .addComponent(deleteProviderBtn)
                                .addGap(402, 402, 402))
                            .addGroup(GroupLayout.Alignment.TRAILING, providerMenuLayout.createSequentialGroup()
                                .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(typeLabel4)
                                    .addComponent(phoneProviderInput, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(noteProviderInput, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(providerLabel4))
                                .addGap(78, 78, 78))))))
        );
        providerMenuLayout.setVerticalGroup(
            providerMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(providerMenuLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel4)
                    .addComponent(typeLabel4)
                    .addComponent(itemLabel4)
                    .addComponent(providerLabel4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addressProviderInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneProviderInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(noteProviderInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameProviderInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(providerMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
                    .addComponent(editProviderBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProviderBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteProviderBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(providerTableContainer, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        statisticExportBtn.setText("Thống kê xuất hàng");
        statisticExportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                List<String> codeList = exportStorages.stream().map(e -> e.code).distinct().collect(Collectors.toList());
                List<StoreEntity> storeEntities = new ArrayList<>();
                for(String code: codeList){
                    StoreEntity storeEntity = new StoreEntity();
                    storeEntity.code = code;
                    List<StoreEntity> tmp = exportStorages.stream().filter(e -> e.code.equals(code)).collect(Collectors.toList());
                    storeEntity.name = tmp.get(0).name;
                    storeEntity.quantity = tmp.stream().mapToInt(e -> e.quantity).sum();
                    storeEntity.total = tmp.stream().mapToInt(e -> e.total).sum();
                    storeEntities.add(storeEntity);
                }
                staticsObj = new Object[storeEntities.size()][5];
                for(int i = 0; i < storeEntities.size(); i++){
                    staticsObj[i][0] = i+1;
                    staticsObj[i][1] = storeEntities.get(i).name;
                    staticsObj[i][2] = storeEntities.get(i).code;
                    staticsObj[i][3] = storeEntities.get(i).quantity;
                    staticsObj[i][4] = storeEntities.get(i).total;
                }
                renderStaticsTable();
            }

        });


        statisticImportBtn.setText("Thống kê nhập hàng");
        statisticImportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                List<String> codeList = importStorages.stream().map(e -> e.code).distinct().collect(Collectors.toList());
                List<StoreEntity> storeEntities = new ArrayList<>();
                for(String code: codeList){
                    StoreEntity storeEntity = new StoreEntity();
                    storeEntity.code = code;
                    List<StoreEntity> tmp = importStorages.stream().filter(e -> e.code.equals(code)).collect(Collectors.toList());
                    storeEntity.name = tmp.get(0).name;
                    storeEntity.quantity = tmp.stream().mapToInt(e -> e.quantity).sum();
                    storeEntity.total = tmp.stream().mapToInt(e -> e.total).sum();
                    storeEntities.add(storeEntity);
                }
                staticsObj = new Object[storeEntities.size()][5];
                for(int i = 0; i < storeEntities.size(); i++){
                    staticsObj[i][0] = i+1;
                    staticsObj[i][1] = storeEntities.get(i).name;
                    staticsObj[i][2] = storeEntities.get(i).code;
                    staticsObj[i][3] = storeEntities.get(i).quantity;
                    staticsObj[i][4] = storeEntities.get(i).total;
                }
                renderStaticsTable();
            }

        });


        renderStaticsTable();
        statisticTableContainer.setViewportView(statisticTable);
        if (statisticTable.getColumnModel().getColumnCount() > 0) {
            statisticTable.getColumnModel().getColumn(0).setPreferredWidth(2);
            statisticTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            statisticTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            statisticTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            statisticTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        GroupLayout statisticMenuLayout = new GroupLayout(statisticMenu);
        statisticMenu.setLayout(statisticMenuLayout);
        statisticMenuLayout.setHorizontalGroup(
            statisticMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statisticMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statisticMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, statisticMenuLayout.createSequentialGroup()
                        .addComponent(statisticTableContainer, GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, statisticMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(statisticExportBtn)
                        .addGap(135, 135, 135)
                        .addComponent(statisticImportBtn)
                        .addGap(337, 337, 337))))
        );
        statisticMenuLayout.setVerticalGroup(
            statisticMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statisticMenuLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(statisticMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(statisticImportBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(statisticExportBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(statisticTableContainer, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(itemMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(itemBtn, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importBtn, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportBtn, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerBtn, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(providerBtn, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statisticBtn, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(storageMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(exportMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(customerMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(providerMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(statisticMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(itemBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(importBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(exportBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(customerBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(providerBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(statisticBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(itemMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(69, Short.MAX_VALUE)
                    .addComponent(storageMenu, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addComponent(exportMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(21, 21, 21)))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addComponent(customerMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(21, 21, 21)))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addComponent(providerMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addComponent(statisticMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }// </editor-fold>//GEN-END:initComponents

    private void addProviderBtnActionPerformed(int selectedProvider, java.awt.event.ActionEvent evt) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.name = nameProviderInput.getText();
        personEntity.address = addressProviderInput.getText();
        personEntity.phone = phoneProviderInput.getText();
        personEntity.note = noteProviderInput.getText();
        personEntity.createdAt = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        personEntity.total = 0;

        if(personEntity.name == null){
            JOptionPane.showMessageDialog(null,"Bạn không được để trống tên nhà cung cấp");
        } else if (personEntity.address == null){
            JOptionPane.showMessageDialog(null, "Bạn không được để trống địa chỉ nhà cung cấp");
        } else if (personEntity.phone == null){
            JOptionPane.showMessageDialog(null, "Bạn không được để trống số điện thoại nhà cung cấp");
        } else {
            Object[] o = new Object[7];
            o[0] = this.providers.size();
            o[1] = personEntity.name;
            o[2] = personEntity.address;
            o[3] = personEntity.phone;
            o[4] = personEntity.createdAt;
            o[5] = personEntity.total;
            o[6] = personEntity.note;

            if (selectedProvider == -1){
                this.providers.add(personEntity);
                ((DefaultTableModel) providerTable.getModel()).addRow(o);
            } else {
                this.providers.set(selectedProvider-1, personEntity);
                providerTable.setValueAt(selectedProvider , selectedProvider - 1, 0);
                providerTable.setValueAt(personEntity.name, selectedProvider - 1, 1);
                providerTable.setValueAt(personEntity.address, selectedProvider - 1, 2);
                providerTable.setValueAt(personEntity.phone, selectedProvider - 1, 3);
                providerTable.setValueAt(personEntity.createdAt, selectedProvider - 1, 4);
                providerTable.setValueAt(personEntity.total, selectedProvider - 1, 5);
                providerTable.setValueAt(personEntity.note, selectedProvider - 1, 6);
            }

            providerService.save(this.providers);
        }
    }

    private void importBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importBtnActionPerformed
        changeView(this.storageMenu);
        renderImportTable();
    }//GEN-LAST:event_importBtnActionPerformed

    private void quantityInputStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityInputStorageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityInputStorageActionPerformed

    private int selectedItem = -1;
    private void addStorageBtnActionPerformed(int selectedImport, java.awt.event.ActionEvent evt) {                                               
        if (quantityInputStorage.getText() == null || quantityInputStorage.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn không được để trống số lượng hàng hoá");
        } else {
            StoreEntity storeEntity = new StoreEntity();
            storeEntity.name = itemListStorage.getSelectedItem().toString();
            storeEntity.code = codeListStorage.getSelectedItem().toString();
            storeEntity.quantity = Integer.parseInt(quantityInputStorage.getText());
            storeEntity.person = providerList.getSelectedItem().toString();
            storeEntity.createdAt = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
            storeEntity.type = "Cái"; // toDo:
            storeEntity.price = 1000;
            storeEntity.total = storeEntity.quantity * storeEntity.price;
            storeEntity.note = "";

            Object[] o = new Object[9];
            o[0] = importStorages.size() + 1;
            o[1] = storeEntity.name;
            o[2] = storeEntity.code;
            o[3] = storeEntity.person;
            o[4] = storeEntity.createdAt;
            o[5] = storeEntity.type;
            o[6] = storeEntity.quantity;
            o[7] = storeEntity.price;
            o[8] = storeEntity.total;

            if (selectedItem == -1){
                importStorages.add(storeEntity);
                ((DefaultTableModel)importTable.getModel()).addRow(o);
            } else {
                importStorages.set(selectedItem-1, storeEntity);

                importTable.setValueAt(selectedItem, selectedItem - 1, 0);
                importTable.setValueAt(storeEntity.name, selectedItem - 1, 1);
                importTable.setValueAt(storeEntity.code, selectedItem - 1, 2);
                importTable.setValueAt(storeEntity.person, selectedItem - 1, 3);
                importTable.setValueAt(storeEntity.createdAt, selectedItem - 1, 4);
                importTable.setValueAt(storeEntity.type, selectedItem - 1, 5);
                importTable.setValueAt(storeEntity.quantity, selectedItem - 1, 6);
                importTable.setValueAt(storeEntity.price, selectedItem - 1, 7);
                importTable.setValueAt(storeEntity.total, selectedItem - 1, 8);
            }

            storeService.save(importStorages);

        }
    }                                              

    private void addExportBtnActionPerformed(int selectedImport, java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editStorageBtnActionPerformed
        if (quantityExportInput.getText() == null || quantityExportInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn không được để trống số lượng hàng hoá");
        } else {
            StoreEntity storeEntity = new StoreEntity();
            storeEntity.name = nameItemExportCom.getSelectedItem().toString();
            storeEntity.code = codeExportCom.getSelectedItem().toString();
            storeEntity.quantity = Integer.parseInt(quantityExportInput.getText());
            storeEntity.person = customerExportCom.getSelectedItem().toString();
            storeEntity.createdAt = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
            storeEntity.type = "Cái"; // toDo:
            storeEntity.price = 1000;
            storeEntity.total = storeEntity.quantity * storeEntity.price;
            storeEntity.note = "";

            Object[] o = new Object[9];
            o[0] = exportStorages.size() + 1;
            o[1] = storeEntity.name;
            o[2] = storeEntity.code;
            o[3] = storeEntity.person;
            o[4] = storeEntity.createdAt;
            o[5] = storeEntity.type;
            o[6] = storeEntity.quantity;
            o[7] = storeEntity.price;
            o[8] = storeEntity.total;

            if (selectedItem == -1){
                exportStorages.add(storeEntity);
                ((DefaultTableModel)exportTable.getModel()).addRow(o);
            } else {
                exportStorages.set(selectedItem-1, storeEntity);

                exportTable.setValueAt(selectedItem, selectedItem - 1, 0);
                exportTable.setValueAt(storeEntity.name, selectedItem - 1, 1);
                exportTable.setValueAt(storeEntity.code, selectedItem - 1, 2);
                exportTable.setValueAt(storeEntity.person, selectedItem - 1, 3);
                exportTable.setValueAt(storeEntity.createdAt, selectedItem - 1, 4);
                exportTable.setValueAt(storeEntity.type, selectedItem - 1, 5);
                exportTable.setValueAt(storeEntity.quantity, selectedItem - 1, 6);
                exportTable.setValueAt(storeEntity.price, selectedItem - 1, 7);
                exportTable.setValueAt(storeEntity.total, selectedItem - 1, 8);
            }

            exportService.save(exportStorages);

        }

    }//GEN-LAST:event_editStorageBtnActionPerformed

    private void itemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBtnActionPerformed
        changeView(this.itemMenu);
    }//GEN-LAST:event_itemBtnActionPerformed

    private void exportBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportBtnMouseClicked
        changeView(this.exportMenu);
        renderExportTable();
    }//GEN-LAST:event_exportBtnMouseClicked

    private void customerBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerBtnMouseClicked
        changeView(this.customerMenu);
    }//GEN-LAST:event_customerBtnMouseClicked

    private void providerBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_providerBtnMouseClicked
        changeView(this.providerMenu);
    }//GEN-LAST:event_providerBtnMouseClicked

    private void statisticBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statisticBtnMouseClicked
        changeView(this.statisticMenu);
    }//GEN-LAST:event_statisticBtnMouseClicked

    private void addCustomerBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCustomerBtnMouseClicked
        PersonEntity customer = new PersonEntity();
        customer.name = nameCustomerInput.getText();
        customer.address = addressCustomerInput.getText();
        customer.phone = phoneCustomertInput.getText();
        customer.note = noteCustomerInput.getText();
        if(customer.name == null){
            JOptionPane.showMessageDialog(null, "Bạn không được để trống tên khách hàng");
        } else if (customer.address == null){
            JOptionPane.showMessageDialog(null, "Bạn không được để trống địa chỉ khách hàng");
        } else if (customer.phone == null){
            JOptionPane.showMessageDialog(null, "Bạn không được để trống số điện thoại khách hàng");
        } else {
            customer.createdAt = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
            customer.total = 0;
            customers.add(customer);
            Object[] o = new Object[7];
            o[0] = customers.size();
            o[1] = customer.name;
            o[2] = customer.address;
            o[3] = customer.phone;
            o[4] = customer.createdAt;
            o[5] = customer.total;
            o[6] = customer.note;
            DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
            model.addRow(o);
        }
    }//GEN-LAST:event_addCustomerBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStorage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addCustomerBtn;
    private JButton addExportBtn;
    private JButton addItemBtn;
    private JButton addProviderBtn;
    private JTextField addressCustomerInput;
    private JTextField addressProviderInput;
    private JComboBox<String> codeExportCom;
    private JLabel codeLabel;
    private JLabel codeLabel1;
    private JLabel codeLabel2;
    private JLabel codeLabel4;
    private JLabel codeListLabel;
    private JComboBox<String> codeListStorage;
    private JButton customerBtn;
    private JComboBox<String> customerExportCom;
    private JPanel customerMenu;
    private JTable customerTable;
    private JScrollPane customerTableContainer;
    private JButton deleteCustomerBtn;
    private JButton deleteExportBtn;
    private JButton deleteItemBtn;
    private JButton deleteItemStorageBtn;
    private JButton deleteProviderBtn;
    private JButton editCustomerBtn;
    private JButton editExportBtn;
    private JButton editItemBtn;
    private JButton editProviderBtn;
    private JButton editStorageBtn;
    private JButton exportBtn;
    private JPanel exportMenu;
    private JTable exportTable;
    private JScrollPane exportTableContainer;
    private JButton importBtn;
    private JScrollPane importContainer;
    private JTable importTable;
    private JButton itemBtn;
    private JTextField itemCodeInput;
    private JLabel itemLabel;
    private JLabel itemLabel1;
    private JLabel itemLabel2;
    private JLabel itemLabel4;
    private JLabel itemListLabel;
    private JComboBox<String> itemListStorage;
    private JPanel itemMenu;
    private JTable itemTable;
    private JScrollPane itemTableContainer;
    private JTextField moneyExportInput;
    private JLabel moneyExportLabel;
    private JTextField moneyImportInput;
    private JLabel moneyImportLabel;
    private JTextField nameCustomerInput;
    private JTextField nameInput;
    private JComboBox<String> nameItemExportCom;
    private JTextField nameProviderInput;
    private JTextField noteCustomerInput;
    private JTextField noteInput;
    private JLabel noteLabel;
    private JTextField noteProviderInput;
    private JTextField phoneCustomertInput;
    private JTextField phoneProviderInput;
    private JButton providerBtn;
    private JComboBox<String> providerComp;
    private JLabel providerLabel;
    private JLabel providerLabel1;
    private JLabel providerLabel2;
    private JLabel providerLabel4;
    private JComboBox<String> providerList;
    private JPanel providerMenu;
    private JLabel providerStorageLabel;
    private JTable providerTable;
    private JScrollPane providerTableContainer;
    private JTextField quantityExportInput;
    private JTextField quantityInputStorage;
    private JLabel quantityLabel;
    private JButton statisticBtn;
    private JButton statisticExportBtn;
    private JButton statisticImportBtn;
    private JPanel statisticMenu;
    private JTable statisticTable;
    private JScrollPane statisticTableContainer;
    private JPanel storageMenu;
    private JButton storeBtn;
    private JComboBox<String> typeItem;
    private JLabel typeLabel;
    private JLabel typeLabel1;
    private JLabel typeLabel2;
    private JLabel typeLabel4;
    private List<PersonEntity> providers;
    private PersonService providerService;
    private int selectedProvider;
    private Object[][] providerObj;
    private List<PersonEntity> customers;
    private PersonService customerService;
    private StoreService storeService;
    private List<StoreEntity> importStorages;
    private Object[][] importStorageObj;
    private List<StoreEntity> exportStorages;
    private Object[][] exportStorageObj;
    private StoreService exportService;
    private Object[][] staticsObj;
    private ItemService itemService;
    private List<ItemEntity> items;
    private Object[][] itemObj;
    // End of variables declaration//GEN-END:variables
}
