/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.*;
/**
 *
 * @author Kevin
 */
public class PoSGUI extends javax.swing.JFrame {
    ArrayList<String> currentItems = new ArrayList<String>();
    ArrayList<String> abbreviatedItems = new ArrayList<String>();
    ArrayList<String> unavailableItems = new ArrayList<String>();
    boolean employeeView = false;
    int selectIndex = 0;
    String customerName = "";
    /**
     * Creates new form PoSGUI
     */
    public PoSGUI() {
        initComponents();
        updateFoodButtons(); 
    }
    
    //Updates the "Current Order" list in the right side of the screen.
    public void update()
    {
        String orderText = "<html>";
        //takes each order entry
        for(int orderEntry = 0; orderEntry < currentItems.size(); orderEntry++) //"meal1,entree1 customization,side1,drink1"
        {
            if(orderEntry == selectIndex)
            {
                orderText+= "<b>";
            }
            //takes each item within order entry (incase of combo meals)
            String[] individualItems = currentItems.get(orderEntry).split(","); //"meal1","entree1 customization","side1","drink1"
            if(individualItems.length != 1) //means it's a meal
            {
                orderText += "-" + individualItems[0] + "<br>";
                for(int i = 1; i < individualItems.length; i++)//"entree1 cusomization"
                {
                    String[] customizations = individualItems[i].split(" "); //"entree1","custmoization"
                    //customizations[0] is the entree
                    orderText += "&emsp;-" + customizations[0] + "<br>"; //entree1
                    for(int j = 1; j < customizations.length; j++)
                    {
                        orderText += "&emsp;&emsp;-" + customizations[j] + "<br>";
                    }
                }               
            }
            else //in case not a meal
            {
                String[] customizations = individualItems[0].split(" ");
                orderText += "-" + customizations[0] + "<br>"; //entree1
                for(int j = 1; j < customizations.length; j++)
                {
                    orderText += "&emsp;-" + customizations[j] + "<br>";
                }
            }
            if(orderEntry == selectIndex)
            {
                orderText+= "</b>";
            }
            orderText += "<br>";
        }
        currentOrder.setText(orderText + "</b></html>");
    }

