package yekebagci.batu.TestDugun;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import  com.vaadin.flow.component.textfield.TextField;
import yekebagci.batu.model.Guests;
import yekebagci.batu.model.Salon;
import yekebagci.batu.model.SalonDetay;

import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends HorizontalLayout {

    VerticalLayout tableLayout,guestLayout,toolBar;
    Checkbox chckGuest;
    Button btnGuest,btnCreateHall;
    Grid<Guests> guestsGrid;

    Salon salon;

    List<SalonDetay> salonDetayList = new ArrayList<>();

    public MainView()
    {
        chckGuest = new Checkbox("Misafir Listesini Göster");
        chckGuest.setValue(true);

        chckGuest.addValueChangeListener(e->{
           guestLayout.setVisible(chckGuest.getValue());
        });

        btnCreateHall = new Button("Salon Yarat");
        btnCreateHall.addClickListener(this::hallClickEvent);

        toolBar = new VerticalLayout(btnCreateHall,chckGuest);
        toolBar.setWidth("100%");

        btnGuest = new Button("Yeni Konuk Ekle");
        btnGuest.addClickListener(this::guestClickEvent);

        tableLayout = new VerticalLayout();
        tableLayout.setSizeFull();
        guestLayout = new VerticalLayout();

        setSizeFull();

        setAlignItems(Alignment.AUTO);
        add(toolBar,tableLayout,guestLayout);
        buildGrid();
        guestLayout.add(btnGuest,guestsGrid);

        tableLayout.setAlignItems(Alignment.AUTO);
    }

    private void hallClickEvent(ClickEvent clickEvent)
    {
        Dialog dialog = new Dialog();

        Label lbl = new Label("Lütfen salonu en ve boy olarak kaç parçaya bölmek istediğinizi seçiniz!.");

        salon = new Salon();


        List<Integer> integerList = new ArrayList<>();
        for (int i=1 ;i<12 ; i++)
            integerList.add(i);

        ComboBox<Integer> cmbEn = new ComboBox<>("Yatayda kaç parça olmalı");
        cmbEn.setItems(integerList);

        ComboBox<Integer> cmbBoy = new ComboBox<>("Dikeyde kaç parça olmalı");
        cmbBoy.setItems(integerList);

        Button btn = new Button("Onayla");
        btn.addClickListener(e->{

            if (cmbBoy.getValue() == null || cmbEn.getValue() == null)
            {
                Notification.show("En boy olmadan salon oluşturmamı beklemiyorsun değil mi");
                return;
            }
            int vCounttt = cmbEn.getValue();
            int hCounttt = cmbBoy.getValue();

            salon.setBoy(hCounttt);
            salon.setEn(vCounttt);

            while (hCounttt>0) {
                tableLayout.add(createHorizantolLayout(hCounttt,vCounttt));
                hCounttt--;
                System.out.println("hCount " + hCounttt);
            }

           dialog.close();
        });


        dialog.add(lbl,cmbEn,cmbBoy,btn);
        dialog.open();
    }

    private void guestClickEvent(ClickEvent clickEvent)
    {
        Dialog dialog = new Dialog();

        TextField txtName = new TextField("Misafir Adı");
        TextField txtSurName = new TextField("Misafir Soyadı");
        TextField txtAge = new TextField("Misafir Yaşı");

        Button  button = new Button("Ekle");
        button.addClickListener(e->{

            if (txtAge.getValue() == null || txtName.getValue() == null || txtSurName.getValue() == null)
            {
                Notification.show("Eksik Bilgi Girdiniz. Lütfen '*' alanları doldurunuz.");
                return;
            }

            ListDataProvider<Guests> guestsListDataProvider = (ListDataProvider<Guests> )guestsGrid.getDataProvider();
            guestsListDataProvider.getItems().add(new Guests(txtName.getValue(),txtSurName.getValue(),Integer.valueOf(txtAge.getValue())));
            guestsListDataProvider.refreshAll();
            dialog.close();
        });


        dialog.add(txtName,txtSurName,txtAge,button);

        dialog.open();
    }


    private void buildGrid()
    {
        guestsGrid = new Grid<Guests>();

        guestsGrid.addColumn(Guests::getName).setHeader("Adı");
        guestsGrid.addColumn(Guests::getSurName).setHeader("SoyAdı");
        guestsGrid.addColumn(Guests::getAge).setHeader("Yaşı");
        guestsGrid.setHeightByRows(true);


        List<Guests> guestsList = new ArrayList<>();

        guestsList.add(new Guests("batu","yekebağcı",25));
        guestsList.add(new Guests("umut","yekebağcı",19));
        guestsList.add(new Guests("merve","canlı",23));

        guestsGrid.setItems(guestsList);
    }


    private HorizontalLayout createHorizantolLayout(int hCount,int vCount)
    {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();

        for (int i = 0; i<vCount; i++)
        {
            horizontalLayout.add(createVerticalLayout(hCount,vCount));
        }
        return horizontalLayout;
    }

    private VerticalLayout createVerticalLayout(int hCount,int vCount)
    {
        //Button button = new Button("Masa Ekle");
        //button.setIcon(VaadinIcon.PLUS.create());

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

       // verticalLayout.add(button);
        verticalLayout.getStyle().set("border","3px dashed gray");
        verticalLayout.getStyle().set("border-radius","5px");
        //verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        //verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setAlignItems(Alignment.STRETCH);

        SalonDetay salonDetay = new SalonDetay();

        salonDetay.setX(vCount);
        salonDetay.setY(hCount);

        Button btnDefine = new Button("Buranın ne olduğunu belirle");

        btnDefine.addClickListener(e->{
            Dialog dialog = new Dialog();

            Button btnMasa = new Button("Masa");
            btnMasa.addClickListener(ez-> {
                    Dialog dialog2 = new Dialog();
                    dialog2.add(new Label("Hoşgeldiniz"));

                    List<Integer> integerList = new ArrayList<>();
                    for (int i = 1; i < 12; i++)
                        integerList.add(i);

                    ComboBox<Integer> comboBox = new ComboBox<>("Katılacak Kişi Sayısını Seçiniz");
                    comboBox.setItems(integerList);

                    dialog2.add(comboBox);

                    Button ekleButton = new Button("Tamamla");

                    ekleButton.addClickListener(ex -> {
                        if (comboBox.getValue() == null) {
                            Notification.show("Lütfen kişi sayısını seçiniz", 1000, Notification.Position.TOP_STRETCH);
                            return;
                        }
                        List<Component> componentList = beSmart(comboBox.getValue());
                        componentList.forEach(ele -> verticalLayout.add(ele));
                        dialog2.close();
                        dialog.close();
                        verticalLayout.remove(btnDefine);
                    });
                    dialog2.add(ekleButton);
                    dialog2.open();
            });

            Button btnKoridor = new Button("Koridor");
            btnKoridor.addClickListener(ex->{
                verticalLayout.add(placeHolder("Koridor",dialog));
                verticalLayout.remove(btnDefine);
            });

            Button btnDugun = new Button("Düğün Masası");
            btnDugun.addClickListener(ex->{
                verticalLayout.add(placeHolder("Düğün Masası",dialog));
                verticalLayout.remove(btnDefine);
            });

            Button btnSahne = new Button("Sahne");
            btnSahne.addClickListener(ex->{
                verticalLayout.add(placeHolder("Sahne",dialog));
                verticalLayout.remove(btnDefine);
            });

            dialog.add(btnMasa,btnKoridor,btnSahne,btnDugun);
            dialog.open();
        });

        verticalLayout.add(btnDefine);
        /*
        button.addClickListener(e->{

            verticalLayout.remove(button);

            Dialog dialog = new Dialog();
            dialog.add(new Label("Hoşgeldiniz"));

            List<Integer> integerList = new ArrayList<>();
            for (int i=1 ;i<12 ; i++)
                integerList.add(i);

            ComboBox<Integer> comboBox = new ComboBox<>("Katılacak Kişi Sayısını Seçiniz");
            comboBox.setItems(integerList);

            dialog.add(comboBox);

            Button ekleButton = new Button("Tamamla");

            ekleButton.addClickListener(ex->{
                if (comboBox.getValue() == null)
                {
                    Notification.show("Lütfen kişi sayısını seçiniz",1000, Notification.Position.TOP_STRETCH);
                    return;
                }

               List<Component> componentList = beSmart(comboBox.getValue());

                componentList.forEach(ele-> verticalLayout.add(ele));

                dialog.close();
            });


            dialog.add(ekleButton);

            dialog.open();
            //verticalLayout.setAlignItems(Alignment.STRETCH);
            //verticalLayout.add(createPersonButton("1"),createPersonButton("2"),createPersonButton("3"));

        }); */

        return verticalLayout;
    }

    private HorizontalLayout placeHolder(String text,Dialog dialog)
    {
        Label lbl = new Label(text);
        HorizontalLayout horizontalLayout = new HorizontalLayout(lbl,lbl,lbl);
        horizontalLayout.setWidth("100%");
        dialog.close();
        return horizontalLayout;
    }

    private Button createPersonButton(String text)
    {
        Button button = new Button(text);
                button.setSizeFull();
                button.setIcon(VaadinIcon.USER.create());

                return button;
    }

    private VerticalLayout createKoridorYatay()
    {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        verticalLayout.add(new Label("KORİDOR"));
        verticalLayout.getStyle().set("border","3px dashed gray");
        verticalLayout.getStyle().set("border-radius","5px");

        return verticalLayout;
    }


    private  List<Component> beSmart(Integer count)
    {
        List<Component> componentList = new ArrayList<>();

        int firstLoop = count/2 ;
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("100%");
        for (int i= 0 ;  i<firstLoop; i++ )
        {
            horizontalLayout.add(createPersonButton(String.valueOf(i+1)));
        }

        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.setWidth("100%");

        for (int i= firstLoop ;  i<count; i++ )
        {
            horizontalLayout2.add(createPersonButton(String.valueOf(i+1)));
        }

        componentList.add(horizontalLayout);
        componentList.add(horizontalLayout2);

        return componentList;
    }

}
