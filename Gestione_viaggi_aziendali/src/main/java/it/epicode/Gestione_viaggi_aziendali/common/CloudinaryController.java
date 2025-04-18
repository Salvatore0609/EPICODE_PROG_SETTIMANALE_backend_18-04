package it.epicode.Gestione_viaggi_aziendali.common;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class CloudinaryController {
    private final Cloudinary cloudinary;
    private final EmailService emailService;

    @PostMapping(path = "/uploadme", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(
            @RequestPart("file")
            MultipartFile file) {

        try {
            Map result = cloudinary.uploader()
                    .upload(file.getBytes(), Cloudinary.asMap(
                            "folder", "GestViaggiProfileImages",
                            "public_id", file.getOriginalFilename()
                    ));

            String url = result.get("secure_url").toString();

            emailService.sendEmail("salvatoredesole1998@gmail.com",
                    "salvataggio immagine",
                    "<h3>Immagine caricata con successo</h3> <p>l'immagine ha il seguente url: <img src=' " + url + " ' /> </p>"
            );

            System.out.println(url);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}