    //Updates every button after availability has been modified.
    public void updateAvailability()
    {
        if(unavailableItems.contains("Meal1") || unavailableItems.contains("Entree1") || unavailableItems.contains("Side2") || unavailableItems.contains("Beverage4"))
            meal1.setEnabled(false);
        else
            meal1.setEnabled(true);
        if(unavailableItems.contains("Meal2") || unavailableItems.contains("Entree5") || unavailableItems.contains("Side4") || unavailableItems.contains("Beverage5"))
            meal2.setEnabled(false);
        else
            meal2.setEnabled(true);
        if(unavailableItems.contains("Meal3") || unavailableItems.contains("Entree6") || unavailableItems.contains("Side2") || unavailableItems.contains("Beverage1"))
            meal3.setEnabled(false);
        else
            meal3.setEnabled(true);
        if(unavailableItems.contains("Meal4") || unavailableItems.contains("Entree1") || unavailableItems.contains("Side2") || unavailableItems.contains("Beverage1"))
            meal4.setEnabled(false);
        else
            meal4.setEnabled(true);
        if(unavailableItems.contains("Meal5") || unavailableItems.contains("Entree5") || unavailableItems.contains("Side2") || unavailableItems.contains("Beverage5"))
            meal5.setEnabled(false);
        else
            meal5.setEnabled(true);
        if(unavailableItems.contains("Entree1"))
            entree1.setEnabled(false);
        else
            entree1.setEnabled(true);
        if(unavailableItems.contains("Entree2"))
            entree2.setEnabled(false);
        else
            entree2.setEnabled(true);
        if(unavailableItems.contains("Entree3"))
            entree3.setEnabled(false);
        else
            entree3.setEnabled(true);
        if(unavailableItems.contains("Entree4"))
            entree4.setEnabled(false);
        else
            entree4.setEnabled(true);
        if(unavailableItems.contains("Entree5"))
            entree5.setEnabled(false);
        else
            entree5.setEnabled(true);
        if(unavailableItems.contains("Entree6"))
            entree6.setEnabled(false);
        else
            entree6.setEnabled(true);
        if(unavailableItems.contains("Entree7"))
            entree7.setEnabled(false);
        else
            entree7.setEnabled(true);
        if(unavailableItems.contains("Side1"))
            side1.setEnabled(false);
        else
            side1.setEnabled(true);
        if(unavailableItems.contains("Side2"))
            side2.setEnabled(false);
        else
            side2.setEnabled(true);
        if(unavailableItems.contains("Side3"))
            side3.setEnabled(false);
        else
            side3.setEnabled(true);
        if(unavailableItems.contains("Side4"))
            side4.setEnabled(false);
        else
            side4.setEnabled(true);
        if(unavailableItems.contains("Beverage1"))
            beverage1.setEnabled(false);
        else
            beverage1.setEnabled(true);
        if(unavailableItems.contains("Beverage2"))
            beverage2.setEnabled(false);
        else
            beverage2.setEnabled(true);
        if(unavailableItems.contains("Beverage3"))
            beverage3.setEnabled(false);
        else
            beverage3.setEnabled(true);
        if(unavailableItems.contains("Beverage5"))
            beverage4.setEnabled(false);
        else
            beverage4.setEnabled(true);
        if(unavailableItems.contains("Beverage5"))
            beverage5.setEnabled(false);
        else
            beverage5.setEnabled(true);
        if(unavailableItems.contains("Dessert1"))
            dessert1.setEnabled(false);
        else
            dessert1.setEnabled(true);
        if(unavailableItems.contains("Dessert2"))
            dessert2.setEnabled(false);
        else
            dessert2.setEnabled(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        scrollPane1 = new java.awt.ScrollPane();
        scrollbar4 = new java.awt.Scrollbar();
        scrollbar3 = new java.awt.Scrollbar();
        scrollbar2 = new java.awt.Scrollbar();
        scrollbar1 = new java.awt.Scrollbar();
        buttonGroup1 = new javax.swing.ButtonGroup();
        mealFrame = new javax.swing.JFrame();
        mealFrame.setVisible(false);
        currentMeal = new javax.swing.JLabel();
        entreeCustomize = new java.awt.Button();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        entreeMealLabel = new javax.swing.JLabel();
        customizations = new javax.swing.JLabel();
        sideLabel = new javax.swing.JLabel();
        beverageLabel = new javax.swing.JLabel();
        mealAvailability = new javax.swing.JCheckBox();
        mealAvailability.setVisible(false);
        mealPriceChange = new javax.swing.JTextField();
        mealPriceChange.setVisible(false);
        confirmPriceMeal = new javax.swing.JButton();
        confirmPriceMeal.setVisible(false);
        priceEffectMeal = new javax.swing.JLabel();
        priceEffectMeal.setVisible(false);
        priceDateMeal = new javax.swing.JTextField();
        priceDateMeal.setVisible(false);
        priceDeltaMeal = new javax.swing.JLabel();
        priceDeltaMeal.setVisible(false);
        addItems = new javax.swing.JToggleButton();
        toppingFrame = new javax.swing.JFrame();
        entreeLabel = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        topping1Box = new javax.swing.JCheckBox();
        topping2Box = new javax.swing.JCheckBox();
        topping3Box = new javax.swing.JCheckBox();
        topping4Box = new javax.swing.JCheckBox();
        topping5Box = new javax.swing.JCheckBox();
        entreeAvailability = new javax.swing.JCheckBox();
        entreeAvailability.setVisible(false);
        entreePriceChange = new javax.swing.JTextField();
        entreePriceChange.setVisible(false);
        confirmPriceEntree = new javax.swing.JButton();
        confirmPriceEntree.setVisible(false);
        addCommitButton = new javax.swing.JButton();
        priceEffectEntree = new javax.swing.JLabel();
        priceEffectEntree.setVisible(false);
        priceDateEntree = new javax.swing.JTextField();
        priceDateEntree.setVisible(false);
        priceDeltaEntree = new javax.swing.JLabel();
        priceDeltaEntree.setVisible(false);
        othersFrame = new javax.swing.JFrame();
        othersLabel = new java.awt.Label();
        quantityAdd = new java.awt.TextField();
        quantityButton = new java.awt.Button();
        otherAvailability = new javax.swing.JCheckBox();
        otherAvailability.setVisible(false);
        otherPriceChange = new javax.swing.JTextField();
        otherPriceChange.setVisible(false);
        confirmPriceOthers = new javax.swing.JButton();
        confirmPriceOthers.setVisible(false);
        priceEffectOthers = new javax.swing.JLabel();
        priceEffectOthers.setVisible(false);
        priceDateOthers = new javax.swing.JTextField();
        priceDateOthers.setVisible(false);
        priceDeltaOthers = new javax.swing.JLabel();
        priceDeltaOthers.setVisible(false);
        employeeVerify = new javax.swing.JFrame();
        employeePWBox = new javax.swing.JPasswordField();
        employeeVerifyLabel = new javax.swing.JLabel();
        employeeConfirm = new javax.swing.JButton();
        trendFrame = new javax.swing.JFrame();
        currentlyTrending = new javax.swing.JLabel();
        topTrendHeader = new javax.swing.JLabel();
        botTrendHeader = new javax.swing.JLabel();
        encouragingWords = new javax.swing.JLabel();
        top1Trending = new javax.swing.JLabel();
        top2Trending = new javax.swing.JLabel();
        bot1Trending = new javax.swing.JLabel();
        bot2Trending = new javax.swing.JLabel();
        trendExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        mealsButton = new java.awt.Button();
        entreesButton = new java.awt.Button();
        sidesButton = new java.awt.Button();
        beverageButton = new java.awt.Button();
        dessertsButton = new java.awt.Button();
        employeeButton = new javax.swing.JButton();
        seeTrend = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        checkoutButton = new javax.swing.JButton();
        enterName = new javax.swing.JTextField();
        clearOrder = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        currentOrder = new javax.swing.JEditorPane();
        currentOrder.setContentType("text/html");
        upSelected = new javax.swing.JButton();
        removeSelected = new javax.swing.JButton();
        downSelected = new javax.swing.JButton();
        customerLogin = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        mealsPanel = new javax.swing.JPanel();
        meal1 = new javax.swing.JButton();
        meal2 = new javax.swing.JButton();
        meal3 = new javax.swing.JButton();
        meal4 = new javax.swing.JButton();
        meal5 = new javax.swing.JButton();
        entreesPanel = new javax.swing.JPanel();
        entreesPanel.setVisible(false);
        entree1 = new javax.swing.JButton();
        entree2 = new javax.swing.JButton();
        entree3 = new javax.swing.JButton();
        entree4 = new javax.swing.JButton();
        entree5 = new javax.swing.JButton();
        entree6 = new javax.swing.JButton();
        entree7 = new javax.swing.JButton();
        sidesPanel = new javax.swing.JPanel();
        side1 = new javax.swing.JButton();
        side2 = new javax.swing.JButton();
        side3 = new javax.swing.JButton();
        side4 = new javax.swing.JButton();
        beveragePanel = new javax.swing.JPanel();
        beverage1 = new javax.swing.JButton();
        beverage2 = new javax.swing.JButton();
        beverage3 = new javax.swing.JButton();
        beverage4 = new javax.swing.JButton();
        beverage5 = new javax.swing.JButton();
        dessertsPanel = new javax.swing.JPanel();
        dessert1 = new javax.swing.JButton();
        dessert2 = new javax.swing.JButton();

        scrollPane1.add(scrollbar4);
        scrollPane1.add(scrollbar3);
        scrollPane1.add(scrollbar2);
        scrollPane1.add(scrollbar1);

        mealFrame.setAlwaysOnTop(true);
        mealFrame.setMinimumSize(new java.awt.Dimension(500, 600));
        mealFrame.setResizable(false);

        currentMeal.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        currentMeal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentMeal.setText("MealLabel");

        entreeCustomize.setLabel("Customize");
        entreeCustomize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entreeCustomizeActionPerformed(evt);
            }
        });

        entreeMealLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        entreeMealLabel.setText("entreeLabel");
        entreeMealLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        customizations.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        customizations.setText("No Customizations");
        customizations.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        sideLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        sideLabel.setText("sideLabel");
        sideLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        beverageLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        beverageLabel.setText("drinkLabel");
        beverageLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        mealAvailability.setSelected(true);
        mealAvailability.setText("Available");
        mealAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mealAvailabilityActionPerformed(evt);
            }
        });

        confirmPriceMeal.setText("Change Price");
        confirmPriceMeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPriceMealActionPerformed(evt);
            }
        });

        priceEffectMeal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceEffectMeal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceEffectMeal.setText("Theoretical Profit Effect");

        priceDateMeal.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        priceDateMeal.setText("Date Range (dd-mm-yyyy)");
        priceDateMeal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                priceDateMealMouseClicked(evt);
            }
        });
        priceDateMeal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceDateMealKeyReleased(evt);
            }
        });

        priceDeltaMeal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceDeltaMeal.setText("$0.00");

        jLayeredPane2.setLayer(entreeMealLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(customizations, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(sideLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(beverageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(mealAvailability, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(mealPriceChange, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(confirmPriceMeal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(priceEffectMeal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(priceDateMeal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(priceDeltaMeal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beverageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sideLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mealAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customizations)
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(mealPriceChange, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(confirmPriceMeal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(107, 107, 107))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(priceEffectMeal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(priceDateMeal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(priceDeltaMeal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(entreeMealLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(customizations, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sideLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(beverageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mealAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmPriceMeal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mealPriceChange, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceEffectMeal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceDeltaMeal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceDateMeal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(entreeMealLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(310, Short.MAX_VALUE)))
        );

        addItems.setText("Add");
        addItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mealFrameLayout = new javax.swing.GroupLayout(mealFrame.getContentPane());
        mealFrame.getContentPane().setLayout(mealFrameLayout);
        mealFrameLayout.setHorizontalGroup(
            mealFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(currentMeal, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mealFrameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(mealFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(addItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(entreeCustomize, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addContainerGap())
        );
        mealFrameLayout.setVerticalGroup(
            mealFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mealFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentMeal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mealFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mealFrameLayout.createSequentialGroup()
                        .addComponent(entreeCustomize, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(307, 307, 307)
                        .addComponent(addItems, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        toppingFrame.setAlwaysOnTop(true);
        toppingFrame.setMinimumSize(new java.awt.Dimension(500, 600));
        toppingFrame.setResizable(false);
        toppingFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                toppingFrameWindowClosing(evt);
            }
        });

        entreeLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        entreeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        entreeLabel.setText("entreeLabel");
        entreeLabel.setToolTipText("");

        topping1Box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        topping1Box.setText("Topping1");

        topping2Box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        topping2Box.setText("Topping2");

        topping3Box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        topping3Box.setText("Topping3");

        topping4Box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        topping4Box.setText("Topping4");

        topping5Box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        topping5Box.setText("Topping5");

        jLayeredPane3.setLayer(topping1Box, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(topping2Box, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(topping3Box, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(topping4Box, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(topping5Box, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(topping1Box, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topping2Box, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topping5Box, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topping3Box, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topping4Box, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addComponent(topping1Box, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topping2Box, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topping3Box, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topping4Box, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(topping5Box, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        entreeAvailability.setSelected(true);
        entreeAvailability.setText("Available");
        entreeAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entreeAvailabilityActionPerformed(evt);
            }
        });

        confirmPriceEntree.setText("Change Price");
        confirmPriceEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPriceEntreeActionPerformed(evt);
            }
        });

        addCommitButton.setText("add/commitchange");
        addCommitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCommitButtonActionPerformed(evt);
            }
        });

        priceEffectEntree.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceEffectEntree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceEffectEntree.setText("Theoretical Profit Effect");

        priceDateEntree.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        priceDateEntree.setText("Date Range (dd-mm-yyyy)");
        priceDateEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                priceDateEntreeMouseClicked(evt);
            }
        });
        priceDateEntree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceDateEntreeKeyReleased(evt);
            }
        });

        priceDeltaEntree.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceDeltaEntree.setText("$0.00");

        javax.swing.GroupLayout toppingFrameLayout = new javax.swing.GroupLayout(toppingFrame.getContentPane());
        toppingFrame.getContentPane().setLayout(toppingFrameLayout);
        toppingFrameLayout.setHorizontalGroup(
            toppingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toppingFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(toppingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(toppingFrameLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(toppingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(toppingFrameLayout.createSequentialGroup()
                                .addComponent(priceDateEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(priceDeltaEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(toppingFrameLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(entreeAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(toppingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, toppingFrameLayout.createSequentialGroup()
                                    .addComponent(entreePriceChange, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(confirmPriceEntree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(priceEffectEntree, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addCommitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65))
                    .addGroup(toppingFrameLayout.createSequentialGroup()
                        .addComponent(entreeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        toppingFrameLayout.setVerticalGroup(
            toppingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toppingFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entreeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(toppingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(toppingFrameLayout.createSequentialGroup()
                        .addComponent(entreeAvailability)
                        .addGap(18, 18, 18)
                        .addGroup(toppingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(entreePriceChange, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmPriceEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priceEffectEntree)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(toppingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceDeltaEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceDateEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84)
                        .addComponent(addCommitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        othersFrame.setAlwaysOnTop(true);
        othersFrame.setMinimumSize(new java.awt.Dimension(400, 500));
        othersFrame.setPreferredSize(new java.awt.Dimension(400, 500));
        othersFrame.setResizable(false);

        othersLabel.setAlignment(java.awt.Label.CENTER);
        othersLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        othersLabel.setText("othersLabel");

        quantityAdd.setText("1");
        quantityAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quantityAddMouseClicked(evt);
            }
        });
        quantityAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityAddActionPerformed(evt);
            }
        });

        quantityButton.setLabel("Add");
        quantityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityButtonActionPerformed(evt);
            }
        });

        otherAvailability.setSelected(true);
        otherAvailability.setText("Available");
        otherAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherAvailabilityActionPerformed(evt);
            }
        });

        confirmPriceOthers.setText("Change Price");
        confirmPriceOthers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPriceOthersActionPerformed(evt);
            }
        });

        priceEffectOthers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceEffectOthers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceEffectOthers.setText("Theoretical Profit Effect");

        priceDateOthers.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        priceDateOthers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                priceDateOthersMouseClicked(evt);
            }
        });
        priceDateOthers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceDateOthersKeyReleased(evt);
            }
        });

        priceDeltaOthers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        priceDeltaOthers.setText("$0.00");

        javax.swing.GroupLayout othersFrameLayout = new javax.swing.GroupLayout(othersFrame.getContentPane());
        othersFrame.getContentPane().setLayout(othersFrameLayout);
        othersFrameLayout.setHorizontalGroup(
            othersFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(othersFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(othersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, othersFrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(otherAvailability)
                .addGap(157, 157, 157))
            .addGroup(othersFrameLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(othersFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(othersFrameLayout.createSequentialGroup()
                        .addComponent(priceDateOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(priceDeltaOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(othersFrameLayout.createSequentialGroup()
                        .addGroup(othersFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(priceEffectOthers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(othersFrameLayout.createSequentialGroup()
                                .addComponent(otherPriceChange, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(confirmPriceOthers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(othersFrameLayout.createSequentialGroup()
                                .addComponent(quantityAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(quantityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))))
        );
        othersFrameLayout.setVerticalGroup(
            othersFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(othersFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(othersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(othersFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(othersFrameLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(quantityAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(otherAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(othersFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otherPriceChange, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPriceOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceEffectOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(othersFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceDateOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceDeltaOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        employeeVerify.setAlwaysOnTop(true);
        employeeVerify.setMinimumSize(new java.awt.Dimension(400, 300));
        employeeVerify.setResizable(false);
        employeeVerify.setSize(new java.awt.Dimension(400, 300));

        employeePWBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeePWBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeePWBoxActionPerformed(evt);
            }
        });

        employeeVerifyLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        employeeVerifyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeeVerifyLabel.setText("Enter Employee ID");

        employeeConfirm.setText("Enter");
        employeeConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout employeeVerifyLayout = new javax.swing.GroupLayout(employeeVerify.getContentPane());
        employeeVerify.getContentPane().setLayout(employeeVerifyLayout);
        employeeVerifyLayout.setHorizontalGroup(
            employeeVerifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeeVerifyLayout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addGroup(employeeVerifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(employeeVerifyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeePWBox)
                    .addComponent(employeeConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(122, 122, 122))
        );
        employeeVerifyLayout.setVerticalGroup(
            employeeVerifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeeVerifyLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(employeeVerifyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(employeePWBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(employeeConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        trendFrame.setAlwaysOnTop(true);
        trendFrame.setMinimumSize(new java.awt.Dimension(400, 400));
        trendFrame.setPreferredSize(new java.awt.Dimension(400, 400));
        trendFrame.setResizable(false);

        currentlyTrending.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        currentlyTrending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentlyTrending.setText("Currently Trending");

        topTrendHeader.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        topTrendHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topTrendHeader.setText("Top 2 Trending");

        botTrendHeader.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botTrendHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botTrendHeader.setText("Bottom 2 Trending");

        encouragingWords.setText("(Try it out!)");

        top1Trending.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        top1Trending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        top1Trending.setText("1. ");

        top2Trending.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        top2Trending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        top2Trending.setText("2. ");

        bot1Trending.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bot1Trending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bot1Trending.setText("1. ");

        bot2Trending.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bot2Trending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bot2Trending.setText("2. ");

        trendExit.setText("Continue");
        trendExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trendExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout trendFrameLayout = new javax.swing.GroupLayout(trendFrame.getContentPane());
        trendFrame.getContentPane().setLayout(trendFrameLayout);
        trendFrameLayout.setHorizontalGroup(
            trendFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trendFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trendFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(top2Trending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bot1Trending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topTrendHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentlyTrending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trendFrameLayout.createSequentialGroup()
                        .addGap(0, 115, Short.MAX_VALUE)
                        .addComponent(botTrendHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(encouragingWords, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bot2Trending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(top1Trending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(trendFrameLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(trendExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trendFrameLayout.setVerticalGroup(
            trendFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trendFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentlyTrending, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topTrendHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top1Trending)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(top2Trending)
                .addGap(23, 23, 23)
                .addGroup(trendFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botTrendHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encouragingWords))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bot1Trending)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bot2Trending)
                .addGap(18, 18, 18)
                .addComponent(trendExit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PoS System");
        setPreferredSize(new java.awt.Dimension(1322, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(1320, 720));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        mealsButton.setLabel("Meals");
        mealsButton.setMinimumSize(new java.awt.Dimension(80, 100));
        mealsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mealsButtonActionPerformed(evt);
            }
        });

        entreesButton.setActionCommand("Entrees");
        entreesButton.setLabel("Entrees");
        entreesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entreesButtonActionPerformed(evt);
            }
        });

        sidesButton.setLabel("Sides");
        sidesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sidesButtonActionPerformed(evt);
            }
        });

        beverageButton.setLabel("Beverages");
        beverageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beverageButtonActionPerformed(evt);
            }
        });

        dessertsButton.setLabel("Desserts");
        dessertsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dessertsButtonActionPerformed(evt);
            }
        });

        employeeButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        employeeButton.setText("Employee View");
        employeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeButtonActionPerformed(evt);
            }
        });

        seeTrend.setText("See Trends");
        seeTrend.setToolTipText("");
        seeTrend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeTrendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(seeTrend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mealsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(entreesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sidesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(beverageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dessertsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mealsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entreesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beverageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dessertsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(seeTrend, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(employeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        checkoutButton.setText("Proceed to Checkout >>");
        checkoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutButtonActionPerformed(evt);
            }
        });

        enterName.setText("Enter Customer Name");
        enterName.setToolTipText("Enter Customer Name");
        enterName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterNameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterNameMouseEntered(evt);
            }
        });
        enterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterNameActionPerformed(evt);
            }
        });

        clearOrder.setText("Clear Order");
        clearOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearOrderActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(currentOrder);

        upSelected.setText("^");
        upSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upSelectedActionPerformed(evt);
            }
        });

        removeSelected.setText("Remove Selected");
        removeSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSelectedActionPerformed(evt);
            }
        });

        downSelected.setText("v");
        downSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downSelectedActionPerformed(evt);
            }
        });

        customerLogin.setText("Login");
        customerLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(upSelected)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(downSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeSelected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clearOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(checkoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(enterName, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeSelected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(upSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(downSelected, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enterName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(customerLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(checkoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        clearOrder.setVisible(false);

        mealsPanel.setMinimumSize(new java.awt.Dimension(860, 533));

        meal1.setText("<html><center>\nMeal1<br><br>\nEntree1<br>\nSide2<br>\nBeverage4 <br><br>\nPrice: $15.82, Calories 2495\n</center> \n</html>");
        meal1.setActionCommand("");
        meal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal1MouseClicked(evt);
            }
        });
        meal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meal1ActionPerformed(evt);
            }
        });

        meal2.setText("<html>\n<center>\nMeal2<br><br>\nEntree5<br>\nSide4<br>\nBeverage5<br><br>\nPrice: $16.74, Calories: 2843\n</center>\n</html>");
        meal2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal2MouseClicked(evt);
            }
        });
        meal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meal2ActionPerformed(evt);
            }
        });

        meal3.setText("<html>\n<center>\nMeal3<br><br>\nEntree6<br>\nSide2<br>\nBeverage1<br><br>\nPrice: $19.80, Calories:2134\n</center>\n</html>");
        meal3.setActionCommand("<html>\n<center>\nMeal3<br><br>\nEntree6<br>\nSide2<br>\nDrink1<br><br>\nPrice: $19.80, Calories: 2134\n</center>\n</html>");
        meal3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal3MouseClicked(evt);
            }
        });
        meal3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meal3ActionPerformed(evt);
            }
        });

        meal4.setText("<html>\n<center>\nMeal4<br><br>\nEntree1<br>\nSide2<br>\nBeverage1<br><br>\nPrice: $17.44, Calories:2501\n</center>\n</html>");
        meal4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal4MouseClicked(evt);
            }
        });
        meal4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meal4ActionPerformed(evt);
            }
        });

        meal5.setText("<html>\n<center>\nMeal5<br><br>\nEntree5<br>\nSide2<br>\nBeverage5<br><br>\nPrice: $16.59, Calories: 2599\n</html>");
        meal5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal5MouseClicked(evt);
            }
        });
        meal5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meal5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mealsPanelLayout = new javax.swing.GroupLayout(mealsPanel);
        mealsPanel.setLayout(mealsPanelLayout);
        mealsPanelLayout.setHorizontalGroup(
            mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mealsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mealsPanelLayout.createSequentialGroup()
                        .addComponent(meal1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(meal2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(meal3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(meal4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(meal5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        mealsPanelLayout.setVerticalGroup(
            mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mealsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meal4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(meal3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(meal2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(meal1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(meal5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        entreesPanel.setEnabled(false);
        entreesPanel.setMinimumSize(new java.awt.Dimension(860, 533));

        entree1.setText("<html>\n<center>\nEntree1<br><br>\nPrice: $9.39, Calories: 2030\n</center>\n</html>\n");
        entree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entree1MouseClicked(evt);
            }
        });
        entree1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entree1ActionPerformed(evt);
            }
        });

        entree2.setText("<html>\n<center>\nEntree2<br><br>\nPrice: $9.55, Calories: 2003\n</center>\n</html>\n");
        entree2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entree2MouseClicked(evt);
            }
        });
        entree2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entree2ActionPerformed(evt);
            }
        });

        entree3.setText("<html>\n<center>\nEntree3<br><br>\nPrice: $10.23, Calories: 1652\n</center>\n</html>\n");
        entree3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entree3MouseClicked(evt);
            }
        });
        entree3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entree3ActionPerformed(evt);
            }
        });

        entree4.setText("<html>\n<center>\nEntree4<br><br>\nPrice: $9.26, Calories: 2064\n</center>\n</html>\n");
        entree4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entree4MouseClicked(evt);
            }
        });
        entree4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entree4ActionPerformed(evt);
            }
        });

        entree5.setText("<html>\n<center>\nEntree5<br><br>\nPrice: $9.86, Calories: 1910\n</center>\n</html>\n");
        entree5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entree5MouseClicked(evt);
            }
        });
        entree5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entree5ActionPerformed(evt);
            }
        });

        entree6.setText("<html>\n<center>\nEntree6<br><br>\nPrice: $10.92, Calories: 1697\n</center>\n</html>\n");
        entree6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entree6MouseClicked(evt);
            }
        });
        entree6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entree6ActionPerformed(evt);
            }
        });

        entree7.setText("<html>\n<center>\nEntree7<br><br>\nPrice: $9.02, Calories: 1942\n</center>\n</html>\n");
        entree7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entree7MouseClicked(evt);
            }
        });
        entree7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entree7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout entreesPanelLayout = new javax.swing.GroupLayout(entreesPanel);
        entreesPanel.setLayout(entreesPanelLayout);
        entreesPanelLayout.setHorizontalGroup(
            entreesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entreesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entreesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entreesPanelLayout.createSequentialGroup()
                        .addComponent(entree1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entree2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entree3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(entree4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(entreesPanelLayout.createSequentialGroup()
                        .addComponent(entree5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(entree6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entree7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        entreesPanelLayout.setVerticalGroup(
            entreesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entreesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entreesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entreesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(entree4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(entree1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(entree2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entree3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(entreesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entree7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(entreesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(entree5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(entree6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sidesPanel.setEnabled(false);
        sidesPanel.setMinimumSize(new java.awt.Dimension(860, 533));

        side1.setText("<html>\n<center>\nSide1<br><br>\nPrice: $4.07, Calories: 456\n</center>\n</html>\n");
        side1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                side1MouseClicked(evt);
            }
        });
        side1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                side1ActionPerformed(evt);
            }
        });

        side2.setText("<html>\n<center>\nSide2<br><br>\nPrice: $3.07, Calories: 241\n</center>\n</html>\n");
        side2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                side2MouseClicked(evt);
            }
        });
        side2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                side2ActionPerformed(evt);
            }
        });

        side3.setText("<html>\n<center>\nSide3<br><br>\nPrice: $4.29, Calories: 222\n</center>\n</html>\n");
        side3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                side3MouseClicked(evt);
            }
        });
        side3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                side3ActionPerformed(evt);
            }
        });

        side4.setText("<html>\n<center>\nSide4<br><br>\nPrice: $3.22, Calories: 485\n</center>\n</html>\n");
        side4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                side4MouseClicked(evt);
            }
        });
        side4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                side4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidesPanelLayout = new javax.swing.GroupLayout(sidesPanel);
        sidesPanel.setLayout(sidesPanelLayout);
        sidesPanelLayout.setHorizontalGroup(
            sidesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(side1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(side2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(side3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(side4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        sidesPanelLayout.setVerticalGroup(
            sidesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(side4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(side1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(side2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(side3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(563, Short.MAX_VALUE))
        );

        beveragePanel.setEnabled(false);
        beveragePanel.setMinimumSize(new java.awt.Dimension(860, 533));

        beverage1.setText("<html>\n<center>\nBeverage1<br><br>\nPrice: $3.36, Calories: 224\n</center>\n</html>\n");
        beverage1.setActionCommand("<html>\n<center>\nDrink1<br><br>\nPrice: $3.36, Calories: 224\n</center>\n</html>\n");
        beverage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beverage1MouseClicked(evt);
            }
        });
        beverage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beverage1ActionPerformed(evt);
            }
        });

        beverage2.setText("<html>\n<center>\nBeverage2<br><br>\nPrice: $4.59, Calories: 215\n</center>\n</html>\n");
        beverage2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beverage2MouseClicked(evt);
            }
        });
        beverage2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beverage2ActionPerformed(evt);
            }
        });

        beverage3.setText("<html>\n<center>\nBeverage3<br><br>\nPrice: $2.16, Calories: 437\n</center>\n</html>\n");
        beverage3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beverage3MouseClicked(evt);
            }
        });
        beverage3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beverage3ActionPerformed(evt);
            }
        });

        beverage4.setText("<html>\n<center>\nBeverage4<br><br>\nPrice $4.98, Calories: 230\n</center>\n</html>\n");
        beverage4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beverage4MouseClicked(evt);
            }
        });
        beverage4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beverage4ActionPerformed(evt);
            }
        });

        beverage5.setText("<html>\n<center>\nBeverage5<br><br>\nPrice: $3.66, Calories: 448\n</center>\n</html>\n");
        beverage5.setActionCommand("<html>\n<center>\nDrink5<br><br>\nPrice: $3.66, Calories: 448\n</center>\n</html>\n");
        beverage5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beverage5MouseClicked(evt);
            }
        });
        beverage5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beverage5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout beveragePanelLayout = new javax.swing.GroupLayout(beveragePanel);
        beveragePanel.setLayout(beveragePanelLayout);
        beveragePanelLayout.setHorizontalGroup(
            beveragePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beveragePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(beveragePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(beveragePanelLayout.createSequentialGroup()
                        .addComponent(beverage1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(beverage2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(beverage3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(beverage4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(beverage5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        beveragePanelLayout.setVerticalGroup(
            beveragePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beveragePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(beveragePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beverage4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(beveragePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(beverage1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(beverage2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(beverage3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(beverage5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );

        dessertsPanel.setEnabled(false);
        dessertsPanel.setMinimumSize(new java.awt.Dimension(860, 533));

        dessert1.setText("<html>\n<center>\nDessert1<br><br>\nPrice: $4.58, Calories: 461\n</center>\n</html>\n");
        dessert1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dessert1MouseClicked(evt);
            }
        });
        dessert1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dessert1ActionPerformed(evt);
            }
        });

        dessert2.setText("<html>\n<center>\nDessert2<br><br>\nPrice: $2.41, Calories: 374\n</center>\n</html>\n");
        dessert2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dessert2MouseClicked(evt);
            }
        });
        dessert2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dessert2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dessertsPanelLayout = new javax.swing.GroupLayout(dessertsPanel);
        dessertsPanel.setLayout(dessertsPanelLayout);
        dessertsPanelLayout.setHorizontalGroup(
            dessertsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dessertsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dessert1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dessert2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(450, Short.MAX_VALUE))
        );
        dessertsPanelLayout.setVerticalGroup(
            dessertsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dessertsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dessertsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dessert2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dessert1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(563, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(mealsPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(entreesPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(sidesPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(beveragePanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(dessertsPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entreesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mealsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sidesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(beveragePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dessertsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entreesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mealsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sidesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(beveragePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dessertsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        sidesPanel.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meal2ActionPerformed
        mealFrame.setVisible(true);
        toppingFrame.setVisible(false);
        mealAvailability.setSelected(true);
        currentMeal.setText("Meal2");
        entreeMealLabel.setText("Entree5");
        sideLabel.setText("Side4");
        beverageLabel.setText("Beverage5");
        customizations.setText("No Customizations");
        mealPriceChange.setText("0.00");
        priceDateMeal.setText("Date Range (dd-mm-yyyy)");
    }//GEN-LAST:event_meal2ActionPerformed

    private void meal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meal3ActionPerformed
        mealFrame.setVisible(true);
        toppingFrame.setVisible(false);
        mealAvailability.setSelected(true);
        currentMeal.setText("Meal3");
        entreeMealLabel.setText("Entree6");
        sideLabel.setText("Side3");
        beverageLabel.setText("Beverage2");
        customizations.setText("No Customizations");
        mealPriceChange.setText("0.00");
        priceDateMeal.setText("Date Range (dd-mm-yyyy)");
    }//GEN-LAST:event_meal3ActionPerformed

    private void meal4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meal4ActionPerformed
        mealFrame.setVisible(true);
        toppingFrame.setVisible(false);
        mealAvailability.setSelected(true);
        currentMeal.setText("Meal4");
        entreeMealLabel.setText("Entree1");
        sideLabel.setText("Side2");
        beverageLabel.setText("Beverage1");
        customizations.setText("No Customizations");
        mealPriceChange.setText("0.00");
        priceDateMeal.setText("Date Range (dd-mm-yyyy)");
    }//GEN-LAST:event_meal4ActionPerformed

    private void meal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meal1ActionPerformed
        mealFrame.setVisible(true);
        toppingFrame.setVisible(false);
        mealAvailability.setSelected(true);
        currentMeal.setText("Meal1");
        entreeMealLabel.setText("Entree1");
        sideLabel.setText("Side2");
        beverageLabel.setText("Beverage4");
        customizations.setText("No Customizations");
        mealPriceChange.setText("0.00");
        priceDateMeal.setText("Date Range (dd-mm-yyyy)");
    }//GEN-LAST:event_meal1ActionPerformed

    private void mealsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mealsButtonActionPerformed
        mealsPanel.setVisible(true);
        entreesPanel.setVisible(false);
        sidesPanel.setVisible(false);
        beveragePanel.setVisible(false);
        dessertsPanel.setVisible(false);
        toppingFrame.setVisible(false);
        othersFrame.setVisible(false);
        trendFrame.setVisible(false);
    }//GEN-LAST:event_mealsButtonActionPerformed

    private void meal5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meal5ActionPerformed
        mealFrame.setVisible(true);
        toppingFrame.setVisible(false);
        mealAvailability.setSelected(true);
        currentMeal.setText("Meal5");
        entreeMealLabel.setText("Entree5");
        sideLabel.setText("Side2");
        beverageLabel.setText("Beverage5");
        customizations.setText("No Customizations");
        mealPriceChange.setText("0.00");
        priceDateMeal.setText("Date Range (dd-mm-yyyy)");
    }//GEN-LAST:event_meal5ActionPerformed

    private void checkoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Order in process!");
        //TODO: Readd checkout stuff.
        customerName = "";
    }//GEN-LAST:event_checkoutButtonActionPerformed

    public void updateFoodButtons()
    {
        //TODO either change things here or have your own function to call on
        //but iirc Christopher has a function like this and it's probably cleaner
        //to put it there instead of here
        meal1.setText("<html><center>\n" +
                        "Meal1<br><br>\n" +
                        "Entree1<br>\n" +
                        "Side2<br>\n" +
                        "Beverage4 <br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center> \n" +
                        "</html>");
        meal2.setText("<html>\n" +
                        "<center>\n" +
                        "Meal2<br><br>\n" +
                        "Entree5<br>\n" +
                        "Side4<br>\n" +
                        "Beverage5<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>");
        meal3.setText("<html>\n" +
                        "<center>\n" +
                        "Meal3<br><br>\n" +
                        "Entree6<br>\n" +
                        "Side2<br>\n" +
                        "Beverage1<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>");
        meal4.setText("<html>\n" +
                        "<center>\n" +
                        "Meal4<br><br>\n" +
                        "Entree1<br>\n" +
                        "Side2<br>\n" +
                        "Beverage1<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>");
        meal5.setText("<html>\n" +
                        "<center>\n" +
                        "Meal5<br><br>\n" +
                        "Entree5<br>\n" +
                        "Side2<br>\n" +
                        "Beverage5<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</html>");
        entree1.setText("<html>\n" +
                        "<center>\n" +
                        "Entree1<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        entree2.setText("<html>\n" +
                        "<center>\n" +
                        "Entree2<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        entree3.setText("<html>\n" +
                        "<center>\n" +
                        "Entree3<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        entree4.setText("<html>\n" +
                        "<center>\n" +
                        "Entree4<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        entree5.setText("<html>\n" +
                        "<center>\n" +
                        "Entree5<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        entree6.setText("<html>\n" +
                        "<center>\n" +
                        "Entree6<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        entree7.setText("<html>\n" +
                        "<center>\n" +
                        "Entree7<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        side1.setText("<html>\n" +
                        "<center>\n" +
                        "Side1<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        side2.setText("<html>\n" +
                        "<center>\n" +
                        "Side2<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        side3.setText("<html>\n" +
                        "<center>\n" +
                        "Side3<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        side4.setText("<html>\n" +
                        "<center>\n" +
                        "Side4<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        beverage1.setText("<html>\n" +
                        "<center>\n" +
                        "Beverage1<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        beverage2.setText("<html>\n" +
                        "<center>\n" +
                        "Beverage2<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        beverage3.setText("<html>\n" +
                        "<center>\n" +
                        "Beverage3<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        beverage4.setText("<html>\n" +
                        "<center>\n" +
                        "Beverage4<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        beverage5.setText("<html>\n" +
                        "<center>\n" +
                        "Beverage5<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        dessert1.setText("<html>\n" +
                        "<center>\n" +
                        "Dessert1<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
        dessert2.setText("<html>\n" +
                        "<center>\n" +
                        "Dessert2<br><br>\n" +
                        "Price: $" + "stuff" + ",  Calories " + "other stuff" + "\n" +
                        "</center>\n" +
                        "</html>\n" +
                        "");
    }
    
    
    private void entreesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entreesButtonActionPerformed
        //JOptionPane.showMessageDialog(this, "Hi");
        mealsPanel.setVisible(false);
        entreesPanel.setVisible(true);
        sidesPanel.setVisible(false);
        beveragePanel.setVisible(false);
        dessertsPanel.setVisible(false);
        mealFrame.setVisible(false);
        toppingFrame.setVisible(false);
        othersFrame.setVisible(false);
        trendFrame.setVisible(false);
    }//GEN-LAST:event_entreesButtonActionPerformed

    private void entree1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entree1ActionPerformed
        toppingFrame.setVisible(true);
        entreeLabel.setText("Adding Entree1");
        entreePriceChange.setText("0.00");
        priceDateEntree.setText("Date Range (dd-mm-yyyy)");
        entreeAvailability.setSelected(true);
        addCommitButton.setText("Add");
        if(employeeView)
        {
            setAvailabilityOn();
        }
        entree1Select();
    }//GEN-LAST:event_entree1ActionPerformed

    private void entree2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entree2ActionPerformed
        toppingFrame.setVisible(true);
        entreeLabel.setText("Adding Entree2");
        entreePriceChange.setText("0.00");
        priceDateEntree.setText("Date Range (dd-mm-yyyy)");
        entreeAvailability.setSelected(true);
        addCommitButton.setText("Add");
        if(employeeView)
        {
            setAvailabilityOn();
        }
        entree2Select();
    }//GEN-LAST:event_entree2ActionPerformed

    private void entree3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entree3ActionPerformed
        toppingFrame.setVisible(true);
        entreeLabel.setText("Adding Entree3");
        entreePriceChange.setText("0.00");
        priceDateEntree.setText("Date Range (dd-mm-yyyy)");
        entreeAvailability.setSelected(true);
        addCommitButton.setText("Add");
        if(employeeView)
        {
            setAvailabilityOn();
        }
        entree3Select();
    }//GEN-LAST:event_entree3ActionPerformed

    private void entree4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entree4ActionPerformed
        toppingFrame.setVisible(true);
        entreeAvailability.setSelected(true);
        entreeLabel.setText("Adding Entree4");
        entreePriceChange.setText("0.00");
        priceDateEntree.setText("Date Range (dd-mm-yyyy)");
        addCommitButton.setText("Add");
        if(employeeView)
        {
            setAvailabilityOn();
        }
        entree4Select();
    }//GEN-LAST:event_entree4ActionPerformed

    private void entree5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entree5ActionPerformed
        toppingFrame.setVisible(true);
        entreeAvailability.setSelected(true);
        entreeLabel.setText("Adding Entree5");
        entreePriceChange.setText("0.00");
        priceDateEntree.setText("Date Range (dd-mm-yyyy)");
        addCommitButton.setText("Add");
        if(employeeView)
        {
            setAvailabilityOn();
        }
        entree5Select();
    }//GEN-LAST:event_entree5ActionPerformed

    private void entree6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entree6ActionPerformed
        toppingFrame.setVisible(true);
        entreeAvailability.setSelected(true);
        entreeLabel.setText("Adding Entree6");
        entreePriceChange.setText("0.00");
        priceDateEntree.setText("Date Range (dd-mm-yyyy)");
        addCommitButton.setText("Add");
        if(employeeView)
        {
            setAvailabilityOn();
        }
        entree6Select();
    }//GEN-LAST:event_entree6ActionPerformed

    private void entree7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entree7ActionPerformed
        toppingFrame.setVisible(true);
        entreeAvailability.setSelected(true);
        entreeLabel.setText("Adding Entree7");
        entreePriceChange.setText("0.00");
        priceDateEntree.setText("Date Range (dd-mm-yyyy)");
        addCommitButton.setText("Add");
        if(employeeView)
        {
            setAvailabilityOn();
        }
        entree7Select();
    }//GEN-LAST:event_entree7ActionPerformed

    private void side1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_side1ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Side1");
    }//GEN-LAST:event_side1ActionPerformed

    private void side2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_side2ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Side2");
    }//GEN-LAST:event_side2ActionPerformed

    private void side3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_side3ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Side3");
    }//GEN-LAST:event_side3ActionPerformed

    private void side4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_side4ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Side4");
    }//GEN-LAST:event_side4ActionPerformed

    private void beverage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beverage1ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Beverage1");
    }//GEN-LAST:event_beverage1ActionPerformed

    private void beverage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beverage2ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Beverage2");
    }//GEN-LAST:event_beverage2ActionPerformed

    private void beverage3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beverage3ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Beverage3");
    }//GEN-LAST:event_beverage3ActionPerformed

    private void beverage4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beverage4ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Beverage4");
    }//GEN-LAST:event_beverage4ActionPerformed

    private void beverage5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beverage5ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Beverage5");
    }//GEN-LAST:event_beverage5ActionPerformed

    private void dessert1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dessert1ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Dessert1");
    }//GEN-LAST:event_dessert1ActionPerformed

    private void dessert2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dessert2ActionPerformed
        othersOpen();
        otherAvailability.setSelected(true);
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
        othersLabel.setText("Adding Dessert2");
    }//GEN-LAST:event_dessert2ActionPerformed

    private void entreeCustomizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entreeCustomizeActionPerformed
        toppingFrame.setVisible(true);
        entreeLabel.setText("Customizing " + entreeMealLabel.getText());
        switch(entreeMealLabel.getText())
        {
            case "Entree1":
                entree1Select();
                break;
            case "Entree2":
                entree2Select();
                break;
            case "Entree3":
                entree3Select();
                break;
            case "Entree4":
                entree4Select();
                break;
            case "Entree5":
                entree5Select();
                break;
            case "Entree6":
                entree6Select();
                break;   
            case "Entree7":
                entree7Select();
                break;
        }
        setAvailabilityOff();
        addCommitButton.setText("Save Changes");
    }//GEN-LAST:event_entreeCustomizeActionPerformed

    private void addItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemsActionPerformed
        if(unavailableItems.contains(currentMeal.getText()))
        {
            JOptionPane.showMessageDialog(this, "Cannot add unavailable item.");
            return;
        }
        if(customizations.getText().equals("No Customizations"))
            customizations.setText("");
        currentItems.add(currentMeal.getText() + "," + entreeMealLabel.getText() + 
                " " + customizations.getText().replaceAll(", ", " ").replaceAll("<html>|</html>", "") + "," + sideLabel.getText() + ","
                + beverageLabel.getText());
        abbreviatedItems.add(currentMeal.getText().charAt(0) + "" + currentMeal.getText().charAt(4) + "," + entreeMealLabel.getText().charAt(0) + entreeMealLabel.getText().charAt(6) + 
                "" + customizations.getText().replaceAll(", ", "").replaceAll("<html>|</html>", "").replaceAll("noTopping","-T").replaceAll("addTopping","+T") + "," + sideLabel.getText().charAt(0) + sideLabel.getText().charAt(4) + ","
                + beverageLabel.getText().charAt(0) + beverageLabel.getText().charAt(8));
        update();
    }//GEN-LAST:event_addItemsActionPerformed

    private void enterNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterNameActionPerformed
    }//GEN-LAST:event_enterNameActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    }//GEN-LAST:event_formMouseClicked

    private void enterNameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterNameMouseEntered
    }//GEN-LAST:event_enterNameMouseEntered

    private void enterNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterNameMouseClicked
        enterName.setText("");
    }//GEN-LAST:event_enterNameMouseClicked

    private void entree1Select()
    {
        topping1Box.setSelected(true);
        topping2Box.setSelected(true);
        topping3Box.setSelected(false);
        topping4Box.setSelected(false);
        topping5Box.setSelected(true);
    }
    
    private void addCommitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCommitButtonActionPerformed
        if(addCommitButton.getText().equals("Add"))
        {   
            String customText = "";
            String currentEntree = entreeLabel.getText().split(" ")[1];
            if(unavailableItems.contains(currentEntree))
            {
                JOptionPane.showMessageDialog(this, "Cannot add unavailable item.");
                return;
            }
            if(currentEntree.equals("Entree1"))
            {//<editor-fold defaultstate="collapsed" desc="Topping Stuff">              
                if(!topping1Box.isSelected())
                    customText += "noTopping1 ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2 ";
                if(topping3Box.isSelected())
                    customText += "addTopping3 ";
                if(topping4Box.isSelected())
                    customText += "addTopping4 ";
                if(!topping5Box.isSelected())
                    customText += "noTopping5";
            }
            else if(currentEntree.equals("Entree2"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1 ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2 ";
                if(!topping3Box.isSelected())
                    customText += "noTopping3 ";
                if(topping4Box.isSelected())
                    customText += "addTopping4 ";
                if(topping5Box.isSelected())
                    customText += "addTopping5";
            }
            else if(currentEntree.equals("Entree3"))
            {
                if(topping1Box.isSelected())
                    customText += "addTopping1 ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2 ";
                if(topping3Box.isSelected())
                    customText += "addTopping3 ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4 ";
                if(!topping5Box.isSelected())
                    customText += "noTopping5";
            }
            else if(currentEntree.equals("Entree4"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1 ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2 ";
                if(topping3Box.isSelected())
                    customText += "addTopping3 ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4 ";
                if(topping5Box.isSelected())
                    customText += "addTopping5";
            }
            else if(currentEntree.equals("Entree5"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1 ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2 ";
                if(topping3Box.isSelected())
                    customText += "addTopping3 ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4 ";
                if(topping5Box.isSelected())
                    customText += "addTopping5";
            }
            else if(currentEntree.equals("Entree6"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1 ";
                if(topping2Box.isSelected())
                    customText += "addTopping2 ";
                if(topping3Box.isSelected())
                    customText += "addTopping3 ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4 ";
                if(!topping5Box.isSelected())
                    customText += "noTopping5";
            }
            else if(currentEntree.equals("Entree7"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1 ";
                if(topping2Box.isSelected())
                    customText += "Topping2 ";
                if(!topping3Box.isSelected())
                    customText += "noTopping3 ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4 ";
                if(topping5Box.isSelected())
                    customText += "addTopping5";
            }//</editor-fold>
            currentItems.add(currentEntree + " " + customText);
            abbreviatedItems.add(currentEntree.charAt(0) + "" + currentEntree.charAt(6) + 
                "" + customText.replaceAll(" ", "").replaceAll("<html>|</html>", "").replaceAll("noTopping","-T").replaceAll("addTopping","+T"));
            update();
        }
        else if(addCommitButton.getText().equals("Save Changes"))
        {
            String customText = "<html>";
            String currentEntree = entreeLabel.getText().split(" ")[1];
            if(currentEntree.equals("Entree1"))
            {//<editor-fold defaultstate="collapsed" desc="Topping Stuff">             
                if(!topping1Box.isSelected())
                    customText += "noTopping1, ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2, ";
                if(topping3Box.isSelected())
                    customText += "addTopping3, ";
                if(topping4Box.isSelected())
                    customText += "addTopping4, ";
                if(!topping5Box.isSelected())
                    customText += "noTopping5";
            }
            else if(currentEntree.equals("Entree2"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1, ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2, ";
                if(!topping3Box.isSelected())
                    customText += "noTopping3, ";
                if(topping4Box.isSelected())
                    customText += "addTopping4, ";
                if(topping5Box.isSelected())
                    customText += "addTopping5";
            }
            else if(currentEntree.equals("Entree3"))
            {
                if(topping1Box.isSelected())
                    customText += "addTopping1, ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2, ";
                if(topping3Box.isSelected())
                    customText += "addTopping3, ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4, ";
                if(!topping5Box.isSelected())
                    customText += "noTopping5";
            }
            else if(currentEntree.equals("Entree4"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1, ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2, ";
                if(topping3Box.isSelected())
                    customText += "addTopping3, ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4, ";
                if(topping5Box.isSelected())
                    customText += "addTopping5";
            }
            else if(currentEntree.equals("Entree5"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1, ";
                if(!topping2Box.isSelected())
                    customText += "noTopping2, ";
                if(topping3Box.isSelected())
                    customText += "addTopping3, ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4, ";
                if(topping5Box.isSelected())
                    customText += "addTopping5";
            }
            else if(currentEntree.equals("Entree6"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1, ";
                if(topping2Box.isSelected())
                    customText += "addTopping2, ";
                if(topping3Box.isSelected())
                    customText += "addTopping3, ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4, ";
                if(!topping5Box.isSelected())
                    customText += "noTopping5";
            }
            else if(currentEntree.equals("Entree7"))
            {
                if(!topping1Box.isSelected())
                    customText += "noTopping1, ";
                if(topping2Box.isSelected())
                    customText += "Topping2, ";
                if(!topping3Box.isSelected())
                    customText += "noTopping3, ";
                if(!topping4Box.isSelected())
                    customText += "noTopping4, ";
                if(topping5Box.isSelected())
                    customText += "addTopping5";
            }
            customizations.setText(customText+"</html>");//</editor-fold>
            toppingFrame.setVisible(false);
        }

    }//GEN-LAST:event_addCommitButtonActionPerformed

    private void entree2Select()
    {
        topping1Box.setSelected(true);
        topping2Box.setSelected(true);
        topping3Box.setSelected(true);
        topping4Box.setSelected(false);
        topping5Box.setSelected(false);
    }  
    private void entree3Select()
    {
        topping1Box.setSelected(false);
        topping2Box.setSelected(true);
        topping3Box.setSelected(false);
        topping4Box.setSelected(true);
        topping5Box.setSelected(true);
    }    
    private void entree4Select()
    {
        topping1Box.setSelected(true);
        topping2Box.setSelected(true);
        topping3Box.setSelected(false);
        topping4Box.setSelected(true);
        topping5Box.setSelected(false);
    }
    private void entree5Select()
    {
        topping1Box.setSelected(true);
        topping2Box.setSelected(true);
        topping3Box.setSelected(false);
        topping4Box.setSelected(true);
        topping5Box.setSelected(false);
    }
    private void entree6Select()
    {
        topping1Box.setSelected(true);
        topping2Box.setSelected(false);
        topping3Box.setSelected(false);
        topping4Box.setSelected(true);
        topping5Box.setSelected(true);
    }
    private void entree7Select()
    {
        topping1Box.setSelected(true);
        topping2Box.setSelected(false);
        topping3Box.setSelected(true);
        topping4Box.setSelected(true);
        topping5Box.setSelected(false);
    }
    
    private void quantityAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityAddActionPerformed
    }//GEN-LAST:event_quantityAddActionPerformed

    private void quantityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityButtonActionPerformed
        if(unavailableItems.contains(othersLabel.getText().split(" ")[1]))
        {
            JOptionPane.showMessageDialog(this, "Cannot add unavailable item.");
            return;
        }
        if(quantityAdd.getText().matches("[+1234567890]"))
        {
            for(int i = 0; i < Integer.parseInt(quantityAdd.getText()); i++)
            {
                currentItems.add(othersLabel.getText().split(" ")[1]);
                String item = othersLabel.getText().split(" ")[1];
                abbreviatedItems.add(item.charAt(0) + "" + item.charAt(item.length()-1));
            }
        }
        update();
    }//GEN-LAST:event_quantityButtonActionPerformed

    private void quantityAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quantityAddMouseClicked
        quantityAdd.setText("");
    }//GEN-LAST:event_quantityAddMouseClicked

    private void othersOpen()
    {
        othersFrame.setVisible(true);
        toppingFrame.setVisible(false);
        mealFrame.setVisible(false);
        trendFrame.setVisible(false);
        quantityAdd.setText("1");
        otherPriceChange.setText("0.00");
        priceDateOthers.setText("Date Range (dd-mm-yyyy)");
    }
    
    private void employeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeButtonActionPerformed
        if(employeeView)
        {
            employeeView = false;
            employeeButton.setText("Employee View");
            JOptionPane.showMessageDialog(this, "Logged off Employee View.");
            clearOrder.setVisible(false);
            setAvailabilityOff();
            //hide employee only stuff
        }
        else
        {
            employeeVerify.setVisible(true);
        }
    }//GEN-LAST:event_employeeButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void employeePWBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeePWBoxActionPerformed

    }//GEN-LAST:event_employeePWBoxActionPerformed

    private void employeeConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeConfirmActionPerformed
        if(String.valueOf(employeePWBox.getPassword()).equals("pw")) //TODO: Change this to match any employee ID in database
        {
            employeeView = true;
            JOptionPane.showMessageDialog(this, "Logged in as " + "employeeName" + "."); //TODO: Fetch name using ID
            employeeButton.setText("Customer View");
            clearOrder.setVisible(true);
            employeeVerify.setVisible(false);
            setAvailabilityOn();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
            employeeVerify.setVisible(false);
        }
        employeePWBox.setText("");
    }//GEN-LAST:event_employeeConfirmActionPerformed

    private void setAvailabilityOn()
    {
        entreeAvailability.setVisible(true);
        mealAvailability.setVisible(true);
        otherAvailability.setVisible(true);
        mealPriceChange.setVisible(true);
        otherPriceChange.setVisible(true);
        entreePriceChange.setVisible(true);
        confirmPriceEntree.setVisible(true);
        confirmPriceMeal.setVisible(true);
        confirmPriceOthers.setVisible(true);

        priceDateEntree.setVisible(true);
        priceDateMeal.setVisible(true);
        priceDateOthers.setVisible(true);
        priceDeltaEntree.setVisible(true);
        priceDeltaMeal.setVisible(true);
        priceDeltaOthers.setVisible(true);
        priceEffectEntree.setVisible(true);
        priceEffectMeal.setVisible(true);
        priceEffectOthers.setVisible(true);
    }
    private void setAvailabilityOff()
    {
        entreeAvailability.setVisible(false);
        mealAvailability.setVisible(false);
        otherAvailability.setVisible(false);
        mealPriceChange.setVisible(false);
        otherPriceChange.setVisible(false);
        entreePriceChange.setVisible(false);
        confirmPriceEntree.setVisible(false);
        confirmPriceMeal.setVisible(false);
        confirmPriceOthers.setVisible(false);

        priceDateEntree.setVisible(false);
        priceDateMeal.setVisible(false);
        priceDateOthers.setVisible(false);
        priceDeltaEntree.setVisible(false);
        priceDeltaMeal.setVisible(false);
        priceDeltaOthers.setVisible(false);
        priceEffectEntree.setVisible(false);
        priceEffectMeal.setVisible(false);
        priceEffectOthers.setVisible(false);
    }
    
    private void upSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upSelectedActionPerformed
        if(selectIndex>0)
        {
            selectIndex--;
            update();
        }
    }//GEN-LAST:event_upSelectedActionPerformed

    private void clearOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearOrderActionPerformed
        currentItems.clear();
        abbreviatedItems.clear();
        update();
    }//GEN-LAST:event_clearOrderActionPerformed

    private void mealAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mealAvailabilityActionPerformed
        if(!mealAvailability.isSelected())
        {
            if(!unavailableItems.contains(currentMeal.getText()))
            {
                unavailableItems.add(currentMeal.getText());
            }
        }
        else
        {
            if(unavailableItems.contains(currentMeal.getText()))
            {
                unavailableItems.remove(unavailableItems.indexOf(currentMeal.getText()));
            }
        }
        updateAvailability();
    }//GEN-LAST:event_mealAvailabilityActionPerformed

    private void customerLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerLoginActionPerformed
        //some stuff about going through the db to verify name
        if(enterName.getText().equals("person")) //TODO check if the name is in the db 
        {
            customerName = enterName.getText();
            JOptionPane.showMessageDialog(this, "Logged in as: " + customerName 
                    + "\n\nWelcome back, " + customerName +
                    "! How about trying " + "recommendationHere" + " today?"); //TODO add recommendation(s) here
        }
        else
        {
            customerName = enterName.getText();
            //add them to db here
            JOptionPane.showMessageDialog(this, "Logged in as: " + customerName 
                    + "\n\nWelcome, " + customerName +
                    "! Don't know what to get? How about a " + "recommendationHere" + "?"); //TODO add recommendation(s) here
                    //recommendation can probably be the #1 trending, top1Trending.getText();
        }
    }//GEN-LAST:event_customerLoginActionPerformed

    private void removeSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSelectedActionPerformed
        currentItems.remove(selectIndex);
        abbreviatedItems.remove(selectIndex);
        if(selectIndex>0)
        {
            selectIndex--;
        }
        update();
    }//GEN-LAST:event_removeSelectedActionPerformed

    private void downSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downSelectedActionPerformed
        if(selectIndex<currentItems.size()-1)
        {
            selectIndex++;
            update();
        }
    }//GEN-LAST:event_downSelectedActionPerformed

    private void sidesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sidesButtonActionPerformed
        mealsPanel.setVisible(false);
        entreesPanel.setVisible(false);
        sidesPanel.setVisible(true);
        beveragePanel.setVisible(false);
        dessertsPanel.setVisible(false);
        mealFrame.setVisible(false);
        toppingFrame.setVisible(false);
        othersFrame.setVisible(false);
    }//GEN-LAST:event_sidesButtonActionPerformed

    private void beverageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beverageButtonActionPerformed
        mealsPanel.setVisible(false);
        entreesPanel.setVisible(false);
        sidesPanel.setVisible(false);
        beveragePanel.setVisible(true);
        dessertsPanel.setVisible(false);
        mealFrame.setVisible(false);
        toppingFrame.setVisible(false);
        othersFrame.setVisible(false);
    }//GEN-LAST:event_beverageButtonActionPerformed

    private void dessertsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dessertsButtonActionPerformed
        mealsPanel.setVisible(false);
        entreesPanel.setVisible(false);
        sidesPanel.setVisible(false);
        beveragePanel.setVisible(false);
        dessertsPanel.setVisible(true);
        mealFrame.setVisible(false);
        toppingFrame.setVisible(false);
        othersFrame.setVisible(false);
    }//GEN-LAST:event_dessertsButtonActionPerformed

    private void entreeAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entreeAvailabilityActionPerformed
        String entree = entreeLabel.getText().split(" ")[1];
        if(!entreeAvailability.isSelected())
        {
            if(!unavailableItems.contains(entree))
            {
                unavailableItems.add(entree);
            }
        }
        else
        {
            if(unavailableItems.contains(entree))
            {
                unavailableItems.remove(unavailableItems.indexOf(entree));
            }
        }
        updateAvailability();
    }//GEN-LAST:event_entreeAvailabilityActionPerformed

    private void toppingFrameWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_toppingFrameWindowClosing
        if(employeeView)
        {
            setAvailabilityOn();
        }
    }//GEN-LAST:event_toppingFrameWindowClosing

    private void otherAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherAvailabilityActionPerformed
        String others = othersLabel.getText().split(" ")[1];
        if(!otherAvailability.isSelected())
        {
            if(!unavailableItems.contains(others))
            {
                unavailableItems.add(others);
            }
        }
        else
        {
            if(unavailableItems.contains(others))
            {
                unavailableItems.remove(unavailableItems.indexOf(others));
            }
        }
        updateAvailability();
    }//GEN-LAST:event_otherAvailabilityActionPerformed

    private void entree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entree1MouseClicked
        if(employeeView && entree1.isEnabled()==false)
        {
            toppingFrame.setVisible(true);
            entreeLabel.setText("Adding Entree1");
            entreePriceChange.setText("0.00");
            priceDateEntree.setText("Date Range (dd-mm-yyyy)");
            setAvailabilityOn();
            entree1Select();
        }
    }//GEN-LAST:event_entree1MouseClicked

    private void entree2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entree2MouseClicked
        if(employeeView && entree2.isEnabled()==false)
        {
            toppingFrame.setVisible(true);
            entreeLabel.setText("Adding Entree2");
            entreePriceChange.setText("0.00");
            priceDateEntree.setText("Date Range (dd-mm-yyyy)");
            setAvailabilityOn();
            entree2Select();
        }
    }//GEN-LAST:event_entree2MouseClicked

    private void entree3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entree3MouseClicked
        if(employeeView && entree3.isEnabled()==false)
        {
            toppingFrame.setVisible(true);
            entreeLabel.setText("Adding Entree3");
            entreePriceChange.setText("0.00");
            priceDateEntree.setText("Date Range (dd-mm-yyyy)");
            setAvailabilityOn();
            entree3Select();
        }
    }//GEN-LAST:event_entree3MouseClicked

    private void entree4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entree4MouseClicked
        if(employeeView && entree4.isEnabled()==false)
        {
            toppingFrame.setVisible(true);
            entreeLabel.setText("Adding Entree4");
            entreePriceChange.setText("0.00");
            priceDateEntree.setText("Date Range (dd-mm-yyyy)");
            setAvailabilityOn();
            entree4Select();
        }
    }//GEN-LAST:event_entree4MouseClicked

    private void entree5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entree5MouseClicked
        if(employeeView && entree5.isEnabled()==false)
        {
            toppingFrame.setVisible(true);
            entreeLabel.setText("Adding Entree5");
            entreePriceChange.setText("0.00");
            priceDateEntree.setText("Date Range (dd-mm-yyyy)");
            setAvailabilityOn();
            entree5Select();
        }
    }//GEN-LAST:event_entree5MouseClicked

    private void entree6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entree6MouseClicked
        if(employeeView && entree6.isEnabled()==false)
        {
            toppingFrame.setVisible(true);
            entreeLabel.setText("Adding Entree6");
            entreePriceChange.setText("0.00");
            priceDateEntree.setText("Date Range (dd-mm-yyyy)");
            setAvailabilityOn();
            entree6Select();
        }
    }//GEN-LAST:event_entree6MouseClicked

    private void entree7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entree7MouseClicked
        if(employeeView && entree7.isEnabled()==false)
        {
            toppingFrame.setVisible(true);
            entreeLabel.setText("Adding Entree7");
            entreePriceChange.setText("0.00");
            priceDateEntree.setText("Date Range (dd-mm-yyyy)");
            setAvailabilityOn();
            entree7Select();
        }
    }//GEN-LAST:event_entree7MouseClicked

    private void meal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal1MouseClicked
        if(employeeView && meal1.isEnabled()==false)
        {
            mealFrame.setVisible(true);
            toppingFrame.setVisible(false);
            mealAvailability.setSelected(false);
            currentMeal.setText("Meal1");
            entreeMealLabel.setText("Entree1");
            sideLabel.setText("Side2");
            beverageLabel.setText("Beverage1");
            customizations.setText("No Customizations");
            mealPriceChange.setText("0.00");
            priceDateMeal.setText("Date Range (dd-mm-yyyy)");
        }
    }//GEN-LAST:event_meal1MouseClicked

    private void meal2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal2MouseClicked
        if(employeeView && meal2.isEnabled()==false)
        {
            mealFrame.setVisible(true);
            toppingFrame.setVisible(false);
            mealAvailability.setSelected(false);
            currentMeal.setText("Meal2");
            entreeMealLabel.setText("Entree5");
            sideLabel.setText("Side4");
            beverageLabel.setText("Beverage5");
            customizations.setText("No Customizations");
            mealPriceChange.setText("0.00");
            priceDateMeal.setText("Date Range (dd-mm-yyyy)");
        }
    }//GEN-LAST:event_meal2MouseClicked

    private void meal3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal3MouseClicked
        if(employeeView && meal3.isEnabled()==false)
        {
            mealFrame.setVisible(true);
            toppingFrame.setVisible(false);
            mealAvailability.setSelected(false);
            currentMeal.setText("Meal3");
            entreeMealLabel.setText("Entree6");
            sideLabel.setText("Side3");
            beverageLabel.setText("Beverage5");
            customizations.setText("No Customizations");
            mealPriceChange.setText("0.00");
            priceDateMeal.setText("Date Range (dd-mm-yyyy)");
        }
    }//GEN-LAST:event_meal3MouseClicked

    private void meal4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal4MouseClicked
        if(employeeView && meal4.isEnabled()==false)
        {
            mealFrame.setVisible(true);
            toppingFrame.setVisible(false);
            mealAvailability.setSelected(false);
            currentMeal.setText("Meal4");
            entreeMealLabel.setText("Entree1");
            sideLabel.setText("Side2");
            beverageLabel.setText("Beverage1");
            customizations.setText("No Customizations");
            mealPriceChange.setText("0.00");
            priceDateMeal.setText("Date Range (dd-mm-yyyy)");
        }
    }//GEN-LAST:event_meal4MouseClicked

    private void meal5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal5MouseClicked
        if(employeeView && meal5.isEnabled()==false)
        {
            mealFrame.setVisible(true);
            toppingFrame.setVisible(false);
            mealAvailability.setSelected(false);
            currentMeal.setText("Meal5");
            entreeMealLabel.setText("Entree5");
            sideLabel.setText("Side2");
            beverageLabel.setText("Beverage5");
            customizations.setText("No Customizations");
            mealPriceChange.setText("0.00");
            priceDateMeal.setText("Date Range (dd-mm-yyyy)");
        }
    }//GEN-LAST:event_meal5MouseClicked

    private void side1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_side1MouseClicked
        if(employeeView && side1.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Side1");
        }
    }//GEN-LAST:event_side1MouseClicked

    private void side2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_side2MouseClicked
        if(employeeView && side2.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            othersLabel.setText("Adding Side2");
        }
    }//GEN-LAST:event_side2MouseClicked

    private void side3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_side3MouseClicked
        if(employeeView && side3.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Side3");
        }
    }//GEN-LAST:event_side3MouseClicked

    private void side4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_side4MouseClicked
        if(employeeView && side4.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Side4");
        }
    }//GEN-LAST:event_side4MouseClicked

    private void beverage1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beverage1MouseClicked
        if(employeeView && beverage1.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Beverage1");
        }
    }//GEN-LAST:event_beverage1MouseClicked

    private void beverage2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beverage2MouseClicked
        if(employeeView && beverage2.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Beverage2");
        }
    }//GEN-LAST:event_beverage2MouseClicked

    private void beverage3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beverage3MouseClicked
        if(employeeView && beverage3.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Beverage3");
        }
    }//GEN-LAST:event_beverage3MouseClicked

    private void beverage4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beverage4MouseClicked
        if(employeeView && beverage4.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Beverage4");
        }
    }//GEN-LAST:event_beverage4MouseClicked

    private void beverage5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beverage5MouseClicked
        if(employeeView && beverage5.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Beverage5");
        }
    }//GEN-LAST:event_beverage5MouseClicked

    private void dessert1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dessert1MouseClicked
        if(employeeView && dessert1.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Dessert1");
        }
    }//GEN-LAST:event_dessert1MouseClicked

    private void dessert2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dessert2MouseClicked
        if(employeeView && dessert2.isEnabled()==false)
        {
            othersOpen();
            otherAvailability.setSelected(false);
            otherPriceChange.setText("0.00");
            priceDateOthers.setText("Date Range (dd-mm-yyyy)");
            othersLabel.setText("Adding Dessert2");
        }
    }//GEN-LAST:event_dessert2MouseClicked

    private void priceDateMealMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_priceDateMealMouseClicked
        if(priceDateMeal.getText().equals("Date Range (dd-mm-yyyy)"))
            priceDateMeal.setText("");
    }//GEN-LAST:event_priceDateMealMouseClicked

    private void priceDateOthersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_priceDateOthersMouseClicked
        if(priceDateOthers.getText().equals("Date Range (dd-mm-yyyy)"))
            priceDateOthers.setText("");
    }//GEN-LAST:event_priceDateOthersMouseClicked

    private void priceDateEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_priceDateEntreeMouseClicked
        if(priceDateEntree.getText().equals("Date Range (dd-mm-yyyy)"))
            priceDateEntree.setText("");
    }//GEN-LAST:event_priceDateEntreeMouseClicked

    private void priceDateMealKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceDateMealKeyReleased
        if(priceDateMeal.getText().matches("(\\d{2}-\\d{2}-\\d{4})"))
        {
            //TODO: do things
            priceDateMeal.setForeground(Color.BLACK);
            //string will look like ex. "03-13-2021"
            //consider the fact that months have differing amount of days
        }
        else
        {
            priceDateMeal.setForeground(Color.RED);
        }
    }//GEN-LAST:event_priceDateMealKeyReleased

    private void priceDateEntreeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceDateEntreeKeyReleased
        if(priceDateEntree.getText().matches("(\\d{2}-\\d{2}-\\d{4})"))
        {
            //TODO: do things
            priceDateEntree.setForeground(Color.BLACK);
            //string will look like ex. "03-13-2021"
            //consider the fact that months have differing amount of days
        }
        else
        {
            priceDateEntree.setForeground(Color.RED);
        }
    }//GEN-LAST:event_priceDateEntreeKeyReleased

    private void priceDateOthersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceDateOthersKeyReleased
        if(priceDateOthers.getText().matches("(\\d{2}-\\d{2}-\\d{4})"))
        {
            //TODO: do things
            priceDateOthers.setForeground(Color.BLACK);
            //string will look like ex. "03-13-2021"
            //consider the fact that months have differing amount of days
        }
        else
        {
            priceDateOthers.setForeground(Color.RED);
        }
    }//GEN-LAST:event_priceDateOthersKeyReleased

    private void confirmPriceOthersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPriceOthersActionPerformed
        // TODO add your handling code here:
        if(otherPriceChange.getText().matches("(\\d?.\\d{2})"))
        {
            //TODO stuff
            //string will look like ex. "1.23"
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please use format x.xx");
        }
    }//GEN-LAST:event_confirmPriceOthersActionPerformed

    private void confirmPriceEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPriceEntreeActionPerformed
        if(entreePriceChange.getText().matches("(\\d?.\\d{2})"))
        {
            //TODO stuff
            //string will look like ex. "1.23"
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please use format x.xx");
        }
    }//GEN-LAST:event_confirmPriceEntreeActionPerformed

    private void confirmPriceMealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPriceMealActionPerformed
        if(mealPriceChange.getText().matches("(\\d?.\\d{2})")) 
        {
            //TODO stuff
            //string will look like ex. "1.23"
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please use format x.xx");
        }
    }//GEN-LAST:event_confirmPriceMealActionPerformed

    private void trendExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trendExitActionPerformed
        trendFrame.setVisible(false);
    }//GEN-LAST:event_trendExitActionPerformed

    private void seeTrendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeTrendActionPerformed
        trendFrame.setVisible(true);
        //TODO: Set trendy stuff here
        //top1Trending.setText("1. " + );
        //top2Trending.setText("2. " + );
        //bot1Trending.setText("1. " + );
        //bot2Trending.setText("2. " + );
    }//GEN-LAST:event_seeTrendActionPerformed

    

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PoSGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PoSGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PoSGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PoSGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PoSGUI().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCommitButton;
    private javax.swing.JToggleButton addItems;
    private javax.swing.JButton beverage1;
    private javax.swing.JButton beverage2;
    private javax.swing.JButton beverage3;
    private javax.swing.JButton beverage4;
    private javax.swing.JButton beverage5;
    private java.awt.Button beverageButton;
    private javax.swing.JLabel beverageLabel;
    private javax.swing.JPanel beveragePanel;
    private javax.swing.JLabel bot1Trending;
    private javax.swing.JLabel bot2Trending;
    private javax.swing.JLabel botTrendHeader;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton checkoutButton;
    private javax.swing.JButton clearOrder;
    private javax.swing.JButton confirmPriceEntree;
    private javax.swing.JButton confirmPriceMeal;
    private javax.swing.JButton confirmPriceOthers;
    private javax.swing.JLabel currentMeal;
    private javax.swing.JEditorPane currentOrder;
    private javax.swing.JLabel currentlyTrending;
    private javax.swing.JButton customerLogin;
    private javax.swing.JLabel customizations;
    private javax.swing.JButton dessert1;
    private javax.swing.JButton dessert2;
    private java.awt.Button dessertsButton;
    private javax.swing.JPanel dessertsPanel;
    private javax.swing.JButton downSelected;
    private javax.swing.JButton employeeButton;
    private javax.swing.JButton employeeConfirm;
    private javax.swing.JPasswordField employeePWBox;
    private javax.swing.JFrame employeeVerify;
    private javax.swing.JLabel employeeVerifyLabel;
    private javax.swing.JLabel encouragingWords;
    private javax.swing.JTextField enterName;
    private javax.swing.JButton entree1;
    private javax.swing.JButton entree2;
    private javax.swing.JButton entree3;
    private javax.swing.JButton entree4;
    private javax.swing.JButton entree5;
    private javax.swing.JButton entree6;
    private javax.swing.JButton entree7;
    private javax.swing.JCheckBox entreeAvailability;
    private java.awt.Button entreeCustomize;
    private javax.swing.JLabel entreeLabel;
    private javax.swing.JLabel entreeMealLabel;
    private javax.swing.JTextField entreePriceChange;
    private java.awt.Button entreesButton;
    private javax.swing.JPanel entreesPanel;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton meal1;
    private javax.swing.JButton meal2;
    private javax.swing.JButton meal3;
    private javax.swing.JButton meal4;
    private javax.swing.JButton meal5;
    private javax.swing.JCheckBox mealAvailability;
    private javax.swing.JFrame mealFrame;
    private javax.swing.JTextField mealPriceChange;
    private java.awt.Button mealsButton;
    private javax.swing.JPanel mealsPanel;
    private javax.swing.JCheckBox otherAvailability;
    private javax.swing.JTextField otherPriceChange;
    private javax.swing.JFrame othersFrame;
    private java.awt.Label othersLabel;
    private javax.swing.JTextField priceDateEntree;
    private javax.swing.JTextField priceDateMeal;
    private javax.swing.JTextField priceDateOthers;
    private javax.swing.JLabel priceDeltaEntree;
    private javax.swing.JLabel priceDeltaMeal;
    private javax.swing.JLabel priceDeltaOthers;
    private javax.swing.JLabel priceEffectEntree;
    private javax.swing.JLabel priceEffectMeal;
    private javax.swing.JLabel priceEffectOthers;
    private java.awt.TextField quantityAdd;
    private java.awt.Button quantityButton;
    private javax.swing.JButton removeSelected;
    private java.awt.ScrollPane scrollPane1;
    private java.awt.Scrollbar scrollbar1;
    private java.awt.Scrollbar scrollbar2;
    private java.awt.Scrollbar scrollbar3;
    private java.awt.Scrollbar scrollbar4;
    private javax.swing.JButton seeTrend;
    private javax.swing.JButton side1;
    private javax.swing.JButton side2;
    private javax.swing.JButton side3;
    private javax.swing.JButton side4;
    private javax.swing.JLabel sideLabel;
    private java.awt.Button sidesButton;
    private javax.swing.JPanel sidesPanel;
    private java.awt.TextArea textArea1;
    private javax.swing.JLabel top1Trending;
    private javax.swing.JLabel top2Trending;
    private javax.swing.JLabel topTrendHeader;
    private javax.swing.JCheckBox topping1Box;
    private javax.swing.JCheckBox topping2Box;
    private javax.swing.JCheckBox topping3Box;
    private javax.swing.JCheckBox topping4Box;
    private javax.swing.JCheckBox topping5Box;
    private javax.swing.JFrame toppingFrame;
    private javax.swing.JButton trendExit;
    private javax.swing.JFrame trendFrame;
    private javax.swing.JButton upSelected;
    // End of variables declaration//GEN-END:variables
}
