/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.b.IDCARD;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Controller
public class controller {
 @RequestMapping("/sendData")
    //@ResponseBody
    public String getData(@RequestParam("mytext") String getText,
                          @RequestParam("myimage") MultipartFile image,
                          @RequestParam("tanggal")@DateTimeFormat(pattern="yyyy-MM-dd") Date myDate,
                          @RequestParam("alamat") String getAlamat,
                          @RequestParam("jeniskel") String getJk,
                          Model model)
            throws IOException{
        
        SimpleDateFormat tanggal = new SimpleDateFormat("EE, dd-MMMM-yyyy");
        
         String newTanggal = tanggal.format(myDate);
        
        String blob = Base64.encodeBase64String(image.getBytes());
        String link = "data:image/png;base64,".concat(blob);
        
        
        model.addAttribute("kirimanNama","Nama :" + getText);
        model.addAttribute("kirimanTglahir","Tanggal Lahir :" + newTanggal);
        model.addAttribute("kirimanJk","Jenis Kelamin :" + getJk);
        model.addAttribute("kirimanAlamat","Alamat :" + getAlamat);
        model.addAttribute("kirimanGambar", link);
        
        return "main";
                
   
    
    
    }
}
