package com.example.application.views.list;

import com.example.application.data.entity.Auto;
import com.example.application.data.repository.AutoRepository;
import com.example.application.data.service.AutoService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Route(value = "AddPictureView", layout = MainLayout.class)
@PermitAll
public class AddPictuerView extends VerticalLayout {
    private Upload upload;
    private Auto auto;
    private VerticalLayout imageContainer;
    private ComboBox<Auto> autoComboBox;

    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    private AutoService autoService;

    public AddPictuerView() {
        add(new H1("Dodaj zdjęcie"));

        autoComboBox = new ComboBox<>();
        autoComboBox.addValueChangeListener(event-> {
            Auto selectedUser = event.getValue();
            if ( selectedUser != null){
                auto = selectedUser;
                showImage();
            }
        });
        add(autoComboBox);

        initImageContainer();
    }

    @PostConstruct
    private void init() {
        List<Auto> auto = autoRepository.findAll();

        autoComboBox.setItems(auto);
        autoComboBox.setItemLabelGenerator(Auto::getModel);

        if ( !auto.isEmpty()){
            autoComboBox.setValue(auto.get(0));
        }
        initUploaderImage();
    }

    private void initUploaderImage() {
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/jpeg","image/jpg", "image/png", "image/gif");

        upload.addSucceededListener(event -> {
            String attachmentName = event.getFileName();
            try {
                // The image can be jpg png or gif, but we store it always as png file in this example
                BufferedImage inputImage = ImageIO.read(buffer.getInputStream(attachmentName));
                ByteArrayOutputStream pngContent = new ByteArrayOutputStream();
                ImageIO.write(inputImage, "png", pngContent);
                saveProfilePicture(pngContent.toByteArray());
                showImage();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        add(upload);
    }

    private void saveProfilePicture(byte[] imageBytes) {
        auto.setZdjecie(imageBytes);
        auto = autoRepository.save(auto);
    }

    private void showImage() {
        Image image = autoService.generateImage(auto);
        image.setHeight("100%");
        imageContainer.removeAll();
        imageContainer.add(image);
    }

    private void initImageContainer(){
        imageContainer = new VerticalLayout();
        imageContainer.setWidth("600px");
        imageContainer.setHeight("600px");
        imageContainer.getStyle().set("overflow-x", "auto");
        add(imageContainer);
    }
}
