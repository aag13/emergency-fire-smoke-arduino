
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class arduinogui {
    
    String host = "172.16.33.82";
    int port = 3000;
    Socket sckt;
    File file = new File("input.txt");
    BufferedWriter bw;

    PrintWriter out;
    BufferedReader in;
    
    LinkedList ll;
    LinkedList tll;
    LinkedList vll;
    
    /////////////////////////////////////////////////
    
    private JFrame frame;
    private JTextField gasSensorField;
    private JLabel gasSensorLabel;
    private JScrollPane incidentScrollPane;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JSeparator jSeparator1;
    private JButton resetMotor;
    private JTextField smokeSensorField;
    private JLabel smokeSensorLabel;
    private JTextField tempSensorField;
    
    boolean listListenerCalled = false;
    int lastIndex = -1;
    

    public void createComponent() {
        jPanel2 = new JPanel();
        resetMotor = new JButton();
        jLabel3 = new JLabel();
        jSeparator1 = new JSeparator();
        
        jPanel1 = new JPanel();
        gasSensorLabel = new JLabel();
        jLabel1 = new JLabel();
        smokeSensorLabel = new JLabel();
        smokeSensorField = new JTextField();
        jLabel2 = new JLabel();
        tempSensorField = new JTextField();
        gasSensorField = new JTextField();
        
        frame = new JFrame("ISMS");
        
        resetMotor.setText("Reset Motor");
        
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Industrial Safety Management System");
        
        JList list = createList();
        incidentScrollPane = new JScrollPane(list);
        
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(resetMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetMotor)
                .addGap(15, 15, 15))
        );
        
        
        
        gasSensorLabel.setText("Gas Sensor");

        jLabel1.setText("Sensor Readings");

        smokeSensorLabel.setText("Smoke Sensor");
        smokeSensorLabel.setMaximumSize(new java.awt.Dimension(68, 18));
        smokeSensorLabel.setMinimumSize(new java.awt.Dimension(68, 18));

        smokeSensorField.setEditable(false);
        smokeSensorField.setText("0.0");

        jLabel2.setText("Temperature Sensor");

        tempSensorField.setEditable(false);
        tempSensorField.setText("0.0");

        gasSensorField.setEditable(false);
        gasSensorField.setText("0.0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(smokeSensorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gasSensorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(smokeSensorField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(gasSensorField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tempSensorField))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gasSensorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gasSensorField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(smokeSensorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(smokeSensorField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tempSensorField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(incidentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(incidentScrollPane)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
    }
    
    
    public void connectWithArduino() {
        try {

            sckt = new Socket(host, port);

            bw = new BufferedWriter(new FileWriter(file));

            out = new PrintWriter(sckt.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sckt.getInputStream()));

            out.println("ISMS\n"); // must end with \n...or it will keep waiting

            String str = "";
            while ((str = in.readLine()) != null) {
                
                bw = new BufferedWriter(new FileWriter(file, true));
                String time = new SimpleDateFormat("yyyy/MM/dd::HH-mm-ss").format(Calendar.getInstance().getTime());
                System.out.println(str);

                bw.append(time +"s"+str);
                bw.newLine();
                bw.close();

            }
            in.close();

            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String[] llToTimeArray(LinkedList ll){
        String[] array = new String[ll.size()];
        
        for(int i=0;i<ll.size();i++){
            String temp = (String) ll.get(i);
            String[] temparray = temp.split("s");
            tll.add(temparray[0]);
            vll.add(temparray[1]);
            
            //System.out.println(temparray[1]);
            
            array[i] = temparray[0];
        }
        
        return array;
    }
    
    public JList createList() {
        try {
            ll = new LinkedList();
            tll = new LinkedList();
            vll = new LinkedList();
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = "";
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                ll.add(str);
            }

            String[] array = llToTimeArray(ll);

            JList<String> timeList = new JList<String>(array);

            br.close();
            
            timeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            timeList.addListSelectionListener(
                    new SharedListSelectionHandler());

            return timeList;

        } catch (Exception e) {
            return new JList<>();
        }

    }
    
    ///////////////////////////////////////////
    
    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            JList lsm = (JList)e.getSource();

            int index = lsm.getSelectedIndex();
            String value = (String) lsm.getSelectedValue();
            
            if(lastIndex != index){
                listListenerCalled = false;
            }
            
            if(listListenerCalled){
                
            }else {
                System.out.println("Selected index is : " + index);
                System.out.println("Selected value is : " + value);
                
                String temp = (String) vll.get(index);
                
                String[] array = temp.split(" ");
                
                gasSensorField.setText(array[0]);
                smokeSensorField.setText(array[1]);
                tempSensorField.setText(array[2]);
                
                listListenerCalled = true;
                lastIndex = index;
            }
            
        }
    }
    
    
    /////////////////////////////////////////////
    
    
    
    private void resetMotorActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        try{
            out.println("m");
            
        }catch(Exception e){
            
        }
    }

    public arduinogui(){
        
        createComponent();
       
        //connectWithArduino();
    }
    public static void main(String[] args) {
        
        arduinogui ardg = new arduinogui();
        
    }
}
