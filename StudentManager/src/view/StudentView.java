package view;

import common.AppConstant;
import controller.StudentController;
import model.Student;
import service.impl.StudentService;
import util.FileUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

public class StudentView extends JFrame {
    private final JButton btnXoa;
    private final JButton btnHuyBo;
    private final JButton btnLuu;
    private final JButton btnUpdate;
    private final StudentService studentService = new StudentService();
    private final ButtonGroup btnSex;
    private final JRadioButton radioButton_nam;
    private final JRadioButton radioButton_nu;
    private final JTextField textField_msv;
    private final JTextField textField_hvt;
    private final JTextField textField_dayBirth;
    private final JTextField textField_class;
    private final JTextField textField_diemTB;
    private final JTable table_thongTin;
    private final JTextField textField_IdSearch;
    private final JTextField textField_ClassSearch;
    private final JTable table_Search;
    private String path;

    //<editor-fold desc="view">
    public StudentView() {
        ActionListener actionListener = new StudentController(this);
        this.setTitle("STUDENT INFORMATION MANAGEMENT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 643);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        URL urllUrl = LoginView.class.getResource("/data/imageStudent/student.icon.png");
        Image image = Toolkit.getDefaultToolkit().createImage(urllUrl);
        this.setIconImage(image);

        JMenu jmenu_File = new JMenu("File");
        jmenu_File.setMnemonic(KeyEvent.VK_F);
        jmenu_File.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        menuBar.add(jmenu_File);

        JMenuItem jmenu_Open = new JMenuItem("Open", KeyEvent.VK_O);
        jmenu_Open.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginView.class.getResource("/data/imageStudent/open.png"))));
        jmenu_Open.addActionListener(actionListener);
        jmenu_Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        jmenu_Open.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jmenu_File.add(jmenu_Open);

        JMenuItem jmenu_Save = new JMenuItem("Save", KeyEvent.VK_S);
        jmenu_Save.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginView.class.getResource("/data/imageStudent/save.png"))));
        jmenu_Save.addActionListener(actionListener);
        jmenu_Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        jmenu_Save.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jmenu_File.add(jmenu_Save);

        JSeparator separator = new JSeparator();
        jmenu_File.add(separator);

        JMenuItem jmenu_Exit = new JMenuItem("Exit", KeyEvent.VK_E);
        jmenu_Exit.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginView.class.getResource("/data/imageStudent/exit.png"))));
        jmenu_Exit.addActionListener(actionListener);
        jmenu_Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        jmenu_Exit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jmenu_File.add(jmenu_Exit);

        JMenu mnNewMenu_1 = new JMenu("Filter");
        mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        menuBar.add(mnNewMenu_1);

        JMenuItem locMaSinhVien = new JMenuItem("M?? sinh vi??n");
        locMaSinhVien.addActionListener(actionListener);
        locMaSinhVien.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginView.class.getResource("/data/imageStudent/filter.png"))));
        locMaSinhVien.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mnNewMenu_1.add(locMaSinhVien);

        JMenuItem locTheoLop = new JMenuItem("Theo l???p");
        locTheoLop.addActionListener(actionListener);
        locTheoLop.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginView.class.getResource("/data/imageStudent/filter.png"))));
        locTheoLop.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mnNewMenu_1.add(locTheoLop);

        JMenu jmenu_About = new JMenu("About");
        jmenu_About.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        menuBar.add(jmenu_About);

        JMenuItem jmenu_About_me = new JMenuItem("About me");
        jmenu_About_me.addActionListener(actionListener);
        jmenu_About_me.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jmenu_About.add(jmenu_About_me);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel msinhvien = new JLabel("M?? sinh vi??n");
        msinhvien.setFont(new Font("Tahoma", Font.BOLD, 18));
        msinhvien.setBounds(29, 63, 118, 22);
        contentPane.add(msinhvien);

        textField_msv = new JTextField();
        textField_msv.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_msv.setBounds(191, 63, 156, 28);
        contentPane.add(textField_msv);
        textField_msv.setColumns(10);

        JLabel lblHVTn = new JLabel("H??? v?? t??n");
        lblHVTn.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblHVTn.setBounds(29, 101, 90, 22);
        contentPane.add(lblHVTn);

        textField_hvt = new JTextField();
        textField_hvt.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_hvt.setColumns(10);
        textField_hvt.setBounds(191, 101, 156, 28);
        contentPane.add(textField_hvt);

        JLabel lblNgySinh = new JLabel("Ng??y sinh");
        lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNgySinh.setBounds(29, 139, 92, 22);
        contentPane.add(lblNgySinh);

        textField_dayBirth = new JTextField();
        textField_dayBirth.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_dayBirth.setColumns(10);
        textField_dayBirth.setBounds(191, 139, 156, 28);
        contentPane.add(textField_dayBirth);

        JLabel lblLp = new JLabel("L???p");
        lblLp.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblLp.setBounds(29, 177, 52, 22);
        contentPane.add(lblLp);

        textField_class = new JTextField();
        textField_class.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_class.setColumns(10);
        textField_class.setBounds(191, 174, 156, 28);
        contentPane.add(textField_class);

        JLabel lblimTrungBnh = new JLabel("??i???m trung b??nh");
        lblimTrungBnh.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblimTrungBnh.setBounds(29, 212, 153, 22);
        contentPane.add(lblimTrungBnh);

        textField_diemTB = new JTextField();
        textField_diemTB.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_diemTB.setColumns(10);
        textField_diemTB.setBounds(191, 209, 156, 28);
        contentPane.add(textField_diemTB);

        JLabel lblNewLabel = new JLabel("Th??ng tin sinh vi??n");
        lblNewLabel.setForeground(new Color(0, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblNewLabel.setBounds(10, 10, 247, 43);
        contentPane.add(lblNewLabel);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 42, 364, 2);
        contentPane.add(separator_1);

        table_thongTin = new JTable();
        table_thongTin.setRowHeight(30);
        table_thongTin.setModel(new DefaultTableModel(new Object[][]{},
                new String[]{"M?? sinh vi??n", "H??? v?? t??n", "Ng??y sinh", "L???p", "Gi???i t??nh", "??i???m trung b??nh"}));
        ((DefaultTableCellRenderer) table_thongTin.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table_thongTin.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table_thongTin.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table_thongTin.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        table_thongTin.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        table_thongTin.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

        table_thongTin.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_thongTin.getColumnModel().getColumn(2).setPreferredWidth(83);
        table_thongTin.getColumnModel().getColumn(3).setPreferredWidth(88);
        table_thongTin.getColumnModel().getColumn(4).setPreferredWidth(55);
        table_thongTin.getColumnModel().getColumn(5).setPreferredWidth(65);
        JScrollPane scrollPane = new JScrollPane(table_thongTin);
        scrollPane.setBounds(390, 22, 586, 242);
        contentPane.add(scrollPane);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(10, 328, 966, 2);
        contentPane.add(separator_2);

        JLabel label_ID_1 = new JLabel("Gi???i t??nh");
        label_ID_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        label_ID_1.setBounds(29, 251, 79, 22);
        contentPane.add(label_ID_1);

        radioButton_nam = new JRadioButton("Nam");
        radioButton_nam.setFont(new Font("Tahoma", Font.PLAIN, 18));
        radioButton_nam.setBounds(191, 247, 66, 26);
        contentPane.add(radioButton_nam);

        radioButton_nu = new JRadioButton("N???");
        radioButton_nu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        radioButton_nu.setBounds(284, 247, 63, 31);
        contentPane.add(radioButton_nu);

        btnSex = new ButtonGroup();
        btnSex.add(radioButton_nam);
        btnSex.add(radioButton_nu);

        JSeparator separator_2_1 = new JSeparator();
        separator_2_1.setBounds(10, 563, 966, 2);
        contentPane.add(separator_2_1);

        JButton btnThem = new JButton("Th??m");
        btnThem.addActionListener(actionListener);
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnThem.setBounds(58, 283, 120, 42);
        contentPane.add(btnThem);

        btnLuu = new JButton("L??u");
        btnLuu.addActionListener(actionListener);
        btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnLuu.setBounds(242, 283, 120, 42);
        contentPane.add(btnLuu);

        btnUpdate = new JButton("C???p nh???t");
        btnUpdate.addActionListener(actionListener);
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnUpdate.setBounds(427, 283, 120, 42);
        contentPane.add(btnUpdate);

        btnXoa = new JButton("X??a");
        btnXoa.addActionListener(actionListener);
        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnXoa.setBounds(612, 283, 120, 42);
        contentPane.add(btnXoa);

        btnHuyBo = new JButton("H???y b???");
        btnHuyBo.addActionListener(actionListener);
        btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnHuyBo.setBounds(809, 283, 120, 42);
        contentPane.add(btnHuyBo);

        JLabel lbliuChnhThng = new JLabel("??i???u ch???nh th??ng tin");
        lbliuChnhThng.setForeground(Color.CYAN);
        lbliuChnhThng.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lbliuChnhThng.setBounds(10, 328, 247, 43);
        contentPane.add(lbliuChnhThng);

        textField_IdSearch = new JTextField();
        textField_IdSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_IdSearch.setColumns(10);
        textField_IdSearch.setBounds(191, 372, 156, 28);
        contentPane.add(textField_IdSearch);

        JLabel msVien_1 = new JLabel("M?? sinh vi??n");
        msVien_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        msVien_1.setBounds(29, 372, 118, 22);
        contentPane.add(msVien_1);

        JLabel lblLp_1 = new JLabel("L???p");
        lblLp_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblLp_1.setBounds(29, 413, 52, 22);
        contentPane.add(lblLp_1);

        textField_ClassSearch = new JTextField();
        textField_ClassSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_ClassSearch.setColumns(10);
        textField_ClassSearch.setBounds(191, 410, 156, 28);
        contentPane.add(textField_ClassSearch);

        table_Search = new JTable();
        table_Search.setBounds(0, 0, 584, 1);
        contentPane.add(table_Search);
        table_Search.setRowHeight(30);
        table_Search.setModel(new DefaultTableModel(new Object[][]{},
                new String[]{"M?? sinh vi??n", "H??? v?? t??n", "Ng??y sinh", "L???p", "Gi???i t??nh", "??i???m trung b??nh"}));
        ((DefaultTableCellRenderer) table_Search.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        table_Search.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table_Search.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table_Search.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        table_Search.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        table_Search.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

        table_Search.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_Search.getColumnModel().getColumn(2).setPreferredWidth(83);
        table_Search.getColumnModel().getColumn(3).setPreferredWidth(88);
        table_Search.getColumnModel().getColumn(4).setPreferredWidth(55);
        table_Search.getColumnModel().getColumn(5).setPreferredWidth(65);
        JScrollPane scrollPane_1 = new JScrollPane(table_Search);
        scrollPane_1.setBounds(390, 365, 586, 178);
        contentPane.add(scrollPane_1);

        JButton btnimTrungBnh = new JButton("??i???m trung b??nh cao nh???t");
        btnimTrungBnh.addActionListener(actionListener);
        btnimTrungBnh.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnimTrungBnh.setBounds(47, 511, 263, 42);
        contentPane.add(btnimTrungBnh);

        JButton btn_tim = new JButton("T??m");
        btn_tim.addActionListener(actionListener);
        btn_tim.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btn_tim.setBounds(68, 459, 230, 42);
        contentPane.add(btn_tim);

        // xet true false button
        int row = table_thongTin.getSelectedRow();
        if (row < 0) {
            btnXoa.setEnabled(false);
            btnUpdate.setEnabled(false);
        }
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        table_Search.setEnabled(false);
    }
    //</editor-fold>

    //<editor-fold desc="x??a to??n b??? th??ng tin tr??n m???c nh???p">
    public void clearInput() {
        this.textField_msv.setEditable(true);
        this.textField_msv.setText("");
        this.textField_hvt.setText("");
        this.textField_class.setText("");
        this.textField_dayBirth.setText("");
        this.textField_diemTB.setText("");
        this.btnSex.clearSelection();
        this.btnLuu.setEnabled(true);
    }
    //</editor-fold>

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void showMessageERROR(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void showMessageWARNING(String message, String title) {
        JOptionPane.showMessageDialog(this,
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }

    private void showMessageINFOMATION() {
        JOptionPane.showMessageDialog(this,
                "X??a th??nh c??ng.",
                "Th??ng b??o",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMessagePLAIN(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Notification",
                JOptionPane.PLAIN_MESSAGE);
    }

    //<editor-fold desc="ki??m tra m?? sinh vi??n">
    private boolean validateId() {
        boolean result = this.textField_msv.getText().matches(AppConstant.ID);
        if (result) {
            return true;
        } else {
            showMessageERROR("M?? sinh vi??n kh??ng h???p l???", "Inane error");
            return false;
        }
    }
    //</editor-fold>

    //<editor-fold desc="c???p nh???t l???i sinh vi??n theo id, ki???m tra sinh vi??n t???n t???i">
    private void checkInformation(Student student) {
        if (!studentService.checkID(student.getCode())) {
            this.studentService.add(student);
            this.displayTable(student, table_thongTin);
            this.clearInput();
        } else {
            int choose = JOptionPane.showConfirmDialog(this,
                    "M?? sinh vi??n ???? t???n t???i \n B???n c?? mu???n c???p nh???t l???i kh??ng ?", "C???nh b??o",
                    JOptionPane.YES_NO_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                this.studentService.deleteId(student.getCode());
                this.loadAndClear(null, table_thongTin);
                this.updateStudent();
                this.displayTable(student, table_thongTin);
                this.clearInput();
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="ki???m tra t??nh h???p l??? t??n">
    private boolean validateName() {
        String nameStudent = studentService.formatName(this.textField_hvt.getText());
        if (!studentService.checkName(nameStudent)) {
            showMessageERROR("T??n kh??ng ch???a s??? ho???c k?? t???.", "Inane error");
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="ki???m tra ng??y sinh">
    private boolean validateDayBirth() {
        boolean result = this.textField_dayBirth.getText().matches(AppConstant.BIRTHDAY);
        if (result) {
            return true;
        } else {
            showMessageERROR("?????nh d???ng kh??ng ch??nh x??c dd/mm/yyyy.", "Inane error");
            return false;
        }
    }
    //</editor-fold>

    //<editor-fold desc="ki???m tra gi???i t??nh">
    private boolean validateSex() {
        if (!this.radioButton_nam.isSelected() && !this.radioButton_nu.isSelected()) {

            showMessageERROR("Ch??a ch???n gi???i t??nh.", "Inane error");
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="ki???m tra t??nh h???p l??? c???a ??i??m">
    private boolean validatePoint() {
        try {
            double pointAvg = Double.parseDouble(this.textField_diemTB.getText());
            if (!studentService.checkPoint(pointAvg)) {
                showMessageERROR("??i???m ph???i> = 0 ho???c ??i???m <= 10.",
                        "Inane error");
                return false;
            }
        } catch (Exception exception) {
            showMessageERROR("??i???m kh??ng ch???a c??c t??? ho???c k?? t???.",
                    "Inane error");
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="ki???m tra class">
    private boolean validateClass() {
        boolean result = this.textField_class.getText().matches(AppConstant.CLASS);
        if (!result) {
            showMessageERROR("D??? li???u l???p kh??ng h???p l???.",
                    "Inane error");
            return false;
        } else {
            return true;
        }
    }
    //</editor-fold>

    //<editor-fold desc="l???y th??ng tin t??? ng?????i nh???p">
    public void getStudentInfo() {
        // l???y t??n l???p
        if (validateId() && validateName() && validateDayBirth() && validateClass() && validatePoint() && validateSex()) {
            int id = Integer.parseInt(this.textField_msv.getText());
            String nameStudent = studentService.formatName(this.textField_hvt.getText());
            String dayBirth = this.textField_dayBirth.getText();
            String className = this.textField_class.getText();
            boolean sex = true;
            if (!this.radioButton_nam.isSelected()) {
                if (this.radioButton_nu.isSelected()) {
                    sex = false;
                }
            }
            double pointAvg = Double.parseDouble(this.textField_diemTB.getText());
            Student student = new Student(id, nameStudent, dayBirth, className, sex ? "Nam" : "N???", pointAvg);
            this.checkInformation(student);
            this.btnXoa.setEnabled(true);
            this.btnUpdate.setEnabled(true);
        }
    }
    //</editor-fold>

    // ??i???n th??ng tin c???a h??ng ???????c ch???n t??? b???ng student
    // v??o c??c tr?????ng t????ng ???ng c???a student.
    // sinh vi??n ??ang ???????c ch???n
    private Student fillStudentFromSelectedRow() {
        DefaultTableModel modelTable = (DefaultTableModel) table_thongTin.getModel();
        int row = table_thongTin.getSelectedRow();
        // l???y th??ng tin t??? b???ng
        int id = Integer.parseInt(modelTable.getValueAt(row, 0) + "");
        String name = modelTable.getValueAt(row, 1) + "";
        String dayBirth = modelTable.getValueAt(row, 2) + "";
        String className = modelTable.getValueAt(row, 3) + "";
        String sex = modelTable.getValueAt(row, 4) + "";
        double avgPoint = Double.parseDouble(modelTable.getValueAt(row, 5).toString());
        Student student = new Student(id, name, dayBirth, className, sex, avgPoint);
        this.btnHuyBo.setEnabled(true);
        this.btnXoa.setEnabled(true);
        return student;
    }

    // c???p nh???t th??ng tin student.
    public void showListStudents() {
        Student student = fillStudentFromSelectedRow();
        this.textField_msv.setText(student.getCode() + "");
        textField_msv.setEditable(false);
        this.textField_hvt.setText(student.getFullName());
        this.textField_dayBirth.setText(student.getBirthYear());
        this.textField_class.setText(student.getClassName());
        if (student.getSex().equals("Nam")) {
            this.radioButton_nam.setSelected(true);
        } else {
            this.radioButton_nu.setSelected(true);
        }
        this.textField_diemTB.setText(student.getAverageGrade() + "");
    }

    // hi???n th??? th??ng tin b???ng
    private void displayTable(Student student, JTable table) {
        DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
        modelTable.addRow(new Object[]{student.getCode() + "",
                student.getFullName(), student.getBirthYear(),
                student.getClassName(), student.getSex(),
                student.getAverageGrade()});
    }

//        private void loadingTable(JTable table) {
//        while (true) {
//            DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
//            int row = modelTable.getRowCount();
//            if (row == 0) {
//                break;
//            } else {
//                try {
//                    modelTable.removeRow(0);
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            }
//        }
//        for (Student student : StudentService.studentList) {
//            this.displayTable(student, table);
//        }
//    }
//    private void clearInput(JTable table) {
//        while (true) {
//            DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
//            int row = modelTable.getRowCount();
//            if (row == 0) {
//                break;
//            } else {
//                try {
//                    modelTable.removeRow(0);
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            }
//        }
//    }

    // t???i l???i d??? li???u table
    // x??a th??ng tin ?? nh???p
    private void loadAndClear(Student students, JTable table) {
        // x??a t???t c??? d??? li???u ra kh???i table
        while (true) {
            DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
            int row = modelTable.getRowCount();
            if (row == 0) {
                break;
            } else {
                try {
                    modelTable.removeRow(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        // c???p nh???t l???i table
        if (students == null) {
            for (Student student : StudentService.studentList) {
                this.displayTable(student, table);
            }
        } else {
            this.displayTable(students, table);
        }
    }

    // c???p nh???t l???i sinh vi??n
    private void updateStudent() {
        int id = Integer.parseInt(this.textField_msv.getText());
        String nameStudent = studentService.formatName(this.textField_hvt.getText());
        String dayBirth = this.textField_dayBirth.getText();
        String className = this.textField_class.getText();
        double pointAvg = Double.parseDouble(this.textField_diemTB.getText());
        boolean sex = true;
        if (!this.radioButton_nam.isSelected()) {
            if (this.radioButton_nu.isSelected()) {
                sex = false;
            }
        }
        Student student = new Student(id, nameStudent, dayBirth, className, sex ? "Nam" : "N???", pointAvg);
        this.studentService.add(student);
    }

    // x??a sinh vi??n
    public void deleteStudent() {
        DefaultTableModel modelTableThongTin = (DefaultTableModel) table_thongTin.getModel();
        DefaultTableModel modelTableSearch = (DefaultTableModel) table_Search.getModel();
        int row = table_thongTin.getSelectedRow();
        int choose = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n x??a sinh vi??n n??y kh??ng?",
                "C???nh b??o", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.YES_OPTION) {
            Student student = fillStudentFromSelectedRow();
            this.studentService.deleteStudent(student);
            modelTableThongTin.removeRow(row);
            modelTableSearch.removeRow(row);
        }

    }

    // t??m ki???m sinh vi??n
    public void searchStudent() {
        try {
            int idSearch = Integer.parseInt(this.textField_IdSearch.getText());
            String nameClass = this.textField_ClassSearch.getText();
            if ("".equals(nameClass) && this.studentService.checkID(idSearch)) {
                Student student = this.studentService.searchId(idSearch);
                this.loadAndClear(student, table_Search);
            } else if (this.studentService.searchIdClass(nameClass, idSearch)) {
                showMessagePLAIN("Sinh vi??n c?? t???n t???i.");
            } else {
                showMessagePLAIN("Sinh vi??n kh??ng t???n t???i.");
            }
        } catch (Exception exception) {
            showMessageWARNING("Kh??ng ??i???n ????? th??ng tin.",
                    "Error");
        }
    }

    // t??m sinh vi??n c?? ??i??m cao nh???t ?????u ti??n
    public String maxAverageScore() {
        Student students = this.studentService.searchPoint();
        return "Sinh vi??n c?? ??i???m cao nh???t: " + students.getFullName() + " v???i ??i???m trung b??nh l??: " + students.getAverageGrade();
    }

    private boolean checkPath() {
        try {
            if (this.path.equals(AppConstant.PATH)) {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public void saveFile() {
        try {
            if (this.checkPath()) {
                FileUtil.writeFile(AppConstant.PATH, StudentService.studentList);
            } else if (!this.checkPath() && path != null) {
                FileUtil.writeFile(path, StudentService.studentList);
            } else {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showSaveDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    FileUtil.writeFile(file.getAbsolutePath(), StudentService.studentList);
                }
            }
            showMessage("L??u th??nh c??ng");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("Luu thanh cong");
    }

    public static void errorPath() {
        new StudentView().showMessageERROR("???????ng d???n kh??ng h???p l???.",
                "Error");
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            this.path = file.getAbsolutePath();
            StudentService.studentList = FileUtil.readFile(path);
            this.loadAndClear(null, table_thongTin);
        }
        this.btnXoa.setEnabled(true);
        this.btnUpdate.setEnabled(true);
        System.out.println("m??? file th??nh c??ng");
    }

    public void exitProgram() {
        int choose = JOptionPane.showConfirmDialog(this,
                "B???n c?? mu???n tho???t kh???i ch????ng tr??nh?", "Exit",
                JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void deleteStudentClass() {
        String nameClass = JOptionPane.showInputDialog(
                this,
                "\nNh???p l???p mu???n x??a: ",
                "Th??ng b??o",
                JOptionPane.PLAIN_MESSAGE);
        try {
            if (this.studentService.deleteStudent(nameClass)) {
                showMessageINFOMATION();
                this.loadAndClear(null, table_thongTin);
//                this.clearInput(table_Search);
            this.loadAndClear(null, table_Search);
            } else {
                showMessageWARNING("Kh??ng t??m th???y l???p ch??? ?????nh",
                        "Th??ng b??o");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // x??a sinh vi??n theo id
    public void deleteStudentId() {
        String idStudent = JOptionPane.showInputDialog(
                this,
                "\nNh???p m?? sinh vi??n mu???n x??a: ",
                "Th??ng b??o",
                JOptionPane.PLAIN_MESSAGE);
        try {
            if (this.studentService.checkID(Integer.parseInt(idStudent))) {
                this.studentService.deleteId(Integer.parseInt(idStudent));
                showMessageINFOMATION();
            } else {
                showMessageWARNING("Sinh vi??n kh??ng t???n t???i.",
                        "Th??ng b??o");
            }
            this.loadAndClear(null, table_thongTin);
            this.loadAndClear(null, table_Search);
//            this.clearInput(table_Search);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void aboutMe() {
        showMessage("Y??u c???u khi nh???p th??ng tin sinh vi??n: " +
                "\n M?? sinh vi??n: kh??ng ch???a k?? t??? v?? k?? t??? ?????c bi???t" +
                "\n H??? v?? t??n: kh??ng ???????c ch???a s??? ho???c k?? t???" +
                "\n Ng??y sinh: nh???p theo ?????nh d???ng dd/MM/yyyy" +
                "\n L???p: kh??ng ???????c ch???a k?? t??? ?????c bi???t" +
                "\n ??i???m: theo thang ??i???m 10");
    }
}
