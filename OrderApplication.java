/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 12051033
 */
//Import packages
import javax.swing.JLabel; // Label
import javax.swing.JComboBox; // ComboBox
import javax.swing.JPanel; // Panel
import javax.swing.JTextArea; // TextArea
import javax.swing.JButton; // Button
import javax.swing.JOptionPane; // OptionPane for warning message
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class OrderApplication extends JFrame
{
   //Declare variables, constants and data structures as needed   
   final private JFrame frame;
   private final JComboBox<String> c;
   private final JComboBox<String> c1;
   private final JComboBox<String> c2;
   private final JButton b;
   private final JButton b1;
   private final JButton b2;
   private final JButton b3;
   private final JTextArea ta;
   private final JLabel jl;
   private int deliveryCost;
    //string array for pizza selection
    private static final String[] items =
    {"Select Pizza type","BBQ Meatlovers", "Chicken and Feta", "Hawaiian", "Vegorama",};
    //string array for size selection
    private static final String[] size = 
    {"Select Pizza size","Small", "Medium", "Large"};
    //string array for drinks selection
    private static final String[] drinks =
    {"Select Drinks","Juice", "Water", "Coke", "Fanta"};
   
   //Constructor
   public OrderApplication()
   {
     //title in the window or frame of the application
     super("Welcome to the Online Pizza Shop");
     
     //initialized value for each elements:
     //Label
     jl = new JLabel("Please select item you would like to order:");
     //ComboBoxes
     c = new JComboBox(items);
     c1 = new JComboBox(size);
     c2 = new JComboBox(drinks);
     frame = new JFrame();
    //Buttons
     b = new JButton("Add Delivery Cost"); 
     b.addActionListener(h); //add handler to implement actionlistener
     b1 = new JButton("Show Total Cost");
     b1.addActionListener(h); //add handler to implement actionlistener
     b2 = new JButton("Submit Order");
     b2.addActionListener(h); //add handler to implement actionlistener
     b3 = new JButton("Exit");
     b3.addActionListener(h); //add handler to implement actionlistener
     //text area
     ta = new JTextArea(20, 65);
   } //end of the constructor
     
    //Inner listener classes for comboboxes and buttons with actionPerformed method
   ButtonHandler h = new ButtonHandler(); // set object for buttonhandler
   
   private class ButtonHandler implements ActionListener
    {// Start of method Buttonhandler
        int choose = 0;
        @Override
        public void actionPerformed(ActionEvent event)
        { //Start of actionPerformed method
            switch (event.getActionCommand()) { //return string associated with the case
                case "Add Delivery Cost": // for button b
                    choose = 1;
                    break;
                case "Show Total Cost": // for button b1
                    choose = 2;
                    break;
                case "Submit Order": // for button b2
                    choose = 3;
                    break;
                case "Exit": // for button b3
                    choose = 4;
                    break;
                default: // when it doesn't satisfy the case case return to 0
                    break;
            } //end of actionPerformed method
             
            //declare string variable to get the selected item on the comboBox
            String getItem = (String)c.getSelectedItem();
            String getItem2 = (String)c1.getSelectedItem();
            String getItem3 = (String)c2.getSelectedItem();
            //New object for Order class to set new value for it's variables
            Order newOrder = new Order(getItem, getItem2, 0, getItem3);
           //declare new variable for total and pizzaPrice  
           double total = newOrder.pizzaPrices() +  newOrder.drinkPrices() + newOrder.sizePrices() + deliveryCost ;
           double pizzaPrice = newOrder.pizzaPrices() + newOrder.sizePrices();
            
           //Sequencial looping to give response for user interaction to the program
          
            switch (choose) 
            {
                case 1: // add $4 when user click Add Delivery Cost otherwise it is 0
                    deliveryCost = 4;  
                    if ((c.getSelectedIndex()==0)&&(c1.getSelectedIndex()==0)&&(c2.getSelectedIndex()==0)) //response when there is nothing selected
                      {
                          JOptionPane.showMessageDialog(frame, "Please select Pizza type, size and drinks!");
                      }
                    ta.setText("Delivery Cost $4 has been added. Please 'Submit Order' to complete your order.");
                    break;
                case 2: // Set Show Total Cost depending on the condition
                    if (total == 0) //response when there is nothing selected
                        JOptionPane.showMessageDialog(frame, "Please select Pizza type, size and drinks!");
                    else if ((c.getSelectedIndex()==0)&&(c1.getSelectedIndex()==0)&&(c2.getSelectedIndex()==0))
                    {
                    //set dialog to warn user
                    int confirmOk = JOptionPane.showConfirmDialog(frame, "Please select Pizza type and pizza size!", "Warning", JOptionPane.OK_OPTION);
                    if (confirmOk == JOptionPane.OK_OPTION)
                    {
                    ta.setText(""); // clear textArea
                    }
                    }
                    //when there is no size and drinks has been selected
                    else if ((c.getSelectedIndex()>=0)&&(c1.getSelectedIndex()==0)&&(c2.getSelectedIndex()==0))
                        //warning message 
                        JOptionPane.showMessageDialog(frame, "Please select pizza size!");
                     //when there is no drinks has been selected
                    else if ((c.getSelectedIndex()>=1)&&(c1.getSelectedIndex()>=1)&&(c2.getSelectedIndex()==0)){
                        ta.setText("Total cost to be paid by you is $"+total);
                        //warning message 
                        JOptionPane.showMessageDialog(frame, "No Drinks!");
                    }
                    //when there is only drinks has been selected
                    else if ((c.getSelectedIndex()>=0)&&(c1.getSelectedIndex()==0)&&(c2.getSelectedIndex()>=1))
                    {
                    //messages and print out put
                    ta.setText("Total cost to be paid by you is $"+total);
                        JOptionPane.showMessageDialog(frame, "You only order "+getItem3+"!");
                    }
                    else
                    //else all are being selected
                    ta.setText("Total cost to be paid by you is $"+total);
                    break;
                case 3: // reponses when Submit Order 
                    //when user selected pizza, size and drinks plus delivery
                    if ((pizzaPrice != 0)&&(c2.getSelectedIndex() != 0))
                    {
                        //set the text on the receipt
                        ta.setText("Your order has been placed. Here is your reciept\n\n"+
                        "\t            Online Pizza Shop\n"+
                        "\t------------------------------------------------\n"+
                        "\t"+getItem+" "+getItem2+"\t"+pizzaPrice+"\n"+
                        "\t"+getItem3+"\t\t"+newOrder.drinkPrices()+"\n"+
                        "\tDelivery\t\t"+deliveryCost+"\n\n"+
                        "\tTotal Cost:\t\t"+total+"\n\n"+
                        "\t------------------------------------------------\n"+
                        "\n\n"+
                        "\t            ABN: 92116388374\n"+
                        "          Thank You for shopping at the Online Pizza Shop");
                        //when Submit Order has been clicked this message will prompt to let user continue order or close the program
                        int confirm1 = JOptionPane.showConfirmDialog(frame, "Would you like to order again?", "You order is successfull", JOptionPane.YES_NO_OPTION);
                        if (confirm1 == JOptionPane.NO_OPTION) //when user click no program exits
                        {
                            System.exit(0); //terminate program
                        }
                        else if (confirm1 == JOptionPane.YES_OPTION)  // when user click yes the textarea will clear out and comboBox will return to index 0
                        {
                            ta.setText("");
                            deliveryCost=0; // delvery will set to 0
                            c.setSelectedIndex(0); //return c to index 0
                            c1.setSelectedIndex(0); // return c1 to index 0
                            c2.setSelectedIndex(0); // return c2 to index 0
                        }
                    }
               // when there is only drinks has been selected
               else if ((c.getSelectedIndex() == 0)&&(c1.getSelectedIndex() == 0)&&(c2.getSelectedIndex() >= 1))
               {
                   //set the text on the receipt
                    ta.setText("Your order has been placed. Here is your reciept\n\n"+
                    "\t            Online Pizza Shop\n"+
                    "\t------------------------------------------------\n"+
                    "\tNo Pizza\t"+pizzaPrice+"\n"+
                    "\t"+getItem3+"\t\t"+newOrder.drinkPrices()+"\n"+
                    "\tDelivery\t\t"+deliveryCost+"\n\n"+
                    "\tTotal Cost:\t\t"+total+"\n\n"+
                    "\t------------------------------------------------\n"+
                    "\n\n"+
                    "\t            ABN: 92116388374\n"+
                    "          Thank You for shopping at the Online Pizza Shop");
                    
                    int confirm1 = JOptionPane.showConfirmDialog(frame, "Would you like to order again?", "You order is successfull", JOptionPane.YES_NO_OPTION);
                    if (confirm1 == JOptionPane.NO_OPTION) //when user click no program exits
                    {
                        System.exit(0);
                    }
                    else if (confirm1 == JOptionPane.YES_OPTION)  // when user click yes the textarea will clear out and comboBox will return to index 0
                    {
                        ta.setText("");
                        deliveryCost=0; // delvery will set to 0
                        c.setSelectedIndex(0);
                        c1.setSelectedIndex(0);
                        c2.setSelectedIndex(0);
                    }
                }
               // when there is no drinks has been selected
                else if ((c2.getSelectedIndex() == 0)&&(pizzaPrice >= 1))
                {
                    //set the text on the receipt
                    ta.setText("Your order has been placed. Here is your reciept\n\n"+
                    "\t            Online Pizza Shop\n"+
                    "\t------------------------------------------------\n"+
                    "\t"+getItem+" "+getItem2+"\t"+pizzaPrice+"\n"+
                    "\tNo Drinks\t\t"+newOrder.drinkPrices()+"\n"+
                    "\tDelivery\t\t"+deliveryCost+"\n\n"+
                    "\tTotal Cost:\t\t"+total+"\n\n"+
                    "\t------------------------------------------------\n"+
                    "\n\n"+
                    "\t            ABN: 92116388374\n"+
                    "          Thank You for shopping at the Online Pizza Shop");
                    
                    int confirm1 = JOptionPane.showConfirmDialog(frame, "Would you like to order again?", "You order is successfull", JOptionPane.YES_NO_OPTION);
                    if (confirm1 == JOptionPane.NO_OPTION) //when user click no program exits
                    {
                        System.exit(0);
                    }
                
                    else if (confirm1 == JOptionPane.YES_OPTION)  // when user click yes the textarea will clear out and comboBox will return to index 0
                    {
                        ta.setText("");
                        deliveryCost=0; // delvery will set to 0
                        c.setSelectedIndex(0);
                        c1.setSelectedIndex(0);
                        c2.setSelectedIndex(0);
                    }
                 }
                //when there is nothing selected    
                else
                    //warning message for the user
                    JOptionPane.showMessageDialog(frame, "Please select Pizza type, size and drinks!");
                break;
            // when user click Exit
            case 4:
                //set warning message for user and give option to continue or not
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Warning message", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.NO_OPTION){} // when click no program will continue
                else if (confirm == JOptionPane.YES_OPTION) // when click yes program will exit
                {
                    System.exit(0); // terminate program
                }
            break;
            }
        }
    }
    //Method to create panels and add components
    private void PanelComp()
    { //Start of method PanelComp()
        JPanel p = new JPanel(); //object for panel
        p.add(jl); // call label
        p.add(c); // call comboBox
        p.add(c1); // call comboBox
        p.add(c2); // call comboBox
        p.add(ta); // call textArea
        p.add(b); // call buttons
        p.add(b1); // call buttons
        p.add(b2); // call buttons
        p.add(b3); // call buttons
        add(p); // add the panel to the frame
     } // end of method PanelComp()
    public static void main(String[] args) // start of main method
    {
    OrderApplication oa = new OrderApplication(); //set new object for OrderApplication()
    oa.setVisible(true); // set the visibility to true
    oa.setSize(800,420); // set the size of the window
    oa.setResizable(false); // set window size to fixed size
    oa.PanelComp(); // call the panels components
    oa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // show close button to exit the program
    } //end of the main method
} // end of class OrderApplication()