package com.example.vize.Controllers;


import com.example.vize.Entities.ProductImage;
import com.example.vize.Services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ImageController {

    final ImageService service;

    Long pid = 0l;
    List<ProductImage> ls = new ArrayList<>();

    @GetMapping("/images/{pid}")
    public String images(@PathVariable Long pid, Model model){
        this.pid = pid;
        ls = service.list(this.pid);
        model.addAttribute("images",ls);
        return "images";
    }

    @PostMapping("/imageAdd")
    public String imageAdd(@RequestParam("image") MultipartFile file) throws IOException, SQLException {
        ProductImage productImage = new ProductImage();
        productImage.setPid(this.pid);
        byte[] fileBytes = file.getBytes();
        Blob blob = new SerialBlob(fileBytes);
        productImage.setImage(blob);
        service.addImage(productImage);
        return "redirect:/images/"+this.pid;
    }

    @ResponseBody
    @GetMapping(value = "/getImage/{pid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long pid) throws SQLException {
        Blob blob = null;
        for(ProductImage image : ls){
            if(image.getPid() == pid){
                blob = image.getImage();
            }
        }
        int blobLength = (int) blob.length();
        byte[] image = blob.getBytes(1, blobLength);
        return image;
    }

    @GetMapping("/imageDelete/{pid}")
    public String imageDelete(@PathVariable Long pid){
        service.delete(pid);
        return "redirect:/dashboard";
    }

}
